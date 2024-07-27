package com.tyme.lunar;

import com.tyme.AbstractTyme;
import com.tyme.culture.Taboo;
import com.tyme.culture.star.nine.NineStar;
import com.tyme.culture.star.twelve.TwelveStar;
import com.tyme.eightchar.EightChar;
import com.tyme.sixtycycle.EarthBranch;
import com.tyme.sixtycycle.HeavenStem;
import com.tyme.sixtycycle.SixtyCycle;
import com.tyme.solar.SolarDay;
import com.tyme.solar.SolarTerm;
import com.tyme.solar.SolarTime;

import java.util.List;

/**
 * 时辰
 *
 * @author 6tail
 */
public class LunarHour extends AbstractTyme {

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
    return day.getLunarMonth().getYear();
  }

  /**
   * 月
   *
   * @return 月
   */
  public int getMonth() {
    return day.getLunarMonth().getMonthWithLeap();
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
   */
  public SixtyCycle getYearSixtyCycle() {
    SolarTime solarTime = getSolarTime();
    int solarYear = day.getSolarDay().getYear();
    SolarTime springSolarTime = SolarTerm.fromIndex(solarYear, 3).getJulianDay().getSolarTime();
    LunarYear lunarYear = day.getLunarMonth().getLunarYear();
    int year = lunarYear.getYear();
    SixtyCycle sixtyCycle = lunarYear.getSixtyCycle();
    if (year == solarYear) {
      if (solarTime.isBefore(springSolarTime)) {
        sixtyCycle = sixtyCycle.next(-1);
      }
    } else if (year < solarYear) {
      if (!solarTime.isBefore(springSolarTime)) {
        sixtyCycle = sixtyCycle.next(1);
      }
    }
    return sixtyCycle;
  }

  /**
   * 当时的月干支（节气换）
   *
   * @return 干支
   */
  public SixtyCycle getMonthSixtyCycle() {
    SolarTime solarTime = getSolarTime();
    int year = solarTime.getYear();
    SolarTerm term = solarTime.getTerm();
    int index = term.getIndex() - 3;
    if (index < 0 && term.getJulianDay().getSolarTime().isAfter(SolarTerm.fromIndex(year, 3).getJulianDay().getSolarTime())) {
      index += 24;
    }
    return LunarMonth.fromYm(year, 1).getSixtyCycle().next((int) Math.floor(index * 1D / 2));
  }

  /**
   * 当时的日干支（23:00开始算做第二天）
   *
   * @return 干支
   */
  public SixtyCycle getDaySixtyCycle() {
    SixtyCycle d = day.getSixtyCycle();
    return hour < 23 ? d : d.next(1);
  }

  /**
   * 干支
   *
   * @return 干支
   */
  public SixtyCycle getSixtyCycle() {
    int earthBranchIndex = getIndexInDay() % 12;
    int heavenStemIndex = getDaySixtyCycle().getHeavenStem().getIndex() % 5 * 2 + earthBranchIndex;
    return SixtyCycle.fromName(HeavenStem.fromIndex(heavenStemIndex).getName() + EarthBranch.fromIndex(earthBranchIndex).getName());
  }

  /**
   * 黄道黑道十二神
   *
   * @return 黄道黑道十二神
   */
  public TwelveStar getTwelveStar() {
    return TwelveStar.fromIndex(getSixtyCycle().getEarthBranch().getIndex() + (8 - getDaySixtyCycle().getEarthBranch().getIndex() % 6) * 2);
  }

  /**
   * 九星（时家紫白星歌诀：三元时白最为佳，冬至阳生顺莫差，孟日七宫仲一白，季日四绿发萌芽，每把时辰起甲子，本时星耀照光华，时星移入中宫去，顺飞八方逐细查。夏至阴生逆回首，孟归三碧季加六，仲在九宫时起甲，依然掌中逆轮跨。）
   *
   * @return 九星
   */
  public NineStar getNineStar() {
    SolarDay solar = day.getSolarDay();
    SolarTerm dongZhi = SolarTerm.fromIndex(solar.getYear(), 0);
    SolarTerm xiaZhi = dongZhi.next(12);
    boolean asc = !solar.isBefore(dongZhi.getJulianDay().getSolarDay()) && solar.isBefore(xiaZhi.getJulianDay().getSolarDay());
    int start = new int[]{8, 5, 2}[day.getSixtyCycle().getEarthBranch().getIndex() % 3];
    if (asc) {
      start = 8 - start;
    }
    int earthBranchIndex = getIndexInDay() % 12;
    return NineStar.fromIndex(start + (asc ? earthBranchIndex : -earthBranchIndex));
  }

  /**
   * 公历时刻
   *
   * @return 公历时刻
   */
  public SolarTime getSolarTime() {
    SolarDay d = day.getSolarDay();
    return SolarTime.fromYmdHms(d.getYear(), d.getMonth(), d.getDay(), hour, minute, second);
  }

  /**
   * 八字
   *
   * @return 八字
   */
  public EightChar getEightChar() {
    return new EightChar(getYearSixtyCycle(), getMonthSixtyCycle(), getDaySixtyCycle(), getSixtyCycle());
  }

  /**
   * 宜
   *
   * @return 宜忌列表
   */
  public List<Taboo> getRecommends() {
    return Taboo.getHourRecommends(getDaySixtyCycle(), getSixtyCycle());
  }

  /**
   * 忌
   *
   * @return 宜忌列表
   */
  public List<Taboo> getAvoids() {
    return Taboo.getHourAvoids(getDaySixtyCycle(), getSixtyCycle());
  }
}
