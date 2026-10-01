package com.tyme.unit;

import com.tyme.culture.Week;

/**
 * 抽象日
 *
 * @author 6tail
 */
public abstract class AbstractDay extends DayUnit {
  public AbstractDay(int year, int month, int day) {
    super(year, month, day);
  }

  /**
   * 星期
   *
   * @return 星期
   */
  public abstract Week getWeek();
}
