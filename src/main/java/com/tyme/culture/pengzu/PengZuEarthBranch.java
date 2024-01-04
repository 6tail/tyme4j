package com.tyme.culture.pengzu;

import com.tyme.LoopTyme;

/**
 * 地支彭祖百忌
 *
 * @author 6tail
 */
public class PengZuEarthBranch extends LoopTyme {

  public static final String[] NAMES = {"子不问卜自惹祸殃", "丑不冠带主不还乡", "寅不祭祀神鬼不尝", "卯不穿井水泉不香", "辰不哭泣必主重丧", "巳不远行财物伏藏", "午不苫盖屋主更张", "未不服药毒气入肠", "申不安床鬼祟入房", "酉不会客醉坐颠狂", "戌不吃犬作怪上床", "亥不嫁娶不利新郎"};

  protected PengZuEarthBranch(String name) {
    super(NAMES, name);
  }

  protected PengZuEarthBranch(int index) {
    super(NAMES, index);
  }

  /**
   * 从名称初始化
   *
   * @param name 名称
   * @return 地支彭祖百忌
   */
  public static PengZuEarthBranch fromName(String name) {
    return new PengZuEarthBranch(name);
  }

  /**
   * 从索引初始化
   *
   * @param index 索引
   * @return 地支彭祖百忌
   */
  public static PengZuEarthBranch fromIndex(int index) {
    return new PengZuEarthBranch(index);
  }


  public PengZuEarthBranch next(int n) {
    return new PengZuEarthBranch(nextIndex(n));
  }

}
