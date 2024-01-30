package com.tyme.test;

import com.tyme.eightchar.ChildLimit;
import com.tyme.eightchar.DecadeFortune;
import com.tyme.eightchar.EightChar;
import com.tyme.eightchar.Fortune;
import com.tyme.enums.Gender;
import com.tyme.lunar.LunarHour;
import com.tyme.sixtycycle.HeavenStem;
import com.tyme.sixtycycle.SixtyCycle;
import com.tyme.solar.SolarTime;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 八字测试
 *
 * @author 6tail
 */
public class EightCharTest {

  /**
   * 十神
   */
  @Test
  public void test1() {
    // 八字
    EightChar eightChar = new EightChar(
        SixtyCycle.fromName("丙寅"),
        SixtyCycle.fromName("癸巳"),
        SixtyCycle.fromName("癸酉"),
        SixtyCycle.fromName("己未")
    );

    // 年柱
    SixtyCycle year = eightChar.getYear();
    // 月柱
    SixtyCycle month = eightChar.getMonth();
    // 日柱
    SixtyCycle day = eightChar.getDay();
    // 时柱
    SixtyCycle hour = eightChar.getHour();

    // 日元(日主、日干)
    HeavenStem me = day.getHeavenStem();

    // 年柱天干十神
    Assert.assertEquals("正财", me.getTenStar(year.getHeavenStem()).getName());
    // 月柱天干十神
    Assert.assertEquals("比肩", me.getTenStar(month.getHeavenStem()).getName());
    // 时柱天干十神
    Assert.assertEquals("七杀", me.getTenStar(hour.getHeavenStem()).getName());

    // 年柱地支十神（本气)
    Assert.assertEquals("伤官", me.getTenStar(year.getEarthBranch().getHideHeavenStemMain()).getName());
    // 年柱地支十神（中气)
    Assert.assertEquals("正财", me.getTenStar(year.getEarthBranch().getHideHeavenStemMiddle()).getName());
    // 年柱地支十神（余气)
    Assert.assertEquals("正官", me.getTenStar(year.getEarthBranch().getHideHeavenStemResidual()).getName());

    // 日柱地支十神（本气)
    Assert.assertEquals("偏印", me.getTenStar(day.getEarthBranch().getHideHeavenStemMain()).getName());
    // 日柱地支藏干（中气)
    Assert.assertNull(day.getEarthBranch().getHideHeavenStemMiddle());
    // 日柱地支藏干（余气)
    Assert.assertNull(day.getEarthBranch().getHideHeavenStemResidual());

    // 指定任意天干的十神
    Assert.assertEquals("正财", me.getTenStar(HeavenStem.fromName("丙")).getName());
  }

  /**
   * 地势(长生十二神)
   */
  @Test
  public void test2() {
    // 八字
    EightChar eightChar = new EightChar(
        SixtyCycle.fromName("丙寅"),
        SixtyCycle.fromName("癸巳"),
        SixtyCycle.fromName("癸酉"),
        SixtyCycle.fromName("己未")
    );

    // 年柱
    SixtyCycle year = eightChar.getYear();
    // 月柱
    SixtyCycle month = eightChar.getMonth();
    // 日柱
    SixtyCycle day = eightChar.getDay();
    // 时柱
    SixtyCycle hour = eightChar.getHour();

    // 日元(日主、日干)
    HeavenStem me = day.getHeavenStem();

    // 年柱地势
    Assert.assertEquals("沐浴", me.getTerrain(year.getEarthBranch()).getName());
    // 月柱地势
    Assert.assertEquals("胎", me.getTerrain(month.getEarthBranch()).getName());
    // 日柱地势
    Assert.assertEquals("病", me.getTerrain(day.getEarthBranch()).getName());
    // 时柱地势
    Assert.assertEquals("墓", me.getTerrain(hour.getEarthBranch()).getName());
  }

