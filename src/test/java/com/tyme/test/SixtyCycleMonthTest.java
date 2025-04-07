package com.tyme.test;

import com.tyme.sixtycycle.SixtyCycleMonth;
import org.junit.Assert;
import org.junit.Test;

/**
 * 干支月测试
 *
 * @author 6tail
 */
public class SixtyCycleMonthTest {

  @Test
  public void test23() {
    SixtyCycleMonth month = SixtyCycleMonth.fromIndex(2025, 0);
    Assert.assertEquals("乙巳年戊寅月", month.toString());
  }
}
