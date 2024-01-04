package com.tyme.culture;

import com.tyme.LoopTyme;

/**
 * 星座
 *
 * @author liuweij
 */
public class Constellation extends LoopTyme {

  public static final String[] NAMES = {"白羊", "金牛", "双子", "巨蟹", "狮子", "处女", "天秤", "天蝎", "射手", "摩羯", "水瓶", "双鱼"};

  protected Constellation(int index) {
    super(NAMES, index);
  }

  protected Constellation(String name) {
    super(NAMES, name);
  }

  public static Constellation fromIndex(int index) {
    return new Constellation(index);
  }

  public static Constellation fromName(String name) {
    return new Constellation(name);
  }

  public Constellation next(int n) {
    return fromIndex(nextIndex(n));
  }

}
