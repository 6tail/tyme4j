package com.tyme.unit;

import com.tyme.culture.Week;

/**
 * 周
 *
 * @author 6tail
 */
public abstract class WeekUnit extends MonthUnit {
  /**
   * 索引，0-5
   */
  protected int index;

  /**
   * 起始星期，1234560分别代表星期一至星期天
   */
  protected int start;

  /**
   * 索引
   *
   * @return 索引，0-5
   */
  public int getIndex() {
    return index;
  }

  /**
   * 起始星期
   *
   * @return 星期
   */
  public Week getStart() {
    return Week.fromIndex(start);
  }

  public static void validate(int index, int start) {
    if (index < 0 || index > 5) {
      throw new IllegalArgumentException(String.format("illegal week index: %d", index));
    }
    if (start < 0 || start > 6) {
      throw new IllegalArgumentException(String.format("illegal week start: %d", start));
    }
  }
}
