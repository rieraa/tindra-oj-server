//package com.aneto.tindraoj.controller;
//
//import com.aneto.tindraoj.annotation.AuthCheck;
//import com.aneto.tindraoj.common.BaseResponse;
//import com.aneto.tindraoj.common.ErrorCode;
//import com.aneto.tindraoj.common.ResultUtils;
//import com.aneto.tindraoj.constant.UserConstant;
//import com.aneto.tindraoj.exception.BusinessException;
//import com.aneto.tindraoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
//import com.aneto.tindraoj.model.dto.questionsubmit.QuestionSubmitRequest;
//import com.aneto.tindraoj.model.entity.QuestionSubmit;
//import com.aneto.tindraoj.model.entity.User;
//import com.aneto.tindraoj.model.vo.QuestionSubmitVO;
//import com.aneto.tindraoj.service.QuestionSubmitService;
//import com.aneto.tindraoj.service.UserService;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//
///**
// * 提交题目接口
// */
//@RestController
//@RequestMapping("/question_submit")
//@Slf4j
//@Deprecated
//public class QuestionSubmitController {
//
//    @Resource
//    private QuestionSubmitService questionSubmitService;
//
//    @Resource
//    private UserService userService;
//
//
//    /**
//     * 题目提交
//     */
//    @PostMapping("/")
//    public BaseResponse<Long> questionSubmit(@RequestBody QuestionSubmitRequest questionSubmitAddRequest,
//                                                HttpServletRequest request) {
//        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        // 登录才能提交题目
//        final User loginUser = userService.getLoginUser(request);
//        long result = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
//        return ResultUtils.success(result);
//    }
//
//    /**
//     * 分页获取列表（仅管理员）
//     *
//     * @param questionSubmitQueryRequest
//     * @return
//     */
//    @PostMapping("/list/page")
//    public BaseResponse<Page<QuestionSubmitVO>> listQuestionSubmitByPage(@RequestBody QuestionSubmitQueryRequest questionSubmitQueryRequest, HttpServletRequest request) {
//        long current = questionSubmitQueryRequest.getCurrent();
//        long size = questionSubmitQueryRequest.getPageSize();
//        // 从数据库中查询原始的题目提交分页信息
//        Page<QuestionSubmit> questionSubmitPage = questionSubmitService.page(new Page<>(current, size),
//                questionSubmitService.getQueryWrapper(questionSubmitQueryRequest));
//        final User loginUser = userService.getLoginUser(request);
//        // 返回脱敏信息
//        return ResultUtils.success(questionSubmitService.getQuestionSubmitVOPage(questionSubmitPage, loginUser));
//
//    }
//
//}
