package cn.math.browser.security.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BroswerUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("登录名："+username);
        //在此根据传来的username去加载用户信息
        //User user = new User(username,"123456", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        //模拟用户账户被锁住
        //模拟数据框存储的加密秘密
        String encodedPwd = passwordEncoder.encode("123456");
        System.out.println("原生密码：123456，加盐加密后："+encodedPwd);
        User user = new User(username,encodedPwd,true,true,true,true,AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return user;
    }
}
