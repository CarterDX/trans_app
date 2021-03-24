package net.trans.service.serviceImpl;

import net.trans.common.properties.RsaKeyProperties;
import net.trans.common.utils.JwtUtils;
import net.trans.mapper.TransUserDao;
import net.trans.mapper.UserDao;
import net.trans.pojo.TransUserTable;
import net.trans.pojo.bo.AccountBo;
import net.trans.service.TransUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 * @Author Cx
 * @Date 2021-02-18
 * @Version 1.0
 */
@Service
public class TransUserServiceImpl implements TransUserService {


    @Autowired
    TransUserDao transUserDao;

    @Autowired
    UserDao userDao;

    @Autowired
    RsaKeyProperties rsaProp;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean mobileIsExist(String phone) {
        if (transUserDao.mobilePhoneIsExist(phone) == 1){
            return true;
        }
        return false;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int register(AccountBo accountBo) {
        accountBo.setPassword(bCryptPasswordEncoder.encode(accountBo.getPassword()));
        accountBo.setuCreatetime(new Timestamp(System.currentTimeMillis()));
        accountBo.setuUpdatetime(new Timestamp(System.currentTimeMillis()));
        if (transUserDao.register(accountBo)== 1){
            return 1;
        }
        return 0;
    }

    @Override
    public boolean checkPassword(String phone,String password) {
        String uPassword = transUserDao.findPasswordByPhone(phone);
        if (bCryptPasswordEncoder.matches(password,uPassword)){
            return true;
        }
        return false;
    }

    @Override
    public String getAccountToken(String phone) {
        TransUserTable user = userDao.findByPhone(phone);
        String token = JwtUtils.generateTokenExpireInMinutes(user, rsaProp.getPrivateKey(), 24 * 60 * 7);
        return token;
    }

    @Override
    public Integer updataPassword(String phone, String password) {
        String updataPassword = bCryptPasswordEncoder.encode(password);
        System.out.println(updataPassword);
        return transUserDao.updataPassword(phone,updataPassword);
    }
}
