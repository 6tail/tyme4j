package com.tyme.solar;

import com.tyme.culture.Constellation;
import com.tyme.culture.Phase;
import com.tyme.culture.PhaseDay;
import com.tyme.culture.Week;
import com.tyme.culture.dog.Dog;
import com.tyme.culture.dog.DogDay;
import com.tyme.culture.nine.Nine;
import com.tyme.culture.nine.NineDay;
import com.tyme.culture.phenology.Phenology;
import com.tyme.culture.phenology.PhenologyDay;
import com.tyme.culture.plumrain.PlumRain;
import com.tyme.culture.plumrain.PlumRainDay;
import com.tyme.culture.star.nine.NineStar;
import com.tyme.enums.HideHeavenStemType;
import com.tyme.event.Event;
import com.tyme.festival.SolarFestival;
import com.tyme.hijri.HijriDay;
import com.tyme.holiday.LegalHoliday;
import com.tyme.jd.JulianDay;
import com.tyme.lunar.LunarDay;
import com.tyme.lunar.LunarMonth;
import com.tyme.rabbyung.RabByungDay;
import com.tyme.sixtycycle.HideHeavenStem;
import com.tyme.sixtycycle.HideHeavenStemDay;
import com.tyme.sixtycycle.SixtyCycleDay;
import com.tyme.unit.DayUnit;

/**
 * 公历日
 *
 * @author 6tail
 */
public class SolarDay extends DayUnit {

  public static final String[] NAMES = {"1日", "2日", "3日", "4日", "5日", "6日", "7日", "8日", "9日", "10日", "11日", "12日", "13日", "14日", "15日", "16日", "17日", "18日", "19日", "20日", "21日", "22日", "23日", "24日", "25日", "26日", "27日", "28日", "29日", "30日", "31日"};

  public static void validate(int year, int month, int day) {
    if (day < 1) {
      throw new IllegalArgumentException(String.format("illegal solar day: %d-%d-%d", year, month, day));
    }
    if (1582 == year && 10 == month) {
      if ((day > 4 && day < 15) || day > 31) {
        throw new IllegalArgumentException(String.format("illegal solar day: %d-%d-%d", year, month, day));
      }
    } else if (day > SolarMonth.fromYm(year, month).getDayCount()) {
      throw new IllegalArgumentException(String.format("illegal solar day: %d-%d-%d", year, month, day));
    }
  }

  /**
   * 初始化
   *
   * @param year  年
   * @param month 月
   * @param day   日
   */
  public SolarDay(int year, int month, int day) {
    validate(year, month, day);
    this.year = year;
    this.month = month;
    this.day = day;
  }

  public static SolarDay fromYmd(int year, int month, int day) {
    return new SolarDay(year, month, day);
  }

  /**
   * 公历月
   *
   * @return 公历月
   */
  public SolarMonth getSolarMonth() {
    return SolarMonth.fromYm(year, month);
  }

  /**
   * 星期
   *
   * @return 星期
   */
  public Week getWeek() {
    return getJulianDay().getWeek();
  }

  /**
   * 星座
   *
   * @return 星座
   */
  public Constellation getConstellation() {
    int y = month * 100 + day;
    return Constellation.fromIndex(y > 1221 || y < 120 ? 9 : y < 219 ? 10 : y < 321 ? 11 : y < 420 ? 0 : y < 521 ? 1 : y < 622 ? 2 : y < 723 ? 3 : y < 823 ? 4 : y < 923 ? 5 : y < 1024 ? 6 : y < 1123 ? 7 : 8);
  }

  public String getName() {
    return NAMES[day - 1];
  }

  @Override
  public String toString() {
    return getSolarMonth() + getName();
  }

  public SolarDay next(int n) {
    return getJulianDay().next(n).getSolarDay();
  }

  /**
   * 是否在指定公历日之前
   *
   * @param target 公历日
   * @return true/false
   */
  public boolean isBefore(SolarDay target) {
    if (year != target.year) {
      return year < target.year;
    }
    return month != target.month ? month < target.month : day < target.day;
  }

  /**
   * 是否在指定公历日之后
   *
   * @param target 公历日
   * @return true/false
   */
  public boolean isAfter(SolarDay target) {
    if (year != target.year) {
      return year > target.year;
    }
    return month != target.month ? month > target.month : day > target.day;
  }

  /**
   * 节气
   *
   * @return 节气
   */
  public SolarTerm getTerm() {
    return getTermDay().getSolarTerm();
  }

