package com.tyme.test;

import com.tyme.solar.SolarDay;
import org.junit.Assert;
import org.junit.Test;

/**
 * 六曜测试
 *
 * @author 6tail
 */
public class SixStarTest {

  @Test
  public void test0() {
    Assert.assertEquals("佛灭", SolarDay.fromYmd(2020, 4, 23).getLunarDay().getSixStar().getName());
  }

  @Test
  public void test1() {
    Assert.assertEquals("友引", SolarDay.fromYmd(2021, 1, 15).getLunarDay().getSixStar().getName());
  }

  @Test
  public void test2() {
    Assert.assertEquals("先胜", SolarDay.fromYmd(2017, 1, 5).getLunarDay().getSixStar().getName());
  }

  @Test
  public void test3() {
    Assert.assertEquals("友引", SolarDay.fromYmd(2020, 4, 10).getLunarDay().getSixStar().getName());
  }

  @Test
  public void test4() {
    Assert.assertEquals("大安", SolarDay.fromYmd(2020, 6, 11).getLunarDay().getSixStar().getName());
  }

  @Test
  public void test5() {
    Assert.assertEquals("先胜", SolarDay.fromYmd(2020, 6, 1).getLunarDay().getSixStar().getName());
  }

  @Test
  public void test6() {
    Assert.assertEquals("先负", SolarDay.fromYmd(2020, 12, 8).getLunarDay().getSixStar().getName());
  }

  @Test
  public void test8() {
    Assert.assertEquals("赤口", SolarDay.fromYmd(2020, 12, 11).getLunarDay().getSixStar().getName());
  }

}
