package com.tyme.culture;

import com.tyme.AbstractCulture;
import com.tyme.lunar.LunarDay;
import com.tyme.sixtycycle.SixtyCycle;

/**
 * 灶马头(灶神的坐骑)
 *
 * @author 6tail
 */
public class KitchenGodSteed extends AbstractCulture {

  public static final String[] NUMBERS = {"一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"};

  /**
   * 正月初一的干支
   */
  protected SixtyCycle firstDaySixtyCycle;

  public KitchenGodSteed(int lunarYear) {
    firstDaySixtyCycle = new LunarDay(lunarYear, 1, 1).getSixtyCycle();
  }

  public static KitchenGodSteed fromLunarYear(int lunarYear) {
    return new KitchenGodSteed(lunarYear);
  }

  protected String byHeavenStem(int n) {
    return NUMBERS[firstDaySixtyCycle.getHeavenStem().stepsTo(n)];
  }

  protected String byEarthBranch(int n) {
    return NUMBERS[firstDaySixtyCycle.getEarthBranch().stepsTo(n)];
  }

  /**
   * 几鼠偷粮
   *
   * @return 几鼠偷粮
   */
  public String getMouse() {
    return byEarthBranch(0) + "鼠偷粮";
  }

  /**
   * 草子几分
   *
   * @return 草子几分
   */
  public String getGrass() {
    return String.format("草子%s分", byEarthBranch(0));
  }

  /**
   * 几牛耕田（正月第一个丑日是初几，就是几牛耕田）
   *
   * @return 几牛耕田
   */
  public String getCattle() {
    return byEarthBranch(1) + "牛耕田";
  }

  /**
   * 花收几分
   *
   * @return 花收几分
   */
  public String getFlower() {
    return String.format("花收%s分", byEarthBranch(3));
  }

  /**
   * 几龙治水（正月第一个辰日是初几，就是几龙治水）
   *
   * @return 几龙治水
   */
  public String getDragon() {
    return byEarthBranch(4) + "龙治水";
  }

  /**
   * 几马驮谷
   *
   * @return 几马驮谷
   */
  public String getHorse() {
    return byEarthBranch(6) + "马驮谷";
  }

  /**
   * 几鸡抢米
   *
   * @return 几鸡抢米
   */
  public String getChicken() {
    return byEarthBranch(9) + "鸡抢米";
  }

  /**
   * 几姑看蚕
   *
   * @return 几姑看蚕
   */
  public String getSilkworm() {
    return byEarthBranch(9) + "姑看蚕";
  }

  /**
   * 几屠共猪
   *
   * @return 几屠共猪
   */
  public String getPig() {
    return byEarthBranch(11) + "屠共猪";
  }

  /**
   * 甲田几分
   *
   * @return 甲田几分
   */
  public String getField() {
    return String.format("甲田%s分", byHeavenStem(0));
  }

  /**
   * 几人分饼（正月第一个丙日是初几，就是几人分饼）
   *
   * @return 几人分饼
   */
  public String getCake() {
    return byHeavenStem(2) + "人分饼";
  }

  /**
   * 几日得金（正月第一个辛日是初几，就是几日得金）
   *
   * @return 几日得金
   */
  public String getGold() {
    return byHeavenStem(7) + "日得金";
  }

  /**
   * 几人几丙
   *
   * @return 几人几丙
   */
  public String getPeopleCakes() {
    return String.format("%s人%s丙", byEarthBranch(2), byHeavenStem(2));
  }

  /**
   * 几人几锄
   *
   * @return 几人几锄
   */
  public String getPeopleHoes() {
    return String.format("%s人%s锄", byEarthBranch(2), byHeavenStem(3));
  }

  @Override
  public String getName() {
    return "灶马头";
  }
}
