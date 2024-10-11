package com.tyme.culture.ren;

import com.tyme.LoopTyme;
import com.tyme.culture.Element;
import com.tyme.culture.Luck;

/**
 * 小六壬
 *
 * @author 6tail
 */
public class MinorRen extends LoopTyme {

  public static final String[] NAMES = {"大安", "留连", "速喜", "赤口", "小吉", "空亡"};

  public MinorRen(int index) {
    super(NAMES, index);
  }

  public MinorRen(String name) {
    super(NAMES, name);
  }

  public static MinorRen fromIndex(int index) {
    return new MinorRen(index);
  }

  public static MinorRen fromName(String name) {
    return new MinorRen(name);
  }

  public MinorRen next(int n) {
    return fromIndex(nextIndex(n));
  }

  /**
   * 吉凶
   *
   * @return 吉凶
   */
  public Luck getLuck() {
    return Luck.fromIndex(index % 2);
  }

  /**
   * 五行
   *
   * @return 五行
   */
  public Element getElement() {
    return Element.fromIndex(new int[]{0, 4, 1, 3, 0, 2}[index]);
  }

}
