package com.tyme.rabbyung;

import com.tyme.culture.Element;

/**
 * 藏历五行
 *
 * @author 6tail
 */
public class RabByungElement extends Element {

  public RabByungElement(int index) {
    super(index);
  }

  public RabByungElement(String name) {
    super(name.replace("铁", "金"));
  }

  public static RabByungElement fromIndex(int index) {
    return new RabByungElement(index);
  }

  public static RabByungElement fromName(String name) {
    return new RabByungElement(name);
  }

  public RabByungElement next(int n) {
    return fromIndex(nextIndex(n));
  }

  /**
   * 我生者
   *
   * @return 五行
   */
  public RabByungElement getReinforce() {
    return next(1);
  }

  /**
   * 我克者
   *
   * @return 五行
   */
  public RabByungElement getRestrain() {
    return next(2);
  }

  /**
   * 生我者
   *
   * @return 五行
   */
  public RabByungElement getReinforced() {
    return next(-1);
  }

  /**
   * 克我者
   *
   * @return 五行
   */
  public RabByungElement getRestrained() {
    return next(-2);
  }

  @Override
  public String getName() {
    return super.getName().replace("金", "铁");
  }
}
