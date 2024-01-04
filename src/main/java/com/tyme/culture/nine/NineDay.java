package com.tyme.culture.nine;

import com.tyme.AbstractCultureDay;

/**
 * 数九天
 *
 * @author 6tail
 */
public class NineDay extends AbstractCultureDay {

  public NineDay(Nine nine, int dayIndex) {
    super(nine, dayIndex);
  }

  /**
   * 数九
   *
   * @return 数九
   */
  public Nine getNine() {
    return (Nine) culture;
  }

}
