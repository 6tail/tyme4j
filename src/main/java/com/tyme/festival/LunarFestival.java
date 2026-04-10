package com.tyme.festival;

import com.tyme.enums.FestivalType;
import com.tyme.event.Event;
import com.tyme.event.EventManager;
import com.tyme.lunar.LunarDay;
import com.tyme.solar.SolarTerm;
import com.tyme.solar.SolarTermDay;

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

  public LunarFestival(FestivalType type, int index, Event event, LunarDay day) {
    super(type, index, event, day);
  }

  public static LunarFestival fromIndex(int year, int index) {
    if (index < 0 || index >= NAMES.length) {
      return null;
    }
    int start = index * 8;
    Event e = new Event(NAMES[index], "@" + DATA.substring(start, start + 8));
    switch (e.getType()) {
      case LUNAR_DAY:
        int[] m = e.getMonth(year);
        LunarDay d = LunarDay.fromYmd(m[0], m[1], e.getValue(3));
        int offset = e.getValue(5);
        return new LunarFestival(FestivalType.DAY, index, e, 0 == offset ? d : d.next(offset));
      case TERM_DAY:
        return new LunarFestival(FestivalType.TERM, index, e, SolarTerm.fromIndex(year, e.getValue(2)).getSolarDay().getLunarDay());
      default:
        return null;
    }
  }

  public static LunarFestival fromYmd(int year, int month, int day) {
    LunarDay d = LunarDay.fromYmd(year, month, day);
    for (int i = 0, j = LunarFestival.NAMES.length; i < j; i++) {
      int start = i * 8;
      Event e = new Event(LunarFestival.NAMES[i], '@' + LunarFestival.DATA.substring(start, start + 8));
      switch (e.getType()) {
        case LUNAR_DAY:
          int offset = e.getValue(5);
          if (0 == offset) {
            if (d.getMonth() == e.getValue(2) && d.getDay() == e.getValue(3)) {
              return new LunarFestival(FestivalType.DAY, i, e, d);
            }
          } else {
            int[] m = e.getMonth(d.getYear());
            LunarDay next = d.next(-offset);
            if (next.getYear() == m[0] && next.getMonth() == m[1] && next.getDay() == e.getValue(3)) {
              return new LunarFestival(FestivalType.DAY, i, e, d);
            }
          }
          break;
        case TERM_DAY:
          SolarTermDay term = d.getSolarDay().getTermDay();
          if (term.getDayIndex() == 0 && term.getSolarTerm().getIndex() == e.getValue(2) % 24) {
            return new LunarFestival(FestivalType.TERM, i, e, d);
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
    return (LunarDay) super.getDay();
  }

  /**
   * 节气，非节气当天返回null
   *
   * @return 节气
   */
  public SolarTerm getSolarTerm() {
    SolarTermDay t = getDay().getSolarDay().getTermDay();
    return t.getDayIndex() == 0 ? t.getSolarTerm() : null;
  }
}
