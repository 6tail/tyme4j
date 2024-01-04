package com.tyme.test;

import com.tyme.culture.phenology.PhenologyDay;
import com.tyme.culture.phenology.ThreePhenology;
import com.tyme.solar.SolarDay;
import org.junit.Assert;
import org.junit.Test;

/**
 * 物候测试
 *
 * @author 6tail
 */
public class PhenologyTest {

  @Test
  public void test0() {
    SolarDay solarDay = SolarDay.fromYmd(2020, 4, 23);
    // 七十二候
    PhenologyDay phenology = solarDay.getPhenologyDay();
    // 三候
    ThreePhenology threePhenology = phenology.getPhenology().getThreePhenology();
    Assert.assertEquals("谷雨", solarDay.getTerm().getName());
    Assert.assertEquals("初候", threePhenology.getName());
    Assert.assertEquals("萍始生", phenology.getName());
    // 该候的第5天
    Assert.assertEquals(4, phenology.getDayIndex());
  }

  @Test
  public void test1() {
    SolarDay solarDay = SolarDay.fromYmd(2021, 12, 26);
    // 七十二候
    PhenologyDay phenology = solarDay.getPhenologyDay();
    // 三候
    ThreePhenology threePhenology = phenology.getPhenology().getThreePhenology();
    Assert.assertEquals("冬至", solarDay.getTerm().getName());
    Assert.assertEquals("二候", threePhenology.getName());
    Assert.assertEquals("麋角解", phenology.getName());
    // 该候的第1天
    Assert.assertEquals(0, phenology.getDayIndex());
  }

}
