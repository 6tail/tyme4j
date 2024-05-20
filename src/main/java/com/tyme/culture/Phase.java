package com.tyme.culture;

import com.tyme.LoopTyme;

/**
 * 月相
 *
 * @author 6tail
 */
public class Phase extends LoopTyme {

  public static final String[] NAMES = {"朔月", "既朔月", "蛾眉新月", "蛾眉新月", "蛾眉月", "夕月", "上弦月", "上弦月", "九夜月", "宵月", "宵月", "宵月", "渐盈凸月", "小望月", "望月", "既望月", "立待月", "居待月", "寝待月", "更待月", "渐亏凸月", "下弦月", "下弦月", "有明月", "有明月", "蛾眉残月", "蛾眉残月", "残月", "晓月", "晦月"};

  public Phase(int index) {
    super(NAMES, index);
  }

  public Phase(String name) {
    super(NAMES, name);
  }

  public static Phase fromIndex(int index) {
    return new Phase(index);
  }

  public static Phase fromName(String name) {
    return new Phase(name);
  }

  public Phase next(int n) {
    return fromIndex(nextIndex(n));
  }

}
