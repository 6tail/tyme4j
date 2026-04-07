package com.tyme;

/**
 * 可轮回的Tyme
 *
 * @author 6tail
 */
public abstract class LoopTyme extends AbstractTyme {

  /**
   * 名称列表
   */
  protected String[] names;

  /**
   * 索引，从0开始
   */
  protected int index;

  /**
   * 通过索引初始化
   *
   * @param names 名称列表
   * @param index 索引，支持负数，自动轮转
   */
  protected LoopTyme(String[] names, int index) {
    this.names = names;
    this.index = indexOf(index);
  }

  /**
   * 通过名称初始化
   *
   * @param names 名称列表
   * @param name  名称
   */
  protected LoopTyme(String[] names, String name) {
    this.names = names;
    this.index = indexOf(name);
  }

  /**
   * 名称
   *
   * @return 名称
   */
  public String getName() {
    return names[index];
  }

  /**
   * 索引
   *
   * @return 索引，从0开始
   */
  public int getIndex() {
    return index;
  }

  /**
   * 数量
   *
   * @return 数量
   */
  public int getSize() {
    return names.length;
  }

  /**
   * 名称对应的索引
   *
   * @param name 名称
   * @return 索引，从0开始
   */
  protected int indexOf(String name) {
    for (int i = 0, j = getSize(); i < j; i++) {
      if (names[i].equals(name)) {
        return i;
      }
    }
    throw new IllegalArgumentException("illegal name: " + name);
  }

  /**
   * 转换为不超范围的索引
   *
   * @param index 索引
   * @return 索引，从0开始
   */
  protected int indexOf(int index) {
    return indexOf(index, getSize());
  }

  /**
   * 推移后的索引
   *
   * @param n 推移步数
   * @return 索引，从0开始
   */
  protected int nextIndex(int n) {
    return indexOf(index + n);
  }

  /**
   * 到目标索引的步数（从左往右顺序）
   *
   * @param targetIndex 目标索引
   * @return 步数（>=0）
   */
  public int stepsTo(int targetIndex) {
    return indexOf(targetIndex - index);
  }

  /**
   * 到目标索引的步数（从右往左逆序）
   *
   * @param targetIndex 目标索引
   * @return 步数（<=0）
   */
  public int stepsBackTo(int targetIndex) {
    int n = getSize();
    return -((index - targetIndex + n) % n);
  }

  /**
   * 到目标索引的最少步数
   *
   * @param targetIndex 目标索引
   * @return 步数（从左往右顺序>=0，从右往左逆序<=0）
   */
  public int stepsCloseTo(int targetIndex) {
    int d1 = stepsTo(targetIndex);
    int d2 = stepsBackTo(targetIndex);
    return d1 <= Math.abs(d2) ? d1 : d2;
  }

}
