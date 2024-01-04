package com.tyme.culture.fetus;

import com.tyme.LoopTyme;

/**
 * 地支六甲胎神（《地支六甲胎神歌》子午二日碓须忌，丑未厕道莫修移。寅申火炉休要动，卯酉大门修当避。辰戌鸡栖巳亥床，犯着六甲身堕胎。）
 *
 * @author 6tail
 */
public class FetusEarthBranch extends LoopTyme {

  public static final String[] NAMES = {"碓", "厕", "炉", "门", "栖", "床"};

  protected FetusEarthBranch(int index) {
    super(NAMES, index);
  }

  public FetusEarthBranch next(int n) {
    return new FetusEarthBranch(nextIndex(n));
  }

}
