package com.tyme.test;

import com.tyme.solar.SolarDay;
import org.junit.Assert;
import org.junit.Test;

/**
 * 建除十二值神测试
 *
 * @author 6tail
 */
public class DutyTest {

  @Test
  public void test0() {
    Assert.assertEquals("闭", SolarDay.fromYmd(2023, 10, 30).getLunarDay().getDuty().getName());
    Assert.assertEquals("闭", SolarDay.fromYmd(2023, 10, 30).getSixtyCycleDay().getDuty().getName());
  }

  @Test
  public void test1() {
    Assert.assertEquals("建", SolarDay.fromYmd(2023, 10, 19).getLunarDay().getDuty().getName());
    Assert.assertEquals("建", SolarDay.fromYmd(2023, 10, 19).getSixtyCycleDay().getDuty().getName());
  }

  @Test
  public void test2() {
    Assert.assertEquals("除", SolarDay.fromYmd(2023, 10, 7).getLunarDay().getDuty().getName());
    Assert.assertEquals("除", SolarDay.fromYmd(2023, 10, 7).getSixtyCycleDay().getDuty().getName());
  }

  @Test
  public void test3() {
    Assert.assertEquals("除", SolarDay.fromYmd(2023, 10, 8).getLunarDay().getDuty().getName());
    Assert.assertEquals("除", SolarDay.fromYmd(2023, 10, 8).getSixtyCycleDay().getDuty().getName());
  }

}
