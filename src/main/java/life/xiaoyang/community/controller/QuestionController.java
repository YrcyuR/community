package life.xiaoyang.community.controller;

import life.xiaoyang.community.dto.QuestionDTO;
        import life.xiaoyang.community.service.QuestionService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable("id")Integer id, Model model)
    {
        System.out.println(id);
        QuestionDTO questionDTO = questionService.selectById(id);
        questionService.updateView(id);
        model.addAttribute("question",questionDTO );
        return "question";
    }

}