  /**
   * 胎元/胎息/命宫
   */
  @Test
  public void test3() {
    // 八字
    EightChar eightChar = new EightChar(
        SixtyCycle.fromName("癸卯"),
        SixtyCycle.fromName("辛酉"),
        SixtyCycle.fromName("己亥"),
        SixtyCycle.fromName("癸酉")
    );

    // 胎元
    SixtyCycle taiYuan = eightChar.getFetalOrigin();
    Assert.assertEquals("壬子", taiYuan.getName());
    // 胎元纳音
    Assert.assertEquals("桑柘木", taiYuan.getSound().getName());
  }

  /**
   * 胎息
   */
  @Test
  public void test4() {
    // 八字
    EightChar eightChar = new EightChar(
        SixtyCycle.fromName("癸卯"),
        SixtyCycle.fromName("辛酉"),
        SixtyCycle.fromName("己亥"),
        SixtyCycle.fromName("癸酉")
    );

    // 胎息
    SixtyCycle taiXi = eightChar.getFetalBreath();
    Assert.assertEquals("甲寅", taiXi.getName());
    // 胎息纳音
    Assert.assertEquals("大溪水", taiXi.getSound().getName());
  }

  /**
   * 命宫
   */
  @Test
  public void test5() {
    // 八字
    EightChar eightChar = new EightChar(
        SixtyCycle.fromName("癸卯"),
        SixtyCycle.fromName("辛酉"),
        SixtyCycle.fromName("己亥"),
        SixtyCycle.fromName("癸酉")
    );

    // 命宫
    SixtyCycle mingGong = eightChar.getOwnSign();
    Assert.assertEquals("癸亥", mingGong.getName());
    // 命宫纳音
    Assert.assertEquals("大海水", mingGong.getSound().getName());
  }

  /**
   * 身宫
   */
  @Test
  public void test6() {
    // 八字
    EightChar eightChar = new EightChar(
        SixtyCycle.fromName("癸卯"),
        SixtyCycle.fromName("辛酉"),
        SixtyCycle.fromName("己亥"),
        SixtyCycle.fromName("癸酉")
    );

    // 身宫
    SixtyCycle shenGong = eightChar.getBodySign();
    Assert.assertEquals("己未", shenGong.getName());
    // 身宫纳音
    Assert.assertEquals("天上火", shenGong.getSound().getName());
  }

  /**
   * 地势(长生十二神)
   */
  @Test
  public void test7() {
    // 八字
    EightChar eightChar = new EightChar(
        SixtyCycle.fromName("乙酉"),
        SixtyCycle.fromName("戊子"),
        SixtyCycle.fromName("辛巳"),
        SixtyCycle.fromName("壬辰")
    );

    // 日干
    HeavenStem me = eightChar.getDay().getHeavenStem();
    // 年柱地势
    Assert.assertEquals("临官", me.getTerrain(eightChar.getYear().getEarthBranch()).getName());
    // 月柱地势
    Assert.assertEquals("长生", me.getTerrain(eightChar.getMonth().getEarthBranch()).getName());
    // 日柱地势
    Assert.assertEquals("死", me.getTerrain(eightChar.getDay().getEarthBranch()).getName());
    // 时柱地势
    Assert.assertEquals("墓", me.getTerrain(eightChar.getHour().getEarthBranch()).getName());
  }

  /**
   * 公历时刻转八字
   */
  @Test
  public void test8() {
    EightChar eightChar = SolarTime.fromYmdHms(2005, 12, 23, 8, 37, 0).getLunarHour().getEightChar();
    Assert.assertEquals("乙酉", eightChar.getYear().getName());
    Assert.assertEquals("戊子", eightChar.getMonth().getName());
    Assert.assertEquals("辛巳", eightChar.getDay().getName());
    Assert.assertEquals("壬辰", eightChar.getHour().getName());
  }

  @Test
  public void test9() {
    EightChar eightChar = SolarTime.fromYmdHms(1988, 2, 15, 23, 30, 0).getLunarHour().getEightChar();
    Assert.assertEquals("戊辰", eightChar.getYear().getName());
    Assert.assertEquals("甲寅", eightChar.getMonth().getName());
    Assert.assertEquals("辛丑", eightChar.getDay().getName());
    Assert.assertEquals("戊子", eightChar.getHour().getName());
  }

