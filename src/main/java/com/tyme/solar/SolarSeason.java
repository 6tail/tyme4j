package com.tyme.solar;

import com.tyme.unit.YearUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * 公历季度
 *
 * @author 6tail
 */
public class SolarSeason extends YearUnit {

  public static final String[] NAMES = {"一季度", "二季度", "三季度", "四季度"};

  /**
   * 索引，0-3
   */
  protected int index;

  public static void validate(int year, int index) {
    if (index < 0 || index > 3) {
      throw new IllegalArgumentException(String.format("illegal solar season index: %d", index));
    }
    SolarYear.validate(year);
  }

  /**
   * 初始化
   *
   * @param year  年
   * @param index 索引，0-3
   */
  public SolarSeason(int year, int index) {
    validate(year, index);
    this.year = year;
    this.index = index;
  }

  public static SolarSeason fromIndex(int year, int index) {
    return new SolarSeason(year, index);
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
   * 索引
   *
   * @return 索引，0-3
   */
  public int getIndex() {
    return index;
  }

  public String getName() {
    return NAMES[index];
  }

  @Override
  public String toString() {
    return getSolarYear() + getName();
  }

  public SolarSeason next(int n) {
    int i = index + n;
    return fromIndex((year * 4 + i) / 4, indexOf(i, 4));
  }

  /**
   * 月份列表
   *
   * @return 月份列表，1季度有3个月。
   */
  public List<SolarMonth> getMonths() {
    List<SolarMonth> l = new ArrayList<>(3);
    for (int i = 1; i < 4; i++) {
      l.add(SolarMonth.fromYm(year, index * 3 + i));
    }
    return l;
  }
}
