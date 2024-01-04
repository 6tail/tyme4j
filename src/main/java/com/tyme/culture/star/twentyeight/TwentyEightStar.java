package com.tyme.culture.star.twentyeight;

import com.tyme.LoopTyme;
import com.tyme.culture.Animal;
import com.tyme.culture.Land;
import com.tyme.culture.Luck;
import com.tyme.culture.Zone;
import com.tyme.culture.star.seven.SevenStar;

/**
 * 二十八宿
 *
 * @author 6tail
 */
public class TwentyEightStar extends LoopTyme {

  public static final String[] NAMES = {"角", "亢", "氐", "房", "心", "尾", "箕", "斗", "牛", "女", "虚", "危", "室", "壁", "奎", "娄", "胃", "昴", "毕", "觜", "参", "井", "鬼", "柳", "星", "张", "翼", "轸"};

  protected TwentyEightStar(int index) {
    super(NAMES, index);
  }

  protected TwentyEightStar(String name) {
    super(NAMES, name);
  }

  public static TwentyEightStar fromIndex(int index) {
    return new TwentyEightStar(index);
  }

  public static TwentyEightStar fromName(String name) {
    return new TwentyEightStar(name);
  }

  public TwentyEightStar next(int n) {
    return fromIndex(nextIndex(n));
  }

  /**
   * 七曜
   *
   * @return 七曜
   */
  public SevenStar getSevenStar() {
    return SevenStar.fromIndex(index % 7 + 4);
  }

  /**
   * 九野
   *
   * @return 九野
   */
  public Land getLand() {
    return Land.fromIndex(new int[]{4, 4, 4, 2, 2, 2, 7, 7, 7, 0, 0, 0, 0, 5, 5, 5, 6, 6, 6, 1, 1, 1, 8, 8, 8, 3, 3, 3}[index]);
  }

  /**
   * 宫
   *
   * @return 宫
   */
  public Zone getZone() {
    return Zone.fromIndex(index / 7);
  }

  /**
   * 动物
   *
   * @return 动物
   */
  public Animal getAnimal() {
    return Animal.fromIndex(index);
  }

  /**
   * 吉凶
   *
   * @return 吉凶
   */
  public Luck getLuck() {
    return Luck.fromIndex(new int[]{0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0}[index]);
  }

}
