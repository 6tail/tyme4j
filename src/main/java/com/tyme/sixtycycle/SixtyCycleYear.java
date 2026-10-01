package com.tyme.sixtycycle;

import com.tyme.unit.AbstractTraditionalYear;

import java.util.ArrayList;
import java.util.List;

/**
 * 干支年
 *
 * @author 6tail
 */
public class SixtyCycleYear extends AbstractTraditionalYear {

  public static void validate(int year) {
    validateRange(year, -1, 9999, "sixty cycle year");
  }

  public SixtyCycleYear(int year) {
    super(year);
    validate(year);
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

  public String getName() {
    return getSixtyCycle() + "年";
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
