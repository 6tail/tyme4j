package com.tyme.test;

import com.tyme.culture.Taboo;
import com.tyme.solar.SolarDay;
import com.tyme.solar.SolarTime;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 宜忌测试
 *
 * @author 6tail
 */
public class TabooTest {
  @Test
  public void test0() {
    List<String> taboos = new ArrayList<>();
    for (Taboo t : SolarDay.fromYmd(2024, 6, 26).getSixtyCycleDay().getRecommends()) {
      taboos.add(t.getName());
    }

    Assert.assertEquals(Arrays.asList("嫁娶", "祭祀", "理发", "作灶", "修饰垣墙", "平治道涂", "整手足甲", "沐浴", "冠笄"), taboos);
  }

  @Test
  public void test1() {
    List<String> taboos = new ArrayList<>();
    for (Taboo t : SolarDay.fromYmd(2024, 6, 26).getSixtyCycleDay().getAvoids()) {
      taboos.add(t.getName());
    }

    Assert.assertEquals(Arrays.asList("破土", "出行", "栽种"), taboos);
  }

  @Test
  public void test2() {
    List<String> taboos = new ArrayList<>();
    for (Taboo t : SolarTime.fromYmdHms(2024, 6, 25, 4, 0, 0).getSixtyCycleHour().getRecommends()) {
      taboos.add(t.getName());
    }

    Assert.assertEquals(Collections.emptyList(), taboos);
  }

  @Test
  public void test3() {
    List<String> taboos = new ArrayList<>();
    for (Taboo t : SolarTime.fromYmdHms(2024, 6, 25, 4, 0, 0).getSixtyCycleHour().getAvoids()) {
      taboos.add(t.getName());
    }

    Assert.assertEquals(Collections.singletonList("诸事不宜"), taboos);
  }

  @Test
  public void test4() {
    List<String> taboos = new ArrayList<>();
    for (Taboo t : SolarTime.fromYmdHms(2024, 4, 22, 0, 0, 0).getSixtyCycleHour().getRecommends()) {
      taboos.add(t.getName());
    }

    Assert.assertEquals(Arrays.asList("嫁娶", "交易", "开市", "安床", "祭祀", "求财"), taboos);
  }

  @Test
  public void test5() {
    List<String> taboos = new ArrayList<>();
    for (Taboo t : SolarTime.fromYmdHms(2024, 4, 22, 0, 0, 0).getSixtyCycleHour().getAvoids()) {
      taboos.add(t.getName());
    }

    Assert.assertEquals(Arrays.asList("出行", "移徙", "赴任", "词讼", "祈福", "修造", "求嗣"), taboos);
  }

  @Test
  public void test6() {
    List<String> taboos = new ArrayList<>();
    for (Taboo t : SolarDay.fromYmd(2021, 3, 7).getSixtyCycleDay().getRecommends()) {
      taboos.add(t.getName());
    }

    Assert.assertEquals(Arrays.asList("裁衣", "经络", "伐木", "开柱眼", "拆卸", "修造", "动土", "上梁", "合脊", "合寿木", "入殓", "除服", "成服", "移柩", "破土", "安葬", "启钻", "修坟", "立碑"), taboos);
  }

  @Test
  public void test7() {
    List<String> taboos = new ArrayList<>();
    for (Taboo t : SolarDay.fromYmd(2024, 6, 26).getLunarDay().getRecommends()) {
      taboos.add(t.getName());
    }

    Assert.assertEquals(Arrays.asList("嫁娶", "祭祀", "理发", "作灶", "修饰垣墙", "平治道涂", "整手足甲", "沐浴", "冠笄"), taboos);
  }

  @Test
  public void test8() {
    List<String> taboos = new ArrayList<>();
    for (Taboo t : SolarDay.fromYmd(2024, 6, 26).getLunarDay().getAvoids()) {
      taboos.add(t.getName());
    }

    Assert.assertEquals(Arrays.asList("破土", "出行", "栽种"), taboos);
  }

  @Test
  public void test9() {
    List<String> taboos = new ArrayList<>();
    for (Taboo t : SolarTime.fromYmdHms(2024, 6, 25, 4, 0, 0).getLunarHour().getRecommends()) {
      taboos.add(t.getName());
    }

    Assert.assertEquals(Collections.emptyList(), taboos);
  }

  @Test
  public void test10() {
    List<String> taboos = new ArrayList<>();
    for (Taboo t : SolarTime.fromYmdHms(2024, 6, 25, 4, 0, 0).getLunarHour().getAvoids()) {
      taboos.add(t.getName());
    }

    Assert.assertEquals(Collections.singletonList("诸事不宜"), taboos);
  }

  @Test
  public void test11() {
    List<String> taboos = new ArrayList<>();
    for (Taboo t : SolarTime.fromYmdHms(2024, 4, 22, 0, 0, 0).getLunarHour().getRecommends()) {
      taboos.add(t.getName());
    }

    Assert.assertEquals(Arrays.asList("嫁娶", "交易", "开市", "安床", "祭祀", "求财"), taboos);
  }

  @Test
  public void test12() {
    List<String> taboos = new ArrayList<>();
    for (Taboo t : SolarTime.fromYmdHms(2024, 4, 22, 0, 0, 0).getLunarHour().getAvoids()) {
      taboos.add(t.getName());
    }

    Assert.assertEquals(Arrays.asList("出行", "移徙", "赴任", "词讼", "祈福", "修造", "求嗣"), taboos);
  }

  @Test
  public void test13() {
    List<String> taboos = new ArrayList<>();
    for (Taboo t : SolarDay.fromYmd(2021, 3, 7).getLunarDay().getRecommends()) {
      taboos.add(t.getName());
    }

    Assert.assertEquals(Arrays.asList("裁衣", "经络", "伐木", "开柱眼", "拆卸", "修造", "动土", "上梁", "合脊", "合寿木", "入殓", "除服", "成服", "移柩", "破土", "安葬", "启钻", "修坟", "立碑"), taboos);
  }

}
