package com.tyme.festival;

import com.tyme.AbstractTyme;
import com.tyme.enums.FestivalType;
import com.tyme.solar.SolarDay;
import com.tyme.solar.SolarMonth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 公历现代节日
 *
 * @author 6tail
 */
public class SolarFestival extends AbstractTyme {

  public static final String[] NAMES = {"元旦", "三八妇女节", "植树节", "五一劳动节", "五四青年节", "六一儿童节", "建党节", "八一建军节", "教师节", "国庆节"};

  public static String DATA = "@00001011950@01003081950@02003121979@03005011950@04005041950@05006011950@06007011941@07008011933@08009101985@09010011950";

  /**
   * 类型
   */
  protected FestivalType type;

  /**
   * 索引
   */
  protected int index;

  /**
   * 公历日
   */
  protected SolarDay day;

  /**
   * 名称
   */
  protected String name;

  /**
   * 起始年
   */
  protected int startYear;

  public SolarFestival(FestivalType type, SolarDay day, int startYear, String data) {
    this.type = type;
    this.day = day;
    this.startYear = startYear;
    index = Integer.parseInt(data.substring(1, 3), 10);
    name = NAMES[index];
  }

  public static SolarFestival fromIndex(int year, int index) {
    if (index < 0 || index >= NAMES.length) {
      throw new IllegalArgumentException(String.format("illegal index: %d", index));
    }
    Matcher matcher = Pattern.compile(String.format("@%02d\\d+", index)).matcher(DATA);
    if (!matcher.find()) {
      return null;
    }
    String data = matcher.group();
    FestivalType type = FestivalType.fromCode(data.charAt(3) - '0');
    if (type != FestivalType.DAY) {
      return null;
    }
    int startYear = Integer.parseInt(data.substring(8), 10);
    return year < startYear ? null : new SolarFestival(type, SolarDay.fromYmd(year, Integer.parseInt(data.substring(4, 6), 10), Integer.parseInt(data.substring(6, 8), 10)), startYear, data);
  }

  public static SolarFestival fromYmd(int year, int month, int day) {
    Matcher matcher = Pattern.compile(String.format("@\\d{2}0%02d%02d\\d+", month, day)).matcher(DATA);
    if (!matcher.find()) {
      return null;
    }
    String data = matcher.group();
    int startYear = Integer.parseInt(data.substring(8), 10);
    return year < startYear ? null : new SolarFestival(FestivalType.DAY, SolarDay.fromYmd(year, month, day), startYear, data);
  }

  public SolarFestival next(int n) {
    SolarMonth m = day.getMonth();
    int year = m.getYear().getYear();
    if (n == 0) {
      return fromYmd(year, m.getMonth(), day.getDay());
    }
    int size = NAMES.length;
    int t = index + n;
    int offset = indexOf(t, size);
    if (t < 0) {
      t -= size;
    }
    return fromIndex(year + t / size, offset);
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
   * 公历日
   *
   * @return 公历日
   */
  public SolarDay getDay() {
    return day;
  }

  /**
   * 起始年
   *
   * @return 年
   */
  public int getStartYear() {
    return startYear;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return String.format("%s %s", day, name);
  }

}
