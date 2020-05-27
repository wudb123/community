package cn.wdb.community.controller;

import cn.wdb.community.dto.QuestionDto;
import cn.wdb.community.exception.ErrorCode;
import cn.wdb.community.exception.ExceptionMessage;
import cn.wdb.community.mapper.QuestionMapper;
import cn.wdb.community.mapper.UserMapper;
import cn.wdb.community.pojo.Question;
import cn.wdb.community.pojo.User;
import cn.wdb.community.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Integer id,
                           Model model){
        Question question = questionService.selectByPrimaryKey(id);
        if (question == null){
            throw new ExceptionMessage(ErrorCode.QUESTION_NOT_FOUND);
        }
        questionService.updateViewById(question);
        QuestionDto questionDto = new QuestionDto();
        BeanUtils.copyProperties(question,questionDto);
        User user = new User();
        user.setAccountId(String.valueOf(question.getCreator()));
        user = userMapper.selectOne(user);
        questionDto.setUser(user);
        model.addAttribute("questionDto",questionDto);
        return "question";
    }
}
