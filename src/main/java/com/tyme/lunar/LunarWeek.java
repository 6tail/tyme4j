package com.tyme.lunar;

import com.tyme.unit.WeekUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * 农历周
 *
 * @author 6tail
 */
public class LunarWeek extends WeekUnit {

  public static final String[] NAMES = {"第一周", "第二周", "第三周", "第四周", "第五周", "第六周"};

  public static void validate(int year, int month, int index, int start) {
    WeekUnit.validate(index, start);
    LunarMonth m = LunarMonth.fromYm(year, month);
    if (index >= m.getWeekCount(start)) {
      throw new IllegalArgumentException(String.format("illegal lunar week index: %d in month: %s", index, m));
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
  public LunarWeek(int year, int month, int index, int start) {
    validate(year, month, index, start);
    this.year = year;
    this.month = month;
    this.index = index;
    this.start = start;
  }

  public static LunarWeek fromYm(int year, int month, int index, int start) {
    return new LunarWeek(year, month, index, start);
  }

  /**
   * 农历月
   *
   * @return 农历月
   */
  public LunarMonth getLunarMonth() {
    return LunarMonth.fromYm(year, month);
  }

  public String getName() {
    return NAMES[index];
  }

  @Override
  public String toString() {
    return getLunarMonth() + getName();
  }

  public LunarWeek next(int n) {
    if (n == 0) {
      return fromYm(getYear(), getMonth(), index, start);
    }
    int d = index + n;
    LunarMonth m = getLunarMonth();
    if (n > 0) {
      int weekCount = m.getWeekCount(start);
      while (d >= weekCount) {
        d -= weekCount;
        m = m.next(1);
        if (m.getFirstDay().getWeek().getIndex() != start) {
          d += 1;
        }
        weekCount = m.getWeekCount(start);
      }
    } else {
      while (d < 0) {
        if (m.getFirstDay().getWeek().getIndex() != start) {
          d -= 1;
        }
        m = m.next(-1);
        d += m.getWeekCount(start);
      }
    }
    return fromYm(m.getYear(), m.getMonthWithLeap(), d, start);
  }

  /**
   * 本周第1天
   *
   * @return 农历日
   */
  public LunarDay getFirstDay() {
    LunarDay firstDay = LunarDay.fromYmd(getYear(), getMonth(), 1);
    return firstDay.next(index * 7 - indexOf(firstDay.getWeek().getIndex() - start, 7));
  }

  /**
   * 本周农历日列表
   *
   * @return 农历日列表
   */
  public List<LunarDay> getDays() {
    List<LunarDay> l = new ArrayList<>(7);
    LunarDay d = getFirstDay();
    l.add(d);
    for (int i = 1; i < 7; i++) {
      l.add(d.next(i));
    }
    return l;
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof LunarWeek && getFirstDay().equals(((LunarWeek) o).getFirstDay());
  }

}
