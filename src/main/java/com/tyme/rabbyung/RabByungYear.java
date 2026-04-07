package com.tyme.rabbyung;

import com.tyme.AbstractTyme;
import com.tyme.culture.Zodiac;
import com.tyme.sixtycycle.SixtyCycle;
import com.tyme.solar.SolarYear;

import java.util.ArrayList;
import java.util.List;

/**
 * 藏历年(公历1027年为藏历元年，第一饶迥火兔年）
 *
 * @author 6tail
 */
public class RabByungYear extends AbstractTyme {

  /**
   * 饶迥(胜生周)序号，从0开始
   */
  protected int rabByungIndex;

  /**
   * 五行索引，从0开始
   */
  protected int elementIndex;

  /**
   * 生肖索引，从0开始
   */
  protected int zodiacIndex;

  public static void validate(int year) {
    if (year < 1027 || year > 9999) {
      throw new IllegalArgumentException("illegal rab-byung year: " + year);
    }
  }

  public RabByungYear(int rabByungIndex, int elementIndex, int zodiacIndex) {
    if (rabByungIndex < 0 || rabByungIndex > 150) {
      throw new IllegalArgumentException("illegal rab-byung index: " + rabByungIndex);
    }
    if (elementIndex < 0 || elementIndex >= RabByungElement.NAMES.length) {
      throw new IllegalArgumentException("illegal element index: " + elementIndex);
    }
    if (zodiacIndex < 0 || zodiacIndex >= Zodiac.NAMES.length) {
      throw new IllegalArgumentException("illegal zodiac index: " + zodiacIndex);
    }
    this.rabByungIndex = rabByungIndex;
    this.elementIndex = elementIndex;
    this.zodiacIndex = zodiacIndex;
  }

  public static RabByungYear fromSixtyCycle(int rabByungIndex, SixtyCycle sixtyCycle) {
    return new RabByungYear(rabByungIndex, sixtyCycle.getHeavenStem().getElement().getIndex(), sixtyCycle.getEarthBranch().getZodiac().getIndex());
  }

  public static RabByungYear fromElementZodiac(int rabByungIndex, RabByungElement element, Zodiac zodiac) {
    return new RabByungYear(rabByungIndex, element.getIndex(), zodiac.getIndex());
  }

  public static RabByungYear fromYear(int year) {
    validate(year);
    return fromSixtyCycle((year - 1024) / 60, SixtyCycle.fromIndex(year - 4));
  }

  /**
   * 饶迥序号
   *
   * @return 数字，从0开始
   */
  public int getRabByungIndex() {
    return rabByungIndex;
  }

  /**
   * 干支
   *
   * @return 干支
   */
  public SixtyCycle getSixtyCycle() {
    return SixtyCycle.fromIndex(6 * (elementIndex * 2 + zodiacIndex % 2) - 5 * zodiacIndex);
  }

  /**
   * 生肖
   *
   * @return 生肖
   */
  public Zodiac getZodiac() {
    return Zodiac.fromIndex(zodiacIndex);
  }

  /**
   * 五行
   *
   * @return 藏历五行
   */
  public RabByungElement getElement() {
    return RabByungElement.fromIndex(elementIndex);
  }

  /**
   * 名称
   *
   * @return 名称
   */
  public String getName() {
    String[] digits = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    String[] units = {"", "十", "百"};
    int n = rabByungIndex + 1;
    StringBuilder s = new StringBuilder();
    int pos = 0;
    while (n > 0) {
      int digit = n % 10;
      if (digit > 0) {
        s.insert(0, digits[digit] + units[pos]);
      } else if (s.length() > 0) {
        s.insert(0, digits[digit]);
      }
      n /= 10;
      pos++;
    }
    if (0 == s.indexOf("一十")) {
      s.delete(0, 1);
    }
    return String.format("第%s饶迥%s%s年", s, getElement(), getZodiac());
  }

  public RabByungYear next(int n) {
    return fromYear(getYear() + n);
  }

  /**
   * 年
   *
   * @return 年
   */
  public int getYear() {
    return 1024 + rabByungIndex * 60 + getSixtyCycle().getIndex();
  }

  /**
   * 闰月
   *
   * @return 闰月数字，1代表闰1月，0代表无闰月
   */
  public int getLeapMonth() {
    int y = 1;
    int m = 4;
    int t = 1;
    int currentYear = getYear();
    while (y < currentYear) {
      int i = m + 31 + t;
      y += 2;
      m = i - 23;
      if (i > 35) {
        y += 1;
        m -= 12;
      }
      t = 1 - t;
    }
    return y == currentYear ? m : 0;
  }

  /**
   * 公历年
   *
   * @return 公历年
   */
  public SolarYear getSolarYear() {
    return SolarYear.fromYear(getYear());
  }

  /**
   * 首月
   *
   * @return 藏历月
   */
  public RabByungMonth getFirstMonth() {
    return RabByungMonth.fromYm(getYear(), 1);
  }

  /**
   * 月份数量
   *
   * @return 数量
   */
  public int getMonthCount() {
    return getLeapMonth() < 1 ? 12 : 13;
  }

  /**
   * 藏历月列表
   *
   * @return 藏历月列表，一般有12个月，当年有闰月时，有13个月。
   */
  public List<RabByungMonth> getMonths() {
    List<RabByungMonth> l = new ArrayList<>(13);
    int y = getYear();
    int leapMonth = getLeapMonth();
    for (int i = 1; i < 13; i++) {
      l.add(RabByungMonth.fromYm(y, i));
      if (i == leapMonth) {
        l.add(RabByungMonth.fromYm(y, -i));
      }
    }
    return l;
  }
}
