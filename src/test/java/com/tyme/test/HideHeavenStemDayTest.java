package com.tyme.test;

import com.tyme.sixtycycle.HideHeavenStemDay;
import com.tyme.solar.SolarDay;
import org.junit.Assert;
import org.junit.Test;

/**
 * 人元司令分野测试
 *
 * @author 6tail
 */
public class HideHeavenStemDayTest {

  @Test
  public void test0() {
    HideHeavenStemDay d = SolarDay.fromYmd(2024, 12, 4).getHideHeavenStemDay();
    Assert.assertEquals("本气", d.getHideHeavenStem().getType().getName());
    Assert.assertEquals("壬", d.getHideHeavenStem().getName());
    Assert.assertEquals("壬", d.getHideHeavenStem().toString());
    Assert.assertEquals("水", d.getHideHeavenStem().getHeavenStem().getElement().getName());

    Assert.assertEquals("壬水", d.getName());
    Assert.assertEquals(15, d.getDayIndex());
    Assert.assertEquals("壬水第16天", d.toString());
  }

  @Test
  public void test1() {
    HideHeavenStemDay d = SolarDay.fromYmd(2024, 11, 7).getHideHeavenStemDay();
    Assert.assertEquals("余气", d.getHideHeavenStem().getType().getName());
    Assert.assertEquals("戊", d.getHideHeavenStem().getName());
    Assert.assertEquals("戊", d.getHideHeavenStem().toString());
    Assert.assertEquals("土", d.getHideHeavenStem().getHeavenStem().getElement().getName());

    Assert.assertEquals("戊土", d.getName());
    Assert.assertEquals(0, d.getDayIndex());
    Assert.assertEquals("戊土第1天", d.toString());
  }

}
