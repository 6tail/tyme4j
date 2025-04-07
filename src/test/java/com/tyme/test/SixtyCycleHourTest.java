package com.tyme.test;

import com.tyme.eightchar.EightChar;
import com.tyme.sixtycycle.SixtyCycleDay;
import com.tyme.sixtycycle.SixtyCycleHour;
import com.tyme.solar.SolarTime;
import org.junit.Assert;
import org.junit.Test;

/**
 * 干支时辰测试
 *
 * @author 6tail
 */
public class SixtyCycleHourTest {

  @Test
  public void test0() {
    SixtyCycleHour hour = SolarTime.fromYmdHms(2025, 2, 3, 23, 0, 0).getSixtyCycleHour();
    Assert.assertEquals("乙巳年戊寅月甲辰日甲子时", hour.toString());
    SixtyCycleDay day = hour.getSixtyCycleDay();
    Assert.assertEquals("乙巳年戊寅月甲辰日", day.toString());
    Assert.assertEquals("2025年2月3日", day.getSolarDay().toString());
  }

  @Test
  public void test1() {
    SixtyCycleHour hour = SolarTime.fromYmdHms(2025, 2, 3, 4, 0, 0).getSixtyCycleHour();
    Assert.assertEquals("甲辰年丁丑月癸卯日甲寅时", hour.toString());
    SixtyCycleDay day = hour.getSixtyCycleDay();
    Assert.assertEquals("甲辰年丁丑月癸卯日", day.toString());
    Assert.assertEquals("2025年2月3日", day.getSolarDay().toString());
  }

  @Test
  public void test2() {
    SixtyCycleHour hour = SolarTime.fromYmdHms(2025, 2, 3, 22, 30, 0).getSixtyCycleHour();
    Assert.assertEquals("乙巳年戊寅月癸卯日癸亥时", hour.toString());
    SixtyCycleDay day = hour.getSixtyCycleDay();
    Assert.assertEquals("乙巳年戊寅月癸卯日", day.toString());
    Assert.assertEquals("2025年2月3日", day.getSolarDay().toString());
  }

  @Test
  public void test3() {
    EightChar eightChar = SolarTime.fromYmdHms(1988, 2, 15, 23, 30, 0).getSixtyCycleHour().getEightChar();
    Assert.assertEquals("戊辰", eightChar.getYear().getName());
    Assert.assertEquals("甲寅", eightChar.getMonth().getName());
    Assert.assertEquals("辛丑", eightChar.getDay().getName());
    Assert.assertEquals("戊子", eightChar.getHour().getName());
  }

}
