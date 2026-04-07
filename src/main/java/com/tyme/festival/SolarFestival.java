package com.tyme.festival;

import com.tyme.enums.FestivalType;
import com.tyme.event.Event;
import com.tyme.event.EventManager;
import com.tyme.solar.SolarDay;

/**
 * 公历现代节日
 *
 * @author 6tail
 */
public class SolarFestival extends AbstractFestival {

  public static final String[] NAMES = {"元旦", "妇女节", "植树节", "劳动节", "青年节", "儿童节", "建党节", "建军节", "教师节", "国庆节"};

  /**
   * 数据
   *
   * @see EventManager#DATA
   */
  public static String DATA = "0VV__0Ux0Xc__0Ux0Xg__0_Q0ZV__0Ux0ZY__0Ux0aV__0Ux0bV__0Uo0cV__0Ug0de__0_V0eV__0Ux";

  public SolarFestival(FestivalType type, int index, Event event, SolarDay day) {
    super(type, index, event, day);
  }

  public static SolarFestival fromIndex(int year, int index) {
    if (index < 0 || index >= NAMES.length) {
      return null;
    }
    int start = index * 8;
    Event e = new Event(NAMES[index], "@" + DATA.substring(start, start + 8));
    SolarDay d = e.getSolarDay(year);
    return null == d ? null : new SolarFestival(FestivalType.DAY, index, e, d);
  }

  public static SolarFestival fromYmd(int year, int month, int day) {
    for (int i = 0, j = NAMES.length; i < j; i++) {
      SolarFestival f = fromIndex(year, i);
      if (null != f) {
        SolarDay d = f.getDay();
        if (null != d && d.getYear() == year && d.getMonth() == month && d.getDay() == day) {
          return f;
        }
      }
    }
    return null;
  }

  public SolarFestival next(int n) {
    int size = NAMES.length;
    int i = index + n;
    return fromIndex((day.getYear() * size + i) / size, indexOf(i, size));
  }

  /**
   * 起始年
   *
   * @return 年
   */
  public int getStartYear() {
    return event.getStartYear();
  }

  /**
   * 公历日
   *
   * @return 公历日
   */
  public SolarDay getDay() {
    return getSolarDay();
  }
}
