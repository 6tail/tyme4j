package com.tyme.enums;

/**
 * 阴阳
 *
 * @author 6tail
 */
public enum YinYang {
  YIN(0, "阴"),
  YANG(1, "阳");

  /**
   * 代码
   */
  private final int code;

  /**
   * 名称
   */
  private final String name;

  YinYang(int code, String name) {
    this.code = code;
    this.name = name;
  }

  public static YinYang fromCode(Integer code) {
    if (null == code) {
      return null;
    }
    for (YinYang item : values()) {
      if (item.getCode() == code) {
        return item;
      }
    }
    return null;
  }

  public static YinYang fromName(String name) {
    if (null == name) {
      return null;
    }
    for (YinYang item : values()) {
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
