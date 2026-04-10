package com.tyme.event;

import com.tyme.AbstractCulture;
import com.tyme.enums.EventType;
import com.tyme.lunar.LunarDay;
import com.tyme.lunar.LunarMonth;
import com.tyme.solar.SolarDay;
import com.tyme.solar.SolarMonth;
import com.tyme.solar.SolarTerm;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 事件
 *
 * @author 6tail
 */
public class Event extends AbstractCulture {
  /**
   * 名称
   */
  protected String name;

  /**
   * 数据
   */
  protected String data;

  public static void validate(String data) {
    if (null == data) {
      throw new IllegalArgumentException("illegal event data: null");
    }
    if (data.length() != 9) {
      throw new IllegalArgumentException("illegal event data: " + data);
    }
  }

  public Event(String name, String data) {
    validate(data);
    this.name = name;
    this.data = data;
  }

  /**
   * 构造器
   *
   * @return 构造器
   */
  public static EventBuilder builder() {
    return new EventBuilder();
  }

  public static Event fromName(String name) {
    Matcher matcher = Pattern.compile(String.format(EventManager.REGEX, name)).matcher(EventManager.DATA);
    return matcher.find() ? new Event(name, matcher.group(1)) : null;
  }

  protected int getCharIndex(int index) {
    return EventManager.CHARS.indexOf(data.charAt(index));
  }

  public int getValue(int index) {
    return getCharIndex(index) - 31;
  }

  public int[] getMonth(int year) {
    int y = year;
    int m = getValue(2);
    if (m > 12) {
      m = 1;
      y += 1;
    }
    return new int[] {y, m};
  }

  /**
   * 事件类型
   *
   * @return 事件类型
   */
  public EventType getType() {
    return EventType.fromCode(getCharIndex(1));
  }

  /**
   * 名称
   *
   * @return 名称
   */
  public String getName() {
    return name;
  }

  /**
   * 数据
   *
   * @return 数据
   */
  public String getData() {
    return data;
  }

  /**
   * 起始年
   *
   * @return 年
   */
  public int getStartYear() {
    int n = 0;
    int size = EventManager.CHARS.length();
    for (int i = 0; i < 3; i++) {
      n = n * size + getCharIndex(6 + i);
    }
    return n;
  }

  /**
   * 指定公历日的事件列表
   *
   * @param d 公历日
   * @return 事件列表
   */
  public static List<Event> fromSolarDay(SolarDay d) {
    List<Event> l = new ArrayList<>();
    for (Event e : all()) {
      if (d.equals(e.getSolarDay(d.getYear()))) {
        l.add(e);
      }
    }
    return l;
  }

  /**
   * 所有事件
   *
   * @return 事件列表
   */
  public static List<Event> all() {
    List<Event> l = new ArrayList<>();
    Matcher matcher = Pattern.compile(String.format(EventManager.REGEX, ".[^@]+")).matcher(EventManager.DATA);
    while (matcher.find()) {
      l.add(new Event(matcher.group(2), matcher.group(1)));
    }
    return l;
  }

  /**
   * 公历日
   *
   * @param year 年
   * @return 公历日，如果当年没有该事件，返回null
   */
  public SolarDay getSolarDay(int year) {
    EventType type = getType();
    if (null == type) {
      return null;
    }
    if (year < getStartYear()) {
      return null;
    }
    SolarDay d = null;
    switch (type) {
      case SOLAR_DAY:
        d = getSolarDayBySolarDay(year);
        break;
      case SOLAR_WEEK:
        d = getSolarDayByWeek(year);
        break;
      case LUNAR_DAY:
        d = getSolarDayByLunarDay(year);
        break;
      case TERM_DAY:
        d = getSolarDayByTerm(year);
        break;
      case TERM_HS:
        d = getSolarDayByTermHeavenStem(year);
        break;
      case TERM_EB:
        d = getSolarDayByTermEarthBranch(year);
        break;
    }
    if (null == d) {
      return null;
    }
    int offset = getValue(5);
    return 0 == offset ? d : d.next(offset);
  }

  protected SolarDay getSolarDayBySolarDay(int year) {
    int[] month = getMonth(year);
    int y = month[0];
    int m = month[1];
    int d = getValue(3);
    int delay = getValue(4);
    int lastDay = SolarMonth.fromYm(y, m).getDayCount();
    if (d > lastDay) {
      if (0 == delay) {
        return null;
      }
      return delay < 0 ? SolarDay.fromYmd(y, m, d + delay) : SolarDay.fromYmd(y, m, lastDay).next(delay);
    }
    return SolarDay.fromYmd(y, m, d);
  }

  protected SolarDay getSolarDayByLunarDay(int year) {
    int[] month = getMonth(year);
    int y = month[0];
    int m = month[1];
    int d = getValue(3);
    int delay = getValue(4);
    int lastDay = LunarMonth.fromYm(y, m).getDayCount();
    if (d > lastDay) {
      if (0 == delay) {
        return null;
      }
      return delay < 0 ? LunarDay.fromYmd(y, m, d + delay).getSolarDay() : LunarDay.fromYmd(y, m, lastDay).getSolarDay().next(delay);
    }
    return LunarDay.fromYmd(y, m, d).getSolarDay();
  }

  protected SolarDay getSolarDayByWeek(int year) {
    // 第几个星期
    int n = getValue(3);
    if (n == 0) {
      return null;
    }
    SolarMonth m = SolarMonth.fromYm(year, getValue(2));
    // 星期几
    int w = getValue(4);
    if (n > 0) {
      // 当月第1天
      SolarDay d = m.getFirstDay();
      // 往后找第几个星期几
      return d.next(d.getWeek().stepsTo(w) + 7 * n - 7);
    }
    // 当月最后一天
    SolarDay d = SolarDay.fromYmd(year, m.getMonth(), m.getDayCount());
    // 往前找第几个星期几
    return d.next(d.getWeek().stepsBackTo(w) + 7 * n + 7);
  }

  protected SolarDay getSolarDayByTerm(int year) {
    SolarDay d = SolarTerm.fromIndex(year, getValue(2)).getSolarDay();
    int offset = getValue(4);
    return 0 == offset ? d : d.next(offset);
  }

  protected SolarDay getSolarDayByTermHeavenStem(int year) {
    SolarDay d = getSolarDayByTerm(year);
    return d.next(d.getLunarDay().getSixtyCycle().getHeavenStem().stepsTo(getValue(3)));
  }

  protected SolarDay getSolarDayByTermEarthBranch(int year) {
    SolarDay d = getSolarDayByTerm(year);
    return d.next(d.getLunarDay().getSixtyCycle().getEarthBranch().stepsTo(getValue(3)));
  }
}
