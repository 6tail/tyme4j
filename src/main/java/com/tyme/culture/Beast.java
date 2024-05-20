package com.tyme.culture;

import com.tyme.LoopTyme;

/**
 * 神兽
 *
 * @author 6tail
 */
public class Beast extends LoopTyme {

  public static final String[] NAMES = {"青龙", "玄武", "白虎", "朱雀"};

  public Beast(int index) {
    super(NAMES, index);
  }

  public Beast(String name) {
    super(NAMES, name);
  }

  public static Beast fromIndex(int index) {
    return new Beast(index);
  }

  public static Beast fromName(String name) {
    return new Beast(name);
  }

  public Beast next(int n) {
    return fromIndex(nextIndex(n));
  }

  /**
   * 宫
   *
   * @return 宫
   */
  public Zone getZone() {
    return Zone.fromIndex(index);
  }

}
