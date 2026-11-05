package com.tyme;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;

/**
 * 传统文化抽象
 *
 * @author 6tail
 */
public abstract class AbstractCulture implements Culture {

  @Override
  public String getName() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String toString() {
    return getName();
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof Culture && toString().equals(o.toString());
  }

  /**
   * 转换为不超范围的索引
   *
   * @param index 索引
   * @param size  数量
   * @return 索引，从0开始
   */
  protected int indexOf(int index, int size) {
    return Math.floorMod(index, size);
  }

  /**
   * 构建列表
   *
   * @param size   大小
   * @param factory 工厂
   * @param <T>    类型
   * @return 列表
   */
  protected static <T> List<T> buildList(int size, IntFunction<T> factory) {
    List<T> l = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      l.add(factory.apply(i));
    }
    return l;
  }

  /**
   * 校验值是否在指定范围内
   *
   * @param value 待校验的值
   * @param min   最小值（含）
   * @param max   最大值（含）
   * @param field 字段名称，用于异常提示
   */
  protected static void validateRange(int value, int min, int max, String field) {
    if (value < min || value > max) {
      throw new IllegalArgumentException(String.format("illegal %s: %d", field, value));
    }
  }
}
