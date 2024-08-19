package com.tyme.eightchar.provider.impl;

import com.tyme.eightchar.ChildLimitInfo;
import com.tyme.eightchar.provider.ChildLimitProvider;
import com.tyme.solar.SolarDay;
import com.tyme.solar.SolarMonth;
import com.tyme.solar.SolarTerm;
import com.tyme.solar.SolarTime;

/**
 * 元亨利贞的童限计算
 *
 * @author 6tail
 */
public class China95ChildLimitProvider implements ChildLimitProvider {
  @Override
  public ChildLimitInfo getInfo(SolarTime birthTime, SolarTerm term) {
    // 出生时刻和节令时刻相差的分钟数
    int minutes = Math.abs(term.getJulianDay().getSolarTime().subtract(birthTime)) / 60;
    int year = minutes / 4320;
    minutes %= 4320;
    int month = minutes / 360;
    minutes %= 360;
    int day = minutes / 12;

    SolarDay birthday = birthTime.getSolarDay();
    SolarMonth sm = SolarMonth.fromYm(birthday.getYear() + year, birthday.getMonth()).next(month);

    int d = birthday.getDay() + day;
    int dc = sm.getDayCount();
    while (d > dc) {
      d -= dc;
      sm = sm.next(1);
      dc = sm.getDayCount();
    }

    return new ChildLimitInfo(birthTime, SolarTime.fromYmdHms(sm.getYear(), sm.getMonth(), d, birthTime.getHour(), birthTime.getMinute(), birthTime.getSecond()), year, month, day, 0, 0);
  }

}
