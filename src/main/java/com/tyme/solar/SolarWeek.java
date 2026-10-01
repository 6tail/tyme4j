package com.tyme.solar;

import com.tyme.unit.AbstractMonth;
import com.tyme.unit.AbstractWeek;
import com.tyme.unit.WeekUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * 公历周
 *
 * @author 6tail
 */
public class SolarWeek extends AbstractWeek {

  public static void validate(int year, int month, int index, int start) {
    WeekUnit.validate(index, start);
    SolarMonth m = new SolarMonth(year, month);
    if (index >= m.getWeekCount(start)) {
      throw new IllegalArgumentException(String.format("illegal solar week index: %d in month: %s", index, m));
    }
  }

  /**
   * 初始化
   *
   * @param year  年
   * @param month 月
   * @param index 索引，0-5
   * @param start 起始星期，1234560分别代表星期一至星期天
   */
  public SolarWeek(int year, int month, int index, int start) {
    super(year, month, index, start);
    validate(year, month, index, start);
  }

  public static SolarWeek fromYm(int year, int month, int index, int start) {
    return new SolarWeek(year, month, index, start);
  }

  /**
   * 公历月
   *
   * @return 公历月
   */
  public SolarMonth getSolarMonth() {
    return new SolarMonth(year, month);
  }

  @Override
  public AbstractMonth getAbstractMonth() {
    return getSolarMonth();
  }

  /**
   * 位于当年的索引
   *
   * @return 索引
   */
  public int getIndexInYear() {
    int i = 0;
    SolarDay firstDay = getFirstDay();
    // 今年第1周
    SolarWeek w = new SolarWeek(year, 1, 0, start);
    while (!w.getFirstDay().equals(firstDay)) {
      w = w.next(1);
      i++;
    }
    return i;
  }

  public String getName() {
    return NAMES[index];
  }

  public SolarWeek next(int n) {
    AbstractWeek w = super.next(n);
    return fromYm(w.getYear(), w.getMonth(), w.getIndex(), start);
  }

  /**
   * 本周第1天
   *
   * @return 公历日
   */
  public SolarDay getFirstDay() {
    SolarDay firstDay = new SolarDay(year, month, 1);
    return firstDay.next(index * 7 - indexOf(firstDay.getWeek().getIndex() - start, 7));
  }

  /**
   * 本周公历日列表
   *
   * @return 公历日列表
   */
  public List<SolarDay> getDays() {
    List<SolarDay> l = new ArrayList<>(7);
    SolarDay d = getFirstDay();
    l.add(d);
    for (int i = 1; i < 7; i++) {
      l.add(d.next(i));
    }
    return l;
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof SolarWeek && getFirstDay().equals(((SolarWeek) o).getFirstDay());
  }
}
