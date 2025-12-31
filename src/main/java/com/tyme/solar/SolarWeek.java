package com.tyme.solar;

import com.tyme.unit.WeekUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * 公历周
 *
 * @author 6tail
 */
public class SolarWeek extends WeekUnit {

  public static final String[] NAMES = {"第一周", "第二周", "第三周", "第四周", "第五周", "第六周"};

  public static void validate(int year, int month, int index, int start) {
    WeekUnit.validate(index, start);
    SolarMonth m = SolarMonth.fromYm(year, month);
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
    validate(year, month, index, start);
    this.year = year;
    this.month = month;
    this.index = index;
    this.start = start;
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
    return SolarMonth.fromYm(year, month);
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
    SolarWeek w = SolarWeek.fromYm(year, 1, 0, start);
    while (!w.getFirstDay().equals(firstDay)) {
      w = w.next(1);
      i++;
    }
    return i;
  }

  public String getName() {
    return NAMES[index];
  }

  @Override
  public String toString() {
    return getSolarMonth() + getName();
  }

  public SolarWeek next(int n) {
    int d = index;
    SolarMonth m = getSolarMonth();
    if (n > 0) {
      d += n;
      int weekCount = m.getWeekCount(start);
      while (d >= weekCount) {
        d -= weekCount;
        m = m.next(1);
        if (m.getFirstDay().getWeek().getIndex() != start) {
          d += 1;
        }
        weekCount = m.getWeekCount(start);
      }
    } else if (n < 0) {
      d += n;
      while (d < 0) {
        if (m.getFirstDay().getWeek().getIndex() != start) {
          d -= 1;
        }
        m = m.next(-1);
        d += m.getWeekCount(start);
      }
    }
    return fromYm(m.getYear(), m.getMonth(), d, start);
  }

  /**
   * 本周第1天
   *
   * @return 公历日
   */
  public SolarDay getFirstDay() {
    SolarDay firstDay = SolarDay.fromYmd(getYear(), getMonth(), 1);
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
