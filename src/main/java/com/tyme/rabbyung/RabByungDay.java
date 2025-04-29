package com.tyme.rabbyung;

import com.tyme.AbstractTyme;
import com.tyme.culture.Zodiac;
import com.tyme.solar.SolarDay;

/**
 * 藏历日，仅支持藏历1950年十二月初一（公历1951年1月8日）至藏历2050年十二月三十（公历2051年2月11日）
 *
 * @author 6tail
 */
public class RabByungDay extends AbstractTyme {

  public static final String[] NAMES = {"初一", "初二", "初三", "初四", "初五", "初六", "初七", "初八", "初九", "初十", "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十", "廿一", "廿二", "廿三", "廿四", "廿五", "廿六", "廿七", "廿八", "廿九", "三十"};

  /**
   * 藏历月
   */
  protected RabByungMonth month;

  /**
   * 日
   */
  protected int day;

  /**
   * 是否闰日
   */
  protected boolean leap;

  public RabByungDay(RabByungMonth month, int day) {
    if (day == 0 || day < -30 || day > 30) {
      throw new IllegalArgumentException(String.format("illegal day %d in %s", day, month));
    }
    boolean leap = day < 0;
    int d = Math.abs(day);
    if (leap && !month.getLeapDays().contains(d)) {
      throw new IllegalArgumentException(String.format("illegal leap day %d in %s", d, month));
    } else if (!leap && month.getMissDays().contains(d)) {
      throw new IllegalArgumentException(String.format("illegal day %d in %s", d, month));
    }
    this.month = month;
    this.day = d;
    this.leap = leap;
  }

  /**
   * 初始化
   *
   * @param year  藏历年
   * @param month 藏历月，闰月为负
   * @param day   藏历日，闰日为负
   */
  public RabByungDay(int year, int month, int day) {
    this(RabByungMonth.fromYm(year, month), day);
  }

  public RabByungDay(int rabByungIndex, RabByungElement element, Zodiac zodiac, int month, int day) {
    this(new RabByungMonth(rabByungIndex, element, zodiac, month), day);
  }

  /**
   * 从藏历年月日初始化
   *
   * @param year  藏历年
   * @param month 藏历月，闰月为负
   * @param day   藏历日，闰日为负
   */
  public static RabByungDay fromYmd(int year, int month, int day) {
    return new RabByungDay(year, month, day);
  }

  public static RabByungDay fromElementZodiac(int rabByungIndex, RabByungElement element, Zodiac zodiac, int month, int day) {
    return new RabByungDay(rabByungIndex, element, zodiac, month, day);
  }

  public static RabByungDay fromSolarDay(SolarDay solarDay) {
    int days = solarDay.subtract(SolarDay.fromYmd(1951, 1, 8));
    RabByungMonth m = RabByungMonth.fromYm(1950, 12);
    int count = m.getDayCount();
    while (days >= count) {
      days -= count;
      m = m.next(1);
      count = m.getDayCount();
    }
    int day = days + 1;
    for (int d : m.getSpecialDays()) {
      if (d < 0) {
        if (day >= -d) {
          day++;
        }
      } else if (d > 0) {
        if (day == d + 1) {
          day = -d;
          break;
        } else if (day > d + 1) {
          day--;
        }
      }
    }
    return new RabByungDay(m, day);
  }

  /**
   * 藏历月
   *
   * @return 藏历月
   */
  public RabByungMonth getRabByungMonth() {
    return month;
  }

  /**
   * 年
   *
   * @return 年
   */
  public int getYear() {
    return month.getYear();
  }

  /**
   * 月
   *
   * @return 月
   */
  public int getMonth() {
    return month.getMonthWithLeap();
  }

  /**
   * 日
   *
   * @return 日
   */
  public int getDay() {
    return day;
  }

  /**
   * 是否闰日
   *
   * @return true/false
   */
  public boolean isLeap() {
    return leap;
  }

  /**
   * 日
   *
   * @return 日，当日为闰日时，返回负数
   */
  public int getDayWithLeap() {
    return leap ? -day : day;
  }

  public String getName() {
    return (leap ? "闰" : "") + NAMES[day - 1];
  }

  @Override
  public String toString() {
    return month + getName();
  }

  /**
   * 藏历日相减
   *
   * @param target 藏历日
   * @return 相差天数
   */
  public int subtract(RabByungDay target) {
    return getSolarDay().subtract(target.getSolarDay());
  }

  /**
   * 公历日
   *
   * @return 公历日
   */
  public SolarDay getSolarDay() {
    RabByungMonth m = RabByungMonth.fromYm(1950, 12);
    int n = 0;
    while (!month.equals(m)) {
      n += m.getDayCount();
      m = m.next(1);
    }
    int t = day;
    for (int d : m.getSpecialDays()) {
      if (d < 0) {
        if (t > -d) {
          t--;
        }
      } else if (d > 0) {
        if (t > d) {
          t++;
        }
      }
    }
    if (leap) {
      t++;
    }
    return SolarDay.fromYmd(1951, 1, 7).next(n + t);
  }

  public RabByungDay next(int n) {
    return getSolarDay().next(n).getRabByungDay();
  }
}
