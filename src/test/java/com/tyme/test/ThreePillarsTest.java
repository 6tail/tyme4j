package com.tyme.test;

import com.tyme.sixtycycle.ThreePillars;
import com.tyme.solar.SolarDay;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 三柱测试
 *
 * @author 6tail
 */
public class ThreePillarsTest {

  @Test
  public void test50() {
    List<SolarDay> solarDays = new ThreePillars("甲戌", "甲戌", "甲戌").getSolarDays(1, 2200);
    List<String> actual = new ArrayList<>();
    for (SolarDay solarDay : solarDays) {
      actual.add(solarDay.toString());
    }

    List<String> expected = new ArrayList<>();
    expected.add("14年10月17日");
    expected.add("194年11月1日");
    expected.add("254年10月17日");
    expected.add("434年11月1日");
    expected.add("494年10月17日");
    expected.add("674年11月1日");
    expected.add("734年10月17日");
    expected.add("794年10月2日");
    expected.add("974年10月17日");
    expected.add("1034年10月2日");
    expected.add("1214年10月17日");
    expected.add("1274年10月2日");
    expected.add("1454年10月17日");
    expected.add("1514年10月2日");
    expected.add("1694年10月27日");
    expected.add("1754年10月13日");
    expected.add("1934年10月30日");
    expected.add("1994年10月15日");
    expected.add("2174年10月31日");
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void test1() {
    Assert.assertEquals("甲戌 甲戌 甲戌", SolarDay.fromYmd(1034, 10, 2).getSixtyCycleDay().getThreePillars().getName());
  }

}
