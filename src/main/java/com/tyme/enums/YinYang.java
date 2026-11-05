package com.tyme.enums;

/**
 * 阴阳
 *
 * @author 6tail
 */
public enum YinYang implements TymeEnum {
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
    return TymeEnum.fromCode(YinYang.class, code);
  }

  public static YinYang fromName(String name) {
    return TymeEnum.fromName(YinYang.class, name);
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
