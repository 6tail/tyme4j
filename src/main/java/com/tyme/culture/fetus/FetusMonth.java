package com.tyme.culture.fetus;

import com.tyme.LoopTyme;
import com.tyme.lunar.LunarMonth;

/**
 * 逐月胎神（正十二月在床房，二三九十门户中，四六十一灶勿犯，五甲七子八厕凶。）
 *
 * @author 6tail
 */
public class FetusMonth extends LoopTyme {

  public static final String[] NAMES = {"占房床", "占户窗", "占门堂", "占厨灶", "占房床", "占床仓", "占碓磨", "占厕户", "占门房", "占房床", "占灶炉", "占房床"};

  public FetusMonth(int index) {
    super(NAMES, index);
  }

  /**
   * 从农历月初始化
   *
   * @param lunarMonth 农历月
   * @return 逐月胎神
   */
  public static FetusMonth fromLunarMonth(LunarMonth lunarMonth) {
    return lunarMonth.isLeap() ? null : new FetusMonth(lunarMonth.getMonth() - 1);
  }

  public FetusMonth next(int n) {
    return new FetusMonth(nextIndex(n));
  }

}
