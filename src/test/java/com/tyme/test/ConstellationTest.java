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
    Assert.assertEquals("白羊", SolarDay.fromYmd(2020, 3, 21).getConstellation().getName());
    Assert.assertEquals("白羊", SolarDay.fromYmd(2020, 4, 19).getConstellation().getName());
  }

  @Test
  public void test1() {
    Assert.assertEquals("金牛", SolarDay.fromYmd(2020, 4, 20).getConstellation().getName());
    Assert.assertEquals("金牛", SolarDay.fromYmd(2020, 5, 20).getConstellation().getName());
  }

  @Test
  public void test2() {
    Assert.assertEquals("双子", SolarDay.fromYmd(2020, 5, 21).getConstellation().getName());
    Assert.assertEquals("双子", SolarDay.fromYmd(2020, 6, 21).getConstellation().getName());
  }

  @Test
  public void test3() {
    Assert.assertEquals("巨蟹", SolarDay.fromYmd(2020, 6, 22).getConstellation().getName());
    Assert.assertEquals("巨蟹", SolarDay.fromYmd(2020, 7, 22).getConstellation().getName());
  }

  @Test
  public void test4() {
    SolarDay solar = SolarDay.fromYmd(2020, 7, 23);
    Assert.assertEquals("狮子", solar.getConstellation().getName());
    solar = SolarDay.fromYmd(2020, 8, 22);
    Assert.assertEquals("狮子", solar.getConstellation().getName());
  }

  @Test
  public void test5() {
    SolarDay solar = SolarDay.fromYmd(2020, 8, 23);
    Assert.assertEquals("处女", solar.getConstellation().getName());
    solar = SolarDay.fromYmd(2020, 9, 22);
    Assert.assertEquals("处女", solar.getConstellation().getName());
  }

  @Test
  public void test6() {
    SolarDay solar = SolarDay.fromYmd(2020, 9, 23);
    Assert.assertEquals("天秤", solar.getConstellation().getName());
    solar = SolarDay.fromYmd(2020, 10, 23);
    Assert.assertEquals("天秤", solar.getConstellation().getName());
  }

  @Test
  public void test7() {
    SolarDay solar = SolarDay.fromYmd(2020, 10, 24);
    Assert.assertEquals("天蝎", solar.getConstellation().getName());
    solar = SolarDay.fromYmd(2020, 11, 22);
    Assert.assertEquals("天蝎", solar.getConstellation().getName());
  }

  @Test
  public void test8() {
    SolarDay solar = SolarDay.fromYmd(2020, 11, 23);
    Assert.assertEquals("射手", solar.getConstellation().getName());
    solar = SolarDay.fromYmd(2020, 12, 21);
    Assert.assertEquals("射手", solar.getConstellation().getName());
  }

  @Test
  public void test9() {
    SolarDay solar = SolarDay.fromYmd(2020, 12, 22);
    Assert.assertEquals("摩羯", solar.getConstellation().getName());
    solar = SolarDay.fromYmd(2021, 1, 19);
    Assert.assertEquals("摩羯", solar.getConstellation().getName());
  }

  @Test
  public void test10() {
    SolarDay solar = SolarDay.fromYmd(2021, 1, 20);
    Assert.assertEquals("水瓶", solar.getConstellation().getName());
    solar = SolarDay.fromYmd(2021, 2, 18);
    Assert.assertEquals("水瓶", solar.getConstellation().getName());
  }

  @Test
  public void test11() {
    SolarDay solar = SolarDay.fromYmd(2021, 2, 19);
    Assert.assertEquals("双鱼", solar.getConstellation().getName());
    solar = SolarDay.fromYmd(2021, 3, 20);
    Assert.assertEquals("双鱼", solar.getConstellation().getName());
  }

}
