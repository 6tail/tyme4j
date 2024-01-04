package com.tyme.culture.pengzu;

import com.tyme.AbstractCulture;
import com.tyme.sixtycycle.SixtyCycle;

/**
 * 彭祖百忌
 *
 * @author 6tail
 */
public class PengZu extends AbstractCulture {

  /**
   * 天干彭祖百忌
   */
  protected PengZuHeavenStem pengZuHeavenStem;

  /**
   * 地支彭祖百忌
   */
  protected PengZuEarthBranch pengZuEarthBranch;

  protected PengZu(SixtyCycle sixtyCycle) {
    pengZuHeavenStem = PengZuHeavenStem.fromIndex(sixtyCycle.getHeavenStem().getIndex());
    pengZuEarthBranch = PengZuEarthBranch.fromIndex(sixtyCycle.getEarthBranch().getIndex());
  }

  /**
   * 从干支初始化
   *
   * @param sixtyCycle 干支
   * @return 彭祖百忌
   */
  public static PengZu fromSixtyCycle(SixtyCycle sixtyCycle) {
    return new PengZu(sixtyCycle);
  }

  public String getName() {
    return String.format("%s %s", pengZuHeavenStem, pengZuEarthBranch);
  }

  /**
   * 天干彭祖百忌
   *
   * @return 天干彭祖百忌
   */
  public PengZuHeavenStem getPengZuHeavenStem() {
    return pengZuHeavenStem;
  }

  /**
   * 地支彭祖百忌
   *
   * @return 地支彭祖百忌
   */
  public PengZuEarthBranch getPengZuEarthBranch() {
    return pengZuEarthBranch;
  }

}
