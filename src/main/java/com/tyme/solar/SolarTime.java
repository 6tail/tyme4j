package com.tyme.solar;

import com.tyme.AbstractTyme;
import com.tyme.jd.JulianDay;
import com.tyme.lunar.LunarDay;
import com.tyme.lunar.LunarHour;
import com.tyme.lunar.LunarMonth;

/**
 * 公历时刻
 *
 * @author 6tail
 */
public class SolarTime extends AbstractTyme {

  /**
   * 公历日
   */
  protected SolarDay day;

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

  /**
   * 初始化
   *
   * @param year   年
   * @param month  月
   * @param day    日
   * @param hour   时
   * @param minute 分
   * @param second 秒
   */
  public SolarTime(int year, int month, int day, int hour, int minute, int second) {
    if (hour < 0 || hour > 23) {
      throw new IllegalArgumentException(String.format("illegal hour: %d", hour));
    }
    if (minute < 0 || minute > 59) {
      throw new IllegalArgumentException(String.format("illegal minute: %d", minute));
    }
    if (second < 0 || second > 59) {
      throw new IllegalArgumentException(String.format("illegal second: %d", second));
    }
    this.day = SolarDay.fromYmd(year, month, day);
    this.hour = hour;
    this.minute = minute;
    this.second = second;
  }

  public static SolarTime fromYmdHms(int year, int month, int day, int hour, int minute, int second) {
    return new SolarTime(year, month, day, hour, minute, second);
  }

  /**
   * 日
   *
   * @return 日
   */
  public SolarDay getDay() {
    return day;
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

  public String getName() {
    return String.format("%02d:%02d:%02d", hour, minute, second);
  }

  @Override
  public String toString() {
    return String.format("%s %s", day, getName());
  }

  /**
   * 是否在指定公历时刻之前
   *
   * @param target 公历时刻
   * @return true/false
   */
  public boolean isBefore(SolarTime target) {
    if (!day.equals(target.getDay())) {
      return day.isBefore(target.getDay());
    }
    if (hour != target.getHour()) {
      return hour < target.getHour();
    }
    return minute != target.getMinute() ? minute < target.getMinute() : second < target.getSecond();
  }

  /**
   * 是否在指定公历时刻之后
   *
   * @param target 公历时刻
   * @return true/false
   */
  public boolean isAfter(SolarTime target) {
    if (!day.equals(target.getDay())) {
      return day.isAfter(target.getDay());
    }
    if (hour != target.getHour()) {
      return hour > target.getHour();
    }
    return minute != target.getMinute() ? minute > target.getMinute() : second > target.getSecond();
  }

  /**
   * 节气
   *
   * @return 节气
   */
  public SolarTerm getTerm() {
    SolarMonth m = day.getMonth();
    int y = m.getYear().getYear();
    int i = m.getMonth() * 2;
    if (i == 24) {
      y += 1;
      i = 0;
    }
    SolarTerm term = SolarTerm.fromIndex(y, i);
    while (isBefore(term.getJulianDay().getSolarTime())) {
      term = term.next(-1);
    }
    return term;
  }

  /**
   * 儒略日
   *
   * @return 儒略日
   */
  public JulianDay getJulianDay() {
    SolarMonth m = day.getMonth();
    return JulianDay.fromYmdHms(m.getYear().getYear(), m.getMonth(), day.getDay(), hour, minute, second);
  }

  /**
   * 公历时刻相减，获得相差秒数
   *
   * @param target 公历时刻
   * @return 秒数
   */
  public int subtract(SolarTime target) {
    int days = day.subtract(target.getDay());
    int cs = hour * 3600 + minute * 60 + second;
    int ts = target.getHour() * 3600 + target.getMinute() * 60 + target.getSecond();
    int seconds = cs - ts;
    if (seconds < 0) {
      seconds += 86400;
      days--;
    }
    seconds += days * 86400;
    return seconds;
  }

  /**
   * 推移
   *
   * @param n 推移秒数
   * @return 公历时刻
   */
  public SolarTime next(int n) {
    if (n == 0) {
      SolarMonth m = day.getMonth();
      return SolarTime.fromYmdHms(m.getYear().getYear(), m.getMonth(), day.getDay(), hour, minute, second);
    }
    int ts = second + n;
    int tm = minute + ts / 60;
    ts %= 60;
    if (ts < 0) {
      ts += 60;
      tm -= 1;
    }
    int th = hour + tm / 60;
    tm %= 60;
    if (tm < 0) {
      tm += 60;
      th -= 1;
    }
    int td = th / 24;
    th %= 24;
    if (th < 0) {
      th += 24;
      td -= 1;
    }

    SolarDay d = day.next(td);
    SolarMonth m = d.getMonth();
    return SolarTime.fromYmdHms(m.getYear().getYear(), m.getMonth(), d.getDay(), th, tm, ts);
  }

  /**
   * 时辰
   *
   * @return 农历时辰
   */
  public LunarHour getLunarHour() {
    LunarDay d = day.getLunarDay();
    LunarMonth m = d.getMonth();
    return LunarHour.fromYmdHms(m.getYear().getYear(), m.getMonthWithLeap(), d.getDay(), hour, minute, second);
  }

}
