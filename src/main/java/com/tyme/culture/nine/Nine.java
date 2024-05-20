package com.tyme.culture.nine;

import com.tyme.LoopTyme;

/**
 * 数九
 *
 * @author 6tail
 */
public class Nine extends LoopTyme {

  public static final String[] NAMES = {"一九", "二九", "三九", "四九", "五九", "六九", "七九", "八九", "九九"};

  public Nine(String name) {
    super(NAMES, name);
  }

  public Nine(int index) {
    super(NAMES, index);
  }

  /**
   * 从名称初始化
   *
   * @param name 名称
   * @return 数九
   */
  public static Nine fromName(String name) {
    return new Nine(name);
  }

  /**
   * 从索引初始化
   *
   * @param index 索引
   * @return 数九
   */
  public static Nine fromIndex(int index) {
    return new Nine(index);
  }

  public Nine next(int n) {
    return fromIndex(nextIndex(n));
  }

}
