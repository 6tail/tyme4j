package com.tyme.test;

import com.tyme.culture.God;
import com.tyme.solar.SolarDay;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 神煞测试
 *
 * @author 6tail
 */
public class GodTest {
  @Test
  public void test0() {
    List<God> gods = SolarDay.fromYmd(2004, 2, 16).getSixtyCycleDay().getGods();
    List<String> ji = new ArrayList<>();
    for (God god : gods) {
      if ("吉".equals(god.getLuck().getName())) {
        ji.add(god.getName());
      }
    }

    List<String> xiong = new ArrayList<>();
    for (God god : gods) {
      if ("凶".equals(god.getLuck().getName())) {
        xiong.add(god.getName());
      }
    }
    Assert.assertEquals(Arrays.asList("天恩", "续世", "明堂"), ji);
    Assert.assertEquals(Arrays.asList("月煞", "月虚", "血支", "天贼", "五虚", "土符", "归忌", "血忌"), xiong);
  }

  @Test
  public void test1() {
    List<God> gods = SolarDay.fromYmd(2029, 11, 16).getSixtyCycleDay().getGods();
    List<String> ji = new ArrayList<>();
    for (God god : gods) {
      if ("吉".equals(god.getLuck().getName())) {
        ji.add(god.getName());
      }
    }

    List<String> xiong = new ArrayList<>();
    for (God god : gods) {
      if ("凶".equals(god.getLuck().getName())) {
        xiong.add(god.getName());
      }
    }
    Assert.assertEquals(Arrays.asList("天德合", "月空", "天恩", "益后", "金匮"), ji);
    Assert.assertEquals(Arrays.asList("月煞", "月虚", "血支", "五虚"), xiong);
  }

  @Test
  public void test2() {
    List<God> gods = SolarDay.fromYmd(1954, 7, 16).getSixtyCycleDay().getGods();

    // 吉神宜趋
    List<String> ji = new ArrayList<>();
    for (God god : gods) {
      if ("吉".equals(god.getLuck().getName())) {
        ji.add(god.getName());
      }
    }

    // 凶神宜忌
    List<String> xiong = new ArrayList<>();
    for (God god : gods) {
      if ("凶".equals(god.getLuck().getName())) {
        xiong.add(god.getName());
      }
    }

    Assert.assertEquals(Arrays.asList("民日", "天巫", "福德", "天仓", "不将", "续世", "除神", "鸣吠"), ji);
    Assert.assertEquals(Arrays.asList("劫煞", "天贼", "五虚", "五离"), xiong);
  }

  @Test
  public void test3() {
    List<God> gods = SolarDay.fromYmd(2024, 12, 27).getSixtyCycleDay().getGods();
    List<String> ji = new ArrayList<>();
    for (God god : gods) {
      if ("吉".equals(god.getLuck().getName())) {
        ji.add(god.getName());
      }
    }

    List<String> xiong = new ArrayList<>();
    for (God god : gods) {
      if ("凶".equals(god.getLuck().getName())) {
        xiong.add(god.getName());
      }
    }
    Assert.assertEquals(Arrays.asList("天恩", "四相", "阴德", "守日", "吉期", "六合", "普护", "宝光"), ji);
    Assert.assertEquals(Arrays.asList("三丧", "鬼哭"), xiong);
  }

  @Test
  public void test4() {
    List<God> gods = SolarDay.fromYmd(2024, 9, 27).getSixtyCycleDay().getGods();
    List<String> ji = new ArrayList<>();
    for (God god : gods) {
      if ("吉".equals(god.getLuck().getName())) {
        ji.add(god.getName());
      }
    }

    List<String> xiong = new ArrayList<>();
    for (God god : gods) {
      if ("凶".equals(god.getLuck().getName())) {
        xiong.add(god.getName());
      }
    }
    Assert.assertEquals(Arrays.asList("月空", "不将", "福生", "金匮", "鸣吠"), ji);
    Assert.assertEquals(Arrays.asList("天罡", "大时", "大败", "咸池", "天贼", "九坎", "九焦"), xiong);
  }

