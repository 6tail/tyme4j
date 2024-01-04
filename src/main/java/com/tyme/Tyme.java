package com.tyme;

/**
 * Tyme
 *
 * @author 6tail
 */
public interface Tyme extends Culture {

  /**
   * 推移
   *
   * @param n 推移步数
   * @return 推移后的Tyme
   */
  Tyme next(int n);

}
