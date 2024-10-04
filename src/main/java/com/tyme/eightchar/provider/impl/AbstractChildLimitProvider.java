package com.tyme.eightchar.provider.impl;

import com.tyme.eightchar.ChildLimitInfo;
import com.tyme.eightchar.provider.ChildLimitProvider;
import com.tyme.solar.SolarMonth;
import com.tyme.solar.SolarTime;

/**
 * 童限计算抽象
 *
 * @author 6tail
 */
public abstract class AbstractChildLimitProvider implements ChildLimitProvider {

  protected ChildLimitInfo next(SolarTime birthTime, int addYear, int addMonth, int addDay, int addHour, int addMinute, int addSecond) {
    int d = birthTime.getDay() + addDay;
    int h = birthTime.getHour() + addHour;
    int mi = birthTime.getMinute() + addMinute;
    int s = birthTime.getSecond() + addSecond;
    mi += s / 60;
    s %= 60;
    h += mi / 60;
    mi %= 60;
    d += h / 24;
    h %= 24;

    SolarMonth sm = SolarMonth.fromYm(birthTime.getYear() + addYear, birthTime.getMonth()).next(addMonth);

    int dc = sm.getDayCount();
    while (d > dc) {
      d -= dc;
      sm = sm.next(1);
      dc = sm.getDayCount();
    }

    return new ChildLimitInfo(birthTime, SolarTime.fromYmdHms(sm.getYear(), sm.getMonth(), d, h, mi, s), addYear, addMonth, addDay, addHour, addMinute);
  }

}
