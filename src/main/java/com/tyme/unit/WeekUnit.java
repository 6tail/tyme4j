package com.tyme.unit;

import com.tyme.culture.Week;

/**
 * 周
 *
 * @author 6tail
 */
public abstract class WeekUnit extends MonthUnit {

  public static final String[] NAMES = {"第一周", "第二周", "第三周", "第四周", "第五周", "第六周"};

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
    validateRange(index, 0, 5, "week index");
    validateRange(start, 0, 6, "week start");
  }
}
