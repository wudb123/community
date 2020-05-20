package cn.wdb.community.controller;

import cn.wdb.community.mapper.QuestionMapper;
import cn.wdb.community.pojo.Question;
import cn.wdb.community.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish(){
    return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(name = "title") String title,
                            @RequestParam(name = "description")String description,
                            @RequestParam(name = "tag")String tag,
                            HttpServletRequest request,
                            Model model) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        if (title == null || title.length()== 0) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || description.length()== 0) {
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if (tag == null || tag.length()== 0) {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }
        User user = (User)request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "请先登录");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setGmtCreated(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreated());
        question.setCreator(user.getId());
        questionMapper.insertSelective(question);

        return "redirect:index";

    }
}
