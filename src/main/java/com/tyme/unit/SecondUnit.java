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
    validateRange(hour, 0, 23, "hour");
    validateRange(minute, 0, 59, "minute");
    validateRange(second, 0, 59, "second");
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

  /**
   * 当天秒数
   *
   * @return 当天秒数
   */
  public int getSecondsInDay() {
    return hour * 3600 + minute * 60 + second;
  }

  @Override
  protected long getCompareIndex() {
    return super.getCompareIndex() * 86400L + getSecondsInDay();
  }
}
