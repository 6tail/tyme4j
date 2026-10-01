package com.tyme.eightchar.provider.impl;

import com.tyme.eightchar.ChildLimitInfo;
import com.tyme.solar.SolarTerm;
import com.tyme.solar.SolarTime;
import com.tyme.unit.SecondUnit;

/**
 * Lunar的流派2童限计算（按分钟数计算）
 *
 * @author 6tail
 */
public class LunarSect2ChildLimitProvider extends AbstractChildLimitProvider {

  protected SecondUnit compute(SolarTime birthTime, SolarTerm term) {
    // 出生时刻和节令时刻相差的分钟数
    int minutes = Math.abs(term.getJulianDay().getSolarTime().subtract(birthTime)) / 60;
    int year = minutes / 4320;
    minutes %= 4320;
    int month = minutes / 360;
    minutes %= 360;
    int day = minutes / 12;
    minutes %= 12;
    int hour = minutes * 2;
    return new SecondUnit(year, month, day, hour, 0, 0){};
  }

  @Override
  public ChildLimitInfo getInfo(SolarTime birthTime, SolarTerm term) {
    SecondUnit t = compute(birthTime, term);
    return next(birthTime, t.getYear(), t.getMonth(), t.getDay(), t.getHour(), 0, 0);
  }

}
