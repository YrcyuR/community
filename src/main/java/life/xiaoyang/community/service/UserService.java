package life.xiaoyang.community.service;

import life.xiaoyang.community.bean.User;
import life.xiaoyang.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {

        User userAccount = userMapper.findByAccountId(user.getAccountId());
        if(userAccount==null)
        {
            userMapper.insertUser(user);
        }
        else
        {
            userMapper.updateUser(user);
        }
    }
}
