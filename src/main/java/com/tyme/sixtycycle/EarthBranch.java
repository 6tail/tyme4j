package com.tyme.sixtycycle;

import com.tyme.LoopTyme;
import com.tyme.culture.Direction;
import com.tyme.culture.Element;
import com.tyme.culture.Zodiac;
import com.tyme.culture.pengzu.PengZuEarthBranch;
import com.tyme.enums.YinYang;

/**
 * 地支
 *
 * @author 6tail
 */
public class EarthBranch extends LoopTyme {

  public static final String[] NAMES = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};

  public EarthBranch(int index) {
    super(NAMES, index);
  }

  public EarthBranch(String name) {
    super(NAMES, name);
  }

  public static EarthBranch fromIndex(int index) {
    return new EarthBranch(index);
  }

  public static EarthBranch fromName(String name) {
    return new EarthBranch(name);
  }

  public EarthBranch next(int n) {
    return fromIndex(nextIndex(n));
  }

  /**
   * 五行
   *
   * @return 五行
   */
  public Element getElement() {
    return Element.fromIndex(new int[]{4, 2, 0, 0, 2, 1, 1, 2, 3, 3, 2, 4}[index]);
  }

  /**
   * 阴阳
   *
   * @return 阴阳
   */
  public YinYang getYinYang() {
    return index % 2 == 0 ? YinYang.YANG : YinYang.YIN;
  }

  /**
   * 藏干之本气
   *
   * @return 天干
   */
  public HeavenStem getHideHeavenStemMain() {
    return HeavenStem.fromIndex(new int[]{9, 5, 0, 1, 4, 2, 3, 5, 6, 7, 4, 8}[index]);
  }

  /**
   * 藏干之中气，无中气返回null
   *
   * @return 天干
   */
  public HeavenStem getHideHeavenStemMiddle() {
    int n = new int[]{-1, 9, 2, -1, 1, 6, 5, 3, 8, -1, 7, 0}[index];
    return n == -1 ? null : HeavenStem.fromIndex(n);
  }

  /**
   * 藏干之余气，无余气返回null
   *
   * @return 天干
   */
  public HeavenStem getHideHeavenStemResidual() {
    int n = new int[]{-1, 7, 4, -1, 9, 4, -1, 1, 4, -1, 3, -1}[index];
    return n == -1 ? null : HeavenStem.fromIndex(n);
  }

  /**
   * 生肖
   *
   * @return 生肖
   */
  public Zodiac getZodiac() {
    return Zodiac.fromIndex(index);
  }

  /**
   * 方位
   *
   * @return 方位
   */
  public Direction getDirection() {
    return Direction.fromIndex(new int[]{0, 4, 2, 2, 4, 8, 8, 4, 6, 6, 4, 0}[index]);
  }

  /**
   * 煞（逢巳日、酉日、丑日必煞东；亥日、卯日、未日必煞西；申日、子日、辰日必煞南；寅日、午日、戌日必煞北。）
   *
   * @return 方位
   */
  public Direction getOminous() {
    return Direction.fromIndex(new int[]{8, 2, 0, 6}[index % 4]);
  }

  /**
   * 地支彭祖百忌
   *
   * @return 地支彭祖百忌
   */
  public PengZuEarthBranch getPengZuEarthBranch() {
    return PengZuEarthBranch.fromIndex(index);
  }

  /**
   * 六冲（子午冲，丑未冲，寅申冲，辰戌冲，卯酉冲，巳亥冲）
   *
   * @return 地支
   */
  public EarthBranch getOpposite() {
    return next(6);
  }

  /**
   * 六合（子丑合，寅亥合，卯戌合，辰酉合，巳申合，午未合）
   *
   * @return 地支
   */
  public EarthBranch getCombine() {
    return fromIndex(1 - index);
  }

  /**
   * 六害（子未害、丑午害、寅巳害、卯辰害、申亥害、酉戌害）
   *
   * @return 地支
   */
  public EarthBranch getHarm() {
    return fromIndex(19 - index);
  }

  /**
   * 合化（子丑合化土，寅亥合化木，卯戌合化火，辰酉合化金，巳申合化水，午未合化土）
   *
   * @param target 地支
   * @return 五行，如果无法合化，返回null
   */
  public Element combine(EarthBranch target) {
    return getCombine().equals(target) ? Element.fromIndex(new int[]{2, 2, 0, 1, 3, 4, 2, 2, 4, 3, 1, 0}[index]) : null;
  }

}
