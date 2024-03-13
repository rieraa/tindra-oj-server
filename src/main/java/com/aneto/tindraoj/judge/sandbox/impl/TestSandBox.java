package com.aneto.tindraoj.judge.sandbox.impl;

import com.aneto.tindraoj.judge.sandbox.SandBox;
import com.aneto.tindraoj.judge.sandbox.model.ExecuteCodeRequest;
import com.aneto.tindraoj.judge.sandbox.model.ExecuteCodeResponse;
import com.aneto.tindraoj.model.dto.jundge.JudgeInfo;
import com.aneto.tindraoj.model.enums.JudgeInfoEnum;
import com.aneto.tindraoj.model.enums.QuestionSubmitStatusEnum;

import java.util.List;


public class TestSandBox implements SandBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {

        List<String> input = executeCodeRequest.getInput();
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoEnum.ACCEPTED.getValue());
        judgeInfo.setMemory(2000L);
        judgeInfo.setTime(1000L);
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutput(input);
        executeCodeResponse.setMessage("系统运行正常");
        executeCodeResponse.setJudgeInfo(judgeInfo);
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.COMPLETED.getCode());
        return executeCodeResponse;

    }
}
