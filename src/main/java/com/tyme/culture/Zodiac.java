package com.tyme.culture;

import com.tyme.LoopTyme;
import com.tyme.sixtycycle.EarthBranch;

/**
 * 生肖
 *
 * @author liuweij
 */
public class Zodiac extends LoopTyme {

  public static final String[] NAMES = {"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};

  protected Zodiac(int index) {
    super(NAMES, index);
  }

  protected Zodiac(String name) {
    super(NAMES, name);
  }

  public static Zodiac fromIndex(int index) {
    return new Zodiac(index);
  }

  public static Zodiac fromName(String name) {
    return new Zodiac(name);
  }

  public Zodiac next(int n) {
    return fromIndex(nextIndex(n));
  }

  /**
   * 地支
   *
   * @return 地支
   */
  public EarthBranch getEarthBranch() {
    return EarthBranch.fromIndex(index);
  }

}
