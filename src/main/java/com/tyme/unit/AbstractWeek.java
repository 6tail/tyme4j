package com.tyme.unit;

/**
 * 抽象周
 *
 * @author 6tail
 */
public abstract class AbstractWeek extends WeekUnit {

  public AbstractWeek(int year, int month, int index, int start) {
    super(year, month, index, start);
  }

  /**
   * 抽象月
   *
   * @return 抽象月
   */
  public abstract AbstractMonth getAbstractMonth();

  public AbstractWeek next(int n) {
    int d = index + n;
    AbstractMonth m = getAbstractMonth();
    if (n > 0) {
      int weekCount = m.getWeekCount(start);
      while (d >= weekCount) {
        d -= weekCount;
        m = m.next(1);
        if (m.getFirstDay().getWeek().getIndex() != start) {
          d += 1;
        }
        weekCount = m.getWeekCount(start);
      }
    } else if (n < 0) {
      while (d < 0) {
        if (m.getFirstDay().getWeek().getIndex() != start) {
          d -= 1;
        }
        m = m.next(-1);
        d += m.getWeekCount(start);
      }
    }
    return new AbstractWeek(m.getYear(), m.getMonthValue(), d, start) {
      @Override
      public AbstractMonth getAbstractMonth() {
        return null;
      }
    };
  }

  @Override
  public String toString() {
    return getAbstractMonth() + getName();
  }
}
