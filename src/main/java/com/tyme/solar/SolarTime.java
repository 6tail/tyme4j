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
    return getCompareIndex() < target.getCompareIndex();
  }

  /**
   * 是否在指定公历时刻之后
   *
   * @param target 公历时刻
   * @return true/false
   */
  public boolean isAfter(SolarTime target) {
    return getCompareIndex() > target.getCompareIndex();
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
    long t = getSolarDay().subtract(target.getSolarDay()) * 86400L + getSecondsInDay() - target.getSecondsInDay();
    if (t < Integer.MIN_VALUE || t > Integer.MAX_VALUE) {
      throw new ArithmeticException("seconds difference exceeds int range: " + t);
    }
    return (int)t;
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
    long t = hour * 3600L + minute * 60L + second + n;
    int s = (int)Math.floorMod(t, 86400);
    SolarDay d = getSolarDay().next((int)Math.floorDiv(t, 86400));
    return SolarTime.fromYmdHms(d.getYear(), d.getMonth(), d.getDay(), s / 3600, (s % 3600) / 60, s % 60);
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
