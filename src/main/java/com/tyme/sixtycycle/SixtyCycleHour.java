package com.tyme.sixtycycle;

import com.tyme.AbstractTyme;
import com.tyme.culture.Taboo;
import com.tyme.culture.star.nine.NineStar;
import com.tyme.culture.star.twelve.TwelveStar;
import com.tyme.eightchar.EightChar;
import com.tyme.lunar.LunarDay;
import com.tyme.lunar.LunarHour;
import com.tyme.lunar.LunarMonth;
import com.tyme.lunar.LunarYear;
import com.tyme.solar.SolarDay;
import com.tyme.solar.SolarTerm;
import com.tyme.solar.SolarTime;

import java.util.List;

/**
 * 干支时辰（立春换年，节令换月，23点换日）
 *
 * @author 6tail
 */
public class SixtyCycleHour extends AbstractTyme {

  /**
   * 公历时刻
   */
  protected SolarTime solarTime;

  /**
   * 干支日
   */
  protected SixtyCycleDay day;

  /**
   * 时柱
   */
  protected SixtyCycle hour;

  /**
   * 初始化
   *
   * @param solarTime 公历时刻
   */
  public SixtyCycleHour(SolarTime solarTime) {
    int solarYear = solarTime.getYear();
    SolarTime springSolarTime = SolarTerm.fromIndex(solarYear, 3).getJulianDay().getSolarTime();
    LunarHour lunarHour = solarTime.getLunarHour();
    LunarDay lunarDay = lunarHour.getLunarDay();
    LunarYear lunarYear = lunarDay.getLunarMonth().getLunarYear();
    if (lunarYear.getYear() == solarYear) {
      if (solarTime.isBefore(springSolarTime)) {
        lunarYear = lunarYear.next(-1);
      }
    } else if (lunarYear.getYear() < solarYear) {
      if (!solarTime.isBefore(springSolarTime)) {
        lunarYear = lunarYear.next(1);
      }
    }

    SolarTerm term = solarTime.getTerm();
    int index = term.getIndex() - 3;
    if (index < 0 && term.getJulianDay().getSolarTime().isAfter(SolarTerm.fromIndex(solarYear, 3).getJulianDay().getSolarTime())) {
      index += 24;
    }
    SixtyCycle d = lunarDay.getSixtyCycle();
    this.solarTime = solarTime;
    this.day = new SixtyCycleDay(solarTime.getSolarDay(), new SixtyCycleMonth(SixtyCycleYear.fromYear(lunarYear.getYear()), LunarMonth.fromYm(solarYear, 1).getSixtyCycle().next((int) Math.floor(index * 1D / 2))), solarTime.getHour() < 23 ? d : d.next(1));
    this.hour = lunarHour.getSixtyCycle();
  }

  public static SixtyCycleHour fromSolarTime(SolarTime solarTime) {
    return new SixtyCycleHour(solarTime);
  }

  /**
   * 年柱
   *
   * @return 年柱
   */
  public SixtyCycle getYear() {
    return day.getYear();
  }

  /**
   * 月柱
   *
   * @return 月柱
   */
  public SixtyCycle getMonth() {
    return day.getMonth();
  }

  /**
   * 日柱
   *
   * @return 日柱
   */
  public SixtyCycle getDay() {
    return day.getSixtyCycle();
  }

  /**
   * 干支
   *
   * @return 干支
   */
  public SixtyCycle getSixtyCycle() {
    return hour;
  }

  /**
   * 干支日
   *
   * @return 干支日
   */
  public SixtyCycleDay getSixtyCycleDay() {
    return day;
  }

  /**
   * 公历时刻
   *
   * @return 公历时刻
   */
  public SolarTime getSolarTime() {
    return solarTime;
  }

  public String getName() {
    return String.format("%s时", hour);
  }

  @Override
  public String toString() {
    return String.format("%s%s", day, getName());
  }

  /**
   * 位于当天的索引
   *
   * @return 索引
   */
  public int getIndexInDay() {
    int h = solarTime.getHour();
    return h == 23 ? 0 : (h + 1) / 2;
  }

  /**
   * 时九星
   *
   * @return 九星
   */
  public NineStar getNineStar() {
    SolarDay solar = solarTime.getSolarDay();
    SolarTerm dongZhi = SolarTerm.fromIndex(solar.getYear(), 0);
    SolarTerm xiaZhi = dongZhi.next(12);
    boolean asc = !solar.isBefore(dongZhi.getJulianDay().getSolarDay()) && solar.isBefore(xiaZhi.getJulianDay().getSolarDay());
    int start = new int[]{8, 5, 2}[getDay().getEarthBranch().getIndex() % 3];
    if (asc) {
      start = 8 - start;
    }
    int earthBranchIndex = getIndexInDay() % 12;
    return NineStar.fromIndex(start + (asc ? earthBranchIndex : -earthBranchIndex));
  }

  /**
   * 黄道黑道十二神
   *
   * @return 黄道黑道十二神
   */
  public TwelveStar getTwelveStar() {
    return TwelveStar.fromIndex(hour.getEarthBranch().getIndex() + (8 - getDay().getEarthBranch().getIndex() % 6) * 2);
  }

  /**
   * 宜
   *
   * @return 宜忌列表
   */
  public List<Taboo> getRecommends() {
    return Taboo.getHourRecommends(getDay(), hour);
  }

  /**
   * 忌
   *
   * @return 宜忌列表
   */
  public List<Taboo> getAvoids() {
    return Taboo.getHourAvoids(getDay(), hour);
  }

  /**
   * 推移
   *
   * @param n 推移秒数
   * @return 干支时辰
   */
  @Override
  public SixtyCycleHour next(int n) {
    return fromSolarTime(solarTime.next(n));
  }

  /**
   * 八字
   *
   * @return 八字
   */
  public EightChar getEightChar() {
    return new EightChar(getYear(), getMonth(), getDay(), hour);
  }

}
