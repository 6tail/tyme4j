package com.tyme.culture;

import com.tyme.LoopTyme;

/**
 * 吉凶
 *
 * @author 6tail
 */
public class Luck extends LoopTyme {

  public static final String[] NAMES = {"吉", "凶"};

  protected Luck(int index) {
    super(NAMES, index);
  }

  protected Luck(String name) {
    super(NAMES, name);
  }

  public static Luck fromIndex(int index) {
    return new Luck(index);
  }

  public static Luck fromName(String name) {
    return new Luck(name);
  }

  public Luck next(int n) {
    return fromIndex(nextIndex(n));
  }

}
