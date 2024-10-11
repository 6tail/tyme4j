package com.tyme.test;

import com.tyme.lunar.LunarDay;
import com.tyme.lunar.LunarMonth;
import com.tyme.solar.SolarDay;
import org.junit.Assert;
import org.junit.Test;

/**
 * 农历月测试
 *
 * @author 6tail
 */
public class LunarMonthTest {

  @Test
  public void test0() {
    Assert.assertEquals("七月", LunarMonth.fromYm(2359, 7).getName());
  }

  /**
   * 闰月
   */
  @Test
  public void test1() {
    Assert.assertEquals("闰七月", LunarMonth.fromYm(2359, -7).getName());
  }

  @Test
  public void test2() {
    Assert.assertEquals(29, LunarMonth.fromYm(2023, 6).getDayCount());
  }

  @Test
  public void test3() {
    Assert.assertEquals(30, LunarMonth.fromYm(2023, 7).getDayCount());
  }

  @Test
  public void test4() {
    Assert.assertEquals(30, LunarMonth.fromYm(2023, 8).getDayCount());
  }

  @Test
  public void test5() {
    Assert.assertEquals(29, LunarMonth.fromYm(2023, 9).getDayCount());
  }

  @Test
  public void test6() {
    Assert.assertEquals("2023年10月15日", LunarMonth.fromYm(2023, 9).getFirstJulianDay().getSolarDay().toString());
  }

  @Test
  public void test7() {
    Assert.assertEquals("甲寅", LunarMonth.fromYm(2023, 1).getSixtyCycle().getName());
  }

  @Test
  public void test8() {
    Assert.assertEquals("丙辰", LunarMonth.fromYm(2023, -2).getSixtyCycle().getName());
  }

  @Test
  public void test9() {
    Assert.assertEquals("丁巳", LunarMonth.fromYm(2023, 3).getSixtyCycle().getName());
  }

  @Test
  public void test10() {
    Assert.assertEquals("丙寅", LunarMonth.fromYm(2024, 1).getSixtyCycle().getName());
  }

  @Test
  public void test11() {
    Assert.assertEquals("丙寅", LunarMonth.fromYm(2023, 12).getSixtyCycle().getName());
  }

  @Test
  public void test12() {
    Assert.assertEquals("壬寅", LunarMonth.fromYm(2022, 1).getSixtyCycle().getName());
  }

  @Test
  public void test13() {
    Assert.assertEquals("闰十二月", LunarMonth.fromYm(37, -12).getName());
  }

  @Test
  public void test14() {
    Assert.assertEquals("闰十二月", LunarMonth.fromYm(5552, -12).getName());
  }

  @Test
  public void test15() {
    Assert.assertEquals("农历戊子年十二月", LunarMonth.fromYm(2008, 11).next(1).toString());
  }

  @Test
  public void test16() {
    Assert.assertEquals("农历己丑年正月", LunarMonth.fromYm(2008, 11).next(2).toString());
  }

  @Test
  public void test17() {
    Assert.assertEquals("农历己丑年五月", LunarMonth.fromYm(2008, 11).next(6).toString());
  }

  @Test
  public void test18() {
    Assert.assertEquals("农历己丑年闰五月", LunarMonth.fromYm(2008, 11).next(7).toString());
  }

  @Test
  public void test19() {
    Assert.assertEquals("农历己丑年六月", LunarMonth.fromYm(2008, 11).next(8).toString());
  }

  @Test
  public void test20() {
    Assert.assertEquals("农历庚寅年正月", LunarMonth.fromYm(2008, 11).next(15).toString());
  }

  @Test
  public void test21() {
    Assert.assertEquals("农历戊子年十一月", LunarMonth.fromYm(2008, 12).next(-1).toString());
  }

  @Test
  public void test22() {
    Assert.assertEquals("农历戊子年十一月", LunarMonth.fromYm(2009, 1).next(-2).toString());
  }

  @Test
  public void test23() {
    Assert.assertEquals("农历戊子年十一月", LunarMonth.fromYm(2009, 5).next(-6).toString());
  }

  @Test
  public void test24() {
    Assert.assertEquals("农历戊子年十一月", LunarMonth.fromYm(2009, -5).next(-7).toString());
  }

  @Test
  public void test25() {
    Assert.assertEquals("农历戊子年十一月", LunarMonth.fromYm(2009, 6).next(-8).toString());
  }

  @Test
  public void test26() {
    Assert.assertEquals("农历戊子年十一月", LunarMonth.fromYm(2010, 1).next(-15).toString());
  }

