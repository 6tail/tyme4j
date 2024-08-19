package com.tyme.sixtycycle;

import com.tyme.*;
import com.tyme.culture.*;
import com.tyme.culture.pengzu.PengZuHeavenStem;
import com.tyme.culture.star.ten.TenStar;
import com.tyme.enums.YinYang;

/**
 * 天干
 *
 * @author 6tail
 */
public class HeavenStem extends LoopTyme {

  public static final String[] NAMES = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};

  public HeavenStem(int index) {
    super(NAMES, index);
  }

  public HeavenStem(String name) {
    super(NAMES, name);
  }

  public static HeavenStem fromIndex(int index) {
    return new HeavenStem(index);
  }

  public static HeavenStem fromName(String name) {
    return new HeavenStem(name);
  }

  public HeavenStem next(int n) {
    return fromIndex(nextIndex(n));
  }

  /**
   * 五行
   *
   * @return 五行
   */
  public Element getElement() {
    return Element.fromIndex(index / 2);
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
   * 十神（生我者，正印偏印。我生者，伤官食神。克我者，正官七杀。我克者，正财偏财。同我者，劫财比肩。）
   *
   * @param target 天干
   * @return 十神
   */
  public TenStar getTenStar(HeavenStem target) {
    if (null == target) {
      return null;
    }
    int targetIndex = target.getIndex();
    int offset = targetIndex - index;
    if (index % 2 != 0 && targetIndex % 2 == 0) {
      offset += 2;
    }
    return TenStar.fromIndex(offset);
  }

  /**
   * 方位
   *
   * @return 方位
   */
  public Direction getDirection() {
    return getElement().getDirection();
  }

  /**
   * 喜神方位（《喜神方位歌》甲己在艮乙庚乾，丙辛坤位喜神安。丁壬只在离宫坐，戊癸原在在巽间。）
   *
   * @return 方位
   */
  public Direction getJoyDirection() {
    return Direction.fromIndex(new int[]{7, 5, 1, 8, 3}[index % 5]);
  }

  /**
   * 阳贵神方位（《阳贵神歌》甲戊坤艮位，乙己是坤坎，庚辛居离艮，丙丁兑与乾，震巽属何日，壬癸贵神安。）
   *
   * @return 方位
   */
  public Direction getYangDirection() {
    return Direction.fromIndex(new int[]{1, 1, 6, 5, 7, 0, 8, 7, 2, 3}[index]);
  }

  /**
   * 阴贵神方位（《阴贵神歌》甲戊见牛羊，乙己鼠猴乡，丙丁猪鸡位，壬癸蛇兔藏，庚辛逢虎马，此是贵神方。）
   *
   * @return 方位
   */
  public Direction getYinDirection() {
    return Direction.fromIndex(new int[]{7, 0, 5, 6, 1, 1, 7, 8, 3, 2}[index]);
  }

  /**
   * 财神方位（《财神方位歌》甲乙东北是财神，丙丁向在西南寻，戊己正北坐方位，庚辛正东去安身，壬癸原来正南坐，便是财神方位真。）
   *
   * @return 方位
   */
  public Direction getWealthDirection() {
    return Direction.fromIndex(new int[]{7, 1, 0, 2, 8}[index / 2]);
  }

  /**
   * 福神方位（《福神方位歌》甲乙东南是福神，丙丁正东是堪宜，戊北己南庚辛坤，壬在乾方癸在西。）
   *
   * @return 方位
   */
  public Direction getMascotDirection() {
    return Direction.fromIndex(new int[]{3, 3, 2, 2, 0, 8, 1, 1, 5, 6}[index]);
  }

  /**
   * 天干彭祖百忌
   *
   * @return 天干彭祖百忌
   */
  public PengZuHeavenStem getPengZuHeavenStem() {
    return PengZuHeavenStem.fromIndex(index);
  }

  /**
   * 地势(长生十二神)
   *
   * @param earthBranch 地支
   * @return 地势(长生十二神)
   */
  public Terrain getTerrain(EarthBranch earthBranch) {
    int earthBranchIndex = earthBranch.getIndex();
    return Terrain.fromIndex(new int[]{1, 6, 10, 9, 10, 9, 7, 0, 4, 3}[index] + (YinYang.YANG == getYinYang() ? earthBranchIndex : -earthBranchIndex));
  }

  /**
   * 五合（甲己合，乙庚合，丙辛合，丁壬合，戊癸合）
   *
   * @return 天干
   */
  public HeavenStem getCombine() {
    return next(5);
  }

  /**
   * 合化（甲己合化土，乙庚合化金，丙辛合化水，丁壬合化木，戊癸合化火）
   *
   * @param target 天干
   * @return 五行，如果无法合化，返回null
   */
  public Element combine(HeavenStem target) {
    return getCombine().equals(target) ? Element.fromIndex(index + 2) : null;
  }

}
