package com.tyme.hijri;

import com.tyme.unit.MonthUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * 回历月
 *
 * @author 6tail
 */
public class HijriMonth extends MonthUnit {

  public static final String[] NAMES = {"穆哈兰姆月", "色法尔月", "赖比尔·敖外鲁月", "赖比尔·阿色尼月", "主马达·敖外鲁月", "主马达·阿色尼月", "赖哲卜月", "舍尔邦月", "赖买丹月", "闪瓦鲁月", "都尔喀尔德月", "都尔黑哲月"};

  public static void validate(int year, int month) {
    validateRange(month, 1, 12, "hijri month");
    HijriYear.validate(year);
  }

  /**
   * 初始化
   *
   * @param year  年
   * @param month 月
   */
  public HijriMonth(int year, int month) {
    validate(year, month);
    this.year = year;
    this.month = month;
  }

  public static HijriMonth fromYm(int year, int month) {
    return new HijriMonth(year, month);
  }

  /**
   * 回历年
   *
   * @return 回历年
   */
  public HijriYear getHijriYear() {
    return new HijriYear(year);
  }

  /**
   * 天数（单数月30天，双数月29天，闰年第12月30天)
   *
   * @return 天数
   */
  public int getDayCount() {
    int d = month % 2 == 0 ? 29 : 30;
    // 闰年第12月30天
    if (12 == month && getHijriYear().isLeap()) {
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

  public String getName() {
    return NAMES[getIndexInYear()];
  }

  @Override
  public String toString() {
    return getHijriYear() + getName();
  }

  public HijriMonth next(int n) {
    int i = month - 1 + n;
    return fromYm((year * 12 + i) / 12, indexOf(i, 12) + 1);
  }

  /**
   * 本月的回历日列表
   *
   * @return 回历日列表
   */
  public List<HijriDay> getDays() {
    int size = getDayCount();
    List<HijriDay> l = new ArrayList<>(size);
    for (int i = 1; i <= size; i++) {
      l.add(new HijriDay(year, month, i));
    }
    return l;
  }

  /**
   * 首日
   *
   * @return 回历日
   */
  public HijriDay getFirstDay() {
    return new HijriDay(year, month, 1);
  }
}
