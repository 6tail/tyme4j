package com.tyme.test;

import com.tyme.solar.SolarTime;
import org.junit.Assert;
import org.junit.Test;

/**
 * 公历时刻测试
 *
 * @author 6tail
 */
public class SolarTimeTest {

  @Test
  public void test0() {
    SolarTime time = SolarTime.fromYmdHms(2023, 1, 1, 13, 5, 20);
    Assert.assertEquals("13:05:20", time.getName());
    Assert.assertEquals("13:04:59", time.next(-21).getName());
  }

  @Test
  public void test1() {
    SolarTime time = SolarTime.fromYmdHms(2023, 1, 1, 13, 5, 20);
    Assert.assertEquals("13:05:20", time.getName());
    Assert.assertEquals("14:06:01", time.next(3641).getName());
  }

}
