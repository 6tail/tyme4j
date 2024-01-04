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

}
