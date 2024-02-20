package com.tyme.eightchar.provider;

import com.tyme.eightchar.ChildLimitInfo;
import com.tyme.solar.SolarTerm;
import com.tyme.solar.SolarTime;

/**
 * 童限计算接口
 *
 * @author 6tail
 */
public interface ChildLimitProvider {

  /**
   * 童限结束（即起运）公历时刻
   *
   * @param birthTime 出生公历时刻
   * @param term      节令
   * @return 童限信息
   */
  ChildLimitInfo getInfo(SolarTime birthTime, SolarTerm term);
}
