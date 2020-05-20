package cn.wdb.community.controller;

import cn.wdb.community.dto.QuestionDto;
import cn.wdb.community.mapper.UserMapper;
import cn.wdb.community.pojo.User;
import cn.wdb.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model){
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies) {
                if ("community_token".equals(cookie.getName())){
                    String token = cookie.getValue();
                    User user = new User();
                    user.setToken(token);
                    List<User> users = userMapper.select(user);
                    if (users != null || users.size()>0){
                        request.getSession().setAttribute("user",users.get(0));
                    }
                    break;
                }
            }
        }

        List<QuestionDto> questionDtoList = questionService.findAll();
        model.addAttribute("questions",questionDtoList);
        return "index";
    }
}
