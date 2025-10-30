package com.tyme.sixtycycle;

import com.tyme.AbstractCulture;
import com.tyme.solar.SolarDay;
import com.tyme.solar.SolarTerm;

import java.util.ArrayList;
import java.util.List;

/**
 * 三柱（年柱、月柱、日柱）
 *
 * @author 6tail
 */
public class ThreePillars extends AbstractCulture {
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
   * 初始化
   *
   * @param year  年柱
   * @param month 月柱
   * @param day   日柱
   */
  public ThreePillars(SixtyCycle year, SixtyCycle month, SixtyCycle day) {
    this.year = year;
    this.month = month;
    this.day = day;
  }

  /**
   * 初始化
   *
   * @param year  年柱
   * @param month 月柱
   * @param day   日柱
   */
  public ThreePillars(String year, String month, String day) {
    this(SixtyCycle.fromName(year), SixtyCycle.fromName(month), SixtyCycle.fromName(day));
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
   * 公历日列表
   *
   * @param startYear 开始年(含)，支持1-9999年
   * @param endYear   结束年(含)，支持1-9999年
   * @return 公历日列表
   */
  public List<SolarDay> getSolarDays(int startYear, int endYear) {
    List<SolarDay> l = new ArrayList<>();
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
      SolarDay solarDay = term.getSolarDay();
      if (solarDay.getYear() >= startYear) {
        // 日干支和节令干支的偏移值
        int d = day.next(-solarDay.getLunarDay().getSixtyCycle().getIndex()).getIndex();
        if (d > 0) {
          // 从节令推移天数
          solarDay = solarDay.next(d);
        }
        // 验证一下
        if (solarDay.getSixtyCycleDay().getThreePillars().equals(this)) {
          l.add(solarDay);
        }
      }
      y += 60;
    }
    return l;
  }

  public String getName() {
    return String.format("%s %s %s", year, month, day);
  }
}
