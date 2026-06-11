package com.tyme.test;

import com.tyme.hijri.HijriYear;
import org.junit.Assert;
import org.junit.Test;

/**
 * 回历年测试
 *
 * @author 6tail
 */
public class HijriYearTest {

  @Test
  public void test0() {
    Assert.assertFalse(HijriYear.fromYear(1).isLeap());
    Assert.assertTrue(HijriYear.fromYear(2).isLeap());
    Assert.assertFalse(HijriYear.fromYear(0).isLeap());
    Assert.assertTrue(HijriYear.fromYear(-1).isLeap());
  }

}
