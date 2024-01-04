package com.tyme.culture;

import com.tyme.LoopTyme;

/**
 * 地势(长生十二神)
 *
 * @author 6tail
 */
public class Terrain extends LoopTyme {

  public static final String[] NAMES = {"长生", "沐浴", "冠带", "临官", "帝旺", "衰", "病", "死", "墓", "绝", "胎", "养"};

  protected Terrain(int index) {
    super(NAMES, index);
  }

  protected Terrain(String name) {
    super(NAMES, name);
  }

  public static Terrain fromIndex(int index) {
    return new Terrain(index);
  }

  public static Terrain fromName(String name) {
    return new Terrain(name);
  }

  public Terrain next(int n) {
    return fromIndex(nextIndex(n));
  }

}
