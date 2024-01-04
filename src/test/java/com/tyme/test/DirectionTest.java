package com.tyme.test;

import com.tyme.solar.SolarDay;
import org.junit.Assert;
import org.junit.Test;

/**
 * 方位测试
 *
 * @author 6tail
 */
public class DirectionTest {

  /**
   * 福神方位
   */
  @Test
  public void test1() {
    Assert.assertEquals("东南", SolarDay.fromYmd(2021, 11, 13).getLunarDay().getSixtyCycle().getHeavenStem().getMascotDirection().getName());
  }

  /**
   * 福神方位
   */
  @Test
  public void test2() {
    Assert.assertEquals("东南", SolarDay.fromYmd(2024, 1, 1).getLunarDay().getSixtyCycle().getHeavenStem().getMascotDirection().getName());
  }

  /**
   * 太岁方位
   */
  @Test
  public void test3() {
    Assert.assertEquals("东", SolarDay.fromYmd(2023, 11, 6).getLunarDay().getJupiterDirection().getName());
  }

}
