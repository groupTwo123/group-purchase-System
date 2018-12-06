package com.felix.grouppurchase.service.impl;

import com.felix.grouppurchase.mapper.RoleMapper;
import com.felix.grouppurchase.model.Role;
import com.felix.grouppurchase.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @ClassName CustomUserService
 * @Description TODO
 * @Author fangyong
 * @Date 2018/12/6 9:49
 **/
@Service
public class CustomUserService implements UserDetailsService {


    @Autowired
    RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = roleMapper.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        } else {
            ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
            //用于添加用户的权限
            for (Role role : user.getType()) {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
                System.out.print(role.getName());
            }
            return new org.springframework.security.core.userdetails.User(user.getUserName(),
                    user.getPassword(), authorities);
        }
    }
}
