package life.xiaoyang.community.controller;

import life.xiaoyang.community.bean.Comment;
import life.xiaoyang.community.bean.User;
import life.xiaoyang.community.dto.CommentDTO;
import life.xiaoyang.community.dto.ResultDTO;
import life.xiaoyang.community.enums.CustomizeErrorEnum;
import life.xiaoyang.community.mapper.CommentMapper;
import life.xiaoyang.community.service.CommentService;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommenController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO, HttpServletRequest request)
    {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(2002, CustomizeErrorEnum.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommentator(1);
        comment.setLikeCount(0L);
        commentService.insertCommen(comment);
        return ResultDTO.okOf();
    }

}
