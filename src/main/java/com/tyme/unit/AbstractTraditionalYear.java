package com.tyme.unit;

import com.tyme.culture.Direction;
import com.tyme.culture.Twenty;
import com.tyme.culture.star.nine.NineStar;
import com.tyme.sixtycycle.SixtyCycle;

/**
 * 传统年抽象
 *
 * @author 6tail
 */
public abstract class AbstractTraditionalYear extends AbstractYear{
  public AbstractTraditionalYear(int year) {
    super(year);
  }

  /**
   * 干支
   *
   * @return 干支
   */
  public SixtyCycle getSixtyCycle() {
    return SixtyCycle.fromIndex(year - 4);
  }

  /**
   * 运
   *
   * @return 运
   */
  public Twenty getTwenty() {
    return Twenty.fromIndex((int) Math.floor((year - 1864) / 20D));
  }

  /**
   * 九星
   *
   * @return 九星
   */
  public NineStar getNineStar() {
    return NineStar.fromIndex(63 + getTwenty().getSixty().getIndex() * 3 - getSixtyCycle().getIndex());
  }

  /**
   * 太岁方位
   *
   * @return 方位
   */
  public Direction getJupiterDirection() {
    return Direction.fromIndex(new int[]{0, 7, 7, 2, 3, 3, 8, 1, 1, 6, 0, 0}[getSixtyCycle().getEarthBranch().getIndex()]);
  }
}
