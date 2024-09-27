package com.tyme.test;

import com.tyme.solar.SolarDay;
import com.tyme.solar.SolarTerm;
import org.junit.Assert;
import org.junit.Test;

/**
 * 节气测试
 *
 * @author 6tail
 */
public class SolarTermTest {

  @Test
  public void test0() {
    // 冬至在去年，2022-12-22 05:48:11
    SolarTerm dongZhi = SolarTerm.fromName(2023, "冬至");
    Assert.assertEquals("冬至", dongZhi.getName());
    Assert.assertEquals(0, dongZhi.getIndex());
    // 儒略日
    Assert.assertEquals(2459935.7417939813, dongZhi.getJulianDay().getDay(), 0.00001);
    // 公历日
    Assert.assertEquals("2022年12月22日", dongZhi.getJulianDay().getSolarDay().toString());

    // 冬至顺推23次，就是大雪 2023-12-07 17:32:55
    SolarTerm daXue = dongZhi.next(23);
    Assert.assertEquals("大雪", daXue.getName());
    Assert.assertEquals(23, daXue.getIndex());
    Assert.assertEquals(2460286.2311921297, daXue.getJulianDay().getDay(), 0.00001);
    Assert.assertEquals("2023年12月7日", daXue.getJulianDay().getSolarDay().toString());

    // 冬至逆推2次，就是上一年的小雪 2022-11-22 16:20:28
    SolarTerm xiaoXue = dongZhi.next(-2);
    Assert.assertEquals("小雪", xiaoXue.getName());
    Assert.assertEquals(22, xiaoXue.getIndex());
    Assert.assertEquals(2459906.1808796297, xiaoXue.getJulianDay().getDay(), 0.00001);
    Assert.assertEquals("2022年11月22日", xiaoXue.getJulianDay().getSolarDay().toString());

    // 冬至顺推24次，就是下一个冬至 2023-12-22 11:27:20
    SolarTerm dongZhi2 = dongZhi.next(24);
    Assert.assertEquals("冬至", dongZhi2.getName());
    Assert.assertEquals(0, dongZhi2.getIndex());
    Assert.assertEquals(2460300.977314815, dongZhi2.getJulianDay().getDay(), 0.00001);
    Assert.assertEquals("2023年12月22日", dongZhi2.getJulianDay().getSolarDay().toString());
  }

  @Test
  public void test1() {
    // 公历2023年的雨水，2023-02-19 06:34:16
    SolarTerm jq = SolarTerm.fromName(2023, "雨水");
    Assert.assertEquals("雨水", jq.getName());
    Assert.assertEquals(4, jq.getIndex());
    Assert.assertEquals(2459994.773796296, jq.getJulianDay().getDay(), 0.00001);
  }

  @Test
  public void test2() {
    // 公历2023年的大雪，2023-12-07 17:32:55
    SolarTerm jq = SolarTerm.fromName(2023, "大雪");
    Assert.assertEquals("大雪", jq.getName());
    // 索引
    Assert.assertEquals(23, jq.getIndex());
    // 公历
    Assert.assertEquals("2023年12月7日", jq.getJulianDay().getSolarDay().toString());
    // 农历
    Assert.assertEquals("农历癸卯年十月廿五", jq.getJulianDay().getSolarDay().getLunarDay().toString());
    // 儒略日
    Assert.assertEquals(2460286.2311921297, jq.getJulianDay().getDay(), 0.00001);
    // 推移
    Assert.assertEquals("雨水", jq.next(5).getName());
  }

  @Test
  public void test3() {
    Assert.assertEquals("寒露", SolarDay.fromYmd(2023, 10, 10).getTerm().getName());
  }

  @Test
  public void test4() {
    // 大雪当天
    Assert.assertEquals("大雪第1天", SolarDay.fromYmd(2023, 12, 7).getTermDay().toString());
    // 天数索引
    Assert.assertEquals(0, SolarDay.fromYmd(2023, 12, 7).getTermDay().getDayIndex());

    Assert.assertEquals("大雪第2天", SolarDay.fromYmd(2023, 12, 8).getTermDay().toString());
    Assert.assertEquals("大雪第15天", SolarDay.fromYmd(2023, 12, 21).getTermDay().toString());

    Assert.assertEquals("冬至第1天", SolarDay.fromYmd(2023, 12, 22).getTermDay().toString());
  }

  @Test
  public void test5() {
    Assert.assertEquals("2024年1月6日 04:49:20", SolarTerm.fromName(2024, "小寒").getJulianDay().getSolarTime().toString());
  }
}
