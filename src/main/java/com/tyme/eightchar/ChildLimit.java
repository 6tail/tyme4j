package com.tyme.eightchar;

import com.tyme.eightchar.provider.ChildLimitProvider;
import com.tyme.eightchar.provider.impl.DefaultChildLimitProvider;
import com.tyme.enums.Gender;
import com.tyme.enums.YinYang;
import com.tyme.lunar.LunarYear;
import com.tyme.solar.SolarTerm;
import com.tyme.solar.SolarTime;

/**
 * 童限（从出生到起运的时间段）
 *
 * @author 6tail
 */
public class ChildLimit {

  /**
   * 童限计算接口
   */
  public static ChildLimitProvider provider = new DefaultChildLimitProvider();

  /**
   * 八字
   */
  protected EightChar eightChar;

  /**
   * 性别
   */
  protected Gender gender;

  /**
   * 顺逆
   */
  protected boolean forward;

  /**
   * 童限信息
   */
  protected ChildLimitInfo info;

  public ChildLimit(SolarTime birthTime, Gender gender) {
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
    if (forward) {
      term = term.next(2);
    }
    info = provider.getInfo(birthTime, term);
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
    return info.getYearCount();
  }

  /**
   * 月数
   *
   * @return 月数
   */
  public int getMonthCount() {
    return info.getMonthCount();
  }

  /**
   * 日数
   *
   * @return 日数
   */
  public int getDayCount() {
    return info.getDayCount();
  }

  /**
   * 小时数
   *
   * @return 小时数
   */
  public int getHourCount() {
    return info.getHourCount();
  }

  /**
   * 分钟数
   *
   * @return 分钟数
   */
  public int getMinuteCount() {
    return info.getMinuteCount();
  }

  /**
   * 开始(即出生)的公历时刻
   *
   * @return 公历时刻
   */
  public SolarTime getStartTime() {
    return info.getStartTime();
  }

  /**
   * 结束(即开始起运)的公历时刻
   *
   * @return 公历时刻
   */
  public SolarTime getEndTime() {
    return info.getEndTime();
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

  /**
   * 结束农历年
   *
   * @return 农历年
   */
  public LunarYear getEndLunarYear() {
    return LunarYear.fromYear(getStartTime().getLunarHour().getYear() + getEndTime().getYear() - getStartTime().getYear());
  }

}
