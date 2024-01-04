package com.tyme.enums;

/**
 * 节日类型
 *
 * @author 6tail
 */
public enum FestivalType {
  DAY(0, "日期"),
  TERM(1, "节气"),
  EVE(2, "除夕");

  /**
   * 代码
   */
  private final int code;

  /**
   * 名称
   */
  private final String name;

  FestivalType(int code, String name) {
    this.code = code;
    this.name = name;
  }

  public static FestivalType fromCode(Integer code) {
    if (null == code) {
      return null;
    }
    for (FestivalType item : values()) {
      if (item.getCode() == code) {
        return item;
      }
    }
    return null;
  }

  public static FestivalType fromName(String name) {
    if (null == name) {
      return null;
    }
    for (FestivalType item : values()) {
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
