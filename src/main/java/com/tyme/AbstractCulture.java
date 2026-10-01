package com.tyme;

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
