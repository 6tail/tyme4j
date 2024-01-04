package com.tyme.test;

import com.tyme.solar.SolarMonth;
import org.junit.Assert;
import org.junit.Test;

/**
 * 公历月测试
 *
 * @author 6tail
 */
public class SolarMonthTest {

  @Test
  public void test0() {
    SolarMonth m = SolarMonth.fromYm(2019, 5);
    Assert.assertEquals("5月", m.getName());
    Assert.assertEquals("2019年5月", m.toString());
  }

  @Test
  public void test1() {
    SolarMonth m = SolarMonth.fromYm(2023, 1);
    Assert.assertEquals(5, m.getWeekCount(0));
    Assert.assertEquals(6, m.getWeekCount(1));
    Assert.assertEquals(6, m.getWeekCount(2));
    Assert.assertEquals(5, m.getWeekCount(3));
    Assert.assertEquals(5, m.getWeekCount(4));
    Assert.assertEquals(5, m.getWeekCount(5));
    Assert.assertEquals(5, m.getWeekCount(6));
  }

  @Test
  public void test2() {
    SolarMonth m = SolarMonth.fromYm(2023, 2);
    Assert.assertEquals(5, m.getWeekCount(0));
    Assert.assertEquals(5, m.getWeekCount(1));
    Assert.assertEquals(5, m.getWeekCount(2));
    Assert.assertEquals(4, m.getWeekCount(3));
    Assert.assertEquals(5, m.getWeekCount(4));
    Assert.assertEquals(5, m.getWeekCount(5));
    Assert.assertEquals(5, m.getWeekCount(6));
  }

  @Test
  public void test3() {
    SolarMonth m = SolarMonth.fromYm(2023, 10).next(1);
    Assert.assertEquals("11月", m.getName());
    Assert.assertEquals("2023年11月", m.toString());
  }

  @Test
  public void test4() {
    SolarMonth m = SolarMonth.fromYm(2023, 10);
    Assert.assertEquals("2023年12月", m.next(2).toString());
    Assert.assertEquals("2024年1月", m.next(3).toString());
    Assert.assertEquals("2023年5月", m.next(-5).toString());
    Assert.assertEquals("2023年1月", m.next(-9).toString());
    Assert.assertEquals("2022年12月", m.next(-10).toString());
    Assert.assertEquals("2025年10月", m.next(24).toString());
    Assert.assertEquals("2021年10月", m.next(-24).toString());
  }

}
