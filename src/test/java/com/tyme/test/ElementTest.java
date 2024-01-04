package com.tyme.test;

import com.tyme.culture.Element;
import com.tyme.sixtycycle.EarthBranch;
import com.tyme.sixtycycle.HeavenStem;
import org.junit.Assert;
import org.junit.Test;

/**
 * 五行测试
 *
 * @author 6tail
 */
public class ElementTest {

  /**
   * 金克木
   */
  @Test
  public void test0() {
    Assert.assertEquals(Element.fromName("木"), Element.fromName("金").getRestrain());
  }

  /**
   * 火生土
   */
  @Test
  public void test1() {
    Assert.assertEquals(Element.fromName("土"), Element.fromName("火").getReinforce());
  }

  @Test
  public void test2() {
    Assert.assertEquals("火", HeavenStem.fromName("丙").getElement().getName());
  }

  @Test
  public void test3() {
    // 地支寅的五行为木
    Assert.assertEquals("木", EarthBranch.fromName("寅").getElement().getName());

    // 地支寅的五行(木)生火
    Assert.assertEquals(Element.fromName("火"), EarthBranch.fromName("寅").getElement().getReinforce());
  }

}
