package cn.wdb.community.controller;

import cn.wdb.community.dto.QuestionDto;
import cn.wdb.community.mapper.UserMapper;
import cn.wdb.community.pojo.Question;
import cn.wdb.community.pojo.User;
import cn.wdb.community.service.QuestionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(@RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "pageSize",defaultValue = "8") Integer pageSize,
                        Model model) {
        List<Question> questions = questionService.find(page,pageSize);
        PageInfo pageInfo = new PageInfo(questions);
        model.addAttribute("pageInfo",pageInfo);
        List<QuestionDto> questionDtoList = questionService.findAll(questions);
        model.addAttribute("questions", questionDtoList);
        return "index";
    }
}
