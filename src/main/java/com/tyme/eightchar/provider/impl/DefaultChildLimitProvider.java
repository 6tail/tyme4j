package com.tyme.eightchar.provider.impl;

import com.tyme.eightchar.ChildLimitInfo;
import com.tyme.eightchar.provider.ChildLimitProvider;
import com.tyme.solar.SolarDay;
import com.tyme.solar.SolarMonth;
import com.tyme.solar.SolarTerm;
import com.tyme.solar.SolarTime;

/**
 * 默认的童限计算
 *
 * @author 6tail
 */
public class DefaultChildLimitProvider implements ChildLimitProvider {
  @Override
  public ChildLimitInfo getInfo(SolarTime birthTime, SolarTerm term) {
    // 出生时刻和节令时刻相差的秒数
    int seconds = Math.abs(term.getJulianDay().getSolarTime().subtract(birthTime));
    // 3天 = 1年，3天=60*60*24*3秒=259200秒 = 1年
    int year = seconds / 259200;
    seconds %= 259200;
    // 1天 = 4月，1天=60*60*24秒=86400秒 = 4月，85400秒/4=21600秒 = 1月
    int month = seconds / 21600;
    seconds %= 21600;
    // 1时 = 5天，1时=60*60秒=3600秒 = 5天，3600秒/5=720秒 = 1天
    int day = seconds / 720;
    seconds %= 720;
    // 1分 = 2时，60秒 = 2时，60秒/2=30秒 = 1时
    int hour = seconds / 30;
    seconds %= 30;
    // 1秒 = 2分，1秒/2=0.5秒 = 1分
    int minute = seconds * 2;

    SolarDay birthday = birthTime.getSolarDay();

    int d = birthday.getDay() + day;
    int h = birthTime.getHour() + hour;
    int mi = birthTime.getMinute() + minute;
    h += mi / 60;
    mi %= 60;
    d += h / 24;
    h %= 24;

    SolarMonth sm = SolarMonth.fromYm(birthday.getYear() + year, birthday.getMonth()).next(month);

    int dc = sm.getDayCount();
    while (d > dc) {
      d -= dc;
      sm = sm.next(1);
      dc = sm.getDayCount();
    }

    return new ChildLimitInfo(birthTime, SolarTime.fromYmdHms(sm.getYear(), sm.getMonth(), d, h, mi, birthTime.getSecond()), year, month, day, hour, minute);
  }

}
