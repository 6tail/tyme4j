package com.tyme.culture.star.nine;

import com.tyme.LoopTyme;
import com.tyme.culture.Direction;
import com.tyme.culture.Element;

/**
 * 九星
 *
 * @author 6tail
 */
public class NineStar extends LoopTyme {

  public static final String[] NAMES = {"一", "二", "三", "四", "五", "六", "七", "八", "九"};

  public NineStar(int index) {
    super(NAMES, index);
  }

  public NineStar(String name) {
    super(NAMES, name);
  }

  public static NineStar fromIndex(int index) {
    return new NineStar(index);
  }

  public static NineStar fromName(String name) {
    return new NineStar(name);
  }

  public NineStar next(int n) {
    return fromIndex(nextIndex(n));
  }

  /**
   * 颜色
   *
   * @return 颜色
   */
  public String getColor() {
    return new String[]{"白", "黑", "碧", "绿", "黄", "白", "赤", "白", "紫"}[index];
  }

  /**
   * 五行
   *
   * @return 五行
   */
  public Element getElement() {
    return Element.fromIndex(new int[]{4, 2, 0, 0, 2, 3, 3, 2, 1}[index]);
  }

  /**
   * 北斗九星
   *
   * @return 北斗九星
   */
  public Dipper getDipper() {
    return Dipper.fromIndex(index);
  }

  /**
   * 方位
   *
   * @return 方位
   */
  public Direction getDirection() {
    return Direction.fromIndex(index);
  }

  @Override
  public String toString() {
    return getName() + getColor() + getElement();
  }

}