  /**
   * 童限测试
   */
  @Test
  public void test11() {
    ChildLimit childLimit = ChildLimit.fromSolarTime(SolarTime.fromYmdHms(2022, 3, 9, 20, 51, 0), Gender.MAN);
    Assert.assertEquals(8, childLimit.getYearCount());
    Assert.assertEquals(9, childLimit.getMonthCount());
    Assert.assertEquals(2, childLimit.getDayCount());
    Assert.assertEquals(10, childLimit.getHourCount());
    Assert.assertEquals(26, childLimit.getMinuteCount());
    Assert.assertEquals("2030年12月12日 07:17:00", childLimit.getEndTime().toString());
  }

  /**
   * 童限测试
   */
  @Test
  public void test12() {
    ChildLimit childLimit = ChildLimit.fromSolarTime(SolarTime.fromYmdHms(2018, 6, 11, 9, 30, 0), Gender.WOMAN);
    Assert.assertEquals(1, childLimit.getYearCount());
    Assert.assertEquals(9, childLimit.getMonthCount());
    Assert.assertEquals(10, childLimit.getDayCount());
    Assert.assertEquals(1, childLimit.getHourCount());
    Assert.assertEquals(42, childLimit.getMinuteCount());
    Assert.assertEquals("2020年3月21日 11:12:00", childLimit.getEndTime().toString());
  }

  /**
   * 大运测试
   */
  @Test
  public void test13() {
    // 童限
    ChildLimit childLimit = ChildLimit.fromSolarTime(SolarTime.fromYmdHms(1983, 2, 15, 20, 0, 0), Gender.WOMAN);
    // 八字
    Assert.assertEquals("癸亥 甲寅 甲戌 甲戌", childLimit.getEightChar().toString());
    // 童限年数
    Assert.assertEquals(6, childLimit.getYearCount());
    // 童限月数
    Assert.assertEquals(2, childLimit.getMonthCount());
    // 童限日数
    Assert.assertEquals(18, childLimit.getDayCount());
    // 童限结束(即开始起运)的公历时刻
    Assert.assertEquals("1989年5月4日 18:24:00", childLimit.getEndTime().toString());
    // 童限开始(即出生)的农历年干支
    Assert.assertEquals("癸亥", childLimit.getStartTime().getLunarHour().getDay().getMonth().getYear().getSixtyCycle().getName());
    // 童限结束(即开始起运)的农历年干支
    Assert.assertEquals("己巳", childLimit.getEndTime().getLunarHour().getDay().getMonth().getYear().getSixtyCycle().getName());

    // 第1轮大运
    DecadeFortune decadeFortune = childLimit.getStartDecadeFortune();
    // 开始年龄
    Assert.assertEquals(7, decadeFortune.getStartAge());
    // 结束年龄
    Assert.assertEquals(16, decadeFortune.getEndAge());
    // 开始年
    Assert.assertEquals(1989, decadeFortune.getStartLunarYear().getYear());
    // 结束年
    Assert.assertEquals(1998, decadeFortune.getEndLunarYear().getYear());
    // 干支
    Assert.assertEquals("乙卯", decadeFortune.getName());
    // 下一大运
    Assert.assertEquals("丙辰", decadeFortune.next(1).getName());
    // 上一大运
    Assert.assertEquals("甲寅", decadeFortune.next(-1).getName());
    // 第9轮大运
    Assert.assertEquals("癸亥", decadeFortune.next(8).getName());

    // 小运
    Fortune fortune = childLimit.getStartFortune();
    // 年龄
    Assert.assertEquals(7, fortune.getAge());
    // 农历年
    Assert.assertEquals(1989, fortune.getLunarYear().getYear());
    // 干支
    Assert.assertEquals("辛巳", fortune.getName());

    // 流年
    Assert.assertEquals("己巳", fortune.getLunarYear().getSixtyCycle().getName());
  }

