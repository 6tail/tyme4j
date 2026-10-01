package com.tyme.rabbyung;

import com.tyme.culture.Zodiac;
import com.tyme.sixtycycle.SixtyCycle;
import com.tyme.solar.SolarYear;
import com.tyme.unit.AbstractTraditionalYear;

import java.util.ArrayList;
import java.util.List;

/**
 * 藏历年(公历1027年为藏历元年，第一饶迥火兔年）
 *
 * @author 6tail
 */
public class RabByungYear extends AbstractTraditionalYear {

  public static void validate(int year) {
    validateRange(year, 1027, 9999, "rab-byung year");
  }

  public RabByungYear(int year) {
    super(year);
    validate(year);
  }

  public static RabByungYear fromSixtyCycle(int rabByungIndex, SixtyCycle sixtyCycle) {
    return fromYear(1024 + rabByungIndex * 60 + sixtyCycle.getIndex());
  }

  public static RabByungYear fromElementZodiac(int rabByungIndex, RabByungElement element, Zodiac zodiac) {
    return fromSixtyCycle(rabByungIndex, SixtyCycle.fromIndex(6 * (element.getIndex() * 2 + zodiac.getIndex() % 2) - 5 * zodiac.getIndex()));
  }

  public static RabByungYear fromYear(int year) {
    return new RabByungYear(year);
  }

  /**
   * 饶迥序号
   *
   * @return 数字，从0开始
   */
  public int getRabByungIndex() {
    return (year - 1024) / 60;
  }

  /**
   * 生肖
   *
   * @return 生肖
   */
  public Zodiac getZodiac() {
    return getSixtyCycle().getEarthBranch().getZodiac();
  }

  /**
   * 五行
   *
   * @return 藏历五行
   */
  public RabByungElement getElement() {
    return RabByungElement.fromIndex(getSixtyCycle().getHeavenStem().getElement().getIndex());
  }

  /**
   * 名称
   *
   * @return 名称
   */
  public String getName() {
    int n = getRabByungIndex() + 1;
    String d = "零一二三四五六七八九";
    String s = n > 99 ? d.charAt(n / 100) + "百" : "";
    n %= 100;
    return String.format("第%s饶迥%s%s年", n == 0 ? s : n < 10 ? s + (s.isEmpty() ? "" : "零") + d.charAt(n) : s + (n < 20 ? (s.isEmpty() ? "" : "一") : d.charAt(n / 10)) + "十" + (n % 10 > 0 ? d.charAt(n % 10) + "" : ""), getElement(), getZodiac());
  }

  public RabByungYear next(int n) {
    return fromYear(year + n);
  }

  @Override
  public int getLeapMonth() {
    int y = 1;
    int m = 4;
    int t = 1;
    while (y < year) {
      int i = m + 31 + t;
      y += 2;
      m = i - 23;
      if (i > 35) {
        y += 1;
        m -= 12;
      }
      t = 1 - t;
    }
    return y == year ? m : 0;
  }

  /**
   * 公历年
   *
   * @return 公历年
   */
  public SolarYear getSolarYear() {
    return new SolarYear(year);
  }

  /**
   * 首月
   *
   * @return 藏历月
   */
  public RabByungMonth getFirstMonth() {
    return new RabByungMonth(year, 1);
  }

  /**
   * 藏历月列表
   *
   * @return 藏历月列表，一般有12个月，当年有闰月时，有13个月。
   */
  public List<RabByungMonth> getMonths() {
    List<RabByungMonth> l = new ArrayList<>(13);
    int leapMonth = getLeapMonth();
    for (int i = 1; i < 13; i++) {
      l.add(new RabByungMonth(year, i));
      if (i == leapMonth) {
        l.add(new RabByungMonth(year, -i));
      }
    }
    return l;
  }
}
