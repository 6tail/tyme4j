package com.tyme.test;

import com.tyme.solar.SolarDay;
import com.tyme.solar.SolarMonth;
import com.tyme.solar.SolarWeek;
import com.tyme.solar.SolarYear;
import org.junit.Assert;
import org.junit.Test;


/**
 * 公历年测试
 *
 * @author 6tail
 */
public class SolarYearTest {

  @Test
  public void test0() {
    Assert.assertEquals("2023年", SolarYear.fromYear(2023).getName());
  }

  @Test
  public void test1() {
    Assert.assertFalse(SolarYear.fromYear(2023).isLeap());
  }

  @Test
  public void test2() {
    Assert.assertTrue(SolarYear.fromYear(1500).isLeap());
  }

  @Test
  public void test3() {
    Assert.assertFalse(SolarYear.fromYear(1700).isLeap());
  }

  @Test
  public void test4() {
    Assert.assertEquals(365, SolarYear.fromYear(2023).getDayCount());
  }

  @Test
  public void test5() {
    Assert.assertEquals("2028年", SolarYear.fromYear(2023).next(5).getName());
  }

  @Test
  public void test6() {
    Assert.assertEquals("2018年", SolarYear.fromYear(2023).next(-5).getName());
  }

  /**
   * 生成公历年历示例
   */
  @Test
  public void test7() {
    SolarYear year = SolarYear.fromYear(2024);
    for (SolarMonth month : year.getMonths()) {
      System.out.println(month);
      for (SolarWeek week : month.getWeeks(1)) {
        System.out.print(week.getName());
        for (SolarDay day : week.getDays()) {
          System.out.print(" " + day.getDay());
        }
        System.out.println();
      }
      System.out.println();
    }
  }

}
