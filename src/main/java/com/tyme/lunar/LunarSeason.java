package com.tyme.lunar;

import com.tyme.LoopTyme;

/**
 * 农历季节
 *
 * @author 6tail
 */
public class LunarSeason extends LoopTyme {

  public static final String[] NAMES = {"孟春", "仲春", "季春", "孟夏", "仲夏", "季夏", "孟秋", "仲秋", "季秋", "孟冬", "仲冬", "季冬"};

  public LunarSeason(int index) {
    super(NAMES, index);
  }

  public LunarSeason(String name) {
    super(NAMES, name);
  }

  public static LunarSeason fromIndex(int index) {
    return new LunarSeason(index);
  }

  public static LunarSeason fromName(String name) {
    return new LunarSeason(name);
  }

  public LunarSeason next(int n) {
    return fromIndex(nextIndex(n));
  }
}
