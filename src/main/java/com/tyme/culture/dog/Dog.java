package com.tyme.culture.dog;

import com.tyme.LoopTyme;

/**
 * 三伏
 *
 * @author 6tail
 */
public class Dog extends LoopTyme {

  public static final String[] NAMES = {"初伏", "中伏", "末伏"};

  public Dog(String name) {
    super(NAMES, name);
  }

  public Dog(int index) {
    super(NAMES, index);
  }

  /**
   * 从名称初始化
   *
   * @param name 名称
   * @return 伏
   */
  public static Dog fromName(String name) {
    return new Dog(name);
  }

  /**
   * 从索引初始化
   *
   * @param index 索引
   * @return 伏
   */
  public static Dog fromIndex(int index) {
    return new Dog(index);
  }

  public Dog next(int n) {
    return fromIndex(nextIndex(n));
  }

}
