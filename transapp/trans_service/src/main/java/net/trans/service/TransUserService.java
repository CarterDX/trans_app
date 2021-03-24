package net.trans.service;

import net.trans.pojo.bo.AccountBo;

/**
 * @Author Cx
 * @Date 2021-02-18
 * @Version 1.0
 */

public interface TransUserService {

    /**
     * 手机是否存在
     * @param phone
     * @return
     */
    public boolean mobileIsExist(String phone);

    /**
     * 注册
     * @param accountBo
     * @return
     */
    public int register(AccountBo accountBo);

    /**
     * 检查密码是否正确
     * @param phone
     * @param password
     * @return
     */
    public boolean checkPassword(String phone,String password);

    /**
     * 生成用户token
     * @return
     */
    public String getAccountToken(String phone);

    /**
     * 修改密码
     * @param phone
     * @param password
     * @return
     */
    public Integer updataPassword(String phone,String password);
}
