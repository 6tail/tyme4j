package com.tyme;

/**
 * 抽象Tyme
 *
 * @author 6tail
 */
public abstract class AbstractTyme extends AbstractCulture implements Tyme {
  @Override
  public Tyme next(int n) {
    throw new UnsupportedOperationException();
  }
}
