package com.tyme.culture.star.nine;

import com.tyme.LoopTyme;
import com.tyme.culture.Direction;
import com.tyme.culture.Element;
import com.tyme.sixtycycle.SixtyCycle;
import com.tyme.solar.SolarDay;
import com.tyme.solar.SolarTerm;
import com.tyme.unit.AbstractTraditionalYear;

/**
 * 九星
 *
 * @author 6tail
 */
public class NineStar extends LoopTyme {

  public static final String[] NAMES = {"一", "二", "三", "四", "五", "六", "七", "八", "九"};

  /**
   * 颜色
   */
  public static final String[] COLORS = {"白", "黑", "碧", "绿", "黄", "白", "赤", "白", "紫"};

  public NineStar(int index) {
    super(NAMES, index);
  }

  public NineStar(String name) {
    super(NAMES, name);
  }

  public static NineStar fromIndex(int index) {
    return new NineStar(index);
  }

  public static NineStar fromName(String name) {
    return new NineStar(name);
  }

  public static NineStar fromYm(AbstractTraditionalYear year, SixtyCycle month) {
    int index = month.getEarthBranch().getIndex();
    if (index < 2) {
      index += 3;
    }
    return fromIndex(27 - year.getSixtyCycle().getEarthBranch().getIndex() % 3 * 3 - index);
  }

  public static NineStar fromHour(SolarDay solarDay, SixtyCycle daySixtyCycle, int hourIndexInDay) {
    SolarTerm dongZhi = SolarTerm.fromIndex(solarDay.getYear(), 0);
    int earthBranchIndex = hourIndexInDay % 12;
    int index = 8 - 3 * (daySixtyCycle.getEarthBranch().getIndex() % 3);
    if (!solarDay.isBefore(dongZhi.getJulianDay().getSolarDay()) && solarDay.isBefore(dongZhi.next(12).getJulianDay().getSolarDay())) {
      index = 8 + earthBranchIndex - index;
    } else {
      index -= earthBranchIndex;
    }
    return NineStar.fromIndex(index);
  }

  public NineStar next(int n) {
    return fromIndex(nextIndex(n));
  }

  /**
   * 颜色
   *
   * @return 颜色
   */
  public String getColor() {
    return COLORS[index];
  }

  /**
   * 五行
   *
   * @return 五行
   */
  public Element getElement() {
    return getDirection().getElement();
  }

  /**
   * 北斗九星
   *
   * @return 北斗九星
   */
  public Dipper getDipper() {
    return Dipper.fromIndex(index);
  }

  /**
   * 方位
   *
   * @return 方位
   */
  public Direction getDirection() {
    return Direction.fromIndex(index);
  }

  @Override
  public String toString() {
    return getName() + getColor() + getElement();
  }

}
