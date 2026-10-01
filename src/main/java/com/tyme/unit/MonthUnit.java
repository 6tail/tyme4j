package com.tyme.unit;

/**
 * 月
 *
 * @author 6tail
 */
public abstract class MonthUnit extends YearUnit {
  /**
   * 月
   */
  protected int month;

  public MonthUnit(int year, int month) {
    super(year);
    this.month = month;
  }

  /**
   * 月
   *
   * @return 月
   */
  public int getMonth() {
    return month;
  }

  @Override
  protected long getCompareIndex() {
    return super.getCompareIndex() + (month > 0 ? month * 2L : -month * 2L + 1) * 100;
  }
}
