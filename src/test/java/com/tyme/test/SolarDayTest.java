package com.tyme.test;

import com.tyme.solar.SolarDay;
import org.junit.Assert;
import org.junit.Test;

/**
 * 公历日测试
 *
 * @author 6tail
 */
public class SolarDayTest {

  @Test
  public void test0() {
    Assert.assertEquals("1日", SolarDay.fromYmd(2023, 1, 1).getName());
    Assert.assertEquals("2023年1月1日", SolarDay.fromYmd(2023, 1, 1).toString());
  }

  @Test
  public void test1() {
    Assert.assertEquals("29日", SolarDay.fromYmd(2000, 2, 29).getName());
    Assert.assertEquals("2000年2月29日", SolarDay.fromYmd(2000, 2, 29).toString());
  }

  @Test
  public void test2() {
    Assert.assertEquals(0, SolarDay.fromYmd(2023, 1, 1).getIndexInYear());
    Assert.assertEquals(364, SolarDay.fromYmd(2023, 12, 31).getIndexInYear());
    Assert.assertEquals(365, SolarDay.fromYmd(2020, 12, 31).getIndexInYear());
  }

  @Test
  public void test3() {
    Assert.assertEquals(0, SolarDay.fromYmd(2023, 1, 1).subtract(SolarDay.fromYmd(2023, 1, 1)));
    Assert.assertEquals(1, SolarDay.fromYmd(2023, 1, 2).subtract(SolarDay.fromYmd(2023, 1, 1)));
    Assert.assertEquals(-1, SolarDay.fromYmd(2023, 1, 1).subtract(SolarDay.fromYmd(2023, 1, 2)));
    Assert.assertEquals(31, SolarDay.fromYmd(2023, 2, 1).subtract(SolarDay.fromYmd(2023, 1, 1)));
    Assert.assertEquals(-31, SolarDay.fromYmd(2023, 1, 1).subtract(SolarDay.fromYmd(2023, 2, 1)));
    Assert.assertEquals(365, SolarDay.fromYmd(2024, 1, 1).subtract(SolarDay.fromYmd(2023, 1, 1)));
    Assert.assertEquals(-365, SolarDay.fromYmd(2023, 1, 1).subtract(SolarDay.fromYmd(2024, 1, 1)));
    Assert.assertEquals(1, SolarDay.fromYmd(1582, 10, 15).subtract(SolarDay.fromYmd(1582, 10, 4)));
  }

  @Test
  public void test4() {
    Assert.assertEquals("1582年10月4日", SolarDay.fromYmd(1582, 10, 15).next(-1).toString());
  }

  @Test
  public void test5() {
    Assert.assertEquals("2000年3月1日", SolarDay.fromYmd(2000, 2, 28).next(2).toString());
  }

  @Test
  public void test6() {
    Assert.assertEquals("农历庚子年闰四月初二", SolarDay.fromYmd(2020, 5, 24).getLunarDay().toString());
  }

  @Test
  public void test7() {
    Assert.assertEquals(31, SolarDay.fromYmd(2020, 5, 24).subtract(SolarDay.fromYmd(2020, 4, 23)));
  }

  @Test
  public void test8() {
    Assert.assertEquals("农历丙子年十一月十二", SolarDay.fromYmd(16, 11, 30).getLunarDay().toString());
  }

  @Test
  public void test9() {
    Assert.assertEquals("霜降", SolarDay.fromYmd(2023, 10, 27).getTerm().toString());
  }

  @Test
  public void test10() {
    Assert.assertEquals("豺乃祭兽第4天", SolarDay.fromYmd(2023, 10, 27).getPhenologyDay().toString());
  }

  @Test
  public void test11() {
    Assert.assertEquals("初候", SolarDay.fromYmd(2023, 10, 27).getPhenologyDay().getPhenology().getThreePhenology().toString());
  }

  @Test
  public void test22() {
    Assert.assertEquals("甲辰", SolarDay.fromYmd(2024, 2, 10).getLunarDay().getLunarMonth().getLunarYear().getSixtyCycle().getName());
  }

  @Test
  public void test23() {
    Assert.assertEquals("癸卯", SolarDay.fromYmd(2024, 2, 9).getLunarDay().getLunarMonth().getLunarYear().getSixtyCycle().getName());
  }

}
