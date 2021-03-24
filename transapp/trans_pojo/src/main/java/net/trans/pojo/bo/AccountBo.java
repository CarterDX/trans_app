package net.trans.pojo.bo;

import java.sql.Timestamp;

/**
 * @Author Cx
 * @Date 2021-02-18
 * @Version 1.0
 */
public class AccountBo {

    private String username;

    private String mobilePhone;

    private String password;

    private String email;

    private String verifyCode;

    private java.sql.Timestamp uCreatetime;
    private java.sql.Timestamp uUpdatetime;

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public Timestamp getuCreatetime() {
        return uCreatetime;
    }

    public void setuCreatetime(Timestamp uCreatetime) {
        this.uCreatetime = uCreatetime;
    }

    public Timestamp getuUpdatetime() {
        return uUpdatetime;
    }

    public void setuUpdatetime(Timestamp uUpdatetime) {
        this.uUpdatetime = uUpdatetime;
    }
}
