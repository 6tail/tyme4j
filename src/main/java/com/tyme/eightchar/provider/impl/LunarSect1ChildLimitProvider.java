package com.tyme.eightchar.provider.impl;

import com.tyme.eightchar.ChildLimitInfo;
import com.tyme.solar.SolarTerm;
import com.tyme.solar.SolarTime;

/**
 * Lunar的流派1童限计算（按天数和时辰数计算，3天1年，1天4个月，1时辰10天）
 *
 * @author 6tail
 */
public class LunarSect1ChildLimitProvider extends AbstractChildLimitProvider {
  @Override
  public ChildLimitInfo getInfo(SolarTime birthTime, SolarTerm term) {
    SolarTime termTime = term.getJulianDay().getSolarTime();
    SolarTime end = termTime;
    SolarTime start = birthTime;
    if (birthTime.isAfter(termTime)) {
      end = birthTime;
      start = termTime;
    }
    int endTimeZhiIndex = (end.getHour() == 23) ? 11 : end.getLunarHour().getIndexInDay();
    int startTimeZhiIndex = (start.getHour() == 23) ? 11 : start.getLunarHour().getIndexInDay();
    // 时辰差
    int hourDiff = endTimeZhiIndex - startTimeZhiIndex;
    // 天数差
    int dayDiff = end.getSolarDay().subtract(start.getSolarDay());
    if (hourDiff < 0) {
      hourDiff += 12;
      dayDiff--;
    }
    int monthDiff = hourDiff * 10 / 30;
    int month = dayDiff * 4 + monthDiff;
    int day = hourDiff * 10 - monthDiff * 30;
    int year = month / 12;
    month = month - year * 12;

    return next(birthTime, year, month, day, 0, 0, 0);
  }

}
