package com.tyme.enums;

/**
 * 内外
 *
 * @author 6tail
 */
public enum Side {
  IN(0, "内"),
  OUT(1, "外");

  /**
   * 代码
   */
  private final int code;

  /**
   * 名称
   */
  private final String name;

  Side(int code, String name) {
    this.code = code;
    this.name = name;
  }

  public static Side fromCode(Integer code) {
    if (null == code) {
      return null;
    }
    for (Side item : values()) {
      if (item.getCode() == code) {
        return item;
      }
    }
    return null;
  }

  public static Side fromName(String name) {
    if (null == name) {
      return null;
    }
    for (Side item : values()) {
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
