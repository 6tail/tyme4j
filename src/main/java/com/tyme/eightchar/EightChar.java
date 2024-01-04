package com.tyme.eightchar;

import com.tyme.AbstractCulture;
import com.tyme.culture.Duty;
import com.tyme.sixtycycle.EarthBranch;
import com.tyme.sixtycycle.SixtyCycle;

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
    int monthEarthBranchIndex = month.getEarthBranch().next(-1).getIndex();
    return SixtyCycle.fromIndex(month.getIndex() + (26 - monthEarthBranchIndex - hour.getEarthBranch().next(-1).getIndex()) % 12 - monthEarthBranchIndex);
  }

  /**
   * 身宫
   *
   * @return 身宫
   */
  public SixtyCycle getBodySign() {
    int monthEarthBranchIndex = month.getEarthBranch().next(-1).getIndex();
    int index = 2 + monthEarthBranchIndex + hour.getEarthBranch().next(-1).getIndex();
    if (index > 12) {
      index -= 12;
    }
    return SixtyCycle.fromIndex(month.getIndex() + index - monthEarthBranchIndex);
  }

  /**
   * 建除十二值神
   *
   * @return 建除十二值神
   */
  public Duty getDuty() {
    return Duty.fromIndex(day.getEarthBranch().getIndex() - month.getEarthBranch().getIndex());
  }

  public String getName() {
    return String.format("%s %s %s %s", year, month, day, hour);
  }

}
