package com.tyme.culture;

import com.tyme.LoopTyme;
import com.tyme.culture.star.twentyeight.TwentyEightStar;

/**
 * 动物
 *
 * @author liuweij
 */
public class Animal extends LoopTyme {

  public static final String[] NAMES = {"蛟", "龙", "貉", "兔", "狐", "虎", "豹", "獬", "牛", "蝠", "鼠", "燕", "猪", "獝", "狼", "狗", "彘", "鸡", "乌", "猴", "猿", "犴", "羊", "獐", "马", "鹿", "蛇", "蚓"};

  protected Animal(int index) {
    super(NAMES, index);
  }

  protected Animal(String name) {
    super(NAMES, name);
  }

  public static Animal fromIndex(int index) {
    return new Animal(index);
  }

  public static Animal fromName(String name) {
    return new Animal(name);
  }

  public Animal next(int n) {
    return fromIndex(nextIndex(n));
  }

  /**
   * 二十八宿
   *
   * @return 二十八宿
   */
  public TwentyEightStar getTwentyEightStar() {
    return TwentyEightStar.fromIndex(index);
  }

}
