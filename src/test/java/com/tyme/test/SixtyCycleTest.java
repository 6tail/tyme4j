package com.tyme.test;

import com.tyme.sixtycycle.EarthBranch;
import com.tyme.sixtycycle.HeavenStem;
import com.tyme.sixtycycle.SixtyCycle;
import org.junit.Assert;
import org.junit.Test;

/**
 * 六十甲子测试
 *
 * @author 6tail
 */
public class SixtyCycleTest {

  @Test
  public void test0() {
    Assert.assertEquals("丁丑", SixtyCycle.fromIndex(13).getName());
  }

  @Test
  public void test1() {
    Assert.assertEquals(13, SixtyCycle.fromName("丁丑").getIndex());
  }

  /**
   * 五行
   */
  @Test
  public void test2() {
    Assert.assertEquals("石榴木", SixtyCycle.fromName("辛酉").getSound().getName());
    Assert.assertEquals("剑锋金", SixtyCycle.fromName("癸酉").getSound().getName());
    Assert.assertEquals("平地木", SixtyCycle.fromName("己亥").getSound().getName());
  }

  /**
   * 旬
   */
  @Test
  public void test3() {
    Assert.assertEquals("甲子", SixtyCycle.fromName("甲子").getTen().getName());
    Assert.assertEquals("甲寅", SixtyCycle.fromName("乙卯").getTen().getName());
    Assert.assertEquals("甲申", SixtyCycle.fromName("癸巳").getTen().getName());
  }

  /**
   * 旬空
   */
  @Test
  public void test4() {
    Assert.assertArrayEquals(new EarthBranch[]{EarthBranch.fromName("戌"), EarthBranch.fromName("亥")}, SixtyCycle.fromName("甲子").getExtraEarthBranches());
    Assert.assertArrayEquals(new EarthBranch[]{EarthBranch.fromName("子"), EarthBranch.fromName("丑")}, SixtyCycle.fromName("乙卯").getExtraEarthBranches());
    Assert.assertArrayEquals(new EarthBranch[]{EarthBranch.fromName("午"), EarthBranch.fromName("未")}, SixtyCycle.fromName("癸巳").getExtraEarthBranches());
  }

  /**
   * 地势(长生十二神)
   */
  @Test
  public void test5() {
    Assert.assertEquals("长生", HeavenStem.fromName("丙").getTerrain(EarthBranch.fromName("寅")).getName());
    Assert.assertEquals("沐浴", HeavenStem.fromName("辛").getTerrain(EarthBranch.fromName("亥")).getName());
  }

}
