package life.xiaoyang.community.service;

import life.xiaoyang.community.enums.CustomizeErrorEnum;
import life.xiaoyang.community.exception.CustomizeException;
import life.xiaoyang.community.bean.Question;
import life.xiaoyang.community.bean.User;
import life.xiaoyang.community.dto.PaginationDTO;
import life.xiaoyang.community.dto.QuestionDTO;
import life.xiaoyang.community.mapper.QuestionMapper;
import life.xiaoyang.community.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    private ArrayList<QuestionDTO> getPaginationDTO(List<Question> list)
    {
        ArrayList<QuestionDTO> questions=new ArrayList<>();
        for(Question question:list)
        {
            User user = userMapper.findUserById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO );
            questionDTO.setUser(user);
            questions.add(questionDTO);
        }
        return questions;
    }

    public PaginationDTO list(Integer page, Integer size)
    {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.selectCount();
        paginationDTO.setPagination(totalCount,size,page);
        Integer startIndex = (paginationDTO.getPage()-1)*size;
        List<Question> list = questionMapper.list(startIndex,size);
        paginationDTO.setQuestions(getPaginationDTO(list));
        return paginationDTO;
    }

    public PaginationDTO list(Integer id, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.selectCountByUserId(id);
        paginationDTO.setPagination(totalCount,size,page);
        Integer startIndex = (paginationDTO.getPage()-1)*size;
        List<Question> list = questionMapper.listByUserId(id,startIndex,size);
        paginationDTO.setQuestions(getPaginationDTO(list));
        return paginationDTO;
    }

    public QuestionDTO selectById(Integer id) {
        Question question = questionMapper.selectById(id);
        if(question==null)
        {
            throw new CustomizeException(CustomizeErrorEnum.NO_FIND_QUESTION);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.findUserById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId()==null)
        {
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insertQuestioin(question);
        }
        else {
            question.setGmtModified(System.currentTimeMillis());
            int i = questionMapper.updateQuestion(question);
            if (i != 1)
            {
                throw  new CustomizeException(CustomizeErrorEnum.NO_FIND_QUESTION);
            }
        }
    }

    public void updateView(Integer id) {
        questionMapper.updateView(id);
    }
}
