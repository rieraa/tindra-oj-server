package com.aneto.tindraoj.judge;

import cn.hutool.json.JSONUtil;
import com.aneto.tindraoj.common.ErrorCode;
import com.aneto.tindraoj.exception.BusinessException;
import com.aneto.tindraoj.judge.sandbox.SandBox;
import com.aneto.tindraoj.judge.sandbox.SandboxFactory;
import com.aneto.tindraoj.judge.sandbox.SandboxProxy;
import com.aneto.tindraoj.judge.sandbox.model.ExecuteCodeRequest;
import com.aneto.tindraoj.judge.sandbox.model.ExecuteCodeResponse;
import com.aneto.tindraoj.judge.strategy.JudgeContext;
import com.aneto.tindraoj.model.dto.jundge.JudgeCase;
import com.aneto.tindraoj.model.dto.jundge.JudgeInfo;
import com.aneto.tindraoj.model.entity.Question;
import com.aneto.tindraoj.model.entity.QuestionSubmit;
import com.aneto.tindraoj.model.enums.QuestionSubmitStatusEnum;
import com.aneto.tindraoj.service.QuestionService;
import com.aneto.tindraoj.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JudgeServiceImpl implements JudgeService {

    @Value("${sandbox.type}")
    private String type;

    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Override
    public QuestionSubmit judgeCode(Long questionSubmitId) {
        // 获取题目的提交记录
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 获取题目信息
        Question question = questionService.getById(questionSubmit.getQuestionId());
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 判断题目是否已经在判题中
        if (questionSubmit.getStatus() != QuestionSubmitStatusEnum.WAITING.getCode()) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "题目正在判题中");
        }
        // 更改题目提交状态为判题中
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.IN_QUEUE.getCode());
        boolean update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update) {
            // 更改失败
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "更新题目提交状态失败");
        }
        // 调用沙箱执行代码
        SandBox codeSandbox = SandboxFactory.sandBoxInstance(type);
        codeSandbox = new SandboxProxy(codeSandbox);
        String code = questionSubmit.getCode();
        String language = questionSubmit.getLanguage();
        // 获取输入用例
        List<JudgeCase> judgeCaseList = JSONUtil.toList(question.getJudgeCase(), JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .input(inputList)
                .build();
        // 调用代理对象的方法
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);

        if (executeCodeResponse == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "执行代码失败");
        }
        // 判断执行结果 通过策略模式实现
        List<String> outputList = executeCodeResponse.getOutput();
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(executeCodeResponse.getJudgeInfo());
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outputList);
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestion(question);
        judgeContext.setQuestionSubmit(questionSubmit);

        JudgeManager judgeManager = new JudgeManager();
        JudgeInfo judgeInfo = judgeManager.judgeOutPut(judgeContext);





        // 此时已判题结束
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.COMPLETED.getCode());
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update) {
            // 更改失败
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "更新题目提交状态失败");
        }


        return questionSubmitService.getById(questionSubmitId);
    }
}
