package life.xiaoyang.community.mapper;

import life.xiaoyang.community.bean.Comment;
import org.apache.ibatis.annotations.Select;

public interface CommentMapper {
    Comment selectByPrimaryKey(Long id);

    @Select("insert into comment(parent_id,type,commentator,gmt_create,gmt_modified,like_count,content) values(#{parentId},#{type},#{commentator},#{gmtCreate},#{gmtModified},#{likeCount},#{content})")
    void insertCommen(Comment comment);
}