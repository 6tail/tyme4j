package com.tyme.jd;

import com.tyme.AbstractTyme;
import com.tyme.culture.Week;
import com.tyme.solar.SolarTime;
import com.tyme.solar.SolarDay;

/**
 * 儒略日
 *
 * @author 6tail
 */
public class JulianDay extends AbstractTyme {

  /**
   * 2000年儒略日数(2000-1-1 12:00:00 UTC)
   */
  public static final double J2000 = 2451545;

  /**
   * 儒略日
   */
  protected double day;

  public JulianDay(double day) {
    this.day = day;
  }

  public static JulianDay fromJulianDay(double day) {
    return new JulianDay(day);
  }

  public static JulianDay fromYmdHms(int year, int month, int day, int hour, int minute, int second) {
    double d = day + ((second * 1D / 60 + minute) / 60 + hour) / 24;
    int n = 0;
    boolean g = year * 372 + month * 31 + (int) d >= 588829;
    if (month <= 2) {
      month += 12;
      year--;
    }
    if (g) {
      n = (int) (year * 1D / 100);
      n = 2 - n + (int) (n * 1D / 4);
    }
    return fromJulianDay((int) (365.25 * (year + 4716)) + (int) (30.6001 * (month + 1)) + d + n - 1524.5);
  }

  /**
   * 儒略日
   *
   * @return 儒略日
   */
  public double getDay() {
    return day;
  }

  public String getName() {
    return day + "";
  }

  public JulianDay next(int n) {
    return fromJulianDay(day + n);
  }

  /**
   * 公历日
   *
   * @return 公历日
   */
  public SolarDay getSolarDay() {
    int d = (int) (this.day + 0.5);
    double f = this.day + 0.5 - d;

    if (d >= 2299161) {
      int c = (int) ((d - 1867216.25) / 36524.25);
      d += 1 + c - (int) (c * 1D / 4);
    }
    d += 1524;
    int year = (int) ((d - 122.1) / 365.25);
    d -= (int) (365.25 * year);
    int month = (int) (d * 1D / 30.601);
    d -= (int) (30.601 * month);
    int day = d;
    if (month > 13) {
      month -= 13;
      year -= 4715;
    } else {
      month -= 1;
      year -= 4716;
    }
    f *= 24;
    int hour = (int) f;

    f -= hour;
    f *= 60;
    int minute = (int) f;

    f -= minute;
    f *= 60;
    int second = (int) Math.round(f);
    if (second > 59) {
      minute++;
    }
    if (minute > 59) {
      hour++;
    }
    if (hour > 23) {
      day += 1;
    }
    return SolarDay.fromYmd(year, month, day);
  }

  /**
   * 公历时刻
   *
   * @return 公历时刻
   */
  public SolarTime getSolarTime() {
    int d = (int) (this.day + 0.5);
    double f = this.day + 0.5 - d;

    if (d >= 2299161) {
      int c = (int) ((d - 1867216.25) / 36524.25);
      d += 1 + c - (int) (c * 1D / 4);
    }
    d += 1524;
    int year = (int) ((d - 122.1) / 365.25);
    d -= (int) (365.25 * year);
    int month = (int) (d * 1D / 30.601);
    d -= (int) (30.601 * month);
    int day = d;
    if (month > 13) {
      month -= 13;
      year -= 4715;
    } else {
      month -= 1;
      year -= 4716;
    }
    f *= 24;
    int hour = (int) f;

    f -= hour;
    f *= 60;
    int minute = (int) f;

    f -= minute;
    f *= 60;
    int second = (int) Math.round(f);
    if (second > 59) {
      second -= 60;
      minute++;
    }
    if (minute > 59) {
      minute -= 60;
      hour++;
    }
    if (hour > 23) {
      hour -= 24;
      day += 1;
    }
    return SolarTime.fromYmdHms(year, month, day, hour, minute, second);
  }

  /**
   * 星期
   *
   * @return 星期
   */
  public Week getWeek() {
    return Week.fromIndex((int) (day + 0.5) + 7000001);
  }

  /**
   * 儒略日相减
   *
   * @param target 儒略日
   * @return 差
   */
  public double subtract(JulianDay target) {
    return day - target.getDay();
  }
}
