package com.tyme.culture;

import com.tyme.LoopTyme;

/**
 * 方位
 *
 * @author 6tail
 */
public class Direction extends LoopTyme {

  /**
   * 依据后天八卦排序（0坎北, 1坤西南, 2震东, 3巽东南, 4中, 5乾西北, 6兑西, 7艮东北, 8离南）
   */
  public static final String[] NAMES = {"北", "西南", "东", "东南", "中", "西北", "西", "东北", "南"};

  public Direction(int index) {
    super(NAMES, index);
  }

  public Direction(String name) {
    super(NAMES, name);
  }

  public static Direction fromIndex(int index) {
    return new Direction(index);
  }

  public static Direction fromName(String name) {
    return new Direction(name);
  }

  public Direction next(int n) {
    return fromIndex(nextIndex(n));
  }

  /**
   * 九野
   *
   * @return 九野
   */
  public Land getLand() {
    return Land.fromIndex(index);
  }

  /**
   * 五行
   *
   * @return 五行
   */
  public Element getElement() {
    return Element.fromIndex(new int[]{4, 2, 0, 0, 2, 3, 3, 2, 1}[index]);
  }

}
