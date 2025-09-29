package com.tyme.test;

import com.tyme.culture.Phase;
import com.tyme.culture.PhaseDay;
import com.tyme.lunar.LunarDay;
import com.tyme.solar.SolarDay;
import com.tyme.solar.SolarTime;
import org.junit.Assert;
import org.junit.Test;

/**
 * 月相测试
 *
 * @author 6tail
 */
public class PhaseTest {

  @Test
  public void test0() {
    Phase phase = Phase.fromName(2025, 7, "下弦月");
    Assert.assertEquals("2025年9月14日 18:32:57", phase.getSolarTime().toString());
  }

  @Test
  public void test1() {
    Phase phase = Phase.fromIndex(2025, 7, 6);
    Assert.assertEquals("2025年9月14日 18:32:57", phase.getSolarTime().toString());
  }

  @Test
  public void test2() {
    Phase phase = Phase.fromIndex(2025, 7, 8);
    Assert.assertEquals("2025年9月22日 03:54:07", phase.getSolarTime().toString());
  }

  @Test
  public void test3() {
    Phase phase = SolarDay.fromYmd(2025, 9, 21).getPhase();
    Assert.assertEquals("残月", phase.toString());
  }

  @Test
  public void test4() {
    Phase phase = LunarDay.fromYmd(2025, 7, 30).getPhase();
    Assert.assertEquals("残月", phase.toString());
  }

  @Test
  public void test5() {
    Phase phase = SolarTime.fromYmdHms(2025, 9, 22, 4, 0, 0).getPhase();
    Assert.assertEquals("蛾眉月", phase.toString());
  }

  @Test
  public void test6() {
    Phase phase = SolarTime.fromYmdHms(2025, 9, 22, 3, 0, 0).getPhase();
    Assert.assertEquals("残月", phase.toString());
  }

  @Test
  public void test7() {
    PhaseDay d = SolarDay.fromYmd(2023, 9, 15).getPhaseDay();
    Assert.assertEquals("新月第1天", d.toString());
  }

  @Test
  public void test8() {
    PhaseDay d = SolarDay.fromYmd(2023, 9, 17).getPhaseDay();
    Assert.assertEquals("蛾眉月第2天", d.toString());
  }

  @Test
  public void test9() {
    Phase phase = SolarTime.fromYmdHms(2025, 9, 22, 3, 54, 7).getPhase();
    Assert.assertEquals("新月", phase.toString());
  }

  @Test
  public void test10() {
    Phase phase = SolarTime.fromYmdHms(2025, 9, 22, 3, 54, 6).getPhase();
    Assert.assertEquals("残月", phase.toString());
  }

  @Test
  public void test11() {
    Phase phase = SolarTime.fromYmdHms(2025, 9, 22, 3, 54, 8).getPhase();
    Assert.assertEquals("蛾眉月", phase.toString());
  }

  @Test
  public void test12() {
    Phase phase = LunarDay.fromYmd(2025, -6, 20).getPhase();
    Assert.assertEquals("亏凸月", phase.toString());
  }

  @Test
  public void test13() {
    Phase phase = SolarDay.fromYmd(2025, 8, 13).getPhase();
    Assert.assertEquals("亏凸月", phase.toString());
  }
}
