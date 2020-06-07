package com.seed.project.utils.enums;

import lombok.Getter;

@Getter
public enum UserTypeEnum {

    COMMON_USER(1, "普通用户"),
    APPROVAL_USER(2, "审批用户"),
    WINDON_USER(3, "窗口用户"),
    OTHER_USER(4, "其它用户"),

    ;
    private Integer code;
    private String desc;

    UserTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getDescByCode(Integer code) {
        if (code == null) {
            return "";
        }
        for (UserTypeEnum value : UserTypeEnum.values()) {
            if (value.code.equals(code)) {
                return value.desc;
            }
        }
        return "";
    }
}
