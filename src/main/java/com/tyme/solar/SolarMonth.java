package com.tyme.solar;

import com.tyme.AbstractTyme;

import java.util.ArrayList;
import java.util.List;

/**
 * 公历月
 *
 * @author 6tail
 */
public class SolarMonth extends AbstractTyme {

  public static final String[] NAMES = {"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"};

  /**
   * 每月天数
   */
  public static final int[] DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

  /**
   * 年
   */
  protected SolarYear year;

  /**
   * 月
   */
  protected int month;

  /**
   * 初始化
   *
   * @param year  年
   * @param month 月
   */
  public SolarMonth(int year, int month) {
    if (month < 1 || month > 12) {
      throw new IllegalArgumentException(String.format("illegal solar month: %d", month));
    }
    this.year = SolarYear.fromYear(year);
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
    return year;
  }

  /**
   * 年
   *
   * @return 年
   */
  public int getYear() {
    return year.getYear();
  }

  /**
   * 月
   *
   * @return 月
   */
  public int getMonth() {
    return month;
  }

  /**
   * 天数（1582年10月只有21天)
   *
   * @return 天数
   */
  public int getDayCount() {
    if (1582 == getYear() && 10 == month) {
      return 21;
    }
    int d = DAYS[getIndexInYear()];
    //公历闰年2月多一天
    if (2 == month && year.isLeap()) {
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
    return SolarSeason.fromIndex(getYear(), getIndexInYear() / 3);
  }

  /**
   * 周数
   *
   * @param start 起始星期，1234560分别代表星期一至星期天
   * @return 周数
   */
  public int getWeekCount(int start) {
    return (int) Math.ceil((indexOf(SolarDay.fromYmd(getYear(), month, 1).getWeek().getIndex() - start, 7) + getDayCount()) / 7D);
  }

  public String getName() {
    return NAMES[getIndexInYear()];
  }

  @Override
  public String toString() {
    return year + getName();
  }

  public SolarMonth next(int n) {
    if (n == 0) {
      return fromYm(getYear(), month);
    }
    int m = month + n;
    int y = getYear() + m / 12;
    m %= 12;
    if (m < 1) {
      m += 12;
      y--;
    }
    return fromYm(y, m);
  }

  /**
   * 获取本月的公历周列表
   *
   * @param start 星期几作为一周的开始，1234560分别代表星期一至星期天
   * @return 周列表
   */
  public List<SolarWeek> getWeeks(int start) {
    int size = getWeekCount(start);
    int y = getYear();
    List<SolarWeek> l = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      l.add(SolarWeek.fromYm(y, month, i, start));
    }
    return l;
  }

  /**
   * 获取本月的公历日列表
   *
   * @return 公历日列表
   */
  public List<SolarDay> getDays() {
    int size = getDayCount();
    int y = getYear();
    List<SolarDay> l = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      l.add(SolarDay.fromYmd(y, month, i + 1));
    }
    return l;
  }

}
