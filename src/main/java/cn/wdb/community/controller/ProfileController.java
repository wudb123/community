package cn.wdb.community.controller;

import cn.wdb.community.mapper.QuestionMapper;
import cn.wdb.community.pojo.Question;
import cn.wdb.community.pojo.User;
import cn.wdb.community.service.QuestionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProfileController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable("action") String action,
                          @RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "pageSize",defaultValue = "8") Integer pageSize,
                          HttpServletRequest request,
                          Model model){
        if ("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
            User user = (User) request.getSession().getAttribute("user");
            List<Question> questions =questionService.findByUserId(page,pageSize,Integer.parseInt(user.getAccountId()));
            PageInfo pageInfo = new PageInfo(questions);
            model.addAttribute("pageInfo",pageInfo);
        }else {
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","我的回复");
        }

        return "profile";
    }
}
