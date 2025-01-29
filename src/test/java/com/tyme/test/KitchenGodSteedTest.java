package com.tyme.test;

import com.tyme.culture.KitchenGodSteed;
import org.junit.Assert;
import org.junit.Test;

/**
 * 灶马头测试
 *
 * @author 6tail
 */
public class KitchenGodSteedTest {

  @Test
  public void test1() {
    Assert.assertEquals("二龙治水", KitchenGodSteed.fromLunarYear(2017).getDragon());
    Assert.assertEquals("二龙治水", KitchenGodSteed.fromLunarYear(2018).getDragon());
    Assert.assertEquals("八龙治水", KitchenGodSteed.fromLunarYear(2019).getDragon());
    Assert.assertEquals("三龙治水", KitchenGodSteed.fromLunarYear(5).getDragon());
  }

  @Test
  public void test2() {
    Assert.assertEquals("二人分饼", KitchenGodSteed.fromLunarYear(2017).getCake());
    Assert.assertEquals("八人分饼", KitchenGodSteed.fromLunarYear(2018).getCake());
    Assert.assertEquals("一人分饼", KitchenGodSteed.fromLunarYear(5).getCake());
  }

  @Test
  public void test3() {
    Assert.assertEquals("十一牛耕田", KitchenGodSteed.fromLunarYear(2021).getCattle());
  }

  @Test
  public void test4() {
    Assert.assertEquals("三日得金", KitchenGodSteed.fromLunarYear(2018).getGold());
  }

}
