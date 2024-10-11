package com.tyme.test;

import com.tyme.lunar.LunarHour;
import org.junit.Assert;
import org.junit.Test;

/**
 * 时辰测试
 *
 * @author 6tail
 */
public class LunarHourTest {

  @Test
  public void test1() {
    LunarHour h = LunarHour.fromYmdHms(2020, -4, 5, 23, 0, 0);
    Assert.assertEquals("子时", h.getName());
    Assert.assertEquals("农历庚子年闰四月初五戊子时", h.toString());
  }

  @Test
  public void test2() {
    LunarHour h = LunarHour.fromYmdHms(2020, -4, 5, 0, 59, 0);
    Assert.assertEquals("子时", h.getName());
    Assert.assertEquals("农历庚子年闰四月初五丙子时", h.toString());
  }

  @Test
  public void test3() {
    LunarHour h = LunarHour.fromYmdHms(2020, -4, 5, 1, 0, 0);
    Assert.assertEquals("丑时", h.getName());
    Assert.assertEquals("农历庚子年闰四月初五丁丑时", h.toString());
  }

  @Test
  public void test4() {
    LunarHour h = LunarHour.fromYmdHms(2020, -4, 5, 21, 30, 0);
    Assert.assertEquals("亥时", h.getName());
    Assert.assertEquals("农历庚子年闰四月初五丁亥时", h.toString());
  }

  @Test
  public void test5() {
    LunarHour h = LunarHour.fromYmdHms(2020, -4, 2, 23, 30, 0);
    Assert.assertEquals("子时", h.getName());
    Assert.assertEquals("农历庚子年闰四月初二壬子时", h.toString());
  }

  @Test
  public void test6() {
    LunarHour h = LunarHour.fromYmdHms(2020, 4, 28, 23, 30, 0);
    Assert.assertEquals("子时", h.getName());
    Assert.assertEquals("农历庚子年四月廿八甲子时", h.toString());
  }

  @Test
  public void test7() {
    LunarHour h = LunarHour.fromYmdHms(2020, 4, 29, 0, 0, 0);
    Assert.assertEquals("子时", h.getName());
    Assert.assertEquals("农历庚子年四月廿九甲子时", h.toString());
  }

  @Test
  public void test8() {
    LunarHour h = LunarHour.fromYmdHms(2023, 11, 14, 23, 0, 0);
    Assert.assertEquals("甲子", h.getSixtyCycle().getName());

    Assert.assertEquals("己未", h.getDaySixtyCycle().getName());
    Assert.assertEquals("戊午", h.getLunarDay().getSixtyCycle().getName());
    Assert.assertEquals("农历癸卯年十一月十四", h.getLunarDay().toString());

    Assert.assertEquals("甲子", h.getMonthSixtyCycle().getName());
    Assert.assertEquals("农历癸卯年十一月", h.getLunarDay().getLunarMonth().toString());
    Assert.assertEquals("乙丑", h.getLunarDay().getLunarMonth().getSixtyCycle().getName());

    Assert.assertEquals("癸卯", h.getYearSixtyCycle().getName());
    Assert.assertEquals("农历癸卯年", h.getLunarDay().getLunarMonth().getLunarYear().toString());
    Assert.assertEquals("癸卯", h.getLunarDay().getLunarMonth().getLunarYear().getSixtyCycle().getName());
  }

  @Test
  public void test9() {
    LunarHour h = LunarHour.fromYmdHms(2023, 11, 14, 6, 0, 0);
    Assert.assertEquals("乙卯", h.getSixtyCycle().getName());

    Assert.assertEquals("戊午", h.getDaySixtyCycle().getName());
    Assert.assertEquals("戊午", h.getLunarDay().getSixtyCycle().getName());
    Assert.assertEquals("农历癸卯年十一月十四", h.getLunarDay().toString());

    Assert.assertEquals("甲子", h.getMonthSixtyCycle().getName());
    Assert.assertEquals("农历癸卯年十一月", h.getLunarDay().getLunarMonth().toString());
    Assert.assertEquals("乙丑", h.getLunarDay().getLunarMonth().getSixtyCycle().getName());

    Assert.assertEquals("癸卯", h.getYearSixtyCycle().getName());
    Assert.assertEquals("农历癸卯年", h.getLunarDay().getLunarMonth().getLunarYear().toString());
    Assert.assertEquals("癸卯", h.getLunarDay().getLunarMonth().getLunarYear().getSixtyCycle().getName());
  }

  @Test
  public void test28() {
    LunarHour h = LunarHour.fromYmdHms(2024, 9, 7, 10, 0, 0);
    Assert.assertEquals("留连", h.getMinorRen().getName());
  }

}
