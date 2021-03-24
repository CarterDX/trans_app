package net.trans.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @Author Cx
 * @Date 2021-02-07
 * @Version 1.0
 */
public class CheckFormatUtil {

    /** 正则表达
     * 手机号码由11位数字组成，
     * 匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147+8位任意数
     * @param mobile 手机号
     * @return
     * @throws PatternSyntaxException
     */
    public static boolean checkMobile(String mobile) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(mobile);
        return m.matches();
    }

    /**
     * 密码长度为8到20位,必须包含字母和数字，字母区分大小写
     * @param password 密码
     * @return
     */
    public static boolean checkPassword(String password) {
        String regExp = "^(?=.*[0-9])(?=.*[a-zA-Z])(.{8,20})$";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    /**
     * 校验email
     * @param email
     * @return
     */
    public static boolean checkEmail(String email){
        String regExp = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
