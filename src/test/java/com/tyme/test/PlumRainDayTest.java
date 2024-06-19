package com.tyme.test;

import com.tyme.culture.plumrain.PlumRainDay;
import com.tyme.solar.SolarDay;
import org.junit.Assert;
import org.junit.Test;

/**
 * 梅雨天测试
 *
 * @author 6tail
 */
public class PlumRainDayTest {

  @Test
  public void test0() {
    PlumRainDay d = SolarDay.fromYmd(2024, 6, 10).getPlumRainDay();
    Assert.assertNull(d);
  }

  @Test
  public void test1() {
    PlumRainDay d = SolarDay.fromYmd(2024, 6, 11).getPlumRainDay();
    Assert.assertEquals("入梅", d.getName());
    Assert.assertEquals("入梅", d.getPlumRain().toString());
    Assert.assertEquals("入梅第1天", d.toString());
  }

  @Test
  public void test2() {
    PlumRainDay d = SolarDay.fromYmd(2024, 7, 6).getPlumRainDay();
    Assert.assertEquals("出梅", d.getName());
    Assert.assertEquals("出梅", d.getPlumRain().toString());
    Assert.assertEquals("出梅", d.toString());
  }

  @Test
  public void test3() {
    PlumRainDay d = SolarDay.fromYmd(2024, 7, 5).getPlumRainDay();
    Assert.assertEquals("入梅", d.getName());
    Assert.assertEquals("入梅", d.getPlumRain().toString());
    Assert.assertEquals("入梅第25天", d.toString());
  }

}
