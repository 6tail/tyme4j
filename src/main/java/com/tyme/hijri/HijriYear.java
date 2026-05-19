package com.tyme.hijri;

import com.tyme.unit.YearUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * 回历年
 *
 * @author 6tail
 */
public class HijriYear extends YearUnit {

  public HijriYear(int year) {
    validate(year);
    this.year = year;
  }

  public static void validate(int year) {
    if (year < -640 || year > 9666) {
      throw new IllegalArgumentException("illegal hijri year: " + year);
    }
  }

  /**
   * 从年初始化
   *
   * @param year 年
   * @return 回历年
   */
  public static HijriYear fromYear(int year) {
    return new HijriYear(year);
  }

  /**
   * 天数（平年354天，闰年355天）
   *
   * @return 天数
   */
  public int getDayCount() {
    return isLeap() ? 355 : 354;
  }

  /**
   * 是否闰年(1个闰周为30年，1个闰周中第2、5、7、10、13、16、18、21、24、26、29年为闰年)
   *
   * @return true/false
   */
  public boolean isLeap() {
    int i = Math.floorMod(year - 1, 30);
    return i == 1 || i == 4 || i == 6 || i == 9 || i == 12 || i == 15 || i == 17 || i == 20 || i == 23 || i == 25 || i == 28;
  }

  public String getName() {
    return year + "年";
  }

  public HijriYear next(int n) {
    return fromYear(year + n);
  }

  /**
   * 月份列表，1年有12个月。
   *
   * @return 回历月列表
   */
  public List<HijriMonth> getMonths() {
    List<HijriMonth> l = new ArrayList<>(12);
    for (int i = 1; i < 13; i++) {
      l.add(new HijriMonth(year, i));
    }
    return l;
  }

  /**
   * 首月
   *
   * @return 回历月
   */
  public HijriMonth getFirstMonth() {
    return new HijriMonth(year, 1);
  }
}
