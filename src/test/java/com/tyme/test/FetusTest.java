package com.tyme.test;

import com.tyme.solar.SolarDay;
import org.junit.Assert;
import org.junit.Test;

/**
 * 胎神测试
 *
 * @author 6tail
 */
public class FetusTest {

  /**
   * 逐日胎神
   */
  @Test
  public void test1() {
    Assert.assertEquals("碓磨厕 外东南", SolarDay.fromYmd(2021, 11, 13).getLunarDay().getFetusDay().getName());
  }

  @Test
  public void test2() {
    Assert.assertEquals("占门碓 外东南", SolarDay.fromYmd(2021, 11, 12).getLunarDay().getFetusDay().getName());
  }

  @Test
  public void test3() {
    Assert.assertEquals("厨灶厕 外西南", SolarDay.fromYmd(2011, 11, 12).getLunarDay().getFetusDay().getName());
  }

}
