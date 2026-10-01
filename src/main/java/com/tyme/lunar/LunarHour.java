package com.tyme.lunar;

import com.tyme.culture.Taboo;
import com.tyme.culture.ren.MinorRen;
import com.tyme.culture.star.nine.NineStar;
import com.tyme.culture.star.twelve.TwelveStar;
import com.tyme.eightchar.EightChar;
import com.tyme.eightchar.provider.EightCharProvider;
import com.tyme.eightchar.provider.impl.DefaultEightCharProvider;
import com.tyme.sixtycycle.EarthBranch;
import com.tyme.sixtycycle.HeavenStem;
import com.tyme.sixtycycle.SixtyCycle;
import com.tyme.sixtycycle.SixtyCycleHour;
import com.tyme.solar.SolarDay;
import com.tyme.solar.SolarTerm;
import com.tyme.solar.SolarTime;
import com.tyme.unit.SecondUnit;

import java.util.List;

/**
 * 农历时辰
 *
 * @author 6tail
 */
public class LunarHour extends SecondUnit {

  /**
   * 八字计算接口
   */
  public static EightCharProvider provider = new DefaultEightCharProvider();

  public static void validate(int year, int month, int day, int hour, int minute, int second) {
    SecondUnit.validate(hour, minute, second);
    LunarDay.validate(year, month, day);
  }

  /**
   * 初始化
   *
   * @param year   农历年
   * @param month  农历月，闰月为负
   * @param day    农历日
   * @param hour   时
   * @param minute 分
   * @param second 秒
   */
  public LunarHour(int year, int month, int day, int hour, int minute, int second) {
    super(year, month, day, hour, minute, second);
    validate(year, month, day, hour, minute, second);
  }

  /**
   * 从农历年月日时分秒初始化
   *
   * @param year   农历年
   * @param month  农历月，闰月为负
   * @param day    农历日
   * @param hour   时
   * @param minute 分
   * @param second 秒
   */
  public static LunarHour fromYmdHms(int year, int month, int day, int hour, int minute, int second) {
    return new LunarHour(year, month, day, hour, minute, second);
  }

  /**
   * 农历日
   *
   * @return 农历日
   */
  public LunarDay getLunarDay() {
    return new LunarDay(year, month, day);
  }

  public String getName() {
    return EarthBranch.fromIndex(getIndexInDay()).getName() + "时";
  }

  @Override
  public String toString() {
    return getLunarDay() + getSixtyCycle().getName() + "时";
  }

  /**
   * 位于当天的索引
   *
   * @return 索引
   */
  public int getIndexInDay() {
    return (hour + 1) / 2;
  }

  public LunarHour next(int n) {
    if (n == 0) {
      return fromYmdHms(year, month, day, hour, minute, second);
    }
    long h = hour + n * 2L;
    LunarDay d = getLunarDay().next((int)Math.floorDiv(h, 24));
    return fromYmdHms(d.getYear(), d.getMonth(), d.getDay(), (int)Math.floorMod(h, 24), minute, second);
  }

  /**
   * 是否在指定农历时辰之前
   *
   * @param target 农历时辰
   * @return true/false
   */
  public boolean isBefore(LunarHour target) {
    return getCompareIndex() < target.getCompareIndex();
  }

  /**
   * 是否在指定农历时辰之后
   *
   * @param target 农历时辰
   * @return true/false
   */
  public boolean isAfter(LunarHour target) {
    return getCompareIndex() > target.getCompareIndex();
  }

  /**
   * 当时的年干支（立春换）
   *
   * @return 干支
   * @see SixtyCycleHour#getYear()
   */
  @Deprecated
  public SixtyCycle getYearSixtyCycle() {
    return getSixtyCycleHour().getYear();
  }

  /**
   * 当时的月干支（节气换）
   *
   * @return 干支
   * @see SixtyCycleHour#getMonth()
   */
  @Deprecated
  public SixtyCycle getMonthSixtyCycle() {
    return getSixtyCycleHour().getMonth();
  }

  /**
   * 当时的日干支（23:00开始算做第二天）
   *
   * @return 干支
   * @see SixtyCycleHour#getDay()
   */
  @Deprecated
  public SixtyCycle getDaySixtyCycle() {
    return getSixtyCycleHour().getDay();
  }

  /**
   * 干支
   *
   * @return 干支
   */
  public SixtyCycle getSixtyCycle() {
    int e = getIndexInDay();
    HeavenStem h = getLunarDay().getSixtyCycle().getHeavenStem();
    if (hour >= 23) {
      h = h.next(1);
      e = 0;
    }
    return SixtyCycle.fromIndex(h.getIndex() * 12 + e);
  }

  /**
   * 黄道黑道十二神
   *
   * @return 黄道黑道十二神
   */
  public TwelveStar getTwelveStar() {
    return TwelveStar.fromIndex(getSixtyCycle().getEarthBranch().getIndex() + (8 - getSixtyCycleHour().getDay().getEarthBranch().getIndex() % 6) * 2);
  }

  /**
   * 九星（时家紫白星歌诀：三元时白最为佳，冬至阳生顺莫差，孟日七宫仲一白，季日四绿发萌芽，每把时辰起甲子，本时星耀照光华，时星移入中宫去，顺飞八方逐细查。夏至阴生逆回首，孟归三碧季加六，仲在九宫时起甲，依然掌中逆轮跨。）
   *
   * @return 九星
   */
  public NineStar getNineStar() {
    LunarDay d = getLunarDay();
    SolarDay solar = d.getSolarDay();
    SolarTerm dongZhi = SolarTerm.fromIndex(solar.getYear(), 0);
    int earthBranchIndex = getIndexInDay() % 12;
    int index = 8 - 3 * (d.getSixtyCycle().getEarthBranch().getIndex() % 3);
    if (!solar.isBefore(dongZhi.getJulianDay().getSolarDay()) && solar.isBefore(dongZhi.next(12).getJulianDay().getSolarDay())) {
      index = 8 + earthBranchIndex - index;
    } else {
      index -= earthBranchIndex;
    }
    return NineStar.fromIndex(index);
  }

  /**
   * 公历时刻
   *
   * @return 公历时刻
   */
  public SolarTime getSolarTime() {
    SolarDay d = getLunarDay().getSolarDay();
    return SolarTime.fromYmdHms(d.getYear(), d.getMonth(), d.getDay(), hour, minute, second);
  }

  /**
   * 八字
   *
   * @return 八字
   */
  public EightChar getEightChar() {
    return provider.getEightChar(this);
  }

  /**
   * 干支时辰
   *
   * @return 干支时辰
   */
  public SixtyCycleHour getSixtyCycleHour() {
    return getSolarTime().getSixtyCycleHour();
  }

  /**
   * 宜
   *
   * @return 宜忌列表
   */
  public List<Taboo> getRecommends() {
    return Taboo.getHourRecommends(getSixtyCycleHour().getDay(), getSixtyCycle());
  }

  /**
   * 忌
   *
   * @return 宜忌列表
   */
  public List<Taboo> getAvoids() {
    return Taboo.getHourAvoids(getSixtyCycleHour().getDay(), getSixtyCycle());
  }

  /**
   * 小六壬
   *
   * @return 小六壬
   */
  public MinorRen getMinorRen() {
    return getLunarDay().getMinorRen().next(getIndexInDay());
  }
}
