package com.tyme.lunar;

import com.tyme.AbstractTyme;
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

import java.util.List;

/**
 * 农历时辰
 *
 * @author 6tail
 */
public class LunarHour extends AbstractTyme {

  /**
   * 八字计算接口
   */
  public static EightCharProvider provider = new DefaultEightCharProvider();

  /**
   * 农历日
   */
  protected LunarDay day;

  /**
   * 时
   */
  protected int hour;

  /**
   * 分
   */
  protected int minute;

  /**
   * 秒
   */
  protected int second;

  /**
   * 公历时刻（第一次使用时才会初始化）
   */
  protected SolarTime solarTime;

  /**
   * 干支时辰（第一次使用时才会初始化）
   */
  protected SixtyCycleHour sixtyCycleHour;

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
    if (hour < 0 || hour > 23) {
      throw new IllegalArgumentException(String.format("illegal hour: %d", hour));
    }
    if (minute < 0 || minute > 59) {
      throw new IllegalArgumentException(String.format("illegal minute: %d", minute));
    }
    if (second < 0 || second > 59) {
      throw new IllegalArgumentException(String.format("illegal second: %d", second));
    }
    this.day = LunarDay.fromYmd(year, month, day);
    this.hour = hour;
    this.minute = minute;
    this.second = second;
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
    return day;
  }

  /**
   * 年
   *
   * @return 年
   */
  public int getYear() {
    return day.getYear();
  }

  /**
   * 月
   *
   * @return 月
   */
  public int getMonth() {
    return day.getMonth();
  }

  /**
   * 日
   *
   * @return 日
   */
  public int getDay() {
    return day.getDay();
  }

  /**
   * 时
   *
   * @return 时
   */
  public int getHour() {
    return hour;
  }

  /**
   * 分
   *
   * @return 分
   */
  public int getMinute() {
    return minute;
  }

  /**
   * 秒
   *
   * @return 秒
   */
  public int getSecond() {
    return second;
  }

  public String getName() {
    return EarthBranch.fromIndex(getIndexInDay()).getName() + "时";
  }

  @Override
  public String toString() {
    return day + getSixtyCycle().getName() + "时";
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
      return fromYmdHms(getYear(), getMonth(), getDay(), hour, minute, second);
    }
    int h = hour + n * 2;
    int diff = h < 0 ? -1 : 1;
    int hour = Math.abs(h);
    int days = hour / 24 * diff;
    hour = (hour % 24) * diff;
    if (hour < 0) {
      hour += 24;
      days--;
    }
    LunarDay d = day.next(days);
    return fromYmdHms(d.getYear(), d.getMonth(), d.getDay(), hour, minute, second);
  }

  /**
   * 是否在指定农历时辰之前
   *
   * @param target 农历时辰
   * @return true/false
   */
  public boolean isBefore(LunarHour target) {
    if (!day.equals(target.getLunarDay())) {
      return day.isBefore(target.getLunarDay());
    }
    if (hour != target.getHour()) {
      return hour < target.getHour();
    }
    return minute != target.getMinute() ? minute < target.getMinute() : second < target.getSecond();
  }

  /**
   * 是否在指定农历时辰之后
   *
   * @param target 农历时辰
   * @return true/false
   */
  public boolean isAfter(LunarHour target) {
    if (!day.equals(target.getLunarDay())) {
      return day.isAfter(target.getLunarDay());
    }
    if (hour != target.getHour()) {
      return hour > target.getHour();
    }
    return minute != target.getMinute() ? minute > target.getMinute() : second > target.getSecond();
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
    int earthBranchIndex = getIndexInDay() % 12;
    SixtyCycle d = day.getSixtyCycle();
    if (hour >= 23) {
      d = d.next(1);
    }
    return SixtyCycle.fromName(HeavenStem.fromIndex(d.getHeavenStem().getIndex() % 5 * 2 + earthBranchIndex).getName() + EarthBranch.fromIndex(earthBranchIndex).getName());
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
    SolarDay solar = day.getSolarDay();
    SolarTerm dongZhi = SolarTerm.fromIndex(solar.getYear(), 0);
    int earthBranchIndex = getIndexInDay() % 12;
    int index = new int[]{8, 5, 2}[day.getSixtyCycle().getEarthBranch().getIndex() % 3];
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
    if (null == solarTime) {
      SolarDay d = day.getSolarDay();
      solarTime = SolarTime.fromYmdHms(d.getYear(), d.getMonth(), d.getDay(), hour, minute, second);
    }
    return solarTime;
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
    if (null == sixtyCycleHour) {
      sixtyCycleHour = getSolarTime().getSixtyCycleHour();
    }
    return sixtyCycleHour;
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
