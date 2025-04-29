package com.tyme.test;

import com.tyme.culture.Zodiac;
import com.tyme.rabbyung.RabByungElement;
import com.tyme.rabbyung.RabByungYear;
import org.junit.Assert;
import org.junit.Test;

/**
 * 藏历年测试
 *
 * @author 6tail
 */
public class RabByungYearTest {

  @Test
  public void test0() {
    RabByungYear y = RabByungYear.fromElementZodiac(0, RabByungElement.fromName("火"), Zodiac.fromName("兔"));
    Assert.assertEquals("第一饶迥火兔年", y.getName());
    Assert.assertEquals("1027年", y.getSolarYear().getName());
    Assert.assertEquals("丁卯", y.getSixtyCycle().getName());
    Assert.assertEquals(10, y.getLeapMonth());
  }

  @Test
  public void test1() {
    Assert.assertEquals("第一饶迥火兔年", RabByungYear.fromYear(1027).getName());
  }

  @Test
  public void test2() {
    Assert.assertEquals("第十七饶迥铁虎年", RabByungYear.fromYear(2010).getName());
  }

  @Test
  public void test3() {
    Assert.assertEquals(5, RabByungYear.fromYear(2043).getLeapMonth());
    Assert.assertEquals(0, RabByungYear.fromYear(2044).getLeapMonth());
  }

  @Test
  public void test4() {
    Assert.assertEquals("第十六饶迥铁牛年", RabByungYear.fromYear(1961).getName());
  }

}
