package com.tyme.test;

import com.tyme.sixtycycle.SixtyCycleYear;
import org.junit.Assert;
import org.junit.Test;

/**
 * 干支年测试
 *
 * @author 6tail
 */
public class SixtyCycleYearTest {

  @Test
  public void test0() {
    Assert.assertEquals("癸卯年", SixtyCycleYear.fromYear(2023).getName());
  }

  @Test
  public void test1() {
    Assert.assertEquals("戊申年", SixtyCycleYear.fromYear(2023).next(5).getName());
  }

  @Test
  public void test2() {
    Assert.assertEquals("戊戌年", SixtyCycleYear.fromYear(2023).next(-5).getName());
  }

  /**
   * 干支年的干支
   */
  @Test
  public void test3() {
    Assert.assertEquals("庚子", SixtyCycleYear.fromYear(2020).getSixtyCycle().getName());
  }

  /**
   * 干支年的生肖(干支年.干支.地支.生肖)
   */
  @Test
  public void test4() {
    Assert.assertEquals("虎", SixtyCycleYear.fromYear(1986).getSixtyCycle().getEarthBranch().getZodiac().getName());
  }

  @Test
  public void test5() {
    Assert.assertEquals("庚子年", SixtyCycleYear.fromYear(2025).next(-5).getName());
  }

  @Test
  public void test7() {
    SixtyCycleYear y = SixtyCycleYear.fromYear(2023);
    Assert.assertEquals("癸卯", y.getSixtyCycle().getName());
    Assert.assertEquals("兔", y.getSixtyCycle().getEarthBranch().getZodiac().getName());
  }

  @Test
  public void test8() {
    Assert.assertEquals("上元", SixtyCycleYear.fromYear(1864).getTwenty().getSixty().getName());
  }

  @Test
  public void test9() {
    Assert.assertEquals("上元", SixtyCycleYear.fromYear(1923).getTwenty().getSixty().getName());
  }

  @Test
  public void test10() {
    Assert.assertEquals("中元", SixtyCycleYear.fromYear(1924).getTwenty().getSixty().getName());
  }

  @Test
  public void test11() {
    Assert.assertEquals("中元", SixtyCycleYear.fromYear(1983).getTwenty().getSixty().getName());
  }

  @Test
  public void test12() {
    Assert.assertEquals("下元", SixtyCycleYear.fromYear(1984).getTwenty().getSixty().getName());
  }

  @Test
  public void test13() {
    Assert.assertEquals("下元", SixtyCycleYear.fromYear(2043).getTwenty().getSixty().getName());
  }

  @Test
  public void test14() {
    Assert.assertEquals("一运", SixtyCycleYear.fromYear(1864).getTwenty().getName());
  }

  @Test
  public void test15() {
    Assert.assertEquals("一运", SixtyCycleYear.fromYear(1883).getTwenty().getName());
  }

  @Test
  public void test16() {
    Assert.assertEquals("二运", SixtyCycleYear.fromYear(1884).getTwenty().getName());
  }

  @Test
  public void test17() {
    Assert.assertEquals("二运", SixtyCycleYear.fromYear(1903).getTwenty().getName());
  }

  @Test
  public void test18() {
    Assert.assertEquals("三运", SixtyCycleYear.fromYear(1904).getTwenty().getName());
  }

  @Test
  public void test19() {
    Assert.assertEquals("三运", SixtyCycleYear.fromYear(1923).getTwenty().getName());
  }

  @Test
  public void test20() {
    Assert.assertEquals("八运", SixtyCycleYear.fromYear(2004).getTwenty().getName());
  }

  @Test
  public void test21() {
    SixtyCycleYear year = SixtyCycleYear.fromYear(1);
    Assert.assertEquals("六运", year.getTwenty().getName());
    Assert.assertEquals("中元", year.getTwenty().getSixty().getName());
  }

  @Test
  public void test22() {
    SixtyCycleYear year = SixtyCycleYear.fromYear(1863);
    Assert.assertEquals("九运", year.getTwenty().getName());
    Assert.assertEquals("下元", year.getTwenty().getSixty().getName());
  }

  @Test
  public void test23() {
    SixtyCycleYear year = SixtyCycleYear.fromYear(2025);
    Assert.assertEquals("戊寅月", year.getFirstMonth().getName());
  }
}
