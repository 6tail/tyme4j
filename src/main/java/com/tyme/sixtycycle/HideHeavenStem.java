package com.tyme.sixtycycle;

import com.tyme.AbstractCulture;
import com.tyme.enums.HideHeavenStemType;

/**
 * 藏干（即人元，司令取天干，分野取天干的五行）
 *
 * @author 6tail
 */
public class HideHeavenStem extends AbstractCulture {

  /**
   * 天干
   */
  protected HeavenStem heavenStem;

  /**
   * 藏干类型
   */
  protected HideHeavenStemType type;

  public HideHeavenStem(HeavenStem heavenStem, HideHeavenStemType type) {
    this.heavenStem = heavenStem;
    this.type = type;
  }

  public HideHeavenStem(String heavenStemName, HideHeavenStemType type) {
    this(HeavenStem.fromName(heavenStemName), type);
  }

  public HideHeavenStem(int heavenStemIndex, HideHeavenStemType type) {
    this(HeavenStem.fromIndex(heavenStemIndex), type);
  }

  /**
   * 天干
   *
   * @return 天干
   */
  public HeavenStem getHeavenStem() {
    return heavenStem;
  }

  /**
   * 藏干类型
   *
   * @return 藏干类型
   */
  public HideHeavenStemType getType() {
    return type;
  }

  @Override
  public String getName() {
    return heavenStem.getName();
  }

}
