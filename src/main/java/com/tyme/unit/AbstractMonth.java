package com.tyme.unit;

/**
 * 抽象月
 *
 * @author 6tail
 */
public abstract class AbstractMonth extends MonthUnit {
  public AbstractMonth(int year, int month) {
    super(year, month);
  }

  /**
   * 周数
   *
   * @param start 起始星期，1234560分别代表星期一至星期天
   * @return 周数
   */
  public int getWeekCount(int start) {
    return (int) Math.ceil((indexOf(getFirstDay().getWeek().getIndex() - start, 7) + getDayCount()) / 7D);
  }

  public abstract AbstractMonth next(int n);

  /**
   * 天数
   *
   * @return 天数
   */
  public abstract int getDayCount();

  /**
   * 本月第1天
   *
   * @return 抽象日
   */
  public abstract AbstractDay getFirstDay();

  /**
   * 月
   *
   * @return 月，当月为闰月时，返回负数，如-2代表闰二月
   */
  public int getMonthValue() {
    return month;
  }

  /**
   * 抽象年
   *
   * @return 抽象年
   */
  public abstract AbstractYear getAbstractYear();

  @Override
  public String toString() {
    return getAbstractYear() + getName();
  }
}
