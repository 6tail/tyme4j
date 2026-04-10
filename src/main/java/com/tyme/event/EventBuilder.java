package com.tyme.event;

import com.tyme.enums.EventType;

/**
 * 事件构建器
 *
 * @author 6tail
 */
public class EventBuilder {

  /**
   * 事件名称
   */
  protected String name;

  /**
   * 事件数据
   */
  protected char[] data = {'@', '_', '_', '_', '_', '_', '0', '0', '0'};

  /**
   * 事件名称
   *
   * @param name 名称
   * @return 事件构造器
   */
  public EventBuilder name(String name) {
    this.name = name;
    return this;
  }

  protected char getChar(int index) {
    return EventManager.CHARS.charAt(index);
  }

  protected EventBuilder setValue(int index, int n) {
    data[index] = getChar(31 + n);
    return this;
  }

  protected EventBuilder content(EventType type, int a, int b, int c) {
    data[1] = getChar(type.getCode());
    return setValue(2, a).setValue(3, b).setValue(4, c);
  }

  /**
   * 公历日
   *
   * @param solarMonth 公历月（1至12）
   * @param solarDay   公历日（1至31）
   * @param delayDays  顺延天数，例如生日在2月29，非闰年没有2月29，是+1天，还是-1天（最远支持-31至31天）
   * @return 事件构建器
   */
  public EventBuilder solarDay(int solarMonth, int solarDay, int delayDays) {
    return content(EventType.SOLAR_DAY, solarMonth, solarDay, delayDays);
  }

  /**
   * 农历日
   *
   * @param lunarMonth 农历月（-12至-1，1至12，闰月为负）
   * @param lunarDay   农历日（1至30）
   * @param delayDays  顺延天数，例如生日在某月的三十，但下一年当月可能只有29天，是+1天，还是-1天（最远支持-31至31天）
   * @return 事件构建器
   */
  public EventBuilder lunarDay(int lunarMonth, int lunarDay, int delayDays) {
    return content(EventType.LUNAR_DAY, lunarMonth, lunarDay, delayDays);
  }

  /**
   * 公历第几个星期几
   *
   * @param solarMonth 公历月（1至12）
   * @param weekIndex  第几个星期（1为第1个星期，-1为倒数第1个星期）
   * @param week       星期几（0至6，0代表星期天，1代表星期一）
   * @return 事件构建器
   */
  public EventBuilder solarWeek(int solarMonth, int weekIndex, int week) {
    return content(EventType.SOLAR_WEEK, solarMonth, weekIndex, week);
  }

  /**
   * 节气
   *
   * @param termIndex 节气索引（0至23）
   * @param delayDays 顺延天数（最远支持-31至31天）
   * @return 事件构建器
   */
  public EventBuilder termDay(int termIndex, int delayDays) {
    return content(EventType.TERM_DAY, termIndex, 0, delayDays);
  }

  /**
   * 节气天干
   *
   * @param termIndex       节气索引（0至23）
   * @param heavenStemIndex 天干索引（0至9）
   * @param delayDays       顺延天数（最远支持-31至31天）
   * @return 事件构建器
   */
  public EventBuilder termHeavenStem(int termIndex, int heavenStemIndex, int delayDays) {
    return content(EventType.TERM_HS, termIndex, heavenStemIndex, delayDays);
  }

  /**
   * 节气地支
   *
   * @param termIndex        节气索引（0至23）
   * @param earthBranchIndex 地支索引（0至11）
   * @param delayDays        顺延天数（最远支持-31至31天）
   * @return 事件构建器
   */
  public EventBuilder termEarthBranch(int termIndex, int earthBranchIndex, int delayDays) {
    return content(EventType.TERM_EB, termIndex, earthBranchIndex, delayDays);
  }

  /**
   * 起始年
   *
   * @param year 年
   * @return 事件构造器
   */
  public EventBuilder startYear(int year) {
    int size = EventManager.CHARS.length();
    int n = year;
    for (int i = 0; i < 3; i++) {
      data[8 - i] = getChar(n % size);
      n /= size;
    }
    return this;
  }

  /**
   * 偏移天数
   *
   * @param days 天数（最远支持-31至31天）
   * @return 事件构造器
   */
  public EventBuilder offset(int days) {
    return setValue(5, days);
  }

  /**
   * 生成事件
   *
   * @return 事件
   */
  public Event build() {
    return new Event(name, new String(data));
  }
}
