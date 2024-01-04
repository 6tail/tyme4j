package com.tyme.culture;

import com.tyme.LoopTyme;

/**
 * 纳音
 *
 * @author 6tail
 */
public class Sound extends LoopTyme {

  public static final String[] NAMES = {"海中金", "炉中火", "大林木", "路旁土", "剑锋金", "山头火", "涧下水", "城头土", "白蜡金", "杨柳木", "泉中水", "屋上土", "霹雳火", "松柏木", "长流水", "沙中金", "山下火", "平地木", "壁上土", "金箔金", "覆灯火", "天河水", "大驿土", "钗钏金", "桑柘木", "大溪水", "沙中土", "天上火", "石榴木", "大海水"};

  protected Sound(int index) {
    super(NAMES, index);
  }

  protected Sound(String name) {
    super(NAMES, name);
  }

  public static Sound fromIndex(int index) {
    return new Sound(index);
  }

  public static Sound fromName(String name) {
    return new Sound(name);
  }

  public Sound next(int n) {
    return fromIndex(nextIndex(n));
  }

}
