package com.tyme.test;

import com.tyme.solar.SolarSeason;
import org.junit.Assert;
import org.junit.Test;

/**
 * 公历季度测试
 *
 * @author 6tail
 */
public class SolarSeasonTest {

  @Test
  public void test0() {
    SolarSeason season = SolarSeason.fromIndex(2023, 0);
    Assert.assertEquals("2023年一季度", season.toString());
    Assert.assertEquals("2021年四季度", season.next(-5).toString());
  }

}
