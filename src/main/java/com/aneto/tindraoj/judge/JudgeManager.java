package com.aneto.tindraoj.judge;

import com.aneto.tindraoj.judge.strategy.DefaultJudgeStrategy;
import com.aneto.tindraoj.judge.strategy.JudgeContext;
import com.aneto.tindraoj.judge.strategy.JudgeStrategy;
import com.aneto.tindraoj.model.dto.jundge.JudgeInfo;
import com.aneto.tindraoj.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化调用）
 */
@Service
public class JudgeManager {

    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo judgeOutPut(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();

        return judgeStrategy.judgeOutPut(judgeContext);
    }

}
