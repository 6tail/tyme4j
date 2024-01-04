package com.tyme.culture.star.twelve;

import com.tyme.LoopTyme;
import com.tyme.culture.Luck;

/**
 * 黄道黑道
 *
 * @author 6tail
 */
public class Ecliptic extends LoopTyme {

  public static final String[] NAMES = {"黄道", "黑道"};

  protected Ecliptic(int index) {
    super(NAMES, index);
  }

  protected Ecliptic(String name) {
    super(NAMES, name);
  }

  public static Ecliptic fromIndex(int index) {
    return new Ecliptic(index);
  }

  public static Ecliptic fromName(String name) {
    return new Ecliptic(name);
  }

  public Ecliptic next(int n) {
    return fromIndex(nextIndex(n));
  }

  /**
   * 吉凶
   *
   * @return 吉凶
   */
  public Luck getLuck() {
    return Luck.fromIndex(index);
  }

}
