package com.tyme.culture;

import com.tyme.LoopTyme;
import com.tyme.culture.star.seven.SevenStar;

/**
 * 星期
 *
 * @author 6tail
 */
public class Week extends LoopTyme {

  public static final String[] NAMES = {"日", "一", "二", "三", "四", "五", "六"};

  protected Week(int index) {
    super(NAMES, index);
  }

  protected Week(String name) {
    super(NAMES, name);
  }

  public static Week fromIndex(int index) {
    return new Week(index);
  }

  public static Week fromName(String name) {
    return new Week(name);
  }

  public Week next(int n) {
    return fromIndex(nextIndex(n));
  }

  /**
   * 七曜
   *
   * @return 七曜
   */
  public SevenStar getSevenStar() {
    return SevenStar.fromIndex(index);
  }

}
