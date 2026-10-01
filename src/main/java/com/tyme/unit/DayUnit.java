package com.tyme.unit;

/**
 * 日
 *
 * @author 6tail
 */
public abstract class DayUnit extends MonthUnit {
  /**
   * 日
   */
  protected int day;

  public DayUnit(int year, int month, int day) {
    super(year, month);
    this.day = day;
  }

  /**
   * 日
   *
   * @return 日
   */
  public int getDay() {
    return day;
  }

  @Override
  protected long getCompareIndex() {
    return super.getCompareIndex() + day;
  }
}
