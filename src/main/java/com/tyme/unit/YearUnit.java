package com.tyme.unit;

import com.tyme.AbstractTyme;

/**
 * 年
 *
 * @author 6tail
 */
public abstract class YearUnit extends AbstractTyme {
  /**
   * 年
   */
  protected int year;

  public YearUnit(int year) {
    this.year = year;
  }

  /**
   * 年
   *
   * @return 年
   */
  public int getYear() {
    return year;
  }

  /**
   * 用于比较大小的索引
   *
   * @return 索引
   */
  protected long getCompareIndex() {
    return year * 10000L;
  }

  /**
   * 是否在之前
   *
   * @param target 年
   * @return true/false
   */
  protected boolean isBefore(YearUnit target) {
    return getCompareIndex() < target.getCompareIndex();
  }

  /**
   * 是否在之后
   *
   * @param target 年
   * @return true/false
   */
  protected boolean isAfter(YearUnit target) {
    return getCompareIndex() > target.getCompareIndex();
  }
}
