package com.tyme.solar;

import com.tyme.AbstractCultureDay;

/**
 * 节气第几天
 *
 * @author 6tail
 */
public class SolarTermDay extends AbstractCultureDay {

  public SolarTermDay(SolarTerm solarTerm, int dayIndex) {
    super(solarTerm, dayIndex);
  }

  /**
   * 节气
   *
   * @return 节气
   */
  public SolarTerm getSolarTerm() {
    return (SolarTerm)culture;
  }

}
