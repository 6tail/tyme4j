package com.tyme.enums;

/**
 * 性别
 *
 * @author 6tail
 */
public enum Gender {
  WOMAN(0, "女"),
  MAN(1, "男");

  /**
   * 代码
   */
  private final int code;

  /**
   * 名称
   */
  private final String name;

  Gender(int code, String name) {
    this.code = code;
    this.name = name;
  }

  public static Gender fromCode(Integer code) {
    if (null == code) {
      return null;
    }
    for (Gender item : values()) {
      if (item.getCode() == code) {
        return item;
      }
    }
    return null;
  }

  public static Gender fromName(String name) {
    if (null == name) {
      return null;
    }
    for (Gender item : values()) {
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
