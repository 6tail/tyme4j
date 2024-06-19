package com.tyme.culture.plumrain;

import com.tyme.AbstractCultureDay;

/**
 * 梅雨天
 *
 * @author 6tail
 */
public class PlumRainDay extends AbstractCultureDay {

  public PlumRainDay(PlumRain plumRain, int dayIndex) {
    super(plumRain, dayIndex);
  }

  /**
   * 梅雨
   *
   * @return 梅雨
   */
  public PlumRain getPlumRain() {
    return (PlumRain) culture;
  }

  @Override
  public String toString() {
    return getPlumRain().getIndex() == 0 ? super.toString() : culture.getName();
  }

}
