package com.tyme.eightchar;

import com.tyme.solar.SolarTime;

/**
 * 童限信息
 *
 * @author 6tail
 */
public class ChildLimitInfo {

  /**
   * 开始(即出生)的公历时刻
   */
  protected SolarTime startTime;

  /**
   * 结束(即开始起运)的公历时刻
   */
  protected SolarTime endTime;

  /**
   * 年数
   */
  protected int yearCount;

  /**
   * 月数
   */
  protected int monthCount;

  /**
   * 日数
   */
  protected int dayCount;

  /**
   * 小时数
   */
  protected int hourCount;

  /**
   * 分钟数
   */
  protected int minuteCount;

  public ChildLimitInfo(SolarTime startTime, SolarTime endTime, int yearCount, int monthCount, int dayCount, int hourCount, int minuteCount) {
    this.startTime = startTime;
    this.endTime = endTime;
    this.yearCount = yearCount;
    this.monthCount = monthCount;
    this.dayCount = dayCount;
    this.hourCount = hourCount;
    this.minuteCount = minuteCount;
  }

  public SolarTime getStartTime() {
    return startTime;
  }

  public SolarTime getEndTime() {
    return endTime;
  }

  public int getYearCount() {
    return yearCount;
  }

  public int getMonthCount() {
    return monthCount;
  }

  public int getDayCount() {
    return dayCount;
  }

  public int getHourCount() {
    return hourCount;
  }

  public int getMinuteCount() {
    return minuteCount;
  }

}
