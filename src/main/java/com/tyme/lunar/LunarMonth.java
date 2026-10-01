package com.tyme.lunar;

import com.tyme.culture.Direction;
import com.tyme.culture.fetus.FetusMonth;
import com.tyme.culture.ren.MinorRen;
import com.tyme.culture.star.nine.NineStar;
import com.tyme.jd.JulianDay;
import com.tyme.sixtycycle.SixtyCycle;
import com.tyme.solar.SolarTerm;
import com.tyme.unit.AbstractYear;
import com.tyme.unit.AbstractLeapMonth;
import com.tyme.util.ShouXingUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 农历月
 *
 * @author 6tail
 */
public class LunarMonth extends AbstractLeapMonth {
  public static final String[] NAMES = {"正月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};

  public static void validate(int year, int month) {
    if (month == 0 || month > 12 || month < -12) {
      throw new IllegalArgumentException("illegal lunar month: " + month);
    }
    // 闰月检查
    if (month < 0 && -month != new LunarYear(year).getLeapMonth()) {
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
    super(year, month);
    validate(year, month);
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
    return new LunarYear(year);
  }

  @Override
  public AbstractYear getAbstractYear() {
    return getLunarYear();
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
    } else if (new LunarYear(year - 1).getLeapMonth() > 10 && year != 239 && year != 240) {
      offset = 3;
    }

    // 本月初一
    return w + 29.5306 * (offset + getIndexInYear());
  }

  public int getDayCount() {
    // 大月30天，小月29天
    double w = getNewMoon();
    // 本月天数 = 下月初一 - 本月初一
    return (int) (ShouXingUtil.calcShuo(w + 29.5306) - ShouXingUtil.calcShuo(w));
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
   * 依据国家标准《农历的编算和颁行》GB/T 33661-2017中农历月的命名方法。
   *
   * @return 名称
   */
  public String getName() {
    return (leap ? "闰" : "") + NAMES[month - 1];
  }

  public LunarMonth next(int n) {
    AbstractLeapMonth m = super.next(n);
    return fromYm(m.getYear(), m.getMonthValue());
  }

  /**
   * 本月的农历日列表
   *
   * @return 农历日列表
   */
  public List<LunarDay> getDays() {
    int size = getDayCount();
    int m = getMonthValue();
    List<LunarDay> l = new ArrayList<>(size);
    for (int i = 1; i <= size; i++) {
      l.add(new LunarDay(year, m, i));
    }
    return l;
  }

  /**
   * 初一
   *
   * @return 农历日
   */
  public LunarDay getFirstDay() {
    return new LunarDay(year, getMonthValue(), 1);
  }

  /**
   * 本月的农历周列表
   *
   * @param start 星期几作为一周的开始，1234560分别代表星期一至星期天
   * @return 周列表
   */
  public List<LunarWeek> getWeeks(int start) {
    int size = getWeekCount(start);
    int m = getMonthValue();
    List<LunarWeek> l = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      l.add(new LunarWeek(year, m, i, start));
    }
    return l;
  }

  /**
   * 干支
   *
   * @return 干支
   */
  public SixtyCycle getSixtyCycle() {
    return SixtyCycle.fromIndex(year * 12 + month - 47);
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
