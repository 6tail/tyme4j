package com.tyme.test;

import com.tyme.rabbyung.RabByungMonth;
import org.junit.Assert;
import org.junit.Test;

/**
 * 藏历月测试
 *
 * @author 6tail
 */
public class RabByungMonthTest {

  @Test
  public void test0() {
    Assert.assertEquals("第十六饶迥铁虎年十二月", RabByungMonth.fromYm(1950, 12).toString());
  }

}
