package life.xiaoyang.community.controller;

import life.xiaoyang.community.bean.User;
import life.xiaoyang.community.dto.PaginationDTO;
import life.xiaoyang.community.dto.QuestionDTO;
import life.xiaoyang.community.mapper.UserMapper;
import life.xiaoyang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/token")
    public String hello(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size
    ) {
        try
        {
            PaginationDTO paginations = questionService.list(page,size);
            model.addAttribute("paginations", paginations);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return "index";
    }

}