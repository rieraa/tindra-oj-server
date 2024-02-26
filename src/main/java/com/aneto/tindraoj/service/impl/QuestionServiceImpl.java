package com.aneto.tindraoj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aneto.tindraoj.model.entity.Question;
import com.aneto.tindraoj.service.QuestionService;
import com.aneto.tindraoj.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

/**
* @author ameee
* @description 针对表【question(题目)】的数据库操作Service实现
* @createDate 2024-02-26 15:01:26
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService{

}




