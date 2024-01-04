package com.tyme.culture.phenology;

import com.tyme.AbstractCultureDay;

/**
 * 七十二候
 *
 * @author 6tail
 */
public class PhenologyDay extends AbstractCultureDay {

  public PhenologyDay(Phenology phenology, int dayIndex) {
    super(phenology, dayIndex);
  }

  /**
   * 候
   *
   * @return 候
   */
  public Phenology getPhenology() {
    return (Phenology) culture;
  }

}