  @Test
  public void test14() {
    // 童限
    ChildLimit childLimit = ChildLimit.fromSolarTime(SolarTime.fromYmdHms(1992, 2, 2, 12, 0, 0), Gender.MAN);
    // 八字
    Assert.assertEquals("辛未 辛丑 戊申 戊午", childLimit.getEightChar().toString());
    // 童限年数
    Assert.assertEquals(9, childLimit.getYearCount());
    // 童限月数
    Assert.assertEquals(0, childLimit.getMonthCount());
    // 童限日数
    Assert.assertEquals(9, childLimit.getDayCount());
    // 童限结束(即开始起运)的公历时刻
    Assert.assertEquals("2001年2月11日 18:58:00", childLimit.getEndTime().toString());
    // 童限开始(即出生)的农历年干支
    Assert.assertEquals("辛未", childLimit.getStartTime().getLunarHour().getDay().getMonth().getYear().getSixtyCycle().getName());
    // 童限结束(即开始起运)的农历年干支
    Assert.assertEquals("辛巳", childLimit.getEndTime().getLunarHour().getDay().getMonth().getYear().getSixtyCycle().getName());

    // 第1轮大运
    DecadeFortune decadeFortune = childLimit.getStartDecadeFortune();
    // 开始年龄
    Assert.assertEquals(10, decadeFortune.getStartAge());
    // 结束年龄
    Assert.assertEquals(19, decadeFortune.getEndAge());
    // 开始年
    Assert.assertEquals(2001, decadeFortune.getStartLunarYear().getYear());
    // 结束年
    Assert.assertEquals(2010, decadeFortune.getEndLunarYear().getYear());
    // 干支
    Assert.assertEquals("庚子", decadeFortune.getName());
    // 下一大运
    Assert.assertEquals("己亥", decadeFortune.next(1).getName());

    // 小运
    Fortune fortune = childLimit.getStartFortune();
    // 年龄
    Assert.assertEquals(10, fortune.getAge());
    // 农历年
    Assert.assertEquals(2001, fortune.getLunarYear().getYear());
    // 干支
    Assert.assertEquals("戊申", fortune.getName());
    // 小运推移
    Assert.assertEquals("丙午", fortune.next(2).getName());
    Assert.assertEquals("庚戌", fortune.next(-2).getName());

    // 流年
    Assert.assertEquals("辛巳", fortune.getLunarYear().getSixtyCycle().getName());
  }

