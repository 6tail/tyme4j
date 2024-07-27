package com.tyme.test;

import com.tyme.lunar.LunarWeek;
import com.tyme.solar.SolarDay;
import com.tyme.solar.SolarWeek;
import org.junit.Assert;
import org.junit.Test;

/**
 * 星期测试
 *
 * @author 6tail
 */
public class WeekTest {

  @Test
  public void test0() {
    Assert.assertEquals("一", SolarDay.fromYmd(1582, 10, 1).getWeek().getName());
  }

  @Test
  public void test1() {
    Assert.assertEquals("五", SolarDay.fromYmd(1582, 10, 15).getWeek().getName());
  }

  @Test
  public void test2() {
    Assert.assertEquals(2, SolarDay.fromYmd(2023, 10, 31).getWeek().getIndex());
  }

  @Test
  public void test3() {
    SolarWeek w = SolarWeek.fromYm(2023, 10, 0, 0);
    Assert.assertEquals("第一周", w.getName());
    Assert.assertEquals("2023年10月第一周", w.toString());
  }

  @Test
  public void test5() {
    SolarWeek w = SolarWeek.fromYm(2023, 10, 4, 0);
    Assert.assertEquals("第五周", w.getName());
    Assert.assertEquals("2023年10月第五周", w.toString());
  }

  @Test
  public void test6() {
    SolarWeek w = SolarWeek.fromYm(2023, 10, 5, 1);
    Assert.assertEquals("第六周", w.getName());
    Assert.assertEquals("2023年10月第六周", w.toString());
  }

  @Test
  public void test7() {
    SolarWeek w = SolarWeek.fromYm(2023, 10, 0, 0).next(4);
    Assert.assertEquals("第五周", w.getName());
    Assert.assertEquals("2023年10月第五周", w.toString());
  }

  @Test
  public void test8() {
    SolarWeek w = SolarWeek.fromYm(2023, 10, 0, 0).next(5);
    Assert.assertEquals("第二周", w.getName());
    Assert.assertEquals("2023年11月第二周", w.toString());
  }

  @Test
  public void test9() {
    SolarWeek w = SolarWeek.fromYm(2023, 10, 0, 0).next(-1);
    Assert.assertEquals("第五周", w.getName());
    Assert.assertEquals("2023年9月第五周", w.toString());
  }

  @Test
  public void test10() {
    SolarWeek w = SolarWeek.fromYm(2023, 10, 0, 0).next(-5);
    Assert.assertEquals("第一周", w.getName());
    Assert.assertEquals("2023年9月第一周", w.toString());
  }

  @Test
  public void test11() {
    SolarWeek w = SolarWeek.fromYm(2023, 10, 0, 0).next(-6);
    Assert.assertEquals("第四周", w.getName());
    Assert.assertEquals("2023年8月第四周", w.toString());
  }

  @Test
  public void test12(){
    SolarDay solar = SolarDay.fromYmd(1582, 10, 1);
    Assert.assertEquals(1, solar.getWeek().getIndex());
  }

  @Test
  public void test13(){
    SolarDay solar = SolarDay.fromYmd(1582, 10, 15);
    Assert.assertEquals(5, solar.getWeek().getIndex());
  }

  @Test
  public void test14(){
    SolarDay solar = SolarDay.fromYmd(1129, 11, 17);
    Assert.assertEquals(0, solar.getWeek().getIndex());
  }

  @Test
  public void test15(){
    SolarDay solar = SolarDay.fromYmd(1129, 11, 1);
    Assert.assertEquals(5, solar.getWeek().getIndex());
  }

  @Test
  public void test16(){
    SolarDay solar = SolarDay.fromYmd(8, 11, 1);
    Assert.assertEquals(4, solar.getWeek().getIndex());
  }

  @Test
  public void test17(){
    SolarDay solar = SolarDay.fromYmd(1582, 9, 30);
    Assert.assertEquals(0, solar.getWeek().getIndex());
  }

  @Test
  public void test18(){
    SolarDay solar = SolarDay.fromYmd(1582, 1, 1);
    Assert.assertEquals(1, solar.getWeek().getIndex());
  }

  @Test
  public void test19(){
    SolarDay solar = SolarDay.fromYmd(1500, 2, 29);
    Assert.assertEquals(6, solar.getWeek().getIndex());
  }

  @Test
  public void test20(){
    SolarDay solar = SolarDay.fromYmd(9865, 7, 26);
    Assert.assertEquals(3, solar.getWeek().getIndex());
  }

  @Test
  public void test21(){
    LunarWeek week = LunarWeek.fromYm(2023, 1, 0, 2);
    Assert.assertEquals("农历癸卯年正月第一周", week.toString());
    Assert.assertEquals("农历壬寅年十二月廿六", week.getFirstDay().toString());
  }

  @Test
  public void test22(){
    SolarWeek week = SolarWeek.fromYm(2023, 1, 0, 2);
    Assert.assertEquals("2023年1月第一周", week.toString());
    Assert.assertEquals("2022年12月27日", week.getFirstDay().toString());
  }

  @Test
  public void test24(){
    int start = 0;
    SolarWeek week = SolarWeek.fromYm(2024, 2, 2, start);
    Assert.assertEquals("2024年2月第三周", week.toString());
    Assert.assertEquals(6, week.getIndexInYear());

    week = SolarDay.fromYmd(2024, 2, 11).getSolarWeek(start);
    Assert.assertEquals("2024年2月第三周", week.toString());

    week = SolarDay.fromYmd(2024, 2, 17).getSolarWeek(start);
    Assert.assertEquals("2024年2月第三周", week.toString());

    week = SolarDay.fromYmd(2024, 2, 10).getSolarWeek(start);
    Assert.assertEquals("2024年2月第二周", week.toString());

    week = SolarDay.fromYmd(2024, 2, 18).getSolarWeek(start);
    Assert.assertEquals("2024年2月第四周", week.toString());
  }

  @Test
  public void test25(){
    LunarWeek week = LunarWeek.fromYm(2024, 6, 0, 0);
    Assert.assertEquals("农历甲辰年六月第一周", week.toString());
    Assert.assertEquals("农历甲辰年六月第三周", week.next(2).toString());
    Assert.assertEquals("农历甲辰年七月第一周", week.next(5).toString());
    Assert.assertEquals("农历甲辰年五月第四周", week.next(-1).toString());
    Assert.assertEquals("农历甲辰年五月第一周", week.next(-4).toString());
  }

}
