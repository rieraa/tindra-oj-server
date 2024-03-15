package com.aneto.tindraoj.judge.strategy;

import cn.hutool.json.JSONUtil;
import com.aneto.tindraoj.model.dto.jundge.JudgeCase;
import com.aneto.tindraoj.model.dto.jundge.JudgeConfig;
import com.aneto.tindraoj.judge.sandbox.model.JudgeInfo;
import com.aneto.tindraoj.model.entity.Question;
import com.aneto.tindraoj.model.enums.JudgeInfoEnum;

import java.util.List;

/**
 * 默认判题策略
 */
public class DefaultJudgeStrategy implements JudgeStrategy {

    /**
     * 对输出进行判断
     *
     * @param judgeContext 上下文
     * @return
     */
    @Override
    public JudgeInfo judgeOutPut(JudgeContext judgeContext) {
        JudgeInfoEnum judgeInfoEnum = null;
        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        List<String> inputList = judgeContext.getInputList();
        List<String> outputList = judgeContext.getOutputList();
        List<JudgeCase> judgeCaseList = judgeContext.getJudgeCaseList();
        Question question = judgeContext.getQuestion();

        // 输出结果的数量与输入用例的数量不一致
        if (judgeContext.getOutputList().size() != judgeContext.getInputList().size()) {
            judgeInfoEnum = JudgeInfoEnum.WRONG_OUTPUT;
            judgeInfo.setMessage(judgeInfoEnum.getValue());
            return judgeInfo;
        }
        for (int i = 0; i < inputList.size(); i++) {
            JudgeCase judgeCase = judgeCaseList.get(i);
            if (!judgeCase.getOutput().equals(outputList.get(i))) {
                judgeInfoEnum = JudgeInfoEnum.WRONG_OUTPUT;
                judgeInfo.setMessage(judgeInfoEnum.getValue());
                return judgeInfo;
            }
        }
        // 判断题目运行时间和内存是否超出限制
        Long memory = judgeInfo.getMemory();
        Long time = judgeInfo.getTime();
        String judgeConfigStr = question.getJudgeConfig();
        JudgeConfig judgeConfig = JSONUtil.toBean(judgeConfigStr, JudgeConfig.class);
        if (time > judgeConfig.getTimeLimit()) {
            judgeInfoEnum = JudgeInfoEnum.TIME_LIMIT_EXCEEDED;
            judgeInfo.setMessage(judgeInfoEnum.getValue());
            return judgeInfo;
        }
        if (memory > judgeConfig.getMemoryLimit()) {
            judgeInfoEnum = JudgeInfoEnum.MEMORY_LIMIT_EXCEEDED;
            judgeInfo.setMessage(judgeInfoEnum.getValue());
            return judgeInfo;
        }

        judgeInfoEnum = JudgeInfoEnum.ACCEPTED;
        judgeInfo.setMessage(judgeInfoEnum.getValue());


        return judgeInfo;
    }
}