  @Test
  public void test5() {
    List<God> gods = SolarDay.fromYmd(2004, 2, 16).getLunarDay().getGods();
    List<String> ji = new ArrayList<>();
    for (God god : gods) {
      if ("吉".equals(god.getLuck().getName())) {
        ji.add(god.getName());
      }
    }

    List<String> xiong = new ArrayList<>();
    for (God god : gods) {
      if ("凶".equals(god.getLuck().getName())) {
        xiong.add(god.getName());
      }
    }
    Assert.assertEquals(Arrays.asList("天恩", "续世", "明堂"), ji);
    Assert.assertEquals(Arrays.asList("月煞", "月虚", "血支", "天贼", "五虚", "土符", "归忌", "血忌"), xiong);
  }

  @Test
  public void test6() {
    List<God> gods = SolarDay.fromYmd(2029, 11, 16).getLunarDay().getGods();
    List<String> ji = new ArrayList<>();
    for (God god : gods) {
      if ("吉".equals(god.getLuck().getName())) {
        ji.add(god.getName());
      }
    }

    List<String> xiong = new ArrayList<>();
    for (God god : gods) {
      if ("凶".equals(god.getLuck().getName())) {
        xiong.add(god.getName());
      }
    }
    Assert.assertEquals(Arrays.asList("天德合", "月空", "天恩", "益后", "金匮"), ji);
    Assert.assertEquals(Arrays.asList("月煞", "月虚", "血支", "五虚"), xiong);
  }

  @Test
  public void test7() {
    List<God> gods = SolarDay.fromYmd(1954, 7, 16).getLunarDay().getGods();

    // 吉神宜趋
    List<String> ji = new ArrayList<>();
    for (God god : gods) {
      if ("吉".equals(god.getLuck().getName())) {
        ji.add(god.getName());
      }
    }

    // 凶神宜忌
    List<String> xiong = new ArrayList<>();
    for (God god : gods) {
      if ("凶".equals(god.getLuck().getName())) {
        xiong.add(god.getName());
      }
    }

    Assert.assertEquals(Arrays.asList("民日", "天巫", "福德", "天仓", "不将", "续世", "除神", "鸣吠"), ji);
    Assert.assertEquals(Arrays.asList("劫煞", "天贼", "五虚", "五离"), xiong);
  }

  @Test
  public void test8() {
    List<God> gods = SolarDay.fromYmd(2024, 12, 27).getLunarDay().getGods();
    List<String> ji = new ArrayList<>();
    for (God god : gods) {
      if ("吉".equals(god.getLuck().getName())) {
        ji.add(god.getName());
      }
    }

    List<String> xiong = new ArrayList<>();
    for (God god : gods) {
      if ("凶".equals(god.getLuck().getName())) {
        xiong.add(god.getName());
      }
    }
    Assert.assertEquals(Arrays.asList("天恩", "四相", "阴德", "守日", "吉期", "六合", "普护", "宝光"), ji);
    Assert.assertEquals(Arrays.asList("三丧", "鬼哭"), xiong);
  }

  @Test
  public void test9() {
    List<God> gods = SolarDay.fromYmd(2024, 9, 27).getLunarDay().getGods();
    List<String> ji = new ArrayList<>();
    for (God god : gods) {
      if ("吉".equals(god.getLuck().getName())) {
        ji.add(god.getName());
      }
    }

    List<String> xiong = new ArrayList<>();
    for (God god : gods) {
      if ("凶".equals(god.getLuck().getName())) {
        xiong.add(god.getName());
      }
    }
    Assert.assertEquals(Arrays.asList("月空", "不将", "福生", "金匮", "鸣吠"), ji);
    Assert.assertEquals(Arrays.asList("天罡", "大时", "大败", "咸池", "天贼", "九坎", "九焦"), xiong);
  }

  @Test
  public void test10() {
    List<God> gods = SolarDay.fromYmd(2025, 12, 15).getLunarDay().getGods();
    List<String> ji = new ArrayList<>();
    for (God god : gods) {
      if ("吉".equals(god.getLuck().getName())) {
        ji.add(god.getName());
      }
    }

    List<String> xiong = new ArrayList<>();
    for (God god : gods) {
      if ("凶".equals(god.getLuck().getName())) {
        xiong.add(god.getName());
      }
    }
    Assert.assertEquals(Arrays.asList("阳德", "六仪", "续世", "解神", "司命"), ji);
    Assert.assertEquals(Arrays.asList("月破", "大耗", "灾煞", "天火", "厌对", "招摇", "五虚", "血忌"), xiong);
  }

}
