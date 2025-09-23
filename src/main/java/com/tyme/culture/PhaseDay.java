package com.tyme.culture;

import com.tyme.AbstractCultureDay;

/**
 * 月相第几天
 *
 * @author 6tail
 */
public class PhaseDay extends AbstractCultureDay {

  public PhaseDay(Phase phase, int dayIndex) {
    super(phase, dayIndex);
  }

  /**
   * 月相
   *
   * @return 月相
   */
  public Phase getPhase() {
    return (Phase)culture;
  }

}
