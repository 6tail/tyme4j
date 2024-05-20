package com.tyme.culture;

import com.tyme.LoopTyme;

/**
 * 旬
 *
 * @author 6tail
 */
public class Ten extends LoopTyme {

  public static final String[] NAMES = {"甲子", "甲戌", "甲申", "甲午", "甲辰", "甲寅"};

  public Ten(int index) {
    super(NAMES, index);
  }

  public Ten(String name) {
    super(NAMES, name);
  }

  public static Ten fromIndex(int index) {
    return new Ten(index);
  }

  public static Ten fromName(String name) {
    return new Ten(name);
  }

  public Ten next(int n) {
    return fromIndex(nextIndex(n));
  }

}
