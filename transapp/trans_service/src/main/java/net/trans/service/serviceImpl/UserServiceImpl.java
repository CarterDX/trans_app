package net.trans.service.serviceImpl;


import net.trans.mapper.UserDao;

import net.trans.pojo.SysRole;
import net.trans.pojo.TransUserTable;
import net.trans.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    	TransUserTable sysUser = userDao.findByPhone(s);
    	if(sysUser==null){
    		return null;
    	}
    	List<SysRole> roles = sysUser.getRoles();
    	for (SysRole role : roles) {
    		System.out.println(sysUser.getUUsername()+":"+role.getRoleName());
    	}
    	return new User(sysUser.getUUsername(), sysUser.getPassword(), sysUser.getRoles());

    }


}
