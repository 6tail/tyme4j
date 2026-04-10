package com.tyme.festival;

import com.tyme.AbstractTyme;
import com.tyme.enums.FestivalType;
import com.tyme.event.Event;
import com.tyme.unit.DayUnit;

/**
 * 节日抽象
 *
 * @author 6tail
 */
public abstract class AbstractFestival extends AbstractTyme {
  /**
   * 类型
   */
  @Deprecated
  protected FestivalType type;

  /**
   * 索引
   */
  protected int index;

  /**
   * 日
   */
  protected DayUnit day;

  /**
   * 事件
   */
  protected Event event;

  public AbstractFestival(FestivalType type, int index, Event event, DayUnit day) {
    this.type = type;
    this.index = index;
    this.event = event;
    this.day = day;
  }

  /**
   * 类型
   *
   * @return 节日类型
   */
  @Deprecated
  public FestivalType getType() {
    return type;
  }

  /**
   * 索引
   *
   * @return 索引
   */
  public int getIndex() {
    return index;
  }

  /**
   * 公历日
   *
   * @return 公历日
   */
  public DayUnit getDay() {
    return day;
  }

  public String getName() {
    return event.getName();
  }

  @Override
  public String toString() {
    return String.format("%s %s", day, getName());
  }
}
