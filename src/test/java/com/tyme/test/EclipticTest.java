package com.tyme.test;

import com.tyme.culture.star.twelve.TwelveStar;
import com.tyme.solar.SolarDay;
import org.junit.Assert;
import org.junit.Test;

/**
 * 黄道黑道十二神测试
 *
 * @author 6tail
 */
public class EclipticTest {

  @Test
  public void test0() {
    TwelveStar star = SolarDay.fromYmd(2023, 10, 30).getLunarDay().getTwelveStar();
    Assert.assertEquals("天德", star.getName());
    Assert.assertEquals("黄道", star.getEcliptic().getName());
    Assert.assertEquals("吉", star.getEcliptic().getLuck().getName());

    star = SolarDay.fromYmd(2023, 10, 30).getSixtyCycleDay().getTwelveStar();
    Assert.assertEquals("天德", star.getName());
    Assert.assertEquals("黄道", star.getEcliptic().getName());
    Assert.assertEquals("吉", star.getEcliptic().getLuck().getName());
  }

  @Test
  public void test1() {
    TwelveStar star = SolarDay.fromYmd(2023, 10, 19).getLunarDay().getTwelveStar();
    Assert.assertEquals("白虎", star.getName());
    Assert.assertEquals("黑道", star.getEcliptic().getName());
    Assert.assertEquals("凶", star.getEcliptic().getLuck().getName());

    star = SolarDay.fromYmd(2023, 10, 19).getSixtyCycleDay().getTwelveStar();
    Assert.assertEquals("白虎", star.getName());
    Assert.assertEquals("黑道", star.getEcliptic().getName());
    Assert.assertEquals("凶", star.getEcliptic().getLuck().getName());
  }

  @Test
  public void test2() {
    TwelveStar star = SolarDay.fromYmd(2023, 10, 7).getLunarDay().getTwelveStar();
    Assert.assertEquals("天牢", star.getName());
    Assert.assertEquals("黑道", star.getEcliptic().getName());
    Assert.assertEquals("凶", star.getEcliptic().getLuck().getName());

    star = SolarDay.fromYmd(2023, 10, 7).getSixtyCycleDay().getTwelveStar();
    Assert.assertEquals("天牢", star.getName());
    Assert.assertEquals("黑道", star.getEcliptic().getName());
    Assert.assertEquals("凶", star.getEcliptic().getLuck().getName());
  }

  @Test
  public void test3() {
    TwelveStar star = SolarDay.fromYmd(2023, 10, 8).getLunarDay().getTwelveStar();
    Assert.assertEquals("玉堂", star.getName());
    Assert.assertEquals("黄道", star.getEcliptic().getName());
    Assert.assertEquals("吉", star.getEcliptic().getLuck().getName());

    star = SolarDay.fromYmd(2023, 10, 8).getSixtyCycleDay().getTwelveStar();
    Assert.assertEquals("玉堂", star.getName());
    Assert.assertEquals("黄道", star.getEcliptic().getName());
    Assert.assertEquals("吉", star.getEcliptic().getLuck().getName());
  }

}
