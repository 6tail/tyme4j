package com.tyme.eightchar;

import com.tyme.enums.Gender;
import com.tyme.enums.YinYang;
import com.tyme.solar.SolarDay;
import com.tyme.solar.SolarMonth;
import com.tyme.solar.SolarTerm;
import com.tyme.solar.SolarTime;

/**
 * 童限（从出生到起运的时间段）
 *
 * @author 6tail
 */
public class ChildLimit {

  /**
   * 开始(即出生)的公历时刻
   */
  protected SolarTime startTime;

  /**
   * 结束(即开始起运)的公历时刻
   */
  protected SolarTime endTime;

  /**
   * 八字
   */
  protected EightChar eightChar;

  /**
   * 性别
   */
  protected Gender gender;

  /**
   * 年数
   */
  protected int yearCount;

  /**
   * 月数
   */
  protected int monthCount;

  /**
   * 日数
   */
  protected int dayCount;

  /**
   * 小时数
   */
  protected int hourCount;

  /**
   * 分钟数
   */
  protected int minuteCount;

  /**
   * 顺逆
   */
  protected boolean forward;

  protected ChildLimit(SolarTime birthTime, Gender gender) {
    this.startTime = birthTime;
    this.gender = gender;
    eightChar = birthTime.getLunarHour().getEightChar();
    // 阳男阴女顺推，阴男阳女逆推
    boolean yang = YinYang.YANG == eightChar.getYear().getHeavenStem().getYinYang();
    boolean man = Gender.MAN == gender;
    forward = (yang && man) || (!yang && !man);
    SolarTerm term = birthTime.getTerm();
    if (!term.isJie()) {
      term = term.next(-1);
    }
    SolarTime start = forward ? birthTime : term.getJulianDay().getSolarTime();
    SolarTime end = forward ? term.next(2).getJulianDay().getSolarTime() : birthTime;

    int seconds = end.subtract(start);
    // 3天 = 1年，3天=60*60*24*3秒=259200秒 = 1年
    int year = seconds / 259200;
    seconds %= 259200;
    // 1天 = 4月，1天=60*60*24秒=86400秒 = 4月，85400秒/4=21600秒 = 1月
    int month = seconds / 21600;
    seconds %= 21600;
    // 1时 = 5天，1时=60*60秒=3600秒 = 5天，3600秒/5=720秒 = 1天
    int day = seconds / 720;
    seconds %= 720;
    // 1分 = 2时，60秒 = 2时，60秒/2=30秒 = 1时
    int hour = seconds / 30;
    seconds %= 30;
    // 1秒 = 2分，1秒/2=0.5秒 = 1分
    int minute = seconds * 2;

    yearCount = year;
    monthCount = month;
    dayCount = day;
    hourCount = hour;
    minuteCount = minute;

    SolarDay birthday = birthTime.getDay();
    SolarMonth birthMonth = birthday.getMonth();

    int d = birthday.getDay() + day;
    int h = birthTime.getHour() + hour;
    int mi = birthTime.getMinute() + minute;
    h += mi / 60;
    mi %= 60;
    d += h / 24;
    h %= 24;

    SolarMonth sm = SolarMonth.fromYm(birthMonth.getYear().getYear() + year, birthMonth.getMonth()).next(month);

    int dc = sm.getDayCount();
    if (d > dc) {
      d -= dc;
      sm = sm.next(1);
    }
    endTime = SolarTime.fromYmdHms(sm.getYear().getYear(), sm.getMonth(), d, h, mi, birthTime.getSecond());
  }

  /**
   * 通过出生公历时刻初始化
   *
   * @param birthTime 出生公历时刻
   * @param gender    性别
   * @return 童限
   */
  public static ChildLimit fromSolarTime(SolarTime birthTime, Gender gender) {
    return new ChildLimit(birthTime, gender);
  }

  /**
   * 八字
   *
   * @return 八字
   */
  public EightChar getEightChar() {
    return eightChar;
  }

  /**
   * 性别
   *
   * @return 性别
   */
  public Gender getGender() {
    return gender;
  }

  /**
   * 是否顺推
   *
   * @return true/false
   */
  public boolean isForward() {
    return forward;
  }

  /**
   * 年数
   *
   * @return 年数
   */
  public int getYearCount() {
    return yearCount;
  }

  /**
   * 月数
   *
   * @return 月数
   */
  public int getMonthCount() {
    return monthCount;
  }

  /**
   * 日数
   *
   * @return 日数
   */
  public int getDayCount() {
    return dayCount;
  }

  /**
   * 小时数
   *
   * @return 小时数
   */
  public int getHourCount() {
    return hourCount;
  }

  /**
   * 分钟数
   *
   * @return 分钟数
   */
  public int getMinuteCount() {
    return minuteCount;
  }

  /**
   * 开始(即出生)的公历时刻
   *
   * @return 公历时刻
   */
  public SolarTime getStartTime() {
    return startTime;
  }

  /**
   * 结束(即开始起运)的公历时刻
   *
   * @return 公历时刻
   */
  public SolarTime getEndTime() {
    return endTime;
  }

  /**
   * 大运
   *
   * @return 大运
   */
  public DecadeFortune getStartDecadeFortune() {
    return DecadeFortune.fromChildLimit(this, 0);
  }

  /**
   * 小运
   *
   * @return 小运
   */
  public Fortune getStartFortune() {
    return Fortune.fromChildLimit(this, 0);
  }

}
