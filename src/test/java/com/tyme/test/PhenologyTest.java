package com.tyme.test;

import com.tyme.culture.phenology.Phenology;
import com.tyme.culture.phenology.PhenologyDay;
import com.tyme.culture.phenology.ThreePhenology;
import com.tyme.jd.JulianDay;
import com.tyme.solar.SolarDay;
import com.tyme.solar.SolarTime;
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
    Assert.assertEquals("2020年4月19日", phenology.getPhenology().getJulianDay().getSolarDay().toString());
    Assert.assertEquals("2020年4月19日 22:45:29", phenology.getPhenology().getJulianDay().getSolarTime().toString());
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
    Assert.assertEquals("2021年12月21日", solarDay.getTerm().getJulianDay().getSolarDay().toString());
    Assert.assertEquals("二候", threePhenology.getName());
    Assert.assertEquals("麋角解", phenology.getName());
    Assert.assertEquals("2021年12月26日", phenology.getPhenology().getJulianDay().getSolarDay().toString());
    Assert.assertEquals("2021年12月26日 21:48:55", phenology.getPhenology().getJulianDay().getSolarTime().toString());
    // 该候的第1天
    Assert.assertEquals(0, phenology.getDayIndex());
  }

  @Test
  public void test2() {
    Phenology p = Phenology.fromIndex(2026, 1);
    JulianDay jd = p.getJulianDay();
    Assert.assertEquals("麋角解", p.getName());
    Assert.assertEquals("2025年12月26日", jd.getSolarDay().toString());
    Assert.assertEquals("2025年12月26日 20:49:56", jd.getSolarTime().toString());
  }

  @Test
  public void test3() {
    Phenology p = SolarDay.fromYmd(2025, 12, 26).getPhenology();
    JulianDay jd = p.getJulianDay();
    Assert.assertEquals("麋角解", p.getName());
    Assert.assertEquals("2025年12月26日", jd.getSolarDay().toString());
    Assert.assertEquals("2025年12月26日 20:49:56", jd.getSolarTime().toString());
  }

  @Test
  public void test4() {
    Assert.assertEquals("蚯蚓结", SolarTime.fromYmdHms(2025, 12, 26, 20, 49, 38).getPhenology().getName());
    Assert.assertEquals("麋角解", SolarTime.fromYmdHms(2025, 12, 26, 20, 49, 56).getPhenology().getName());
  }

}