  /**
   * 排盘示例
   */
  @Test
  public void test15() {
    EightChar eightChar = new EightChar(
        SixtyCycle.fromName("丙寅"),
        SixtyCycle.fromName("癸巳"),
        SixtyCycle.fromName("癸酉"),
        SixtyCycle.fromName("己未")
    );
    SixtyCycle year = eightChar.getYear();
    SixtyCycle month = eightChar.getMonth();
    SixtyCycle day = eightChar.getDay();
    SixtyCycle hour = eightChar.getHour();

    HeavenStem me = day.getHeavenStem();
    System.out.printf("主星：%s %s 日主 %s%n",
        me.getTenStar(year.getHeavenStem()),
        me.getTenStar(month.getHeavenStem()),
        me.getTenStar(hour.getHeavenStem())
    );
    System.out.printf("八字：%s %s %s %s%n",
        year,
        month,
        day,
        hour
    );
    System.out.printf("藏干：[%s %s %s] [%s %s %s] [%s %s %s] [%s %s %s]%n",
        year.getEarthBranch().getHideHeavenStemMain(),
        year.getEarthBranch().getHideHeavenStemMiddle(),
        year.getEarthBranch().getHideHeavenStemResidual(),
        month.getEarthBranch().getHideHeavenStemMain(),
        month.getEarthBranch().getHideHeavenStemMiddle(),
        month.getEarthBranch().getHideHeavenStemResidual(),
        day.getEarthBranch().getHideHeavenStemMain(),
        day.getEarthBranch().getHideHeavenStemMiddle(),
        day.getEarthBranch().getHideHeavenStemResidual(),
        hour.getEarthBranch().getHideHeavenStemMain(),
        hour.getEarthBranch().getHideHeavenStemMiddle(),
        hour.getEarthBranch().getHideHeavenStemResidual()
    );
    System.out.printf("副星：[%s %s %s] [%s %s %s] [%s %s %s] [%s %s %s]%n",
        me.getTenStar(year.getEarthBranch().getHideHeavenStemMain()),
        me.getTenStar(year.getEarthBranch().getHideHeavenStemMiddle()),
        me.getTenStar(year.getEarthBranch().getHideHeavenStemResidual()),

        me.getTenStar(month.getEarthBranch().getHideHeavenStemMain()),
        me.getTenStar(month.getEarthBranch().getHideHeavenStemMiddle()),
        me.getTenStar(month.getEarthBranch().getHideHeavenStemResidual()),

        me.getTenStar(day.getEarthBranch().getHideHeavenStemMain()),
        me.getTenStar(day.getEarthBranch().getHideHeavenStemMiddle()),
        me.getTenStar(day.getEarthBranch().getHideHeavenStemResidual()),

        me.getTenStar(hour.getEarthBranch().getHideHeavenStemMain()),
        me.getTenStar(hour.getEarthBranch().getHideHeavenStemMiddle()),
        me.getTenStar(hour.getEarthBranch().getHideHeavenStemResidual())
    );
    System.out.printf("五行：%s%s %s%s %s%s %s%s%n",
        year.getHeavenStem().getElement(),
        year.getEarthBranch().getElement(),
        month.getHeavenStem().getElement(),
        month.getEarthBranch().getElement(),
        day.getHeavenStem().getElement(),
        day.getEarthBranch().getElement(),
        hour.getHeavenStem().getElement(),
        hour.getEarthBranch().getElement()
    );
    System.out.printf("纳音：%s %s %s %s%n",
        year.getSound(),
        month.getSound(),
        day.getSound(),
        hour.getSound()
    );
    System.out.printf("星运：%s %s %s %s%n",
        me.getTerrain(year.getEarthBranch()),
        me.getTerrain(month.getEarthBranch()),
        me.getTerrain(day.getEarthBranch()),
        me.getTerrain(hour.getEarthBranch())
    );
    System.out.printf("自坐：%s %s %s %s%n",
        year.getHeavenStem().getTerrain(year.getEarthBranch()),
        month.getHeavenStem().getTerrain(month.getEarthBranch()),
        day.getHeavenStem().getTerrain(day.getEarthBranch()),
        hour.getHeavenStem().getTerrain(hour.getEarthBranch())
    );
    System.out.printf("空亡：%s %s %s %s%n",
        Arrays.toString(year.getExtraEarthBranches()),
        Arrays.toString(month.getExtraEarthBranches()),
        Arrays.toString(day.getExtraEarthBranches()),
        Arrays.toString(hour.getExtraEarthBranches())
    );

    System.out.printf("胎元：%s(%s)%n", eightChar.getFetalOrigin(), eightChar.getFetalOrigin().getSound());
    System.out.printf("胎息：%s(%s)%n", eightChar.getFetalBreath(), eightChar.getFetalBreath().getSound());
    System.out.printf("命宫：%s(%s)%n", eightChar.getOwnSign(), eightChar.getOwnSign().getSound());
    System.out.printf("身宫：%s(%s)%n", eightChar.getBodySign(), eightChar.getBodySign().getSound());
  }

  @Test
  public void test16() {
    // 童限
    ChildLimit childLimit = ChildLimit.fromSolarTime(SolarTime.fromYmdHms(1990, 3, 15, 10, 30, 0), Gender.MAN);
    // 八字
    Assert.assertEquals("庚午 己卯 己卯 己巳", childLimit.getEightChar().toString());
    // 童限年数
    Assert.assertEquals(6, childLimit.getYearCount());
    // 童限月数
    Assert.assertEquals(11, childLimit.getMonthCount());
    // 童限日数
    Assert.assertEquals(23, childLimit.getDayCount());
    // 童限结束(即开始起运)的公历时刻
    Assert.assertEquals("1997年3月11日 00:22:00", childLimit.getEndTime().toString());

    // 小运
    Fortune fortune = childLimit.getStartFortune();
    // 年龄
    Assert.assertEquals(7, fortune.getAge());
  }

  @Test
  public void test17() {
    EightChar eightChar = new EightChar(
            SixtyCycle.fromName("己丑"),
            SixtyCycle.fromName("戊辰"),
            SixtyCycle.fromName("戊辰"),
            SixtyCycle.fromName("甲子")
    );
    Assert.assertEquals("丁丑", eightChar.getOwnSign().getName());  }

