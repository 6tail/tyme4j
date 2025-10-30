package com.tyme.solar;

import com.tyme.LoopTyme;
import com.tyme.jd.JulianDay;
import com.tyme.util.ShouXingUtil;

/**
 * 节气
 *
 * @author 6tail
 */
public class SolarTerm extends LoopTyme {

  public static final String[] NAMES = {"冬至", "小寒", "大寒", "立春", "雨水", "惊蛰", "春分", "清明", "谷雨", "立夏", "小满", "芒种", "夏至", "小暑", "大暑", "立秋", "处暑", "白露", "秋分", "寒露", "霜降", "立冬", "小雪", "大雪"};

  /**
   * 年
   */
  protected int year;

  /**
   * 儒略日（用于日历，只精确到日中午12:00）
   */
  protected double cursoryJulianDay;

  public SolarTerm(int year, int index) {
    super(NAMES, index);
    int size = getSize();
    initByYear((year * size + index) / size, getIndex());
  }

  public SolarTerm(int year, String name) {
    super(NAMES, name);
    initByYear(year, index);
  }

  protected void initByYear(int year, int offset) {
    double jd = Math.floor((year - 2000) * 365.2422 + 180);
    // 355是2000.12冬至，得到较靠近jd的冬至估计值
    double w = Math.floor((jd - 355 + 183) / 365.2422) * 365.2422 + 355;
    if (ShouXingUtil.calcQi(w) > jd) {
      w -= 365.2422;
    }
    this.year = year;
    cursoryJulianDay = ShouXingUtil.calcQi(w + 15.2184 * offset);
  }

  public static SolarTerm fromIndex(int year, int index) {
    return new SolarTerm(year, index);
  }

  public static SolarTerm fromName(int year, String name) {
    return new SolarTerm(year, name);
  }

  public SolarTerm next(int n) {
    int size = getSize();
    int i = index + n;
    return fromIndex((year * size + i) / size, indexOf(i));
  }

  /**
   * 是否节令
   *
   * @return true/false
   */
  public boolean isJie() {
    return index % 2 == 1;
  }

  /**
   * 是否气令
   *
   * @return true/false
   */
  public boolean isQi() {
    return index % 2 == 0;
  }

  /**
   * 儒略日（精确到秒）
   *
   * @return 儒略日
   */
  public JulianDay getJulianDay() {
    return JulianDay.fromJulianDay(ShouXingUtil.qiAccurate2(cursoryJulianDay) + JulianDay.J2000);
  }

  /**
   * 公历日（用于日历）
   *
   * @return 公历日
   */
  public SolarDay getSolarDay() {
    return JulianDay.fromJulianDay(cursoryJulianDay + JulianDay.J2000).getSolarDay();
  }

  /**
   * 年
   *
   * @return 年
   */
  public int getYear() {
    return year;
  }

  /**
   * 儒略日（用于日历，只精确到日中午12:00）
   *
   * @return 儒略日数
   */
  public double getCursoryJulianDay() {
    return cursoryJulianDay;
  }

}
