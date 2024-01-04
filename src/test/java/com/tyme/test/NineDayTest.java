package com.tyme.test;

import com.tyme.culture.nine.NineDay;
import com.tyme.solar.SolarDay;
import org.junit.Assert;
import org.junit.Test;

/**
 * 数九测试
 *
 * @author 6tail
 */
public class NineDayTest {

  @Test
  public void test0() {
    NineDay d = SolarDay.fromYmd(2020, 12, 21).getNineDay();
    Assert.assertEquals("一九", d.getName());
    Assert.assertEquals("一九", d.getNine().toString());
    Assert.assertEquals("一九第1天", d.toString());
  }

  @Test
  public void test1() {
    NineDay d = SolarDay.fromYmd(2020, 12, 22).getNineDay();
    Assert.assertEquals("一九", d.getName());
    Assert.assertEquals("一九", d.getNine().toString());
    Assert.assertEquals("一九第2天", d.toString());
  }

  @Test
  public void test2() {
    NineDay d = SolarDay.fromYmd(2020, 1, 7).getNineDay();
    Assert.assertEquals("二九", d.getName());
    Assert.assertEquals("二九", d.getNine().toString());
    Assert.assertEquals("二九第8天", d.toString());
  }

  @Test
  public void test3() {
    NineDay d = SolarDay.fromYmd(2021, 1, 6).getNineDay();
    Assert.assertEquals("二九", d.getName());
    Assert.assertEquals("二九", d.getNine().toString());
    Assert.assertEquals("二九第8天", d.toString());
  }

  @Test
  public void test4() {
    NineDay d = SolarDay.fromYmd(2021, 1, 8).getNineDay();
    Assert.assertEquals("三九", d.getName());
    Assert.assertEquals("三九", d.getNine().toString());
    Assert.assertEquals("三九第1天", d.toString());
  }

  @Test
  public void test5() {
    NineDay d = SolarDay.fromYmd(2021, 3, 5).getNineDay();
    Assert.assertEquals("九九", d.getName());
    Assert.assertEquals("九九", d.getNine().toString());
    Assert.assertEquals("九九第3天", d.toString());
  }

  @Test
  public void test6() {
    NineDay d = SolarDay.fromYmd(2021, 7, 5).getNineDay();
    Assert.assertNull(d);
  }

}
