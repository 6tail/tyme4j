package com.tyme.enums;

/**
 * 枚举接口
 *
 * @author 6tail
 */
interface TymeEnum {

  /**
   * 代码
   *
   * @return 代码
   */
  int getCode();

  /**
   * 名称
   *
   * @return 名称
   */
  String getName();

  static <T extends Enum<T> & TymeEnum> T fromCode(Class<T> type, Integer code) {
    if (null == code) {
      return null;
    }
    for (T item : type.getEnumConstants()) {
      if (item.getCode() == code) {
        return item;
      }
    }
    return null;
  }

  static <T extends Enum<T> & TymeEnum> T fromName(Class<T> type, String name) {
    if (null == name) {
      return null;
    }
    for (T item : type.getEnumConstants()) {
      if (item.getName().equals(name)) {
        return item;
      }
    }
    return null;
  }
}