  @Test
  public void test18() {
    EightChar eightChar = new EightChar(
            SixtyCycle.fromName("戊戌"),
            SixtyCycle.fromName("庚申"),
            SixtyCycle.fromName("丁亥"),
            SixtyCycle.fromName("丙午")
    );
    Assert.assertEquals("乙卯", eightChar.getOwnSign().getName());
  }

  @Test
  public void test19() {
    EightChar eightChar = new EightChar(
            SixtyCycle.fromName("甲子"),
            SixtyCycle.fromName("壬申"),
            null,
            SixtyCycle.fromName("乙亥")
    );
    Assert.assertEquals("甲戌", eightChar.getOwnSign().getName());
  }

  @Test
  public void test20() {
    EightChar eightChar = ChildLimit.fromSolarTime(SolarTime.fromYmdHms(2024, 1, 29, 9, 33, 0), Gender.MAN).getEightChar();
    Assert.assertEquals("癸亥", eightChar.getOwnSign().getName());
    Assert.assertEquals("己未", eightChar.getBodySign().getName());
  }

  @Test
  public void test21() {
    EightChar eightChar = new EightChar(
            SixtyCycle.fromName("辛亥"),
            SixtyCycle.fromName("乙未"),
            null,
            SixtyCycle.fromName("甲辰")
    );
    Assert.assertEquals("庚子", eightChar.getBodySign().getName());
  }

  @Test
  public void test22() {
    Assert.assertEquals("丙寅", ChildLimit.fromSolarTime(SolarTime.fromYmdHms(1990, 1, 27, 0, 0, 0), Gender.MAN).getEightChar().getBodySign().getName());
  }

  @Test
  public void test23() {
    Assert.assertEquals("甲戌", ChildLimit.fromSolarTime(SolarTime.fromYmdHms(2019, 3, 7, 8, 0, 0), Gender.MAN).getEightChar().getOwnSign().getName());
  }

  @Test
  public void test24() {
    Assert.assertEquals("丁丑", ChildLimit.fromSolarTime(SolarTime.fromYmdHms(2019, 3, 27, 2, 0, 0), Gender.MAN).getEightChar().getOwnSign().getName());
  }

  @Test
  public void test25() {
    Assert.assertEquals("丙寅", LunarHour.fromYmdHms(1994, 5, 20, 18, 0 ,0).getEightChar().getOwnSign().getName());
  }

  @Test
  public void test26() {
    Assert.assertEquals("己丑", SolarTime.fromYmdHms(1986, 5, 29, 13, 37, 0).getLunarHour().getEightChar().getBodySign().getName());
  }

  @Test
  public void test27() {
    Assert.assertEquals("乙丑", SolarTime.fromYmdHms(1994, 12, 6, 2, 0 ,0).getLunarHour().getEightChar().getBodySign().getName());
  }

  @Test
  public void test28() {
    EightChar eightChar = new EightChar(
            SixtyCycle.fromName("辛亥"),
            SixtyCycle.fromName("丁酉"),
            SixtyCycle.fromName("丙午"),
            SixtyCycle.fromName("癸巳")
    );
    Assert.assertEquals("辛卯", eightChar.getOwnSign().getName());
  }

  @Test
  public void test29() {
    EightChar eightChar = new EightChar(
            SixtyCycle.fromName("丙寅"),
            SixtyCycle.fromName("庚寅"),
            SixtyCycle.fromName("辛卯"),
            SixtyCycle.fromName("壬辰")
    );
    Assert.assertEquals("己亥", eightChar.getOwnSign().getName());
    Assert.assertEquals("乙未", eightChar.getBodySign().getName());
  }

  @Test
  public void test30() {
    EightChar eightChar = new EightChar(
            SixtyCycle.fromName("壬子"),
            SixtyCycle.fromName("辛亥"),
            SixtyCycle.fromName("壬戌"),
            SixtyCycle.fromName("乙巳")
    );
    Assert.assertEquals("乙巳", eightChar.getBodySign().getName());
  }

}
