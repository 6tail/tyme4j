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
   * @param n 推移的步数，正数顺推，负数逆推
   * @return 推移后的Tyme
   */
  Tyme next(int n);

}
