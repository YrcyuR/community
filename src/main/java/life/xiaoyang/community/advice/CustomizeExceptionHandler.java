package life.xiaoyang.community.advice;

import life.xiaoyang.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(CustomizeException.class)
    public ModelAndView handle(Throwable throwable, Model model)
    {
        if(throwable instanceof CustomizeException)
        {
            model.addAttribute("message",throwable.getMessage());
        }
        return new ModelAndView("error");
    }

}