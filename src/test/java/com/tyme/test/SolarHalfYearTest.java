package com.tyme.test;

import com.tyme.solar.SolarHalfYear;
import org.junit.Assert;
import org.junit.Test;

/**
 * 公历半年测试
 *
 * @author 6tail
 */
public class SolarHalfYearTest {

  @Test
  public void test0() {
    Assert.assertEquals("上半年", SolarHalfYear.fromIndex(2023, 0).getName());
    Assert.assertEquals("2023年上半年", SolarHalfYear.fromIndex(2023, 0).toString());
  }

  @Test
  public void test1() {
    Assert.assertEquals("下半年", SolarHalfYear.fromIndex(2023, 1).getName());
    Assert.assertEquals("2023年下半年", SolarHalfYear.fromIndex(2023, 1).toString());
  }

  @Test
  public void test2() {
    Assert.assertEquals("下半年", SolarHalfYear.fromIndex(2023, 0).next(1).getName());
    Assert.assertEquals("2023年下半年", SolarHalfYear.fromIndex(2023, 0).next(1).toString());
  }

  @Test
  public void test3() {
    Assert.assertEquals("上半年", SolarHalfYear.fromIndex(2023, 0).next(2).getName());
    Assert.assertEquals("2024年上半年", SolarHalfYear.fromIndex(2023, 0).next(2).toString());
  }

  @Test
  public void test4() {
    Assert.assertEquals("上半年", SolarHalfYear.fromIndex(2023, 0).next(-2).getName());
    Assert.assertEquals("2022年上半年", SolarHalfYear.fromIndex(2023, 0).next(-2).toString());
  }
}