  /**
   * 节气第几天
   *
   * @return 节气第几天
   */
  public SolarTermDay getTermDay() {
    int y = year;
    int i = month * 2;
    if (i == 24) {
      y += 1;
      i = 0;
    }
    SolarTerm term = SolarTerm.fromIndex(y, i + 1);
    SolarDay day = term.getSolarDay();
    while (isBefore(day)) {
      term = term.next(-1);
      day = term.getSolarDay();
    }
    return new SolarTermDay(term, subtract(day));
  }

  /**
   * 公历周
   *
   * @param start 起始星期，1234560分别代表星期一至星期天
   * @return 公历周
   */
  public SolarWeek getSolarWeek(int start) {
    return SolarWeek.fromYm(year, month, (int) Math.ceil((day + fromYmd(year, month, 1).getWeek().next(-start).getIndex()) / 7D) - 1, start);
  }

  /**
   * 候
   *
   * @return 候
   */
  public Phenology getPhenology() {
    return getPhenologyDay().getPhenology();
  }

  /**
   * 七十二候
   *
   * @return 七十二候
   */
  public PhenologyDay getPhenologyDay() {
    SolarTermDay d = getTermDay();
    int dayIndex = d.getDayIndex();
    int index = dayIndex / 5;
    if (index > 2) {
      index = 2;
    }
    SolarTerm term = d.getSolarTerm();
    return new PhenologyDay(Phenology.fromIndex(term.getYear(), term.getIndex() * 3 + index), dayIndex - index * 5);
  }

  /**
   * 三伏天
   *
   * @return 三伏天
   */
  public DogDay getDogDay() {
    // 初伏，夏至后第3个庚日
    SolarDay d0 = Event.builder().termHeavenStem(12, 6, 20).build().getSolarDay(year);
    // 中伏，夏至后第4个庚日
    SolarDay d1 = Event.builder().termHeavenStem(12, 6, 30).build().getSolarDay(year);
    // 末伏，立秋后第1个庚日
    SolarDay d2 = Event.builder().termHeavenStem(15, 6, 0).build().getSolarDay(year);
    if (isBefore(d0) || isAfter(d2.next(9))) {
      return null;
    }
    if (!isBefore(d2)) {
      return new DogDay(Dog.fromIndex(2), subtract(d2));
    }
    return isBefore(d1) ? new DogDay(Dog.fromIndex(0), subtract(d0)) : new DogDay(Dog.fromIndex(1), subtract(d1));
  }

  /**
   * 数九天
   *
   * @return 数九天
   */
  public NineDay getNineDay() {
    SolarDay start = SolarTerm.fromIndex(year + 1, 0).getSolarDay();
    if (isBefore(start)) {
      start = SolarTerm.fromIndex(year, 0).getSolarDay();
    }
    SolarDay end = start.next(81);
    if (isBefore(start) || !isBefore(end)) {
      return null;
    }
    int days = subtract(start);
    return new NineDay(Nine.fromIndex(days / 9), days % 9);
  }

  /**
   * 梅雨天（芒种后的第1个丙日入梅，小暑后的第1个未日出梅）
   *
   * @return 梅雨天
   */
  public PlumRainDay getPlumRainDay() {
    // 入梅，芒种后第1个丙日
    SolarDay start = Event.builder().termHeavenStem(11, 2, 0).build().getSolarDay(year);
    // 出梅，小暑后第1个未日
    SolarDay end = Event.builder().termEarthBranch(13, 7, 0).build().getSolarDay(year);
    if (isBefore(start) || isAfter(end)) {
      return null;
    }
    return equals(end) ? new PlumRainDay(PlumRain.fromIndex(1), 0) : new PlumRainDay(PlumRain.fromIndex(0), subtract(start));
  }

  /**
   * 人元司令分野
   *
   * @return 人元司令分野
   */
  public HideHeavenStemDay getHideHeavenStemDay() {
    int[] dayCounts = {3, 5, 7, 9, 10, 30};
    SolarTerm term = getTerm();
    if (term.isQi()) {
      term = term.next(-1);
    }
    int dayIndex = subtract(term.getSolarDay());
    int startIndex = (term.getIndex() - 1) * 3;
    String data = "93705542220504xx1513904541632524533533105544806564xx7573304542018584xx95".substring(startIndex, startIndex + 6);
    int days = 0;
    int heavenStemIndex = 0;
    int typeIndex = 0;
    while (typeIndex < 3) {
      int i = typeIndex * 2;
      String d = data.substring(i, i + 1);
      int count = 0;
      if (!d.equals("x")) {
        heavenStemIndex = Integer.parseInt(d);
        count = dayCounts[Integer.parseInt(data.substring(i + 1, i + 2))];
        days += count;
      }
      if (dayIndex <= days) {
        dayIndex -= days - count;
        break;
      }
      typeIndex++;
    }
    return new HideHeavenStemDay(new HideHeavenStem(heavenStemIndex, HideHeavenStemType.fromCode(typeIndex)), dayIndex);
  }

