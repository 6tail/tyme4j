package com.tyme.culture;

import com.tyme.LoopTyme;

/**
 * 运（20年=1运，3运=1元）
 *
 * @author 6tail
 */
public class Twenty extends LoopTyme {

  public static final String[] NAMES = {"一运", "二运", "三运", "四运", "五运", "六运", "七运", "八运", "九运"};

  protected Twenty(int index) {
    super(NAMES, index);
  }

  protected Twenty(String name) {
    super(NAMES, name);
  }

  public static Twenty fromIndex(int index) {
    return new Twenty(index);
  }

  public static Twenty fromName(String name) {
    return new Twenty(name);
  }

  public Twenty next(int n) {
    return fromIndex(nextIndex(n));
  }

  /**
   * 元
   * @return 元
   */
  public Sixty getSixty() {
    return Sixty.fromIndex(index / 3);
  }

}
