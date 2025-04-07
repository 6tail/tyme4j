package com.tyme.eightchar.provider.impl;

import com.tyme.eightchar.EightChar;
import com.tyme.eightchar.provider.EightCharProvider;
import com.tyme.lunar.LunarHour;

/**
 * 默认的八字计算（晚子时日柱算第二天）
 *
 * @author 6tail
 */
public class DefaultEightCharProvider implements EightCharProvider {

  @Override
  public EightChar getEightChar(LunarHour hour) {
    return hour.getSixtyCycleHour().getEightChar();
  }
}
