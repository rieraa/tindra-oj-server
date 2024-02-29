package com.aneto.tindraoj.controller;

import com.aneto.tindraoj.common.BaseResponse;
import com.aneto.tindraoj.common.ErrorCode;
import com.aneto.tindraoj.common.ResultUtils;
import com.aneto.tindraoj.exception.BusinessException;
import com.aneto.tindraoj.model.dto.questionsubmit.QuestionSubmitRequest;
import com.aneto.tindraoj.model.entity.User;
import com.aneto.tindraoj.service.QuestionSubmitService;
import com.aneto.tindraoj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 提交题目接口
 */
@RestController
@RequestMapping("/question_submit")
@Slf4j
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;

    /**
     * 题目提交
     */
    @PostMapping("/")
    public BaseResponse<Long> questionSubmit(@RequestBody QuestionSubmitRequest questionSubmitAddRequest,
                                                HttpServletRequest request) {
        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能提交题目
        final User loginUser = userService.getLoginUser(request);
        long result = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
        return ResultUtils.success(result);
    }

}