  /**
   * 位于当年的索引
   *
   * @return 索引
   */
  public int getIndexInYear() {
    return subtract(fromYmd(year, 1, 1));
  }

  /**
   * 公历日期相减，获得相差天数
   *
   * @param target 公历日
   * @return 天数
   */
  public int subtract(SolarDay target) {
    return (int) (getJulianDay().subtract(target.getJulianDay()));
  }

  /**
   * 儒略日
   *
   * @return 儒略日
   */
  public JulianDay getJulianDay() {
    return JulianDay.fromYmdHms(year, month, day, 0, 0, 0);
  }

  /**
   * 农历日
   *
   * @return 农历日
   */
  public LunarDay getLunarDay() {
    LunarMonth m = LunarMonth.fromYm(year, month);
    int days = subtract(m.getFirstJulianDay().getSolarDay());
    while (days < 0) {
      m = m.next(-1);
      days += m.getDayCount();
    }
    return LunarDay.fromYmd(m.getYear(), m.getMonthWithLeap(), days + 1);
  }

  /**
   * 干支日
   *
   * @return 干支日
   */
  public SixtyCycleDay getSixtyCycleDay() {
    return SixtyCycleDay.fromSolarDay(this);
  }

  /**
   * 藏历日
   *
   * @return 藏历日
   */
  public RabByungDay getRabByungDay() {
    return RabByungDay.fromSolarDay(this);
  }

  /**
   * 法定假日，如果当天不是法定假日，返回null
   *
   * @return 法定假日
   */
  public LegalHoliday getLegalHoliday() {
    return LegalHoliday.fromYmd(year, month, day);
  }

  /**
   * 公历现代节日，如果当天不是公历现代节日，返回null
   *
   * @return 公历现代节日
   */
  public SolarFestival getFestival() {
    return SolarFestival.fromYmd(year, month, day);
  }

  /**
   * 月相第几天
   *
   * @return 月相第几天
   */
  public PhaseDay getPhaseDay() {
    LunarMonth month = getLunarDay().getLunarMonth().next(1);
    Phase p = Phase.fromIndex(month.getYear(), month.getMonthWithLeap(), 0);
    SolarDay d = p.getSolarDay();
    while (d.isAfter(this)) {
      p = p.next(-1);
      d = p.getSolarDay();
    }
    return new PhaseDay(p, subtract(d));
  }

  /**
   * 月相
   *
   * @return 月相
   */
  public Phase getPhase() {
    return getPhaseDay().getPhase();
  }

  /**
   * 九星（在冬至前后找到最近的甲子日为一白，往后二黑依次顺推；在夏至前后找到最近的甲子日为九紫，往后八白依次逆推。）
   *
   * @return 九星
   */
  public NineStar getNineStar() {
    SolarDay winterSolstice = SolarTerm.fromIndex(year, 0).getSolarDay();
    SolarDay summerSolstice = SolarTerm.fromIndex(year, 12).getSolarDay();
    SolarDay nextWinterSolstice = SolarTerm.fromIndex(year + 1, 0).getSolarDay();
    // 距冬至最近的甲子日
    SolarDay w = winterSolstice.next(winterSolstice.getLunarDay().getSixtyCycle().stepsCloseTo(0));
    // 距夏至最近的甲子日
    SolarDay s = summerSolstice.next(summerSolstice.getLunarDay().getSixtyCycle().stepsCloseTo(0));
    // 距下个冬至最近的甲子日
    SolarDay n = nextWinterSolstice.next(nextWinterSolstice.getLunarDay().getSixtyCycle().stepsCloseTo(0));
    // 43210012345678876543210012345
    //      w        s        n
    //     冬至     夏至      冬至
    if (isBefore(w)) {
      return NineStar.fromIndex(w.subtract(this) - 1);
    }
    if (isBefore(s)) {
      return NineStar.fromIndex(subtract(w));
    }
    return NineStar.fromIndex(isBefore(n) ? n.subtract(this) - 1 : subtract(n));
  }

  /**
   * 回历日
   *
   * @return 回历日
   */
  public HijriDay getHijriDay() {
    int d = subtract(new SolarDay(622, 7, 16));
    int z = Math.floorDiv(d, 10631);
    d -= z * 10631;
    int y = (int) Math.floor((d + 0.5) / 354.366);
    d -= (int) Math.floor(y * 354.366 + 0.5);
    int m = (int) Math.floor((d + 0.11) / 29.51);
    d -= (int) Math.floor(m * 29.5 + 0.5);
    return new HijriDay(z * 30 + y + 1, m + 1, d + 1);
  }
}