  @Test
  public void test27() {
    Assert.assertEquals(29, LunarMonth.fromYm(2012, -4).getDayCount());
  }

  @Test
  public void test28() {
    Assert.assertEquals("癸亥", LunarMonth.fromYm(2023, 9).getSixtyCycle().toString());
  }

  @Test
  public void test29() {
    LunarDay d = SolarDay.fromYmd(2023, 10, 7).getLunarDay();
    Assert.assertEquals("壬戌", d.getLunarMonth().getSixtyCycle().toString());
    Assert.assertEquals("辛酉", d.getMonthSixtyCycle().toString());
  }

  @Test
  public void test30() {
    LunarDay d = SolarDay.fromYmd(2023, 10, 8).getLunarDay();
    Assert.assertEquals("壬戌", d.getLunarMonth().getSixtyCycle().toString());
    Assert.assertEquals("壬戌", d.getMonthSixtyCycle().toString());
  }

  @Test
  public void test31() {
    LunarDay d = SolarDay.fromYmd(2023, 10, 15).getLunarDay();
    Assert.assertEquals("九月", d.getLunarMonth().getName());
    Assert.assertEquals("癸亥", d.getLunarMonth().getSixtyCycle().toString());
    Assert.assertEquals("壬戌", d.getMonthSixtyCycle().toString());
  }

  @Test
  public void test32() {
    LunarDay d = SolarDay.fromYmd(2023, 11, 7).getLunarDay();
    Assert.assertEquals("癸亥", d.getLunarMonth().getSixtyCycle().toString());
    Assert.assertEquals("壬戌", d.getMonthSixtyCycle().toString());
  }

  @Test
  public void test33() {
    LunarDay d = SolarDay.fromYmd(2023, 11, 8).getLunarDay();
    Assert.assertEquals("癸亥", d.getLunarMonth().getSixtyCycle().toString());
    Assert.assertEquals("癸亥", d.getMonthSixtyCycle().toString());
  }

  @Test
  public void test34() {
    // 2023年闰2月
    LunarMonth m = LunarMonth.fromYm(2023, 12);
    Assert.assertEquals("农历癸卯年十二月", m.toString());
    Assert.assertEquals("农历癸卯年十一月", m.next(-1).toString());
    Assert.assertEquals("农历癸卯年十月", m.next(-2).toString());
  }

  @Test
  public void test35() {
    // 2023年闰2月
    LunarMonth m = LunarMonth.fromYm(2023, 3);
    Assert.assertEquals("农历癸卯年三月", m.toString());
    Assert.assertEquals("农历癸卯年闰二月", m.next(-1).toString());
    Assert.assertEquals("农历癸卯年二月", m.next(-2).toString());
    Assert.assertEquals("农历癸卯年正月", m.next(-3).toString());
    Assert.assertEquals("农历壬寅年十二月", m.next(-4).toString());
    Assert.assertEquals("农历壬寅年十一月", m.next(-5).toString());
  }

  @Test
  public void test36() {
    LunarDay d = SolarDay.fromYmd(1983, 2, 15).getLunarDay();
    Assert.assertEquals("甲寅", d.getLunarMonth().getSixtyCycle().toString());
    Assert.assertEquals("甲寅", d.getMonthSixtyCycle().toString());
  }

  @Test
  public void test37() {
    LunarDay d = SolarDay.fromYmd(2023, 10, 30).getLunarDay();
    Assert.assertEquals("癸亥", d.getLunarMonth().getSixtyCycle().toString());
    Assert.assertEquals("壬戌", d.getMonthSixtyCycle().toString());
  }

  @Test
  public void test38() {
    LunarDay d = SolarDay.fromYmd(2023, 10, 19).getLunarDay();
    Assert.assertEquals("癸亥", d.getLunarMonth().getSixtyCycle().toString());
    Assert.assertEquals("壬戌", d.getMonthSixtyCycle().toString());
  }

  @Test
  public void test39() {
    LunarMonth m = LunarMonth.fromYm(2023, 11);
    Assert.assertEquals("农历癸卯年十一月", m.toString());
    Assert.assertEquals("乙丑", m.getSixtyCycle().toString());
  }

  @Test
  public void test40() {
    Assert.assertEquals("庚申", LunarDay.fromYmd(2018, 6, 26).getMonthSixtyCycle().toString());
  }

  @Test
  public void test41() {
    Assert.assertEquals("辛丑", LunarMonth.fromYm(1991, 12).getSixtyCycle().toString());
  }

  @Test
  public void test42() {
    Assert.assertEquals("速喜", LunarMonth.fromYm(1991, 3).getMinorRen().getName());
  }

}
