package com.tyme.eightchar;

import com.tyme.AbstractTyme;
import com.tyme.lunar.LunarYear;
import com.tyme.sixtycycle.SixtyCycle;
import com.tyme.sixtycycle.SixtyCycleYear;

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
    return childLimit.getEndSixtyCycleYear().getYear() - childLimit.getStartSixtyCycleYear().getYear() + 1 + index * 10;
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
   * @see #getStartSixtyCycleYear()
   */
  @Deprecated
  public LunarYear getStartLunarYear() {
    return childLimit.getEndLunarYear().next(index * 10);
  }

  /**
   * 结束农历年
   *
   * @return 农历年
   * @see #getEndSixtyCycleYear()
   */
  @Deprecated
  public LunarYear getEndLunarYear() {
    return getStartLunarYear().next(9);
  }

  /**
   * 开始干支年
   *
   * @return 干支年
   */
  public SixtyCycleYear getStartSixtyCycleYear() {
    return childLimit.getEndSixtyCycleYear().next(index * 10);
  }

  /**
   * 结束干支年
   *
   * @return 干支年
   */
  public SixtyCycleYear getEndSixtyCycleYear() {
    return getStartSixtyCycleYear().next(9);
  }

  /**
   * 干支
   *
   * @return 干支
   */
  public SixtyCycle getSixtyCycle() {
    return childLimit.getEightChar().getMonth().next(childLimit.isForward() ? index + 1 : -index - 1);
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
