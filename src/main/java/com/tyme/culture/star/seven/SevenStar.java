package com.tyme.culture.star.seven;

import com.tyme.LoopTyme;
import com.tyme.culture.Week;

/**
 * 七曜（七政、七纬、七耀）
 *
 * @author 6tail
 */
public class SevenStar extends LoopTyme {

  public static final String[] NAMES = {"日", "月", "火", "水", "木", "金", "土"};

  protected SevenStar(int index) {
    super(NAMES, index);
  }

  protected SevenStar(String name) {
    super(NAMES, name);
  }

  public static SevenStar fromIndex(int index) {
    return new SevenStar(index);
  }

  public static SevenStar fromName(String name) {
    return new SevenStar(name);
  }

  public SevenStar next(int n) {
    return fromIndex(nextIndex(n));
  }

  /**
   * 星期
   *
   * @return 星期
   */
  public Week getWeek() {
    return Week.fromIndex(index);
  }

}
