package com.aneto.tindraoj.service.impl;

import com.aneto.tindraoj.common.ErrorCode;
import com.aneto.tindraoj.exception.BusinessException;
import com.aneto.tindraoj.mapper.QuestionSubmitMapper;
import com.aneto.tindraoj.model.dto.questionsubmit.QuestionSubmitRequest;
import com.aneto.tindraoj.model.entity.Question;
import com.aneto.tindraoj.model.entity.QuestionSubmit;
import com.aneto.tindraoj.model.entity.User;
import com.aneto.tindraoj.service.QuestionService;
import com.aneto.tindraoj.service.QuestionSubmitService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author ameee
* @description 针对表【question_submit(题目提交)】的数据库操作Service实现
* @createDate 2024-02-26 15:02:34
*/
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
    implements QuestionSubmitService{
    @Resource
    private QuestionService questionService;

    /**
     * 提交题目
     *
     * @param questionSubmitRequest 提交的题目信息
     * @param loginUser             登录用户
     * @return 提交的题目记录 id
     */
    @Override
    public long doQuestionSubmit(QuestionSubmitRequest questionSubmitRequest, User loginUser) {
        // 判断实体是否存在，根据类别获取实体
        Question question = questionService.getById(questionSubmitRequest.getQuestionId());
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 是否已登录
        long userId = loginUser.getId();
        // 每个用户串行提交题目
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setUserId(userId);
        questionSubmit.setLanguage(questionSubmitRequest.getLanguage());
        questionSubmit.setCode(questionSubmitRequest.getCode());
        questionSubmit.setQuestionId(questionSubmitRequest.getQuestionId());
        // todo 题目初始状态
        questionSubmit.setStatus(0);
        questionSubmit.setJudgeInfo("");
        QueryWrapper<QuestionSubmit> questionSubmitQueryWrapper = new QueryWrapper<>(questionSubmit);
        boolean flag = this.save(questionSubmit);
        if (!flag) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "提交题目插入失败");
        }
        return questionSubmit.getId();
    }


}




