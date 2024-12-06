package com.tyme.sixtycycle;

import com.tyme.AbstractCultureDay;

/**
 * 人元司令分野（地支藏干+天索引）
 *
 * @author 6tail
 */
public class HideHeavenStemDay extends AbstractCultureDay {

  public HideHeavenStemDay(HideHeavenStem hideHeavenStem, int dayIndex) {
    super(hideHeavenStem, dayIndex);
  }

  /**
   * 藏干
   *
   * @return 藏干
   */
  public HideHeavenStem getHideHeavenStem() {
    return (HideHeavenStem)culture;
  }

  @Override
  public String getName() {
    HeavenStem heavenStem = getHideHeavenStem().getHeavenStem();
    return heavenStem.getName() + heavenStem.getElement().getName();
  }

  @Override
  public String toString() {
    return String.format("%s第%d天", getName(), dayIndex + 1);
  }
}
