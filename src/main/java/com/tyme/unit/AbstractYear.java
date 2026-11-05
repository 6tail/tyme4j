package com.tyme.unit;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;

/**
 * 抽象年
 *
 * @author 6tail
 */
public abstract class AbstractYear extends YearUnit {
  public AbstractYear(int year) {
    super(year);
  }

  /**
   * 月数
   *
   * @return 月数
   */
  public int getMonthCount() {
    return getLeapMonth() < 1 ? 12 : 13;
  }

  /**
   * 闰月
   *
   * @return 闰月数字，1代表闰1月，0代表无闰月
   */
  public int getLeapMonth() {
    return 0;
  }

  /**
   * 月份列表
   *
   * @param factory 工厂
   * @return 月份列表
   */
  protected <T> List<T> buildMonths(IntFunction<T> factory) {
    List<T> l = new ArrayList<>(13);
    int leapMonth = getLeapMonth();
    for (int i = 1; i < 13; i++) {
      l.add(factory.apply(i));
      if (i == leapMonth) {
        l.add(factory.apply(-i));
      }
    }
    return l;
  }

  @Override
  public String getName() {
    return year + "年";
  }

  public abstract AbstractYear next(int n);
}
