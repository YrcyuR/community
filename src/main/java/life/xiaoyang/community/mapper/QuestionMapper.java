package life.xiaoyang.community.mapper;

import life.xiaoyang.community.bean.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

//@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,comment_count,view_count,like_count,tag) " +
            "values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{commentCount},#{viewCount},#{likeCount},#{tag})")
    void insertQuestioin(Question question);

    @Select("select * from question limit #{startIndex},#{size}")
    List<Question> list(@Param("startIndex") Integer startIndex,@Param("size") Integer size);

    @Select("select count(1) from question")
    Integer selectCount();

    @Select("select * from question where creator=#{id} limit #{startIndex},#{size}")
    List<Question> listByUserId(@Param("id")Integer id, @Param("startIndex")Integer startIndex,@Param("size") Integer size);

    @Select("select count(1) from question where creator=#{id}")
    Integer selectCountByUserId(@Param("id") Integer id);

    @Select("select * from question where id = #{id}")
    Question selectById(Integer id);

    @Update("update question set title=#{title},description=#{description},gmt_modified=#{gmtModified},tag = #{tag} where id = #{id}")
    int updateQuestion(Question question);

    @Update("update question set view_count = view_count+1 where id =#{id}")
    void updateView(Integer id);
}
