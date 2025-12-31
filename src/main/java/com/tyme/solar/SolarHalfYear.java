package com.tyme.solar;

import com.tyme.unit.YearUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * 公历半年
 *
 * @author 6tail
 */
public class SolarHalfYear extends YearUnit {

  public static final String[] NAMES = {"上半年", "下半年"};

  /**
   * 索引，0-1
   */
  protected int index;

  public static void validate(int year, int index) {
    if (index < 0 || index > 1) {
      throw new IllegalArgumentException(String.format("illegal solar half year index: %d", index));
    }
    SolarYear.validate(year);
  }

  /**
   * 初始化
   *
   * @param year  年
   * @param index 索引，0-1
   */
  public SolarHalfYear(int year, int index) {
    validate(year, index);
    this.year = year;
    this.index = index;
  }

  public static SolarHalfYear fromIndex(int year, int index) {
    return new SolarHalfYear(year, index);
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
   * @return 索引，0-1
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

  public SolarHalfYear next(int n) {
    int i = index + n;
    return fromIndex((year * 2 + i) / 2, indexOf(i, 2));
  }

  /**
   * 月份列表
   *
   * @return 月份列表，半年有6个月。
   */
  public List<SolarMonth> getMonths() {
    List<SolarMonth> l = new ArrayList<>(6);
    for (int i = 1; i < 7; i++) {
      l.add(SolarMonth.fromYm(year, index * 6 + i));
    }
    return l;
  }

  /**
   * 季度列表
   *
   * @return 季度列表，半年有2个季度。
   */
  public List<SolarSeason> getSeasons() {
    List<SolarSeason> l = new ArrayList<>(2);
    for (int i = 0; i < 2; i++) {
      l.add(SolarSeason.fromIndex(year, index * 2 + i));
    }
    return l;
  }
}
