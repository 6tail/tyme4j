package com.tyme.lunar;

import com.tyme.unit.AbstractMonth;
import com.tyme.unit.AbstractWeek;
import com.tyme.unit.WeekUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * 农历周
 *
 * @author 6tail
 */
public class LunarWeek extends AbstractWeek {

  public static void validate(int year, int month, int index, int start) {
    WeekUnit.validate(index, start);
    LunarMonth m = new LunarMonth(year, month);
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
    super(year, month, index, start);
    validate(year, month, index, start);
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
    return new LunarMonth(year, month);
  }

  @Override
  public AbstractMonth getAbstractMonth() {
    return getLunarMonth();
  }

  public String getName() {
    return NAMES[index];
  }

  public LunarWeek next(int n) {
    AbstractWeek w = super.next(n);
    return fromYm(w.getYear(), w.getMonth(), w.getIndex(), start);
  }

  /**
   * 本周第1天
   *
   * @return 农历日
   */
  public LunarDay getFirstDay() {
    LunarDay firstDay = new LunarDay(year, month, 1);
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
