package com.tyme.culture.dog;

import com.tyme.AbstractCultureDay;

/**
 * 三伏天
 *
 * @author 6tail
 */
public class DogDay extends AbstractCultureDay {

  public DogDay(Dog dog, int dayIndex) {
    super(dog, dayIndex);
  }

  /**
   * 三伏
   *
   * @return 三伏
   */
  public Dog getDog() {
    return (Dog)culture;
  }

}
