package com.tyme.sixtycycle;

import com.tyme.AbstractTyme;
import com.tyme.culture.*;
import com.tyme.culture.fetus.FetusDay;
import com.tyme.culture.star.nine.NineStar;
import com.tyme.culture.star.twelve.TwelveStar;
import com.tyme.culture.star.twentyeight.TwentyEightStar;
import com.tyme.lunar.LunarDay;
import com.tyme.lunar.LunarMonth;
import com.tyme.lunar.LunarYear;
import com.tyme.solar.SolarDay;
import com.tyme.solar.SolarTerm;
import com.tyme.solar.SolarTime;

import java.util.ArrayList;
import java.util.List;

/**
 * 干支日（立春换年，节令换月）
 *
 * @author 6tail
 */
public class SixtyCycleDay extends AbstractTyme {

  /**
   * 公历日
   */
  protected SolarDay solarDay;

  /**
   * 干支月
   */
  protected SixtyCycleMonth month;

  /**
   * 日柱
   */
  protected SixtyCycle day;

  SixtyCycleDay(SolarDay solarDay, SixtyCycleMonth month, SixtyCycle day) {
    this.solarDay = solarDay;
    this.month = month;
    this.day = day;
  }

  /**
   * 初始化
   *
   * @param solarDay 公历日
   */
  public SixtyCycleDay(SolarDay solarDay) {
    int solarYear = solarDay.getYear();
    SolarDay springSolarDay = SolarTerm.fromIndex(solarYear, 3).getJulianDay().getSolarDay();
    LunarDay lunarDay = solarDay.getLunarDay();
    LunarYear lunarYear = lunarDay.getLunarMonth().getLunarYear();
    if (lunarYear.getYear() == solarYear) {
      if (solarDay.isBefore(springSolarDay)) {
        lunarYear = lunarYear.next(-1);
      }
    } else if (lunarYear.getYear() < solarYear) {
      if (!solarDay.isBefore(springSolarDay)) {
        lunarYear = lunarYear.next(1);
      }
    }
    SolarTerm term = solarDay.getTerm();
    int index = term.getIndex() - 3;
    if (index < 0 && term.getJulianDay().getSolarDay().isAfter(springSolarDay)) {
      index += 24;
    }
    this.solarDay = solarDay;
    this.month = new SixtyCycleMonth(SixtyCycleYear.fromYear(lunarYear.getYear()), LunarMonth.fromYm(solarYear, 1).getSixtyCycle().next((int) Math.floor(index * 1D / 2)));
    this.day = lunarDay.getSixtyCycle();
  }

  public static SixtyCycleDay fromSolarDay(SolarDay solarDay) {
    return new SixtyCycleDay(solarDay);
  }

  /**
   * 公历日
   *
   * @return 公历日
   */
  public SolarDay getSolarDay() {
    return solarDay;
  }

  /**
   * 干支月
   *
   * @return 干支月
   */
  public SixtyCycleMonth getSixtyCycleMonth() {
    return month;
  }

  /**
   * 年柱
   *
   * @return 年柱
   */
  public SixtyCycle getYear() {
    return month.getYear();
  }

  /**
   * 月柱
   *
   * @return 月柱
   */
  public SixtyCycle getMonth() {
    return month.getSixtyCycle();
  }

  /**
   * 干支
   *
   * @return 干支
   */
  public SixtyCycle getSixtyCycle() {
    return day;
  }

  public String getName() {
    return String.format("%s日", day);
  }

  @Override
  public String toString() {
    return String.format("%s%s", month, getName());
  }

  /**
   * 建除十二值神
   *
   * @return 建除十二值神
   */
  public Duty getDuty() {
    return Duty.fromIndex(day.getEarthBranch().getIndex() - getMonth().getEarthBranch().getIndex());
  }

  /**
   * 黄道黑道十二神
   *
   * @return 黄道黑道十二神
   */
  public TwelveStar getTwelveStar() {
    return TwelveStar.fromIndex(day.getEarthBranch().getIndex() + (8 - getMonth().getEarthBranch().getIndex() % 6) * 2);
  }

