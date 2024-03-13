package com.aneto.tindraoj.judge;

import com.aneto.tindraoj.model.entity.QuestionSubmit;

public interface JudgeService {
    QuestionSubmit judgeCode(Long questionSubmitId);
}
