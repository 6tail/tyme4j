package com.tyme.test;

import com.tyme.culture.star.nine.NineStar;
import com.tyme.lunar.LunarDay;
import com.tyme.lunar.LunarHour;
import com.tyme.lunar.LunarMonth;
import com.tyme.lunar.LunarYear;
import com.tyme.solar.SolarDay;
import org.junit.Assert;
import org.junit.Test;

/**
 * 九星测试
 *
 * @author 6tail
 */
public class NineStarTest {

  @Test
  public void test0() {
    NineStar nineStar = LunarYear.fromYear(1985).getNineStar();
    Assert.assertEquals("六", nineStar.getName());
    Assert.assertEquals("六白金", nineStar.toString());
  }

  @Test
  public void test1() {
    NineStar nineStar = LunarYear.fromYear(2022).getNineStar();
    Assert.assertEquals("五黄土", nineStar.toString());
    Assert.assertEquals("玉衡", nineStar.getDipper().toString());
  }

  @Test
  public void test2() {
    NineStar nineStar = LunarYear.fromYear(2033).getNineStar();
    Assert.assertEquals("三碧木", nineStar.toString());
    Assert.assertEquals("天玑", nineStar.getDipper().toString());
  }

  @Test
  public void test3() {
    NineStar nineStar = LunarMonth.fromYm(1985, 2).getNineStar();
    Assert.assertEquals("四绿木", nineStar.toString());
    Assert.assertEquals("天权", nineStar.getDipper().toString());
  }

  @Test
  public void test4() {
    NineStar nineStar = LunarMonth.fromYm(1985, 2).getNineStar();
    Assert.assertEquals("四绿木", nineStar.toString());
    Assert.assertEquals("天权", nineStar.getDipper().toString());
  }

  @Test
  public void test5() {
    NineStar nineStar = LunarMonth.fromYm(2022, 1).getNineStar();
    Assert.assertEquals("二黑土", nineStar.toString());
    Assert.assertEquals("天璇", nineStar.getDipper().toString());
  }

  @Test
  public void test6() {
    NineStar nineStar = LunarMonth.fromYm(2033, 1).getNineStar();
    Assert.assertEquals("五黄土", nineStar.toString());
    Assert.assertEquals("玉衡", nineStar.getDipper().toString());
  }

  @Test
  public void test7() {
    NineStar nineStar = SolarDay.fromYmd(1985, 2, 19).getLunarDay().getNineStar();
    Assert.assertEquals("五黄土", nineStar.toString());
    Assert.assertEquals("玉衡", nineStar.getDipper().toString());
  }

  @Test
  public void test8() {
    NineStar nineStar = LunarDay.fromYmd(2022, 1, 1).getNineStar();
    Assert.assertEquals("四绿木", nineStar.toString());
    Assert.assertEquals("天权", nineStar.getDipper().toString());
  }

  @Test
  public void test9() {
    NineStar nineStar = LunarDay.fromYmd(2033, 1, 1).getNineStar();
    Assert.assertEquals("一白水", nineStar.toString());
    Assert.assertEquals("天枢", nineStar.getDipper().toString());
  }

  @Test
  public void test10() {
    NineStar nineStar = LunarHour.fromYmdHms(2033, 1, 1, 12, 0, 0).getNineStar();
    Assert.assertEquals("七赤金", nineStar.toString());
    Assert.assertEquals("摇光", nineStar.getDipper().toString());
  }

  @Test
  public void test11() {
    NineStar nineStar = LunarHour.fromYmdHms(2011, 5, 3, 23, 0, 0).getNineStar();
    Assert.assertEquals("七赤金", nineStar.toString());
    Assert.assertEquals("摇光", nineStar.getDipper().toString());
  }

  @Test
  public void test12() {
    LunarMonth m = LunarMonth.fromYm(2024, 11);
    Assert.assertEquals("四绿木", m.getNineStar().toString());
    m = LunarMonth.fromYm(2024, 12);
    Assert.assertEquals("三碧木", m.getNineStar().toString());
  }

}
