package com.tyme.culture;

import com.tyme.LoopTyme;

/**
 * 建除十二值神
 *
 * @author 6tail
 */
public class Duty extends LoopTyme {

  public static final String[] NAMES = {"建", "除", "满", "平", "定", "执", "破", "危", "成", "收", "开", "闭"};

  protected Duty(int index) {
    super(NAMES, index);
  }

  protected Duty(String name) {
    super(NAMES, name);
  }

  public static Duty fromIndex(int index) {
    return new Duty(index);
  }

  public static Duty fromName(String name) {
    return new Duty(name);
  }

  public Duty next(int n) {
    return fromIndex(nextIndex(n));
  }

}
