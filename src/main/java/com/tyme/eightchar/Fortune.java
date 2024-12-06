package com.tyme.eightchar;

import com.tyme.AbstractTyme;
import com.tyme.lunar.LunarYear;
import com.tyme.sixtycycle.SixtyCycle;

/**
 * 小运
 *
 * @author 6tail
 */
public class Fortune extends AbstractTyme {

  /**
   * 童限
   */
  protected ChildLimit childLimit;

  /**
   * 序号
   */
  protected int index;

  public Fortune(ChildLimit childLimit, int index) {
    this.childLimit = childLimit;
    this.index = index;
  }

  /**
   * 通过童限初始化
   *
   * @param childLimit 童限
   * @param index      序号
   * @return 小运
   */
  public static Fortune fromChildLimit(ChildLimit childLimit, int index) {
    return new Fortune(childLimit, index);
  }

  /**
   * 年龄
   *
   * @return 年龄
   */
  public int getAge() {
    return childLimit.getEndTime().getYear() - childLimit.getStartTime().getYear() + 1 + index;
  }

  /**
   * 农历年
   *
   * @return 农历年
   */
  public LunarYear getLunarYear() {
    return childLimit.getEndLunarYear().next(index);
  }

  /**
   * 干支
   *
   * @return 干支
   */
  public SixtyCycle getSixtyCycle() {
    int n = getAge();
    return childLimit.getEightChar().getHour().next(childLimit.isForward() ? n: -n);
  }

  public String getName() {
    return getSixtyCycle().getName();
  }

  public Fortune next(int n) {
    return fromChildLimit(childLimit, index + n);
  }

}
