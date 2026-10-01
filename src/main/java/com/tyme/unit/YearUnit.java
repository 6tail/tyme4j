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
}
