package com.tyme.enums;

/**
 * 藏干类型
 *
 * @author 6tail
 */
public enum HideHeavenStemType {
  RESIDUAL(0, "余气"),
  MIDDLE(1, "中气"),
  MAIN(2, "本气");

  /**
   * 代码
   */
  private final int code;

  /**
   * 名称
   */
  private final String name;

  HideHeavenStemType(int code, String name) {
    this.code = code;
    this.name = name;
  }

  public static HideHeavenStemType fromCode(Integer code) {
    if (null == code) {
      return null;
    }
    for (HideHeavenStemType item : values()) {
      if (item.getCode() == code) {
        return item;
      }
    }
    return null;
  }

  public static HideHeavenStemType fromName(String name) {
    if (null == name) {
      return null;
    }
    for (HideHeavenStemType item : values()) {
      if (item.getName().equals(name)) {
        return item;
      }
    }
    return null;
  }

  public int getCode() {
    return code;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return getName();
  }

}
