package cn.math.service.impl;

import cn.math.dao.UserRepository;
import cn.math.dto.Condition;
import cn.math.dto.User;
import cn.math.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    //依赖dao
    @Autowired
    private UserRepository dao;

    @Override
    public User queryById(Long id) {
        return dao.findOne(id);
    }

    @Override
    public List<User> queryByCondition(Condition condition) {
        return null;
    }

    @Override
    public void saveOrUpdate(User user) {
        dao.save(user);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }
}
