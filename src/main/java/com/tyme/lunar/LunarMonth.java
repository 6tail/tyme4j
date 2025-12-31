package com.tyme.lunar;

import com.tyme.culture.Direction;
import com.tyme.culture.fetus.FetusMonth;
import com.tyme.culture.ren.MinorRen;
import com.tyme.culture.star.nine.NineStar;
import com.tyme.jd.JulianDay;
import com.tyme.sixtycycle.EarthBranch;
import com.tyme.sixtycycle.HeavenStem;
import com.tyme.sixtycycle.SixtyCycle;
import com.tyme.solar.SolarTerm;
import com.tyme.unit.MonthUnit;
import com.tyme.util.ShouXingUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 农历月
 *
 * @author 6tail
 */
public class LunarMonth extends MonthUnit {
  public static final String[] NAMES = {"正月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};

  /**
   * 是否闰月
   */
  protected boolean leap;

  public static void validate(int year, int month) {
    if (month == 0 || month > 12 || month < -12) {
      throw new IllegalArgumentException(String.format("illegal lunar month: %d", month));
    }
    // 闰月检查
    if (month < 0 && -month != LunarYear.fromYear(year).getLeapMonth()) {
      throw new IllegalArgumentException(String.format("illegal leap month %d in lunar year %d", -month, year));
    }
  }

  /**
   * 从农历年月初始化
   *
   * @param year  农历年
   * @param month 农历月，闰月为负
   */
  public LunarMonth(int year, int month) {
    validate(year, month);
    this.year = year;
    this.month = Math.abs(month);
    this.leap = month < 0;
  }

  /**
   * 从农历年月初始化
   *
   * @param year  农历年
   * @param month 农历月，闰月为负
   * @return 农历月
   */
  public static LunarMonth fromYm(int year, int month) {
    return new LunarMonth(year, month);
  }

  /**
   * 农历年
   *
   * @return 农历年
   */
  public LunarYear getLunarYear() {
    return LunarYear.fromYear(year);
  }

  /**
   * 月
   *
   * @return 月，当月为闰月时，返回负数
   */
  public int getMonthWithLeap() {
    return leap ? -month : month;
  }

  protected double getNewMoon() {
    // 冬至
    double dongZhiJd = SolarTerm.fromIndex(year, 0).getCursoryJulianDay();

    // 冬至前的初一，今年首朔的日月黄经差
    double w = ShouXingUtil.calcShuo(dongZhiJd);
    if (w > dongZhiJd) {
      w -= 29.53;
    }

    // 正常情况正月初一为第3个朔日，但有些特殊的
    int offset = 2;
    if (year > 8 && year < 24) {
      offset = 1;
    } else if (LunarYear.fromYear(year - 1).getLeapMonth() > 10 && year != 239 && year != 240) {
      offset = 3;
    }

    // 本月初一
    return w + 29.5306 * (offset + getIndexInYear());
  }

  /**
   * 天数(大月30天，小月29天)
   *
   * @return 天数
   */
  public int getDayCount() {
    double w = getNewMoon();
    // 本月天数 = 下月初一 - 本月初一
    return (int) (ShouXingUtil.calcShuo(w + 29.5306) - ShouXingUtil.calcShuo(w));
  }

  /**
   * 位于当年的索引(0-12)
   *
   * @return 索引
   */
  public int getIndexInYear() {
    int index = month - 1;
    if (isLeap()) {
      index += 1;
    } else {
      int leapMonth = LunarYear.fromYear(year).getLeapMonth();
      if (leapMonth > 0 && month > leapMonth) {
        index += 1;
      }
    }
    return index;
  }

  /**
   * 农历季节
   *
   * @return 农历季节
   */
  public LunarSeason getSeason() {
    return LunarSeason.fromIndex(month - 1);
  }

  /**
   * 初一的儒略日
   *
   * @return 儒略日
   */
  public JulianDay getFirstJulianDay() {
    return JulianDay.fromJulianDay(JulianDay.J2000 + ShouXingUtil.calcShuo(getNewMoon()));
  }

  /**
   * 是否闰月
   *
   * @return true/false
   */
  public boolean isLeap() {
    return leap;
  }

  /**
   * 周数
   *
   * @param start 起始星期，1234560分别代表星期一至星期天
   * @return 周数
   */
  public int getWeekCount(int start) {
    return (int) Math.ceil((indexOf(getFirstJulianDay().getWeek().getIndex() - start, 7) + getDayCount()) / 7D);
  }

  /**
   * 依据国家标准《农历的编算和颁行》GB/T 33661-2017中农历月的命名方法。
   *
   * @return 名称
   */
  public String getName() {
    return (leap ? "闰" : "") + NAMES[month - 1];
  }

  @Override
  public String toString() {
    return getLunarYear() + getName();
  }

  public LunarMonth next(int n) {
    if (n == 0) {
      return fromYm(year, getMonthWithLeap());
    }
    int m = getIndexInYear() + 1 + n;
    LunarYear y = getLunarYear();
    if (n > 0) {
      int monthCount = y.getMonthCount();
      while (m > monthCount) {
        m -= monthCount;
        y = y.next(1);
        monthCount = y.getMonthCount();
      }
    } else {
      while (m <= 0) {
        y = y.next(-1);
        m += y.getMonthCount();
      }
    }
    boolean leap = false;
    int leapMonth = y.getLeapMonth();
    if (leapMonth > 0) {
      if (m == leapMonth + 1) {
        leap = true;
      }
      if (m > leapMonth) {
        m--;
      }
    }
    return fromYm(y.getYear(), leap ? -m : m);
  }

  /**
   * 本月的农历日列表
   *
   * @return 农历日列表
   */
  public List<LunarDay> getDays() {
    int size = getDayCount();
    int m = getMonthWithLeap();
    List<LunarDay> l = new ArrayList<>(size);
    for (int i = 1; i <= size; i++) {
      l.add(LunarDay.fromYmd(year, m, i));
    }
    return l;
  }

  /**
   * 初一
   *
   * @return 农历日
   */
  public LunarDay getFirstDay() {
    return LunarDay.fromYmd(year, getMonthWithLeap(), 1);
  }

  /**
   * 本月的农历周列表
   *
   * @param start 星期几作为一周的开始，1234560分别代表星期一至星期天
   * @return 周列表
   */
  public List<LunarWeek> getWeeks(int start) {
    int size = getWeekCount(start);
    int m = getMonthWithLeap();
    List<LunarWeek> l = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      l.add(LunarWeek.fromYm(year, m, i, start));
    }
    return l;
  }

