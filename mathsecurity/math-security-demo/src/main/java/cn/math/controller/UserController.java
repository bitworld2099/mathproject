package cn.math.controller;

import cn.math.dto.Condition;
import cn.math.dto.User;
import cn.math.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    @GetMapping("/me")
    public Object getCurrentUser(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    //新增或者修改
    @PutMapping
    public String update(@Valid @RequestBody User user, BindingResult ex){
        System.out.println(ReflectionToStringBuilder.toString(ex, ToStringStyle.MULTI_LINE_STYLE));
        System.out.println("错误对象列表：");
        if(ex.hasErrors()){
            ex.getAllErrors().stream().forEach(e->{
                System.out.println(ReflectionToStringBuilder.toString(e, ToStringStyle.MULTI_LINE_STYLE));
            });
            throw new RuntimeException("更新错误：");
        }
        service.saveOrUpdate(user);
        return "操作成功";
    }

    //新增或者修改
    @PostMapping
    public String create(@Valid @RequestBody User user, BindingResult ex){
        System.out.println(ReflectionToStringBuilder.toString(ex, ToStringStyle.MULTI_LINE_STYLE));
        System.out.println("错误对象列表：");
        if(ex.hasErrors()){
            ex.getAllErrors().stream().forEach(e->{
                System.out.println(ReflectionToStringBuilder.toString(e, ToStringStyle.MULTI_LINE_STYLE));
            });
            throw new RuntimeException("操作失败！");
        }
        service.saveOrUpdate(user);
        return "操作成功";

    }

    //查询详情
    @GetMapping("/{id:\\d+}")
    public User getInfo(@PathVariable Long id){
        System.out.println("接收的id="+id);
        return service.queryById(id);
    }

    //普通查询
    @GetMapping
    public List<User> query(@RequestBody List<User> users){
        for(User user:users){
            logger.info("username="+user.getUsername());
            logger.info("pwd="+user.getPassword());
            logger.info("id="+user.getId());
        }
        //logger.info("birthday="+user.getBirthday());
        List<User> list = new ArrayList<>();
        list.add(new User(1l,"tom1","123",true,true,true,true));
        list.add(new User(2l,"tom2","123",true,true,true,true));
        list.add(new User(3l,"tom3","123",true,true,true,true));
        return list;
    }
}
