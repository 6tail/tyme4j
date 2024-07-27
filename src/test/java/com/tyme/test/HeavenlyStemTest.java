package com.tyme.test;

import com.tyme.sixtycycle.HeavenStem;
import org.junit.Assert;
import org.junit.Test;

/**
 * 天干测试
 *
 * @author 6tail
 */
public class HeavenlyStemTest {

  @Test
  public void test0() {
    Assert.assertEquals("甲", HeavenStem.fromIndex(0).getName());
  }

  @Test
  public void test1() {
    Assert.assertEquals(0, HeavenStem.fromName("甲").getIndex());
  }

  /**
   * 天干的五行生克
   */
  @Test
  public void test2() {
    Assert.assertEquals(HeavenStem.fromName("丙").getElement(), HeavenStem.fromName("甲").getElement().getReinforce());
  }

  /**
   * 十神
   */
  @Test
  public void test3() {
    Assert.assertEquals("比肩", HeavenStem.fromName("甲").getTenStar(HeavenStem.fromName("甲")).getName());
    Assert.assertEquals("劫财", HeavenStem.fromName("甲").getTenStar(HeavenStem.fromName("乙")).getName());
    Assert.assertEquals("食神", HeavenStem.fromName("甲").getTenStar(HeavenStem.fromName("丙")).getName());
    Assert.assertEquals("伤官", HeavenStem.fromName("甲").getTenStar(HeavenStem.fromName("丁")).getName());
    Assert.assertEquals("偏财", HeavenStem.fromName("甲").getTenStar(HeavenStem.fromName("戊")).getName());
    Assert.assertEquals("正财", HeavenStem.fromName("甲").getTenStar(HeavenStem.fromName("己")).getName());
    Assert.assertEquals("七杀", HeavenStem.fromName("甲").getTenStar(HeavenStem.fromName("庚")).getName());
    Assert.assertEquals("正官", HeavenStem.fromName("甲").getTenStar(HeavenStem.fromName("辛")).getName());
    Assert.assertEquals("偏印", HeavenStem.fromName("甲").getTenStar(HeavenStem.fromName("壬")).getName());
    Assert.assertEquals("正印", HeavenStem.fromName("甲").getTenStar(HeavenStem.fromName("癸")).getName());
  }

  /**
   * 天干五合
   */
  @Test
  public void test4() {
    Assert.assertEquals("乙", HeavenStem.fromName("庚").getCombine().getName());
    Assert.assertEquals("庚", HeavenStem.fromName("乙").getCombine().getName());
    Assert.assertEquals("土", HeavenStem.fromName("甲").combine(HeavenStem.fromName("己")).getName());
    Assert.assertEquals("土", HeavenStem.fromName("己").combine(HeavenStem.fromName("甲")).getName());
    Assert.assertEquals("木", HeavenStem.fromName("丁").combine(HeavenStem.fromName("壬")).getName());
    Assert.assertEquals("木", HeavenStem.fromName("壬").combine(HeavenStem.fromName("丁")).getName());
    Assert.assertNull(HeavenStem.fromName("甲").combine(HeavenStem.fromName("乙")));
  }

}
