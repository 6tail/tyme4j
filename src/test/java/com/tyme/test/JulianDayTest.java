package com.tyme.test;

import com.tyme.solar.SolarDay;
import org.junit.Assert;
import org.junit.Test;

/**
 * 儒略日测试
 *
 * @author 6tail
 */
public class JulianDayTest {

  @Test
  public void test0() {
    Assert.assertEquals("2023年1月1日", SolarDay.fromYmd(2023, 1, 1).getJulianDay().getSolarDay().toString());
  }

}
