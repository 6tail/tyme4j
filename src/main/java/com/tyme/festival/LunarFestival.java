package com.tyme.festival;

import com.tyme.enums.EventType;
import com.tyme.enums.FestivalType;
import com.tyme.event.Event;
import com.tyme.event.EventManager;
import com.tyme.lunar.LunarDay;
import com.tyme.solar.SolarDay;
import com.tyme.solar.SolarTerm;

/**
 * 农历传统节日（依据国家标准《农历的编算和颁行》GB/T 33661-2017）
 *
 * @author 6tail
 */
public class LunarFestival extends AbstractFestival {

  public static final String[] NAMES = {"春节", "元宵节", "龙头节", "上巳节", "清明节", "端午节", "七夕节", "中元节", "中秋节", "重阳节", "冬至节", "腊八节", "除夕"};

  /**
   * 数据
   *
   * @see EventManager#DATA
   */
  public static String DATA = "2VV__0002Vj__0002WW__0002XX__0003b___0002ZZ__0002bb__0002bj__0002cj__0002dd__0003s___0002gc__0002hV_U000";

  public LunarFestival(FestivalType type, int index, Event event, SolarDay day) {
    super(type, index, event, day);
  }

  public static LunarFestival fromIndex(int year, int index) {
    if (index < 0 || index >= NAMES.length) {
      return null;
    }
    int start = index * 8;
    Event e = new Event(NAMES[index], "@" + DATA.substring(start, start + 8));
    SolarDay d = e.getSolarDay(year);
    if (null == d) {
      return null;
    }
    return new LunarFestival(e.getType() == EventType.TERM_DAY ? FestivalType.TERM : FestivalType.DAY, index, e, d);
  }

  public static LunarFestival fromYmd(int year, int month, int day) {
    for (int i = 0, j = NAMES.length; i < j; i++) {
      LunarFestival f = fromIndex(year, i);
      if (null != f) {
        LunarDay d = f.getDay();
        if (null != d && d.getYear() == year && d.getMonth() == month && d.getDay() == day) {
          return f;
        }
      }
    }
    return null;
  }

  public LunarFestival next(int n) {
    int size = NAMES.length;
    int i = index + n;
    return fromIndex((day.getYear() * size + i) / size, indexOf(i, size));
  }

  /**
   * 农历日
   *
   * @return 农历日
   */
  public LunarDay getDay() {
    return getSolarDay().getLunarDay();
  }

  /**
   * 节气，非节气返回null
   *
   * @return 节气
   */
  public SolarTerm getSolarTerm() {
    return getSolarDay().getTerm();
  }

  @Override
  public String toString() {
    return String.format("%s %s", getDay(), getName());
  }
}
