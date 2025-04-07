package com.tyme.test;

import com.tyme.sixtycycle.SixtyCycleDay;
import com.tyme.solar.SolarDay;
import org.junit.Assert;
import org.junit.Test;

/**
 * 干支日测试
 *
 * @author 6tail
 */
public class SixtyCycleDayTest {

  @Test
  public void test0() {
    Assert.assertEquals("乙巳年戊寅月癸卯日", SixtyCycleDay.fromSolarDay(SolarDay.fromYmd(2025, 2, 3)).toString());
  }

  @Test
  public void test1() {
    Assert.assertEquals("甲辰年丁丑月壬寅日", SixtyCycleDay.fromSolarDay(SolarDay.fromYmd(2025, 2, 2)).toString());
  }

}
