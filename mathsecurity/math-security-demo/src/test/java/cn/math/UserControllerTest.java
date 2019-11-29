package cn.math;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    //由spring容器主动注入web上下文对象
    @Autowired
    private WebApplicationContext wac;
    //定义一个用于伪造mvc请求环境的对象
    private MockMvc mockMvc;

    //每个测试方法被调用之前都会先被调用
    //用于测试资源的初始化
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenUpdateSuccess()throws Exception{
        String content = "{\"id\":5,\"username\":\"ivey\",\"password\":\"888888\",\"enabled\":true,\"accountNonExpired\":true,\"credentialsNonExpired\":true,\"accountNonLocked\":true}";
        String msg = mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(msg);
    }

    @Test
    public void whenCreateSeuccess()throws Exception{
        String content = "{\"id\":null,\"username\":\"Tom1\",\"password\":\"123456\",\"enabled\":true,\"accountNonExpired\":true,\"credentialsNonExpired\":true,\"accountNonLocked\":true}";
        String msg = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(msg);
    }

    //详情查询
    @Test
    public void whenGetInfoSuccess()throws Exception{
        String json = mockMvc.perform(get("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println("详情查询："+json);
    }

    //普通查询
    @Test
    public void whenQuerySuccess() throws Exception {
        long birthday = System.currentTimeMillis();
        String param = "[{\"id\":1,\"username\":\"tom1\",\"password\":\"123\"},{\"id\":2,\"username\":\"tom2\",\"password\":\"123\"},{\"id\":3,\"username\":\"tom3\",\"password\":\"123\"}]";
        //发起一个rest请求，查询所有用户
        //期待返回的结果的状态码三：200
        String list = mockMvc.perform(get("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(param)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();
        System.out.println(list);
    }

}
