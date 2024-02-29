package com.aneto.tindraoj.service;

import com.aneto.tindraoj.model.dto.questionsubmit.QuestionSubmitRequest;
import com.aneto.tindraoj.model.entity.QuestionSubmit;
import com.aneto.tindraoj.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author ameee
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2024-02-26 15:02:34
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    /**
     * 题目提交
     *
     * @param questionSubmitRequest 提交的题目信息
     * @param loginUser             登录用户
     * @return
     */
    long doQuestionSubmit(QuestionSubmitRequest questionSubmitRequest, User loginUser);


}
