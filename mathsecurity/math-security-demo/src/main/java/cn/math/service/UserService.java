package cn.math.service;

import cn.math.dto.Condition;
import cn.math.dto.User;

import java.util.List;

public interface UserService {
    //根据用户Id查询用户
    public User queryById(Long id);
    //条件查询
    public List<User> queryByCondition(Condition condition);
    //新增或者修改用户
    public void saveOrUpdate(User user);
    //删除用户
    public void delete(Long id);
}
