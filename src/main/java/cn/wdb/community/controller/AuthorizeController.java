package cn.wdb.community.controller;

import cn.wdb.community.dto.GitHubDTO;
import cn.wdb.community.dto.GitHubUser;
import cn.wdb.community.provider.AuthorizeProvider;
import cn.wdb.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import cn.wdb.community.pojo.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private AuthorizeProvider authorizeProvider;

    @Autowired
    private UserMapper userMapper;
    @Value("${gitHub.authorize.client_id}")
    private String client_id;
    @Value("${gitHub.authorize.client_secret}")
    private String client_secret;
    @Value("${gitHub.authorize.redirect_uri}")
    private String redirect_uri;
    @GetMapping("/callBack")
    public String callBack(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        GitHubDTO gitHubDTO = new GitHubDTO();
        gitHubDTO.setClient_id(client_id);
        gitHubDTO.setClient_secret(client_secret);
        gitHubDTO.setCode(code);
        gitHubDTO.setRedirect_uri(redirect_uri);
        gitHubDTO.setState(state);
        String access_token = authorizeProvider.getAccess_token(gitHubDTO);
        GitHubUser gitHubUser = authorizeProvider.getUser(access_token);
        HttpSession session = request.getSession();
        if(gitHubUser != null){
            //写入数据库中
            User user = new User();
            user.setAccountId(String.valueOf(gitHubUser.getId()));
            user.setUsername(gitHubUser.getName());
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setGmtCreated(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreated());
            userMapper.insert(user);
            Cookie cookie = new Cookie("community_token",token);
            response.addCookie(cookie);
            return "redirect:/";
        }else {
            //登录失败，重新登录
            return "redirect:/";
        }

    }
}
