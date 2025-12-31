package com.tyme.solar;

import com.tyme.culture.Phase;
import com.tyme.culture.phenology.Phenology;
import com.tyme.jd.JulianDay;
import com.tyme.lunar.LunarDay;
import com.tyme.lunar.LunarHour;
import com.tyme.lunar.LunarMonth;
import com.tyme.sixtycycle.SixtyCycleHour;
import com.tyme.unit.SecondUnit;

/**
 * 公历时刻
 *
 * @author 6tail
 */
public class SolarTime extends SecondUnit {

  public static void validate(int year, int month, int day, int hour, int minute, int second) {
    SecondUnit.validate(hour, minute, second);
    SolarDay.validate(year, month, day);
  }

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
    validate(year, month, day, hour, minute, second);
    this.year = year;
    this.month = month;
    this.day = day;
    this.hour = hour;
    this.minute = minute;
    this.second = second;
  }

  public static SolarTime fromYmdHms(int year, int month, int day, int hour, int minute, int second) {
    return new SolarTime(year, month, day, hour, minute, second);
  }

  /**
   * 公历日
   *
   * @return 公历日
   */
  public SolarDay getSolarDay() {
    return SolarDay.fromYmd(year, month, day);
  }

  public String getName() {
    return String.format("%02d:%02d:%02d", hour, minute, second);
  }

  @Override
  public String toString() {
    return String.format("%s %s", getSolarDay(), getName());
  }

  /**
   * 是否在指定公历时刻之前
   *
   * @param target 公历时刻
   * @return true/false
   */
  public boolean isBefore(SolarTime target) {
    SolarDay d = getSolarDay();
    if (!d.equals(target.getSolarDay())) {
      return d.isBefore(target.getSolarDay());
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
    SolarDay d = getSolarDay();
    if (!d.equals(target.getSolarDay())) {
      return d.isAfter(target.getSolarDay());
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
    SolarTerm term = getSolarDay().getTerm();
    if (isBefore(term.getJulianDay().getSolarTime())) {
      term = term.next(-1);
    }
    return term;
  }

  /**
   * 候
   *
   * @return 候
   */
  public Phenology getPhenology() {
    Phenology p = getSolarDay().getPhenology();
    if (isBefore(p.getJulianDay().getSolarTime())) {
      p = p.next(-1);
    }
    return p;
  }

  /**
   * 儒略日
   *
   * @return 儒略日
   */
  public JulianDay getJulianDay() {
    return JulianDay.fromYmdHms(year, month, day, hour, minute, second);
  }

  /**
   * 公历时刻相减，获得相差秒数
   *
   * @param target 公历时刻
   * @return 秒数
   */
  public int subtract(SolarTime target) {
    int days = getSolarDay().subtract(target.getSolarDay());
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
      return SolarTime.fromYmdHms(year, month, day, hour, minute, second);
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

    SolarDay d = getSolarDay().next(td);
    return SolarTime.fromYmdHms(d.getYear(), d.getMonth(), d.getDay(), th, tm, ts);
  }

  /**
   * 农历时辰
   *
   * @return 农历时辰
   */
  public LunarHour getLunarHour() {
    LunarDay d = getSolarDay().getLunarDay();
    return LunarHour.fromYmdHms(d.getYear(), d.getMonth(), d.getDay(), hour, minute, second);
  }

  /**
   * 干支时辰
   *
   * @return 干支时辰
   */
  public SixtyCycleHour getSixtyCycleHour() {
    return SixtyCycleHour.fromSolarTime(this);
  }

  /**
   * 月相
   *
   * @return 月相
   */
  public Phase getPhase() {
    LunarMonth month = getLunarHour().getLunarDay().getLunarMonth().next(1);
    Phase p = Phase.fromIndex(month.getYear(), month.getMonthWithLeap(), 0);
    while (p.getSolarTime().isAfter(this)) {
      p = p.next(-1);
    }
    return p;
  }

}
