package com.tyme.culture;

import com.tyme.LoopTyme;

/**
 * 五行
 *
 * @author 6tail
 */
public class Element extends LoopTyme {

  public static final String[] NAMES = {"木", "火", "土", "金", "水"};

  public Element(int index) {
    super(NAMES, index);
  }

  public Element(String name) {
    super(NAMES, name);
  }

  public static Element fromIndex(int index) {
    return new Element(index);
  }

  public static Element fromName(String name) {
    return new Element(name);
  }

  public Element next(int n) {
    return fromIndex(nextIndex(n));
  }

  /**
   * 我生者
   *
   * @return 五行
   */
  public Element getReinforce() {
    return next(1);
  }

  /**
   * 我克者
   *
   * @return 五行
   */
  public Element getRestrain() {
    return next(2);
  }

  /**
   * 生我者
   *
   * @return 五行
   */
  public Element getReinforced() {
    return next(-1);
  }

  /**
   * 克我者
   *
   * @return 五行
   */
  public Element getRestrained() {
    return next(-2);
  }

  /**
   * 方位
   *
   * @return 方位
   */
  public Direction getDirection() {
    return Direction.fromIndex(new int[]{2, 8, 4, 6, 0}[index]);
  }
}
