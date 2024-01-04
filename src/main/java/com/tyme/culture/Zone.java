package com.tyme.culture;

import com.tyme.LoopTyme;

/**
 * 宫
 *
 * @author 6tail
 */
public class Zone extends LoopTyme {

  public static final String[] NAMES = {"东", "北", "西", "南"};

  protected Zone(int index) {
    super(NAMES, index);
  }

  protected Zone(String name) {
    super(NAMES, name);
  }

  public static Zone fromIndex(int index) {
    return new Zone(index);
  }

  public static Zone fromName(String name) {
    return new Zone(name);
  }

  public Zone next(int n) {
    return fromIndex(nextIndex(n));
  }

  /**
   * 方位
   *
   * @return 方位
   */
  public Direction getDirection() {
    return Direction.fromName(getName());
  }

  /**
   * 神兽
   *
   * @return 神兽
   */
  public Beast getBeast() {
    return Beast.fromIndex(index);
  }

}
