package cn.wdb.community.advice;

import cn.wdb.community.exception.ExceptionMessage;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandler {
    ModelAndView handle(Throwable e, Model model){
        if (e instanceof ExceptionMessage) {
            model.addAttribute("message", e.getMessage());
        } else {
            model.addAttribute("message","服务器太过拥挤，请稍候再试");
        }
        return new ModelAndView("error");
    }

}
