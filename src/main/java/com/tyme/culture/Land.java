package com.tyme.culture;

import com.tyme.LoopTyme;

/**
 * 九野
 *
 * @author 6tail
 */
public class Land extends LoopTyme {

  public static final String[] NAMES = {"玄天", "朱天", "苍天", "阳天", "钧天", "幽天", "颢天", "变天", "炎天"};

  public Land(int index) {
    super(NAMES, index);
  }

  public Land(String name) {
    super(NAMES, name);
  }

  public static Land fromIndex(int index) {
    return new Land(index);
  }

  public static Land fromName(String name) {
    return new Land(name);
  }

  public Land next(int n) {
    return fromIndex(nextIndex(n));
  }

  /**
   * 方位
   *
   * @return 方位
   */
  public Direction getDirection() {
    return Direction.fromIndex(index);
  }

}
