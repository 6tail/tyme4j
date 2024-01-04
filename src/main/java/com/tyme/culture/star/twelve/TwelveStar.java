package com.tyme.culture.star.twelve;

import com.tyme.LoopTyme;

/**
 * 黄道黑道十二神
 *
 * @author 6tail
 */
public class TwelveStar extends LoopTyme {

  public static final String[] NAMES = {"青龙", "明堂", "天刑", "朱雀", "金匮", "天德", "白虎", "玉堂", "天牢", "玄武", "司命", "勾陈"};

  protected TwelveStar(int index) {
    super(NAMES, index);
  }

  protected TwelveStar(String name) {
    super(NAMES, name);
  }

  public static TwelveStar fromIndex(int index) {
    return new TwelveStar(index);
  }

  public static TwelveStar fromName(String name) {
    return new TwelveStar(name);
  }

  public TwelveStar next(int n) {
    return fromIndex(nextIndex(n));
  }

  /**
   * 黄道黑道
   *
   * @return 黄道黑道
   */
  public Ecliptic getEcliptic() {
    return Ecliptic.fromIndex(new int[]{0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1}[index]);
  }

}
