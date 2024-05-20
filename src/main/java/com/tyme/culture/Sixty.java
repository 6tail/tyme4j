package com.tyme.culture;

import com.tyme.LoopTyme;

/**
 * 元（60年=1元）
 *
 * @author 6tail
 */
public class Sixty extends LoopTyme {

  public static final String[] NAMES = {"上元", "中元", "下元"};

  public Sixty(int index) {
    super(NAMES, index);
  }

  public Sixty(String name) {
    super(NAMES, name);
  }

  public static Sixty fromIndex(int index) {
    return new Sixty(index);
  }

  public static Sixty fromName(String name) {
    return new Sixty(name);
  }

  public Sixty next(int n) {
    return fromIndex(nextIndex(n));
  }

}
