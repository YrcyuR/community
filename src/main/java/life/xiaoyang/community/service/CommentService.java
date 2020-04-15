package life.xiaoyang.community.service;

import life.xiaoyang.community.bean.Comment;
import life.xiaoyang.community.enums.CustomizeErrorEnum;
import life.xiaoyang.community.exception.CustomizeException;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    public void insertCommen(Comment comment) {
        if(comment.getParentId()==null || comment.getParentId()==0)
        {
            throw new CustomizeException(CustomizeErrorEnum.TARGET_PARAM_NOT_FOUND);
        }
    }
}
