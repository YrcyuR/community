package life.xiaoyang.community.controller;

import life.xiaoyang.community.bean.User;
import life.xiaoyang.community.dto.PaginationDTO;
import life.xiaoyang.community.mapper.QuestionMapper;
import life.xiaoyang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller()
public class profileController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String prolie(@PathVariable("action")String action, Model model, HttpServletRequest request,
                         @RequestParam(name = "page", defaultValue = "1") Integer page,
                         @RequestParam(name = "size", defaultValue = "5") Integer size
                         )
    {
        if(action.equals("questions"))
        {
            model.addAttribute("action","questions");
            model.addAttribute("actionName","我的问题" );
        }
        else if(action.equals("replies"))
        {
            model.addAttribute("action","replies");
            model.addAttribute("actionName","最新回复" );
        }

        User user = (User) request.getSession().getAttribute("user");
        if(user == null)
        {
            return "redirect:/token";
        }
        PaginationDTO pagination = questionService.list(user.getId(), page, size);
        model.addAttribute("paginations", pagination);

        return "profile";
    }
}
