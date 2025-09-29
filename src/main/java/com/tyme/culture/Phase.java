package com.tyme.culture;

import com.tyme.LoopTyme;
import com.tyme.jd.JulianDay;
import com.tyme.lunar.LunarDay;
import com.tyme.lunar.LunarMonth;
import com.tyme.solar.SolarDay;
import com.tyme.solar.SolarTime;
import com.tyme.util.ShouXingUtil;

/**
 * 月相（月日黄经差：新月0，蛾眉月0-90，上弦月90，盈凸月90-180，满月180，亏凸月180-270，下弦月270，残月270-360）
 *
 * @author 6tail
 */
public class Phase extends LoopTyme {

  public static final String[] NAMES = {"新月", "蛾眉月", "上弦月", "盈凸月", "满月", "亏凸月", "下弦月", "残月"};

  /**
   * 农历年
   */
  protected int lunarYear;

  /**
   * 农历月
   */
  protected int lunarMonth;

  public Phase(int lunarYear, int lunarMonth, int index) {
    super(NAMES, index);
    LunarMonth m = LunarMonth.fromYm(lunarYear, lunarMonth).next(index / getSize());
    this.lunarYear = m.getYear();
    this.lunarMonth = m.getMonthWithLeap();
  }

  public Phase(int lunarYear, int lunarMonth, String name) {
    super(NAMES, name);
    this.lunarYear = lunarYear;
    this.lunarMonth = lunarMonth;
  }

  public static Phase fromIndex(int lunarYear, int lunarMonth, int index) {
    return new Phase(lunarYear, lunarMonth, index);
  }

  public static Phase fromName(int lunarYear, int lunarMonth, String name) {
    return new Phase(lunarYear, lunarMonth, name);
  }

  public Phase next(int n) {
    int size = getSize();
    int i = index + n;
    if (i < 0) {
      i -= size;
    }
    i /= size;
    LunarMonth m = LunarMonth.fromYm(lunarYear, lunarMonth);
    if (i != 0) {
      m = m.next(i);
    }
    return fromIndex(m.getYear(), m.getMonthWithLeap(), nextIndex(n));
  }

  protected SolarTime getStartSolarTime() {
    int n = (int) Math.floor((lunarYear - 2000) * 365.2422 / 29.53058886);
    int i = 0;
    double jd = JulianDay.J2000 + ShouXingUtil.ONE_THIRD;
    SolarDay d = LunarDay.fromYmd(lunarYear, lunarMonth, 1).getSolarDay();
    while (true) {
      double t = ShouXingUtil.msaLonT((n + i) * ShouXingUtil.PI_2) * 36525;
      if (!JulianDay.fromJulianDay(jd + t - ShouXingUtil.dtT(t)).getSolarDay().isBefore(d)) {
        break;
      }
      i++;
    }
    int[] r = {0, 90, 180, 270};
    double t = ShouXingUtil.msaLonT((n + i + r[index / 2] / 360D) * ShouXingUtil.PI_2) * 36525;
    return JulianDay.fromJulianDay(jd + t - ShouXingUtil.dtT(t)).getSolarTime();
  }

  /**
   * 公历时刻
   *
   * @return 公历时刻
   */
  public SolarTime getSolarTime() {
    SolarTime t = getStartSolarTime();
    return index % 2 == 1 ? t.next(1) : t;
  }

  /**
   * 公历日
   *
   * @return 公历日
   */
  public SolarDay getSolarDay() {
    SolarDay d = getStartSolarTime().getSolarDay();
    return index % 2 == 1 ? d.next(1) : d;
  }

}
