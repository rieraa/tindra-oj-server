package com.aneto.tindraoj.judge.strategy;

import com.aneto.tindraoj.model.dto.jundge.JudgeCase;
import com.aneto.tindraoj.model.dto.jundge.JudgeInfo;
import com.aneto.tindraoj.model.entity.Question;
import com.aneto.tindraoj.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * 上下文（用于定义在策略中传递的参数）
 */
@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private List<JudgeCase> judgeCaseList;

    private Question question;

    private QuestionSubmit questionSubmit;

}
