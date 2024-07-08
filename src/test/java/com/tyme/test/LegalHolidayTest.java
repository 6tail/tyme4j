package com.tyme.test;

import com.tyme.holiday.LegalHoliday;
import com.tyme.solar.SolarDay;
import org.junit.Assert;
import org.junit.Test;

/**
 * 法定节假日测试
 *
 * @author 6tail
 */
public class LegalHolidayTest {

  @Test
  public void test0() {
    LegalHoliday d = LegalHoliday.fromYmd(2011, 5, 1);
    Assert.assertNotNull(d);
    Assert.assertEquals("2011年5月1日 劳动节(休)", d.toString());

    Assert.assertEquals("2011年5月2日 劳动节(休)", d.next(1).toString());
    Assert.assertEquals("2011年6月4日 端午节(休)", d.next(2).toString());
    Assert.assertEquals("2011年4月30日 劳动节(休)", d.next(-1).toString());
    Assert.assertEquals("2011年4月5日 清明节(休)", d.next(-2).toString());
  }

  @Test
  public void test1() {
    LegalHoliday d = LegalHoliday.fromYmd(2010, 1, 1);
    Assert.assertNotNull(d);
    while (d.getDay().getYear() < 2012) {
      System.out.println(d);
      d = d.next(1);
    }
  }

  @Test
  public void test2() {
    LegalHoliday d = LegalHoliday.fromYmd(2010, 1, 1);
    Assert.assertNotNull(d);
    while (d.getDay().getYear() > 2007) {
      System.out.println(d);
      d = d.next(-1);
    }
  }

  @Test
  public void test3() {
    LegalHoliday d = LegalHoliday.fromYmd(2001, 12, 29);
    Assert.assertNotNull(d);
    Assert.assertEquals("2001年12月29日 元旦节(班)", d.toString());
    Assert.assertNull(d.next(-1));
  }

  @Test
  public void test4() {
    LegalHoliday d = LegalHoliday.fromYmd(2022, 10, 5);
    Assert.assertNotNull(d);
    Assert.assertEquals("2022年10月5日 国庆节(休)", d.toString());
    Assert.assertEquals("2022年10月4日 国庆节(休)", d.next(-1).toString());
    Assert.assertEquals("2022年10月6日 国庆节(休)", d.next(1).toString());
  }

  @Test
  public void test5() {
    LegalHoliday d = SolarDay.fromYmd(2010, 10, 1).getLegalHoliday();
    Assert.assertNotNull(d);
    Assert.assertEquals("2010年10月1日 国庆节(休)", d.toString());
  }

}
