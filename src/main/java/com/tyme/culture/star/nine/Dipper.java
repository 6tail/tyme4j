package com.tyme.culture.star.nine;

import com.tyme.LoopTyme;

/**
 * 北斗九星
 *
 * @author 6tail
 */
public class Dipper extends LoopTyme {

  public static final String[] NAMES = {"天枢", "天璇", "天玑", "天权", "玉衡", "开阳", "摇光", "洞明", "隐元"};

  protected Dipper(int index) {
    super(NAMES, index);
  }

  protected Dipper(String name) {
    super(NAMES, name);
  }

  public static Dipper fromIndex(int index) {
    return new Dipper(index);
  }

  public static Dipper fromName(String name) {
    return new Dipper(name);
  }

  public Dipper next(int n) {
    return fromIndex(nextIndex(n));
  }

}
