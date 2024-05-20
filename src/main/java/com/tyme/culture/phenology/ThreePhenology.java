package com.tyme.culture.phenology;

import com.tyme.LoopTyme;

/**
 * 三候
 *
 * @author 6tail
 */
public class ThreePhenology extends LoopTyme {

  public static final String[] NAMES = {"初候", "二候", "三候"};

  public ThreePhenology(String name) {
    super(NAMES, name);
  }

  public ThreePhenology(int index) {
    super(NAMES, index);
  }

  /**
   * 从名称初始化
   *
   * @param name 名称
   * @return 三候
   */
  public static ThreePhenology fromName(String name) {
    return new ThreePhenology(name);
  }

  /**
   * 从索引初始化
   *
   * @param index 索引
   * @return 三候
   */
  public static ThreePhenology fromIndex(int index) {
    return new ThreePhenology(index);
  }

  public ThreePhenology next(int n) {
    return fromIndex(nextIndex(n));
  }

}
