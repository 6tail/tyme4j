package com.tyme.test;

import com.tyme.culture.dog.DogDay;
import com.tyme.solar.SolarDay;
import org.junit.Assert;
import org.junit.Test;

/**
 * 三伏测试
 *
 * @author 6tail
 */
public class DogDayTest {

  @Test
  public void test0() {
    DogDay d = SolarDay.fromYmd(2011, 7, 14).getDogDay();
    Assert.assertEquals("初伏", d.getName());
    Assert.assertEquals("初伏", d.getDog().toString());
    Assert.assertEquals("初伏第1天", d.toString());
  }

  @Test
  public void test1() {
    DogDay d = SolarDay.fromYmd(2011, 7, 23).getDogDay();
    Assert.assertEquals("初伏", d.getName());
    Assert.assertEquals("初伏", d.getDog().toString());
    Assert.assertEquals("初伏第10天", d.toString());
  }

  @Test
  public void test2() {
    DogDay d = SolarDay.fromYmd(2011, 7, 24).getDogDay();
    Assert.assertEquals("中伏", d.getName());
    Assert.assertEquals("中伏", d.getDog().toString());
    Assert.assertEquals("中伏第1天", d.toString());
  }

  @Test
  public void test3() {
    DogDay d = SolarDay.fromYmd(2011, 8, 12).getDogDay();
    Assert.assertEquals("中伏", d.getName());
    Assert.assertEquals("中伏", d.getDog().toString());
    Assert.assertEquals("中伏第20天", d.toString());
  }

  @Test
  public void test4() {
    DogDay d = SolarDay.fromYmd(2011, 8, 13).getDogDay();
    Assert.assertEquals("末伏", d.getName());
    Assert.assertEquals("末伏", d.getDog().toString());
    Assert.assertEquals("末伏第1天", d.toString());
  }

  @Test
  public void test5() {
    DogDay d = SolarDay.fromYmd(2011, 8, 22).getDogDay();
    Assert.assertEquals("末伏", d.getName());
    Assert.assertEquals("末伏", d.getDog().toString());
    Assert.assertEquals("末伏第10天", d.toString());
  }

  @Test
  public void test6() {
    Assert.assertNull(SolarDay.fromYmd(2011, 7, 13).getDogDay());
  }

  @Test
  public void test7() {
    Assert.assertNull(SolarDay.fromYmd(2011, 8, 23).getDogDay());
  }

  @Test
  public void test8() {
    DogDay d = SolarDay.fromYmd(2012, 7, 18).getDogDay();
    Assert.assertEquals("初伏", d.getName());
    Assert.assertEquals("初伏", d.getDog().toString());
    Assert.assertEquals("初伏第1天", d.toString());
  }

  @Test
  public void test9() {
    DogDay d = SolarDay.fromYmd(2012, 8, 5).getDogDay();
    Assert.assertEquals("中伏", d.getName());
    Assert.assertEquals("中伏", d.getDog().toString());
    Assert.assertEquals("中伏第9天", d.toString());
  }

  @Test
  public void test10() {
    DogDay d = SolarDay.fromYmd(2012, 8, 8).getDogDay();
    Assert.assertEquals("末伏", d.getName());
    Assert.assertEquals("末伏", d.getDog().toString());
    Assert.assertEquals("末伏第2天", d.toString());
  }

}
