package com.tyme.culture.star.six;

import com.tyme.LoopTyme;

/**
 * 六曜（孔明六曜星、小六壬）
 *
 * @author 6tail
 */
public class SixStar extends LoopTyme {

  public static final String[] NAMES = {"先胜", "友引", "先负", "佛灭", "大安", "赤口"};

  public SixStar(int index) {
    super(NAMES, index);
  }

  public SixStar(String name) {
    super(NAMES, name);
  }

  public static SixStar fromIndex(int index) {
    return new SixStar(index);
  }

  public static SixStar fromName(String name) {
    return new SixStar(name);
  }

  public SixStar next(int n) {
    return fromIndex(nextIndex(n));
  }

}
