package com.tyme.unit;

/**
 * 抽象年
 *
 * @author 6tail
 */
public abstract class AbstractYear extends YearUnit {
  public AbstractYear(int year) {
    super(year);
  }

  /**
   * 月数
   *
   * @return 月数
   */
  public int getMonthCount() {
    return getLeapMonth() < 1 ? 12 : 13;
  }

  /**
   * 闰月
   *
   * @return 闰月数字，1代表闰1月，0代表无闰月
   */
  public int getLeapMonth() {
    return 0;
  }

  @Override
  public String getName() {
    return year + "年";
  }

  public abstract AbstractYear next(int n);
}
