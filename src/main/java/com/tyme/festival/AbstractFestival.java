package com.tyme.festival;

import com.tyme.AbstractTyme;
import com.tyme.event.Event;
import com.tyme.unit.DayUnit;

/**
 * 节日抽象
 *
 * @author 6tail
 */
public abstract class AbstractFestival extends AbstractTyme {
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

  public AbstractFestival(int index, Event event, DayUnit day) {
    this.index = index;
    this.event = event;
    this.day = day;
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
   * 日
   *
   * @return 日
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

  protected static Event buildEvent(String[] names, String data, int index) {
    if (index < 0 || index >= names.length) {
      return null;
    }
    int start = index * 8;
    return new Event(names[index], "@" + data.substring(start, start + 8));
  }
}