  /**
   * 干支
   *
   * @return 干支
   */
  public SixtyCycle getSixtyCycle() {
    return SixtyCycle.fromName(HeavenStem.fromIndex(getLunarYear().getSixtyCycle().getHeavenStem().getIndex() * 2 + month + 1).getName() + EarthBranch.fromIndex(month + 1).getName());
  }

  /**
   * 九星
   *
   * @return 九星
   */
  public NineStar getNineStar() {
    int index = getSixtyCycle().getEarthBranch().getIndex();
    if (index < 2) {
      index += 3;
    }
    return NineStar.fromIndex(27 - getLunarYear().getSixtyCycle().getEarthBranch().getIndex() % 3 * 3 - index);
  }

  /**
   * 太岁方位
   *
   * @return 方位
   */
  public Direction getJupiterDirection() {
    SixtyCycle sixtyCycle = getSixtyCycle();
    int n = new int[]{7, -1, 1, 3}[sixtyCycle.getEarthBranch().next(-2).getIndex() % 4];
    return n != -1 ? Direction.fromIndex(n) : sixtyCycle.getHeavenStem().getDirection();
  }

  /**
   * 逐月胎神
   *
   * @return 逐月胎神
   */
  public FetusMonth getFetus() {
    return FetusMonth.fromLunarMonth(this);
  }

  /**
   * 小六壬
   *
   * @return 小六壬
   */
  public MinorRen getMinorRen() {
    return MinorRen.fromIndex((month - 1) % 6);
  }
}
