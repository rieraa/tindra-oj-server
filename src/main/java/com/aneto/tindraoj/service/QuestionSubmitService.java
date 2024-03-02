package com.aneto.tindraoj.service;

import com.aneto.tindraoj.model.dto.question.QuestionQueryRequest;
import com.aneto.tindraoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.aneto.tindraoj.model.dto.questionsubmit.QuestionSubmitRequest;
import com.aneto.tindraoj.model.entity.Question;
import com.aneto.tindraoj.model.entity.QuestionSubmit;
import com.aneto.tindraoj.model.entity.User;
import com.aneto.tindraoj.model.vo.QuestionSubmitVO;
import com.aneto.tindraoj.model.vo.QuestionVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

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

    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);

    /**
     * 获取题目封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取题目封装
     *
     * @param questionSubmitPage
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);


}
