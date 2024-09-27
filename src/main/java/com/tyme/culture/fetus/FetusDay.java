package com.tyme.culture.fetus;

import com.tyme.AbstractCulture;
import com.tyme.culture.Direction;
import com.tyme.enums.Side;
import com.tyme.sixtycycle.SixtyCycle;
import com.tyme.lunar.LunarDay;

/**
 * 逐日胎神
 *
 * @author 6tail
 */
public class FetusDay extends AbstractCulture {

  /**
   * 天干六甲胎神
   */
  protected FetusHeavenStem fetusHeavenStem;

  /**
   * 地支六甲胎神
   */
  protected FetusEarthBranch fetusEarthBranch;

  /**
   * 内外
   */
  protected Side side;

  /**
   * 方位
   */
  protected Direction direction;

  public FetusDay(LunarDay lunarDay) {
    SixtyCycle sixtyCycle = lunarDay.getSixtyCycle();
    fetusHeavenStem = new FetusHeavenStem(sixtyCycle.getHeavenStem().getIndex() % 5);
    fetusEarthBranch = new FetusEarthBranch(sixtyCycle.getEarthBranch().getIndex() % 6);
    int index = new int[]{3, 3, 8, 8, 8, 8, 8, 1, 1, 1, 1, 1, 1, 6, 6, 6, 6, 6, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, -9, -9, -9, -9, -9, -5, -5, -1, -1, -1, -3, -7, -7, -7, -7, -5, 7, 7, 7, 7, 7, 7, 2, 2, 2, 2, 2, 3, 3, 3, 3}[sixtyCycle.getIndex()];
    side = Side.fromCode(index < 0 ? 0 : 1);
    direction = Direction.fromIndex(index);
  }

  /**
   * 从农历日初始化
   *
   * @param lunarDay 农历日
   * @return 逐日胎神
   */
  public static FetusDay fromLunarDay(LunarDay lunarDay) {
    return new FetusDay(lunarDay);
  }

  public String getName() {
    String s = fetusHeavenStem.getName() + fetusEarthBranch.getName();
    if ("门门".equals(s)) {
      s = "占大门";
    } else if ("碓磨碓".equals(s)) {
      s = "占碓磨";
    } else if ("房床床".equals(s)) {
      s = "占房床";
    } else if (s.startsWith("门")) {
      s = "占" + s;
    }

    s += " ";

    String directionName = direction.getName();
    if (Side.IN == side) {
      s += "房";
    }
    s += side.getName();

    if (Side.OUT == side && "北南西东".contains(directionName)) {
      s += "正";
    }
    s += directionName;
    return s;
  }

  /**
   * 内外
   *
   * @return 内外
   */
  public Side getSide() {
    return side;
  }

  /**
   * 方位
   *
   * @return 方位
   */
  public Direction getDirection() {
    return direction;
  }

  /**
   * 天干六甲胎神
   *
   * @return 天干六甲胎神
   */
  public FetusHeavenStem getFetusHeavenStem() {
    return fetusHeavenStem;
  }

  /**
   * 地支六甲胎神
   *
   * @return 地支六甲胎神
   */
  public FetusEarthBranch getFetusEarthBranch() {
    return fetusEarthBranch;
  }

}
