package com.tyme.test;

import com.tyme.sixtycycle.EarthBranch;
import org.junit.Assert;
import org.junit.Test;

/**
 * 地支测试
 *
 * @author 6tail
 */
public class EarthlyBranchTest {

  @Test
  public void test0() {
    Assert.assertEquals("子", EarthBranch.fromIndex(0).getName());
  }

  @Test
  public void test1() {
    Assert.assertEquals(0, EarthBranch.fromName("子").getIndex());
  }

  @Test
  public void test2() {
    // 冲
    Assert.assertEquals("午", EarthBranch.fromName("子").getOpposite().getName());
    Assert.assertEquals("辰", EarthBranch.fromName("戌").getOpposite().getName());
  }

  @Test
  public void test3() {
    // 六合
    Assert.assertEquals("丑", EarthBranch.fromName("子").getCombine().getName());
    Assert.assertEquals("巳", EarthBranch.fromName("申").getCombine().getName());
  }

  @Test
  public void test4() {
    // 六害
    Assert.assertEquals("寅", EarthBranch.fromName("巳").getHarm().getName());
    Assert.assertEquals("亥", EarthBranch.fromName("申").getHarm().getName());
  }

  @Test
  public void test5() {
    // 合化
    Assert.assertEquals("火", EarthBranch.fromName("卯").combine(EarthBranch.fromName("戌")).getName());
    Assert.assertEquals("火", EarthBranch.fromName("戌").combine(EarthBranch.fromName("卯")).getName());
    // 卯子无法合化
    Assert.assertNull(EarthBranch.fromName("卯").combine(EarthBranch.fromName("子")));
  }

}
