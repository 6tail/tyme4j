package com.tyme.festival;

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

  public SolarFestival(int index, Event event, SolarDay day) {
    super(index, event, day);
  }

  public static SolarFestival fromIndex(int year, int index) {
    Event e = buildEvent(NAMES, DATA, index);
    return null == e ? null : (year < e.getStartYear() ? null : new SolarFestival(index, e, SolarDay.fromYmd(year, e.getValue(2), e.getValue(3))));
  }

  public static SolarFestival fromYmd(int year, int month, int day) {
    SolarDay d = SolarDay.fromYmd(year, month, day);
    for (int i = 0, j = NAMES.length; i < j; i++) {
      int start = i * 8;
      Event e = new Event(NAMES[i], "@" + DATA.substring(start, start + 8));
      if (d.getYear() >= e.getStartYear() && d.getMonth() == e.getValue(2) && d.getDay() == e.getValue(3)) {
        return new SolarFestival(i, e, d);
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
    return (SolarDay) super.getDay();
  }
}
