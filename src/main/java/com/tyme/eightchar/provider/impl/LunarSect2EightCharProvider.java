package com.tyme.eightchar.provider.impl;

import com.tyme.eightchar.EightChar;
import com.tyme.eightchar.provider.EightCharProvider;
import com.tyme.lunar.LunarHour;
import com.tyme.sixtycycle.SixtyCycleHour;

/**
 * Lunar流派2的八字计算（晚子时日柱算当天）
 *
 * @author 6tail
 */
public class LunarSect2EightCharProvider implements EightCharProvider {

  @Override
  public EightChar getEightChar(LunarHour hour) {
    SixtyCycleHour h = hour.getSixtyCycleHour();
    return new EightChar(h.getYear(), h.getMonth(), hour.getLunarDay().getSixtyCycle(), h.getSixtyCycle());
  }
}
