package com.tyme.test;

import com.tyme.festival.LunarFestival;
import com.tyme.lunar.LunarDay;
import org.junit.Assert;
import org.junit.Test;

/**
 * 农历传统节日测试
 *
 * @author 6tail
 */
public class LunarFestivalTest {

  @Test
  public void test0() {
    for (int i = 0, j = LunarFestival.NAMES.length; i < j; i++) {
      LunarFestival f = LunarFestival.fromIndex(2023, i);
      Assert.assertNotNull(f);
      Assert.assertEquals(LunarFestival.NAMES[i], f.getName());
    }
  }

  @Test
  public void test1() {
    LunarFestival f = LunarFestival.fromIndex(2023, 0);
    Assert.assertNotNull(f);
    for (int i = 0, j = LunarFestival.NAMES.length; i < j; i++) {
      Assert.assertEquals(LunarFestival.NAMES[i], f.next(i).getName());
    }
  }

  @Test
  public void test2() {
    LunarFestival f = LunarFestival.fromIndex(2023, 0);
    Assert.assertNotNull(f);
    Assert.assertEquals("农历癸卯年正月初一 春节", f.toString());
    Assert.assertEquals("农历癸卯年十一月初十 冬至节", f.next(10).toString());
    Assert.assertEquals("农历甲辰年正月初一 春节", f.next(13).toString());
    Assert.assertEquals("农历壬寅年十一月廿九 冬至节", f.next(-3).toString());
  }

  @Test
  public void test3() {
    LunarFestival f = LunarFestival.fromIndex(2023, 0);
    Assert.assertNotNull(f);
    Assert.assertEquals("农历壬寅年三月初五 清明节", f.next(-9).toString());
  }

  @Test
  public void test4() {
    LunarFestival f = LunarDay.fromYmd(2010, 1, 15).getFestival();
    Assert.assertNotNull(f);
    Assert.assertEquals("农历庚寅年正月十五 元宵节", f.toString());
  }

  @Test
  public void test5() {
    LunarFestival f = LunarDay.fromYmd(2021, 12, 29).getFestival();
    Assert.assertNotNull(f);
    Assert.assertEquals("农历辛丑年十二月廿九 除夕", f.toString());
  }

}
