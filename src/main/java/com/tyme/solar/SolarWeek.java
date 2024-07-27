package com.tyme.solar;

import com.tyme.AbstractTyme;
import com.tyme.culture.Week;

import java.util.ArrayList;
import java.util.List;

/**
 * 公历周
 *
 * @author 6tail
 */
public class SolarWeek extends AbstractTyme {

  public static final String[] NAMES = {"第一周", "第二周", "第三周", "第四周", "第五周", "第六周"};

  /**
   * 月
   */
  protected SolarMonth month;

  /**
   * 索引，0-5
   */
  protected int index;

  /**
   * 起始星期
   */
  protected Week start;

  /**
   * 初始化
   *
   * @param year  年
   * @param month 月
   * @param index 索引，0-5
   * @param start 起始星期，1234560分别代表星期一至星期天
   */
  public SolarWeek(int year, int month, int index, int start) {
    if (index < 0 || index > 5) {
      throw new IllegalArgumentException(String.format("illegal solar week index: %d", index));
    }
    if (start < 0 || start > 6) {
      throw new IllegalArgumentException(String.format("illegal solar week start: %d", start));
    }
    SolarMonth m = SolarMonth.fromYm(year, month);
    if (index >= m.getWeekCount(start)) {
      throw new IllegalArgumentException(String.format("illegal solar week index: %d in month: %s", index, m));
    }
    this.month = m;
    this.index = index;
    this.start = Week.fromIndex(start);
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
    return month;
  }

  /**
   * 年
   *
   * @return 年
   */
  public int getYear() {
    return month.getYear();
  }

  /**
   * 月
   *
   * @return 月
   */
  public int getMonth() {
    return month.getMonth();
  }

  /**
   * 索引
   *
   * @return 索引，0-5
   */
  public int getIndex() {
    return index;
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
    SolarWeek w = SolarWeek.fromYm(getYear(), 1, 0, start.getIndex());
    while (!w.getFirstDay().equals(firstDay)) {
      w = w.next(1);
      i++;
    }
    return i;
  }

  /**
   * 起始星期
   *
   * @return 星期
   */
  public Week getStart() {
    return start;
  }

  public String getName() {
    return NAMES[index];
  }

  @Override
  public String toString() {
    return month + getName();
  }

  public SolarWeek next(int n) {
    int startIndex = start.getIndex();
    if (n == 0) {
      return fromYm(getYear(), getMonth(), index, startIndex);
    }
    int d = index + n;
    SolarMonth m = month;
    if (n > 0) {
      int weekCount = m.getWeekCount(startIndex);
      while (d >= weekCount) {
        d -= weekCount;
        m = m.next(1);
        if (!SolarDay.fromYmd(m.getYear(), m.getMonth(), 1).getWeek().equals(start)) {
          d += 1;
        }
        weekCount = m.getWeekCount(startIndex);
      }
    } else {
      while (d < 0) {
        if (!SolarDay.fromYmd(m.getYear(), m.getMonth(), 1).getWeek().equals(start)) {
          d -= 1;
        }
        m = m.next(-1);
        d += m.getWeekCount(startIndex);
      }
    }
    return fromYm(m.getYear(), m.getMonth(), d, startIndex);
  }

  /**
   * 本周第1天
   *
   * @return 公历日
   */
  public SolarDay getFirstDay() {
    SolarDay firstDay = SolarDay.fromYmd(getYear(), getMonth(), 1);
    return firstDay.next(index * 7 - indexOf(firstDay.getWeek().getIndex() - start.getIndex(), 7));
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
