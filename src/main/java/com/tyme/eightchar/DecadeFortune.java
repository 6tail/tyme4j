package com.tyme.eightchar;

import com.tyme.AbstractTyme;
import com.tyme.lunar.LunarYear;
import com.tyme.sixtycycle.SixtyCycle;

/**
 * 大运（10年1大运）
 *
 * @author 6tail
 */
public class DecadeFortune extends AbstractTyme {

  /**
   * 童限
   */
  protected ChildLimit childLimit;

  /**
   * 序号
   */
  protected int index;

  public DecadeFortune(ChildLimit childLimit, int index) {
    this.childLimit = childLimit;
    this.index = index;
  }

  /**
   * 通过童限初始化
   *
   * @param childLimit 童限
   * @param index      序号
   * @return 大运
   */
  public static DecadeFortune fromChildLimit(ChildLimit childLimit, int index) {
    return new DecadeFortune(childLimit, index);
  }

  /**
   * 开始年龄
   *
   * @return 开始年龄
   */
  public int getStartAge() {
    return childLimit.getYearCount() + 1 + index * 10;
  }

  /**
   * 结束年龄
   *
   * @return 结束年龄
   */
  public int getEndAge() {
    return getStartAge() + 9;
  }

  /**
   * 开始农历年
   *
   * @return 农历年
   */
  public LunarYear getStartLunarYear() {
    return childLimit.getEndTime().getLunarHour().getLunarDay().getLunarMonth().getLunarYear().next(index * 10);
  }

  /**
   * 结束农历年
   *
   * @return 农历年
   */
  public LunarYear getEndLunarYear() {
    return getStartLunarYear().next(9);
  }

  /**
   * 干支
   *
   * @return 干支
   */
  public SixtyCycle getSixtyCycle() {
    int n = index + 1;
    return childLimit.getEightChar().getMonth().next(childLimit.isForward() ? n: -n);
  }

  public String getName() {
    return getSixtyCycle().getName();
  }

  public DecadeFortune next(int n) {
    return fromChildLimit(childLimit, index + n);
  }

  /**
   * 开始小运
   *
   * @return 小运
   */
  public Fortune getStartFortune() {
    return Fortune.fromChildLimit(childLimit, index * 10);
  }

}
