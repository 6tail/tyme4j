package com.tyme.test;

import com.tyme.culture.star.twentyeight.TwentyEightStar;
import com.tyme.lunar.LunarDay;
import org.junit.Assert;
import org.junit.Test;

/**
 * 农历日测试
 *
 * @author 6tail
 */
public class LunarDayTest {
  @Test
  public void test1() {
    Assert.assertEquals("1年1月1日", LunarDay.fromYmd(0, 11, 18).getSolarDay().toString());
  }

  @Test
  public void test2() {
    Assert.assertEquals("9999年12月31日", LunarDay.fromYmd(9999, 12, 2).getSolarDay().toString());
  }

  @Test
  public void test3() {
    Assert.assertEquals("1905年2月4日", LunarDay.fromYmd(1905, 1, 1).getSolarDay().toString());
  }

  @Test
  public void test4() {
    Assert.assertEquals("2039年1月23日", LunarDay.fromYmd(2038, 12, 29).getSolarDay().toString());
  }

  @Test
  public void test5() {
    Assert.assertEquals("1500年1月31日", LunarDay.fromYmd(1500, 1, 1).getSolarDay().toString());
  }

  @Test
  public void test6() {
    Assert.assertEquals("1501年1月18日", LunarDay.fromYmd(1500, 12, 29).getSolarDay().toString());
  }

  @Test
  public void test7() {
    Assert.assertEquals("1582年10月4日", LunarDay.fromYmd(1582, 9, 18).getSolarDay().toString());
  }

  @Test
  public void test8() {
    Assert.assertEquals("1582年10月15日", LunarDay.fromYmd(1582, 9, 19).getSolarDay().toString());
  }

  @Test
  public void test9() {
    Assert.assertEquals("2020年1月6日", LunarDay.fromYmd(2019, 12, 12).getSolarDay().toString());
  }

  @Test
  public void test10() {
    Assert.assertEquals("2033年12月22日", LunarDay.fromYmd(2033, -11, 1).getSolarDay().toString());
  }

  @Test
  public void test11() {
    Assert.assertEquals("2021年7月16日", LunarDay.fromYmd(2021, 6, 7).getSolarDay().toString());
  }

  @Test
  public void test12() {
    Assert.assertEquals("2034年2月19日", LunarDay.fromYmd(2034, 1, 1).getSolarDay().toString());
  }

  @Test
  public void test13() {
    Assert.assertEquals("2034年1月20日", LunarDay.fromYmd(2033, 12, 1).getSolarDay().toString());
  }

  @Test
  public void test14() {
    Assert.assertEquals("7013年12月24日", LunarDay.fromYmd(7013, -11, 4).getSolarDay().toString());
  }

  @Test
  public void test15() {
    Assert.assertEquals("己亥", LunarDay.fromYmd(2023, 8, 24).getSixtyCycle().toString());
  }

  @Test
  public void test16() {
    Assert.assertEquals("癸酉", LunarDay.fromYmd(1653, 1, 6).getSixtyCycle().toString());
  }

  @Test
  public void test17() {
    Assert.assertEquals("农历庚寅年二月初二", LunarDay.fromYmd(2010, 1, 1).next(31).toString());
  }

  @Test
  public void test18() {
    Assert.assertEquals("农历壬辰年闰四月初一", LunarDay.fromYmd(2012, 3, 1).next(60).toString());
  }

  @Test
  public void test19() {
    Assert.assertEquals("农历壬辰年闰四月廿九", LunarDay.fromYmd(2012, 3, 1).next(88).toString());
  }

  @Test
  public void test20() {
    Assert.assertEquals("农历壬辰年五月初一", LunarDay.fromYmd(2012, 3, 1).next(89).toString());
  }

  @Test
  public void test21() {
    Assert.assertEquals("2020年4月23日", LunarDay.fromYmd(2020, 4, 1).getSolarDay().toString());
  }

  @Test
  public void test22() {
    Assert.assertEquals("甲辰", LunarDay.fromYmd(2024, 1, 1).getLunarMonth().getLunarYear().getSixtyCycle().getName());
  }

  @Test
  public void test23() {
    Assert.assertEquals("癸卯", LunarDay.fromYmd(2023, 12, 30).getLunarMonth().getLunarYear().getSixtyCycle().getName());
  }

  /**
   * 二十八宿
   */
  @Test
  public void test24() {
    LunarDay d = LunarDay.fromYmd(2020, 4, 13);
    TwentyEightStar star = d.getTwentyEightStar();
    Assert.assertEquals("南", star.getZone().getName());
    Assert.assertEquals("朱雀", star.getZone().getBeast().getName());
    Assert.assertEquals("翼", star.getName());
    Assert.assertEquals("火", star.getSevenStar().getName());
    Assert.assertEquals("蛇", star.getAnimal().getName());
    Assert.assertEquals("凶", star.getLuck().getName());

    Assert.assertEquals("阳天", star.getLand().getName());
    Assert.assertEquals("东南", star.getLand().getDirection().getName());
  }

  @Test
  public void test25() {
    LunarDay d = LunarDay.fromYmd(2023, 9, 28);
    TwentyEightStar star = d.getTwentyEightStar();
    Assert.assertEquals("南", star.getZone().getName());
    Assert.assertEquals("朱雀", star.getZone().getBeast().getName());
    Assert.assertEquals("柳", star.getName());
    Assert.assertEquals("土", star.getSevenStar().getName());
    Assert.assertEquals("獐", star.getAnimal().getName());
    Assert.assertEquals("凶", star.getLuck().getName());

    Assert.assertEquals("炎天", star.getLand().getName());
    Assert.assertEquals("南", star.getLand().getDirection().getName());
  }

  @Test
  public void test26() {
    LunarDay lunar = LunarDay.fromYmd(2005, 11, 23);
    Assert.assertEquals("戊子", lunar.getLunarMonth().getSixtyCycle().getName());
    Assert.assertEquals("戊子", lunar.getSixtyCycleDay().getMonth().getName());
  }

  @Test
  public void test27() {
    LunarDay lunar = LunarDay.fromYmd(2024, 1, 1);
    Assert.assertEquals("农历甲辰年二月初三", lunar.next(31).toString());
  }

  @Test
  public void test28() {
    LunarDay lunar = LunarDay.fromYmd(2024, 3, 5);
    Assert.assertEquals("大安", lunar.getMinorRen().getName());
  }

}
