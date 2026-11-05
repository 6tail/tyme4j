package com.tyme.enums;

/**
 * 性别
 *
 * @author 6tail
 */
public enum Gender implements TymeEnum {
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
    return TymeEnum.fromCode(Gender.class, code);
  }

  public static Gender fromName(String name) {
    return TymeEnum.fromName(Gender.class, name);
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
