package com.tyme;

/**
 * 带天索引的传统文化抽象
 *
 * @author 6tail
 */
public abstract class AbstractCultureDay extends AbstractCulture {

  /**
   * 传统文化
   */
  protected AbstractCulture culture;

  /**
   * 天索引
   */
  protected int dayIndex;

  public AbstractCultureDay(AbstractCulture culture, int dayIndex) {
    this.culture = culture;
    this.dayIndex = dayIndex;
  }

  /**
   * 天索引
   *
   * @return 索引
   */
  public int getDayIndex() {
    return dayIndex;
  }

  protected Culture getCulture() {
    return culture;
  }

  @Override
  public String toString() {
    return String.format("%s第%d天", culture, dayIndex + 1);
  }

  public String getName() {
    return culture.getName();
  }
}
