package net.trans.mapper;

import net.trans.pojo.bo.AccountBo;
import org.apache.ibatis.annotations.Param;

public interface TransUserDao{

    /**
     * 判断手机号是否存在
     * @param mobilePhone
     * @return
     */
    public int mobilePhoneIsExist(String mobilePhone);

    /**
     * 注册，将用户信息存入数据库
     * @param accountBo
     * @return
     */
    public int register(AccountBo accountBo);

    /**
     * 根据手机号找出密码并匹配
     * @param phone
     * @return
     */
    public String findPasswordByPhone(String phone);

    /**
     * 更改密码
     * @param phone
     * @param password
     * @return
     */
    public Integer updataPassword(@Param("phone")String phone,@Param("password")String password);
}
