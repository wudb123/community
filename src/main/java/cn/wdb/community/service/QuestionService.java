package cn.wdb.community.service;

import cn.wdb.community.dto.QuestionDto;
import cn.wdb.community.mapper.QuestionMapper;
import cn.wdb.community.mapper.UserMapper;
import cn.wdb.community.pojo.Question;
import cn.wdb.community.pojo.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public List<Question> find(Integer page, Integer pageSize){
        if(page < 1)
            page = 1;
        PageHelper.startPage(page,pageSize);
        List<Question> questions = questionMapper.selectAll();
        return questions;
    }

    public List<QuestionDto> findAll(List<Question> questions) {
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        return questionDtoList;
    }

}
