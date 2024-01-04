package com.tyme.test;

import com.tyme.solar.SolarDay;
import org.junit.Assert;
import org.junit.Test;

/**
 * 星座测试
 *
 * @author 6tail
 */
public class ConstellationTest {

  @Test
  public void test0() {
    Assert.assertEquals("白羊", SolarDay.fromYmd(2020,3,21).getConstellation().getName());
    Assert.assertEquals("白羊", SolarDay.fromYmd(2020,4,19).getConstellation().getName());
  }

  @Test
  public void test1() {
    Assert.assertEquals("金牛", SolarDay.fromYmd(2020,4,20).getConstellation().getName());
    Assert.assertEquals("金牛", SolarDay.fromYmd(2020,5,20).getConstellation().getName());
  }

  @Test
  public void test2() {
    Assert.assertEquals("双子", SolarDay.fromYmd(2020,5,21).getConstellation().getName());
    Assert.assertEquals("双子", SolarDay.fromYmd(2020,6,21).getConstellation().getName());
  }

  @Test
  public void test3() {
    Assert.assertEquals("巨蟹", SolarDay.fromYmd(2020,6,22).getConstellation().getName());
    Assert.assertEquals("巨蟹", SolarDay.fromYmd(2020,7,22).getConstellation().getName());
  }

}
