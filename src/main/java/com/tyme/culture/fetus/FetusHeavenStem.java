package com.tyme.culture.fetus;

import com.tyme.LoopTyme;

/**
 * 天干六甲胎神（《天干六甲胎神歌》甲己之日占在门，乙庚碓磨休移动。丙辛厨灶莫相干，丁壬仓库忌修弄。戊癸房床若移整，犯之孕妇堕孩童。）
 *
 * @author 6tail
 */
public class FetusHeavenStem extends LoopTyme {

  public static final String[] NAMES = {"门", "碓磨", "厨灶", "仓库", "房床"};

  protected FetusHeavenStem(int index) {
    super(NAMES, index);
  }

  public FetusHeavenStem next(int n) {
    return new FetusHeavenStem(nextIndex(n));
  }

}
