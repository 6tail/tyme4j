package com.tyme.unit;

/**
 * 秒
 *
 * @author 6tail
 */
public abstract class SecondUnit extends DayUnit {
  /**
   * 时
   */
  protected int hour;

  /**
   * 分
   */
  protected int minute;

  /**
   * 秒
   */
  protected int second;

  public static void validate(int hour, int minute, int second) {
    if (hour < 0 || hour > 23) {
      throw new IllegalArgumentException("illegal hour: " + hour);
    }
    if (minute < 0 || minute > 59) {
      throw new IllegalArgumentException("illegal minute: " + minute);
    }
    if (second < 0 || second > 59) {
      throw new IllegalArgumentException("illegal second: " + second);
    }
  }

  /**
   * 时
   *
   * @return 时
   */
  public int getHour() {
    return hour;
  }

  /**
   * 分
   *
   * @return 分
   */
  public int getMinute() {
    return minute;
  }

  /**
   * 秒
   *
   * @return 秒
   */
  public int getSecond() {
    return second;
  }
}
