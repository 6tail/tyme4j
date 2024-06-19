package com.tyme.culture.plumrain;

import com.tyme.LoopTyme;

/**
 * 梅雨
 *
 * @author 6tail
 */
public class PlumRain extends LoopTyme {

  public static final String[] NAMES = {"入梅", "出梅"};

  public PlumRain(String name) {
    super(NAMES, name);
  }

  public PlumRain(int index) {
    super(NAMES, index);
  }

  /**
   * 从名称初始化
   *
   * @param name 名称
   * @return 梅雨
   */
  public static PlumRain fromName(String name) {
    return new PlumRain(name);
  }

  /**
   * 从索引初始化
   *
   * @param index 索引
   * @return 梅雨
   */
  public static PlumRain fromIndex(int index) {
    return new PlumRain(index);
  }

  public PlumRain next(int n) {
    return fromIndex(nextIndex(n));
  }

}
