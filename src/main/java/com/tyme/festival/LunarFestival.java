package com.tyme.festival;

import com.tyme.AbstractTyme;
import com.tyme.enums.FestivalType;
import com.tyme.lunar.LunarDay;
import com.tyme.solar.SolarTerm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 农历传统节日（依据国家标准《农历的编算和颁行》GB/T 33661-2017）
 *
 * @author 6tail
 */
public class LunarFestival extends AbstractTyme {

  public static final String[] NAMES = {"春节", "元宵节", "龙头节", "上巳节", "清明节", "端午节", "七夕节", "中元节", "中秋节", "重阳节", "冬至节", "腊八节", "除夕"};

  public static String DATA = "@0000101@0100115@0200202@0300303@04107@0500505@0600707@0700715@0800815@0900909@10124@1101208@122";

  /**
   * 类型
   */
  protected FestivalType type;

  /**
   * 索引
   */
  protected int index;

  /**
   * 农历日
   */
  protected LunarDay day;

  /**
   * 节气
   */
  protected SolarTerm solarTerm;

  /**
   * 名称
   */
  protected String name;

  public LunarFestival(FestivalType type, LunarDay day, SolarTerm solarTerm, String data) {
    this.type = type;
    this.day = day;
    this.solarTerm = solarTerm;
    index = Integer.parseInt(data.substring(1, 3), 10);
    name = NAMES[index];
  }

  public static LunarFestival fromIndex(int year, int index) {
    if (index < 0 || index >= NAMES.length) {
      throw new IllegalArgumentException(String.format("illegal index: %d", index));
    }
    Matcher matcher = Pattern.compile(String.format("@%02d\\d+", index)).matcher(DATA);
    if (!matcher.find()) {
      return null;
    }
    String data = matcher.group();
    FestivalType type = FestivalType.fromCode(data.charAt(3) - '0');
    switch (type) {
      case DAY:
        return new LunarFestival(type, LunarDay.fromYmd(year, Integer.parseInt(data.substring(4, 6), 10), Integer.parseInt(data.substring(6), 10)), null, data);
      case TERM:
        SolarTerm solarTerm = SolarTerm.fromIndex(year, Integer.parseInt(data.substring(4), 10));
        return new LunarFestival(type, solarTerm.getJulianDay().getSolarDay().getLunarDay(), solarTerm, data);
      case EVE:
        return new LunarFestival(type, LunarDay.fromYmd(year + 1, 1, 1).next(-1), null, data);
      default:
        return null;
    }
  }

  public static LunarFestival fromYmd(int year, int month, int day) {
    Matcher matcher = Pattern.compile(String.format("@\\d{2}0%02d%02d", month, day)).matcher(DATA);
    if (matcher.find()) {
      return new LunarFestival(FestivalType.DAY, LunarDay.fromYmd(year, month, day), null, matcher.group());
    }
    matcher = Pattern.compile("@\\d{2}1\\d{2}").matcher(DATA);
    while (matcher.find()) {
      String data = matcher.group();
      SolarTerm solarTerm = SolarTerm.fromIndex(year, Integer.parseInt(data.substring(4), 10));
      LunarDay lunarDay = solarTerm.getJulianDay().getSolarDay().getLunarDay();
      if (lunarDay.getYear() == year && lunarDay.getMonth() == month && lunarDay.getDay() == day) {
        return new LunarFestival(FestivalType.TERM, lunarDay, solarTerm, data);
      }
    }
    matcher = Pattern.compile("@\\d{2}2").matcher(DATA);
    if (!matcher.find()) {
      return null;
    }
    LunarDay lunarDay = LunarDay.fromYmd(year, month, day);
    LunarDay nextDay = lunarDay.next(1);
    return nextDay.getMonth() == 1 && nextDay.getDay() == 1 ? new LunarFestival(FestivalType.EVE, lunarDay, null, matcher.group()) : null;
  }

  public LunarFestival next(int n) {
    int size = NAMES.length;
    int i = index + n;
    return fromIndex((day.getYear() * size + i) / size, indexOf(i, size));
  }

  /**
   * 类型
   *
   * @return 节日类型
   */
  public FestivalType getType() {
    return type;
  }

  /**
   * 索引
   *
   * @return 索引
   */
  public int getIndex() {
    return index;
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
   * 节气，非节气返回null
   *
   * @return 节气
   */
  public SolarTerm getSolarTerm() {
    return solarTerm;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return String.format("%s %s", day, name);
  }

}
