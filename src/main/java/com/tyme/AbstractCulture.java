package com.tyme;

/**
 * 传统文化抽象
 *
 * @author 6tail
 */
public abstract class AbstractCulture implements Culture {

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
    int i = index % size;
    if (i < 0) {
      i += size;
    }
    return i;
  }

}
