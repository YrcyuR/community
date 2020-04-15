//package life.xiaoyang.community.community;
//
//import life.xiaoyang.community.bean.Question;
//import life.xiaoyang.community.mapper.QuestionMapper;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class CommunityApplicationTests {
//
//    @Autowired
//    private QuestionMapper questionMapper;
//
//	@Test
//	void contextLoads() {
//
//	    for(int i =0 ;i< 500;++i)
//        {
//
//            Question question = new Question();
//            question.setDescription(description);
//            question.setTitle(title);
//            question.setTag(tag);
//            question.setCreator(user.getId());
//            question.setGmtCreate(System.currentTimeMillis());
//            question.setGmtModified(question.getGmtCreate());
//            questionMapper.insertQuestioin(question);
//            questionMapper.insertQuestioin(question);
//        }
//	}
//
//}
