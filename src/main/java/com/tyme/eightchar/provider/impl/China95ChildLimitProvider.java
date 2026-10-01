package com.tyme.eightchar.provider.impl;

import com.tyme.eightchar.ChildLimitInfo;
import com.tyme.solar.SolarTerm;
import com.tyme.solar.SolarTime;
import com.tyme.unit.SecondUnit;

/**
 * 元亨利贞的童限计算
 *
 * @author 6tail
 */
public class China95ChildLimitProvider extends LunarSect2ChildLimitProvider {
  @Override
  public ChildLimitInfo getInfo(SolarTime birthTime, SolarTerm term) {
    SecondUnit t = compute(birthTime, term);
    return next(birthTime, t.getYear(), t.getMonth(), t.getDay(), 0, 0, 0);
  }

}
