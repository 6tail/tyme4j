package com.tyme.sixtycycle;

import com.tyme.AbstractTyme;
import com.tyme.culture.Direction;
import com.tyme.culture.Twenty;
import com.tyme.culture.star.nine.NineStar;

import java.util.ArrayList;
import java.util.List;

/**
 * 干支年
 *
 * @author 6tail
 */
public class SixtyCycleYear extends AbstractTyme {

  /**
   * 年
   */
  protected int year;

  public SixtyCycleYear(int year) {
    validateRange(year, -1, 9999, "sixty cycle year");
    this.year = year;
  }

  /**
   * 从年初始化
   *
   * @param year 年，支持-1到9999年
   * @return 干支年
   */
  public static SixtyCycleYear fromYear(int year) {
    return new SixtyCycleYear(year);
  }

  /**
   * 年
   *
   * @return 年
   */
  public int getYear() {
    return year;
  }

  /**
   * 干支
   *
   * @return 干支
   */
  public SixtyCycle getSixtyCycle() {
    return SixtyCycle.fromIndex(year - 4);
  }

  public String getName() {
    return getSixtyCycle() + "年";
  }

  /**
   * 运
   *
   * @return 运
   */
  public Twenty getTwenty() {
    return Twenty.fromIndex((int) Math.floor((year - 1864) / 20D));
  }

  /**
   * 九星
   *
   * @return 九星
   */
  public NineStar getNineStar() {
    return NineStar.fromIndex(63 + getTwenty().getSixty().getIndex() * 3 - getSixtyCycle().getIndex());
  }

  /**
   * 太岁方位
   *
   * @return 方位
   */
  public Direction getJupiterDirection() {
    return Direction.fromIndex(new int[]{0, 7, 7, 2, 3, 3, 8, 1, 1, 6, 0, 0}[getSixtyCycle().getEarthBranch().getIndex()]);
  }

  /**
   * 推移
   *
   * @param n 推移年数
   * @return 干支年
   */
  public SixtyCycleYear next(int n) {
    return fromYear(year + n);
  }

  /**
   * 首月（五虎遁：甲己之年丙作首，乙庚之岁戊为头，丙辛必定寻庚起，丁壬壬位顺行流，若问戊癸何方发，甲寅之上好追求。）
   *
   * @return 干支月
   */
  public SixtyCycleMonth getFirstMonth() {
    return new SixtyCycleMonth(this, SixtyCycle.fromIndex(year * 12 - 46));
  }

  /**
   * 干支月列表
   *
   * @return 干支月列表
   */
  public List<SixtyCycleMonth> getMonths() {
    List<SixtyCycleMonth> l = new ArrayList<>();
    SixtyCycleMonth m = getFirstMonth();
    l.add(m);
    for (int i = 1; i < 12; i++) {
      l.add(m.next(i));
    }
    return l;
  }

}
