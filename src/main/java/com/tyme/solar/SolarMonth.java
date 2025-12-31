package com.tyme.solar;

import com.tyme.unit.MonthUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * 公历月
 *
 * @author 6tail
 */
public class SolarMonth extends MonthUnit {

  public static final String[] NAMES = {"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"};

  /**
   * 每月天数
   */
  public static final int[] DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

  public static void validate(int year, int month) {
    if (month < 1 || month > 12) {
      throw new IllegalArgumentException(String.format("illegal solar month: %d", month));
    }
    SolarYear.validate(year);
  }

  /**
   * 初始化
   *
   * @param year  年
   * @param month 月
   */
  public SolarMonth(int year, int month) {
    validate(year, month);
    this.year = year;
    this.month = month;
  }

  public static SolarMonth fromYm(int year, int month) {
    return new SolarMonth(year, month);
  }

  /**
   * 公历年
   *
   * @return 公历年
   */
  public SolarYear getSolarYear() {
    return SolarYear.fromYear(year);
  }

  /**
   * 天数（1582年10月只有21天)
   *
   * @return 天数
   */
  public int getDayCount() {
    if (1582 == year && 10 == month) {
      return 21;
    }
    int d = DAYS[getIndexInYear()];
    //公历闰年2月多一天
    if (2 == month && getSolarYear().isLeap()) {
      d++;
    }
    return d;
  }

  /**
   * 位于当年的索引(0-11)
   *
   * @return 索引
   */
  public int getIndexInYear() {
    return month - 1;
  }

  /**
   * 公历季度
   *
   * @return 公历季度
   */
  public SolarSeason getSeason() {
    return SolarSeason.fromIndex(year, getIndexInYear() / 3);
  }

  /**
   * 周数
   *
   * @param start 起始星期，1234560分别代表星期一至星期天
   * @return 周数
   */
  public int getWeekCount(int start) {
    return (int) Math.ceil((indexOf(SolarDay.fromYmd(year, month, 1).getWeek().getIndex() - start, 7) + getDayCount()) / 7D);
  }

  public String getName() {
    return NAMES[getIndexInYear()];
  }

  @Override
  public String toString() {
    return getSolarYear() + getName();
  }

  public SolarMonth next(int n) {
    int i = month - 1 + n;
    return fromYm((year * 12 + i) / 12, indexOf(i, 12) + 1);
  }

  /**
   * 本月的公历周列表
   *
   * @param start 星期几作为一周的开始，1234560分别代表星期一至星期天
   * @return 公历周列表
   */
  public List<SolarWeek> getWeeks(int start) {
    int size = getWeekCount(start);
    List<SolarWeek> l = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      l.add(SolarWeek.fromYm(year, month, i, start));
    }
    return l;
  }

  /**
   * 本月的公历日列表
   *
   * @return 公历日列表
   */
  public List<SolarDay> getDays() {
    int size = getDayCount();
    List<SolarDay> l = new ArrayList<>(size);
    for (int i = 1; i <= size; i++) {
      l.add(SolarDay.fromYmd(year, month, i));
    }
    return l;
  }

  /**
   * 本月第1天
   *
   * @return 公历日
   */
  public SolarDay getFirstDay() {
    return SolarDay.fromYmd(year, month, 1);
  }
}
