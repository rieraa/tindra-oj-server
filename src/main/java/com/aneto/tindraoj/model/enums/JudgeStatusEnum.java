package com.aneto.tindraoj.model.enums;

import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;

/**
 * 题目提交状态枚举
 * code 存储在数据库中的
 * value 用于展示
 *
 */

public enum JudgeStatusEnum {

    WAITING("等待中", 0),
    IN_QUEUE("判题队列中", 1),
    COMPLETED("已完成", 2),
    FAIL("提交失败", 3);


    private final String value;
    private final int code;

    JudgeStatusEnum(String value, int code) {
        this.value = value;
        this.code = code;
    }

    public String getDescription() {
        return value;
    }

    public int getCode() {
        return code;
    }

    /**
     * 根据 code 获取枚举
     *
     * @param code
     * @return
     */
    public static JudgeStatusEnum getEnumByValue(Integer code) {
        if (ObjectUtils.isEmpty(code)) {
            return null;
        }
        for (JudgeStatusEnum judgeStatusEnum : JudgeStatusEnum.values()) {
            if (judgeStatusEnum.code == code) {
                return judgeStatusEnum;
            }
        }
        return null;
    }
}
