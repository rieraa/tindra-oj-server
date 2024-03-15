package com.aneto.tindraoj.judge.strategy;

import com.aneto.tindraoj.judge.sandbox.model.JudgeInfo;

/**
 * 判题策略
 */
public interface JudgeStrategy {

    /**
     * 对输出进行判断
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo judgeOutPut(JudgeContext judgeContext);
}
