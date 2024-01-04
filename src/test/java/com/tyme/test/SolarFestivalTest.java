package com.tyme.test;

import com.tyme.festival.SolarFestival;
import com.tyme.solar.SolarDay;
import org.junit.Assert;
import org.junit.Test;

/**
 * 公历现代节日测试
 *
 * @author 6tail
 */
public class SolarFestivalTest {

  @Test
  public void test0() {
    for (int i = 0, j = SolarFestival.NAMES.length; i < j; i++) {
      SolarFestival f = SolarFestival.fromIndex(2023, i);
      Assert.assertNotNull(f);
      Assert.assertEquals(SolarFestival.NAMES[i], f.getName());
    }
  }

  @Test
  public void test1() {
    SolarFestival f = SolarFestival.fromIndex(2023, 0);
    Assert.assertNotNull(f);
    for (int i = 0, j = SolarFestival.NAMES.length; i < j; i++) {
      Assert.assertEquals(SolarFestival.NAMES[i], f.next(i).getName());
    }
  }

  @Test
  public void test2() {
    SolarFestival f = SolarFestival.fromIndex(2023, 0);
    Assert.assertNotNull(f);
    Assert.assertEquals("2024年5月1日 五一劳动节", f.next(13).toString());
    Assert.assertEquals("2022年8月1日 八一建军节", f.next(-3).toString());
  }

  @Test
  public void test3() {
    SolarFestival f = SolarFestival.fromIndex(2023, 0);
    Assert.assertNotNull(f);
    Assert.assertEquals("2022年3月8日 三八妇女节", f.next(-9).toString());
  }

  @Test
  public void test4() {
    SolarFestival f = SolarDay.fromYmd(2010, 1, 1).getFestival();
    Assert.assertNotNull(f);
    Assert.assertEquals("2010年1月1日 元旦", f.toString());
  }

  @Test
  public void test5() {
    SolarFestival f = SolarDay.fromYmd(2021, 5, 4).getFestival();
    Assert.assertNotNull(f);
    Assert.assertEquals("2021年5月4日 五四青年节", f.toString());
  }

  @Test
  public void test6() {
    SolarFestival f = SolarDay.fromYmd(1939, 5, 4).getFestival();
    Assert.assertNull(f);
  }

}
