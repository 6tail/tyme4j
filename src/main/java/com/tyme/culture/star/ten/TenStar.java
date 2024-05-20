package com.tyme.culture.star.ten;

import com.tyme.LoopTyme;

/**
 * 十神
 *
 * @author 6tail
 */
public class TenStar extends LoopTyme {

  public static final String[] NAMES = {"比肩", "劫财", "食神", "伤官", "偏财", "正财", "七杀", "正官", "偏印", "正印"};

  public TenStar(int index) {
    super(NAMES, index);
  }

  public TenStar(String name) {
    super(NAMES, name);
  }

  public static TenStar fromIndex(int index) {
    return new TenStar(index);
  }

  public static TenStar fromName(String name) {
    return new TenStar(name);
  }

  public TenStar next(int n) {
    return fromIndex(nextIndex(n));
  }

}
