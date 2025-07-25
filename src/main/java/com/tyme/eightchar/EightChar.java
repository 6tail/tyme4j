package com.tyme.eightchar;

import com.tyme.AbstractCulture;
import com.tyme.culture.Duty;
import com.tyme.sixtycycle.EarthBranch;
import com.tyme.sixtycycle.HeavenStem;
import com.tyme.sixtycycle.SixtyCycle;
import com.tyme.sixtycycle.SixtyCycleDay;
import com.tyme.solar.SolarDay;
import com.tyme.solar.SolarTerm;
import com.tyme.solar.SolarTime;

import java.util.ArrayList;
import java.util.List;

/**
 * 八字
 *
 * @author 6tail
 */
public class EightChar extends AbstractCulture {

  /**
   * 年柱
   */
  protected SixtyCycle year;

  /**
   * 月柱
   */
  protected SixtyCycle month;

  /**
   * 日柱
   */
  protected SixtyCycle day;

  /**
   * 时柱
   */
  protected SixtyCycle hour;

  /**
   * 初始化
   *
   * @param year  年柱
   * @param month 月柱
   * @param day   日柱
   * @param hour  时柱
   */
  public EightChar(SixtyCycle year, SixtyCycle month, SixtyCycle day, SixtyCycle hour) {
    this.year = year;
    this.month = month;
    this.day = day;
    this.hour = hour;
  }

  /**
   * 初始化
   *
   * @param year  年柱
   * @param month 月柱
   * @param day   日柱
   * @param hour  时柱
   */
  public EightChar(String year, String month, String day, String hour) {
    this(SixtyCycle.fromName(year), SixtyCycle.fromName(month), SixtyCycle.fromName(day), SixtyCycle.fromName(hour));
  }

  /**
   * 年柱
   *
   * @return 年柱
   */
  public SixtyCycle getYear() {
    return year;
  }

  /**
   * 月柱
   *
   * @return 月柱
   */
  public SixtyCycle getMonth() {
    return month;
  }

  /**
   * 日柱
   *
   * @return 日柱
   */
  public SixtyCycle getDay() {
    return day;
  }

  /**
   * 时柱
   *
   * @return 时柱
   */
  public SixtyCycle getHour() {
    return hour;
  }

  /**
   * 胎元
   *
   * @return 胎元
   */
  public SixtyCycle getFetalOrigin() {
    return SixtyCycle.fromName(month.getHeavenStem().next(1).getName() + month.getEarthBranch().next(3).getName());
  }

  /**
   * 胎息
   *
   * @return 胎息
   */
  public SixtyCycle getFetalBreath() {
    return SixtyCycle.fromName(day.getHeavenStem().next(5).getName() + EarthBranch.fromIndex(13 - day.getEarthBranch().getIndex()).getName());
  }

  /**
   * 命宫
   *
   * @return 命宫
   */
  public SixtyCycle getOwnSign() {
    int m = month.getEarthBranch().getIndex() - 1;
    if (m < 1) {
      m += 12;
    }
    int h = hour.getEarthBranch().getIndex() - 1;
    if (h < 1) {
      h += 12;
    }
    int offset = m + h;
    offset = (offset >= 14 ? 26 : 14) - offset;
    return SixtyCycle.fromName(HeavenStem.fromIndex((year.getHeavenStem().getIndex() + 1) * 2 + offset - 1).getName() + EarthBranch.fromIndex(offset + 1).getName());
  }

  /**
   * 身宫
   *
   * @return 身宫
   */
  public SixtyCycle getBodySign() {
    int offset = month.getEarthBranch().getIndex() - 1;
    if (offset < 1) {
      offset += 12;
    }
    offset += hour.getEarthBranch().getIndex() + 1;
    if (offset > 12) {
      offset -= 12;
    }
    return SixtyCycle.fromName(HeavenStem.fromIndex((year.getHeavenStem().getIndex() + 1) * 2 + offset - 1).getName() + EarthBranch.fromIndex(offset + 1).getName());
  }

  /**
   * 建除十二值神
   *
   * @return 建除十二值神
   * @see SixtyCycleDay#getDuty()
   */
  @Deprecated
  public Duty getDuty() {
    return Duty.fromIndex(day.getEarthBranch().getIndex() - month.getEarthBranch().getIndex());
  }

  /**
   * 公历时刻列表
   *
   * @param startYear 开始年(含)，支持1-9999年
   * @param endYear   结束年(含)，支持1-9999年
   * @return 公历时刻列表
   */
  public List<SolarTime> getSolarTimes(int startYear, int endYear) {
    List<SolarTime> l = new ArrayList<>();
    // 月地支距寅月的偏移值
    int m = month.getEarthBranch().next(-2).getIndex();
    // 月天干要一致
    if (!HeavenStem.fromIndex((year.getHeavenStem().getIndex() + 1) * 2 + m).equals(month.getHeavenStem())) {
      return l;
    }
    // 1年的立春是辛酉，序号57
    int y = year.next(-57).getIndex() + 1;
    // 节令偏移值
    m *= 2;
    // 时辰地支转时刻
    int h = hour.getEarthBranch().getIndex() * 2;
    // 兼容子时多流派
    int[] hours = h == 0 ? new int[]{0, 23} : new int[]{h};
    int baseYear = startYear - 1;
    if (baseYear > y) {
      y += 60 * (int)Math.ceil((baseYear - y) / 60D);
    }
    while (y <= endYear) {
      // 立春为寅月的开始
      SolarTerm term = SolarTerm.fromIndex(y, 3);
      // 节令推移，年干支和月干支就都匹配上了
      if (m > 0) {
        term = term.next(m);
      }
      SolarTime solarTime = term.getJulianDay().getSolarTime();
      if (solarTime.getYear() >= startYear) {
        // 日干支和节令干支的偏移值
        SolarDay solarDay = solarTime.getSolarDay();
        int d = day.next(-solarDay.getLunarDay().getSixtyCycle().getIndex()).getIndex();
        if (d > 0) {
          // 从节令推移天数
          solarDay = solarDay.next(d);
        }
        for (int hour : hours) {
          int mi = 0;
          int s = 0;
          // 如果正好是节令当天，且小时和节令的小时数相等的极端情况，把分钟和秒钟带上
          if (d == 0 && hour == solarTime.getHour()) {
            mi = solarTime.getMinute();
            s = solarTime.getSecond();
          }
          SolarTime time = SolarTime.fromYmdHms(solarDay.getYear(), solarDay.getMonth(), solarDay.getDay(), hour, mi, s);
          // 验证一下
          if (time.getLunarHour().getEightChar().equals(this)) {
            l.add(time);
          }
        }
      }
      y += 60;
    }
    return l;
  }

  public String getName() {
    return String.format("%s %s %s %s", year, month, day, hour);
  }

}
