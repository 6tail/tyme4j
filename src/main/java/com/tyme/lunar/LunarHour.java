package com.tyme.lunar;

import com.tyme.AbstractTyme;
import com.tyme.eightchar.EightChar;
import com.tyme.culture.star.nine.NineStar;
import com.tyme.sixtycycle.EarthBranch;
import com.tyme.sixtycycle.HeavenStem;
import com.tyme.sixtycycle.SixtyCycle;
import com.tyme.solar.SolarDay;
import com.tyme.solar.SolarMonth;
import com.tyme.solar.SolarTerm;
import com.tyme.solar.SolarTime;

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
  protected LunarHour(int year, int month, int day, int hour, int minute, int second) {
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
  public LunarDay getDay() {
    return day;
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
    LunarMonth month = d.getMonth();
    return fromYmdHms(month.getYear().getYear(), month.getMonthWithLeap(), d.getDay(), hour, minute, second);
  }

  /**
   * 是否在指定农历时辰之前
   *
   * @param target 农历时辰
   * @return true/false
   */
  public boolean isBefore(LunarHour target) {
    if (!day.equals(target.getDay())) {
      return day.isBefore(target.getDay());
    }
    int bHour = target.getHour();
    if (hour == bHour) {
      int bMinute = target.getMinute();
      return minute == bMinute ? second < target.getSecond() : minute < bMinute;
    }
    return hour < bHour;
  }

  /**
   * 是否在指定农历时辰之后
   *
   * @param target 农历时辰
   * @return true/false
   */
  public boolean isAfter(LunarHour target) {
    if (!day.equals(target.getDay())) {
      return day.isAfter(target.getDay());
    }
    int bHour = target.getHour();
    if (hour == bHour) {
      int bMinute = target.getMinute();
      return minute == bMinute ? second > target.getSecond() : minute > bMinute;
    }
    return hour > bHour;
  }

  /**
   * 当时的年干支
   *
   * @return 干支
   */
  public SixtyCycle getYearSixtyCycle() {
    int solarYear = day.getSolarDay().getMonth().getYear().getYear();
    SolarTerm spring = SolarTerm.fromIndex(solarYear, 3);
    LunarYear lunarYear = day.getMonth().getYear();
    SixtyCycle year = lunarYear.getSixtyCycle();
    if (lunarYear.getYear() < solarYear) {
      year = year.next(1);
    }
    if (getSolarTime().isBefore(spring.getJulianDay().getSolarTime())) {
      year = year.next(-1);
    }
    return year;
  }

  /**
   * 当时的月干支
   *
   * @return 干支
   */
  public SixtyCycle getMonthSixtyCycle() {
    SolarTerm term = getSolarTime().getTerm();
    if (term.isQi()) {
      term = term.next(-1);
    }
    return term.getJulianDay().getSolarTime().getLunarHour().getDay().getMonth().getSixtyCycle();
  }

  /**
   * 当时的日干支（23:00开始算做第二天）
   *
   * @return 干支
   */
  public SixtyCycle getDaySixtyCycle() {
    SixtyCycle d = day.getSixtyCycle();
    return hour > 22 ? d.next(1) : d;
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
   * 九星（时家紫白星歌诀：三元时白最为佳，冬至阳生顺莫差，孟日七宫仲一白，季日四绿发萌芽，每把时辰起甲子，本时星耀照光华，时星移入中宫去，顺飞八方逐细查。夏至阴生逆回首，孟归三碧季加六，仲在九宫时起甲，依然掌中逆轮跨。）
   *
   * @return 九星
   */
  public NineStar getNineStar() {
    SolarDay solar = day.getSolarDay();
    SolarTerm dongZhi = SolarTerm.fromIndex(solar.getMonth().getYear().getYear(), 0);
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
    SolarMonth m = d.getMonth();
    return SolarTime.fromYmdHms(m.getYear().getYear(), m.getMonth(), d.getDay(), hour, minute, second);
  }

  /**
   * 八字
   *
   * @return 八字
   */
  public EightChar getEightChar() {
    return new EightChar(getYearSixtyCycle(), getMonthSixtyCycle(), getDaySixtyCycle(), getSixtyCycle());
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof LunarHour)) {
      return false;
    }
    LunarHour target = (LunarHour) o;
    return day.equals(target.getDay()) && hour == target.getHour() && minute == target.getMinute() && second == target.getSecond();
  }

}
