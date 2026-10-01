package com.tyme.unit;

/**
 * 支持闰月的月份抽象
 *
 * @author 6tail
 */
public abstract class AbstractLeapMonth extends AbstractMonth {
  /**
   * 是否闰月
   */
  protected boolean leap;

  public AbstractLeapMonth(int year, int month) {
    super(year, Math.abs(month));
    this.leap = month < 0;
  }

  /**
   * 是否闰月
   *
   * @return true/false
   */
  public boolean isLeap() {
    return leap;
  }

  /**
   * 月
   *
   * @return 月，当月为闰月时，返回负数
   * @deprecated 使用{@link #getMonthValue()}
   */
  @Deprecated
  public int getMonthWithLeap() {
    return getMonthValue();
  }

  @Override
  public int getMonthValue() {
    return leap ? -month : month;
  }

  /**
   * 位于当年的索引(0-12)
   *
   * @return 索引
   */
  public int getIndexInYear() {
    int index = month - 1;
    if (leap) {
      index += 1;
    } else {
      int leapMonth = getAbstractYear().getLeapMonth();
      if (leapMonth > 0 && month > leapMonth) {
        index += 1;
      }
    }
    return index;
  }

  public AbstractLeapMonth next(int n) {
    int ty = getYear();
    int tm = getMonthValue();
    if (n != 0) {
      int m = getIndexInYear() + 1 + n;
      AbstractYear y = getAbstractYear();
      if (n > 0) {
        int monthCount = y.getMonthCount();
        while (m > monthCount) {
          m -= monthCount;
          y = y.next(1);
          monthCount = y.getMonthCount();
        }
      } else {
        while (m <= 0) {
          y = y.next(-1);
          m += y.getMonthCount();
        }
      }
      boolean leap = false;
      int leapMonth = y.getLeapMonth();
      if (leapMonth > 0) {
        if (m == leapMonth + 1) {
          leap = true;
        }
        if (m > leapMonth) {
          m--;
        }
      }
      ty = y.getYear();
      tm = leap ? -m : m;
    }
    return new AbstractLeapMonth(ty, tm) {
      @Override
      public int getDayCount() {
        return 0;
      }

      @Override
      public AbstractDay getFirstDay() {
        return null;
      }

      @Override
      public AbstractYear getAbstractYear() {
        return null;
      }
    };
  }
}