  /**
   * 九星
   *
   * @return 九星
   */
  public NineStar getNineStar() {
    SolarTerm dongZhi = SolarTerm.fromIndex(solarDay.getYear(), 0);
    SolarTerm xiaZhi = dongZhi.next(12);
    SolarTerm dongZhi2 = dongZhi.next(24);
    SolarDay dongZhiSolar = dongZhi.getJulianDay().getSolarDay();
    SolarDay xiaZhiSolar = xiaZhi.getJulianDay().getSolarDay();
    SolarDay dongZhiSolar2 = dongZhi2.getJulianDay().getSolarDay();
    int dongZhiIndex = dongZhiSolar.getLunarDay().getSixtyCycle().getIndex();
    int xiaZhiIndex = xiaZhiSolar.getLunarDay().getSixtyCycle().getIndex();
    int dongZhiIndex2 = dongZhiSolar2.getLunarDay().getSixtyCycle().getIndex();
    SolarDay solarShunBai = dongZhiSolar.next(dongZhiIndex > 29 ? 60 - dongZhiIndex : -dongZhiIndex);
    SolarDay solarShunBai2 = dongZhiSolar2.next(dongZhiIndex2 > 29 ? 60 - dongZhiIndex2 : -dongZhiIndex2);
    SolarDay solarNiZi = xiaZhiSolar.next(xiaZhiIndex > 29 ? 60 - xiaZhiIndex : -xiaZhiIndex);
    int offset = 0;
    if (!solarDay.isBefore(solarShunBai) && solarDay.isBefore(solarNiZi)) {
      offset = solarDay.subtract(solarShunBai);
    } else if (!solarDay.isBefore(solarNiZi) && solarDay.isBefore(solarShunBai2)) {
      offset = 8 - solarDay.subtract(solarNiZi);
    } else if (!solarDay.isBefore(solarShunBai2)) {
      offset = solarDay.subtract(solarShunBai2);
    } else if (solarDay.isBefore(solarShunBai)) {
      offset = 8 + solarShunBai.subtract(solarDay);
    }
    return NineStar.fromIndex(offset);
  }

  /**
   * 太岁方位
   *
   * @return 方位
   */
  public Direction getJupiterDirection() {
    int index = day.getIndex();
    return index % 12 < 6 ? Element.fromIndex(index / 12).getDirection() : month.getSixtyCycleYear().getJupiterDirection();
  }

  /**
   * 逐日胎神
   *
   * @return 逐日胎神
   */
  public FetusDay getFetusDay() {
    return FetusDay.fromSixtyCycleDay(this);
  }

  /**
   * 二十八宿
   *
   * @return 二十八宿
   */
  public TwentyEightStar getTwentyEightStar() {
    return TwentyEightStar.fromIndex(new int[]{10, 18, 26, 6, 14, 22, 2}[solarDay.getWeek().getIndex()]).next(-7 * day.getEarthBranch().getIndex());
  }

  /**
   * 神煞列表(吉神宜趋，凶神宜忌)
   *
   * @return 神煞列表
   */
  public List<God> getGods() {
    return God.getDayGods(getMonth(), day);
  }

  /**
   * 宜
   *
   * @return 宜忌列表
   */
  public List<Taboo> getRecommends() {
    return Taboo.getDayRecommends(getMonth(), day);
  }

  /**
   * 忌
   *
   * @return 宜忌列表
   */
  public List<Taboo> getAvoids() {
    return Taboo.getDayAvoids(getMonth(), day);
  }

  /**
   * 推移
   *
   * @param n 推移天数
   * @return 干支日
   */
  @Override
  public SixtyCycleDay next(int n) {
    return fromSolarDay(solarDay.next(n));
  }

  /**
   * 干支时辰列表
   *
   * @return 干支时辰列表
   */
  public List<SixtyCycleHour> getHours() {
    List<SixtyCycleHour> l = new ArrayList<>();
    SolarDay d = solarDay.next(-1);
    SixtyCycleHour h = SixtyCycleHour.fromSolarTime(SolarTime.fromYmdHms(d.getYear(), d.getMonth(), d.getDay(), 23, 0, 0));
    l.add(h);
    for (int i = 0; i < 11; i++) {
      h = h.next(7200);
      l.add(h);
    }
    return l;
  }

}
