package com.tyme.enums;

/**
 * 内外
 *
 * @author 6tail
 */
public enum Side implements TymeEnum {
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
    return TymeEnum.fromCode(Side.class, code);
  }

  public static Side fromName(String name) {
    return TymeEnum.fromName(Side.class, name);
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
