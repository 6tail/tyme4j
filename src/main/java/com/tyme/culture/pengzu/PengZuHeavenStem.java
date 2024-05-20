package com.tyme.culture.pengzu;

import com.tyme.LoopTyme;

/**
 * 天干彭祖百忌
 *
 * @author 6tail
 */
public class PengZuHeavenStem extends LoopTyme {

  public static final String[] NAMES = {"甲不开仓财物耗散", "乙不栽植千株不长", "丙不修灶必见灾殃", "丁不剃头头必生疮", "戊不受田田主不祥", "己不破券二比并亡", "庚不经络织机虚张", "辛不合酱主人不尝", "壬不泱水更难提防", "癸不词讼理弱敌强"};

  public PengZuHeavenStem(String name) {
    super(NAMES, name);
  }

  public PengZuHeavenStem(int index) {
    super(NAMES, index);
  }

  /**
   * 从名称初始化
   *
   * @param name 名称
   * @return 天干彭祖百忌
   */
  public static PengZuHeavenStem fromName(String name) {
    return new PengZuHeavenStem(name);
  }

  /**
   * 从索引初始化
   *
   * @param index 索引
   * @return 天干彭祖百忌
   */
  public static PengZuHeavenStem fromIndex(int index) {
    return new PengZuHeavenStem(index);
  }

  public PengZuHeavenStem next(int n) {
    return new PengZuHeavenStem(nextIndex(n));
  }

}
