package com.tyme.hijri;

import com.tyme.culture.Week;
import com.tyme.jd.JulianDay;
import com.tyme.solar.SolarDay;
import com.tyme.unit.AbstractDay;

/**
 * 回历日（公元622年7月16日为伊斯兰历元年元旦）
 *
 * @author 6tail
 */
public class HijriDay extends AbstractDay {

  public static final String[] NAMES = {"1日", "2日", "3日", "4日", "5日", "6日", "7日", "8日", "9日", "10日", "11日", "12日", "13日", "14日", "15日", "16日", "17日", "18日", "19日", "20日", "21日", "22日", "23日", "24日", "25日", "26日", "27日", "28日", "29日", "30日"};

  public static void validate(int year, int month, int day) {
    if (day < 1 || day > new HijriMonth(year, month).getDayCount()) {
      throw new IllegalArgumentException(String.format("illegal hijri day: %d-%d-%d", year, month, day));
    }
  }

  /**
   * 初始化
   *
   * @param year  年
   * @param month 月
   * @param day   日
   */
  public HijriDay(int year, int month, int day) {
    super(year, month, day);
    validate(year, month, day);
  }

  @Override
  public Week getWeek() {
    return getJulianDay().getWeek();
  }

  public static HijriDay fromYmd(int year, int month, int day) {
    return new HijriDay(year, month, day);
  }

  /**
   * 回历月
   *
   * @return 回历月
   */
  public HijriMonth getHijriMonth() {
    return new HijriMonth(year, month);
  }

  public String getName() {
    return NAMES[day - 1];
  }

  @Override
  public String toString() {
    return getHijriMonth() + getName();
  }

  public HijriDay next(int n) {
    return getSolarDay().next(n).getHijriDay();
  }

  /**
   * 是否在指定回历日之前
   *
   * @param target 回历日
   * @return true/false
   */
  public boolean isBefore(HijriDay target) {
    return getCompareIndex() < target.getCompareIndex();
  }

  /**
   * 是否在指定回历日之后
   *
   * @param target 回历日
   * @return true/false
   */
  public boolean isAfter(HijriDay target) {
    return getCompareIndex() > target.getCompareIndex();
  }

  /**
   * 位于当年的索引
   *
   * @return 索引
   */
  public int getIndexInYear() {
    return subtract(new HijriDay(year, 1, 1));
  }

  /**
   * 回历日期相减，获得相差天数
   *
   * @param target 回历日
   * @return 天数
   */
  public int subtract(HijriDay target) {
    return (int) (getJulianDay().subtract(target.getJulianDay()));
  }

  /**
   * 儒略日
   *
   * @return 儒略日
   */
  public JulianDay getJulianDay() {
    return new JulianDay(Math.floorDiv(11 * year + 3, 30) + 354 * year + 30 * month - Math.floorDiv(month - 1, 2) + day + 1948055);
  }

  /**
   * 公历日
   *
   * @return 公历日
   */
  public SolarDay getSolarDay() {
    return new SolarDay(622, 7, 16).next(subtract(new HijriDay(1, 1, 1)));
  }
}
