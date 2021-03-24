package net.trans.service.serviceImpl;

import net.trans.common.BaseResult;
import net.trans.common.redis.RedisUtil;
import net.trans.common.utils.SmsSample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Author Cx
 * @Date 2021-02-22
 * @Version 1.0
 */
@Service
public class SmsService {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 发送注册验证码
     * @param mobile
     * @return
     */
    public BaseResult sendRegisterMessage(String mobile){
        if (StringUtils.isEmpty(mobile)) {
            return BaseResult.buildErrorResult(8994,"手机号为空");
        }
        String identifyCode = SmsSample.getCode();
        String returnCode = redisUtil.getString(mobile+"-register");
        if (!StringUtils.isEmpty(returnCode)) {
            SmsSample.regSendSMS(mobile,returnCode);
        } else {
            if(!redisUtil.set(mobile+"-register",identifyCode,60*15)){
                return BaseResult.buildErrorResult(8995,"验证码发送失败");
            }
            SmsSample.regSendSMS(mobile,identifyCode);
        }
        return BaseResult.buildSuccessResult();
    }

    /**
     * 发送登入验证码
     * @param mobile
     * @return
     */
    public BaseResult sendLoginMessage(String mobile){
        if (StringUtils.isEmpty(mobile)) {
            return BaseResult.buildErrorResult(8994,"手机号为空");
        }
        String identifyCode = SmsSample.getCode();
        String returnCode = redisUtil.getString(mobile+"-login");
        if (!StringUtils.isEmpty(returnCode)) {
            SmsSample.regSendSMS(mobile,returnCode);
        } else {
            if(!redisUtil.set(mobile+"-login",identifyCode,60*15)){
                return BaseResult.buildErrorResult(8995,"验证码发送失败");
            }
            SmsSample.regSendSMS(mobile,identifyCode);
        }
        return BaseResult.buildSuccessResult();
    }

    /**
     * 发送找回密码验证码
     * @param mobile
     * @return
     */
    public BaseResult sendFindPasswordMessage(String mobile){
        if (StringUtils.isEmpty(mobile)) {
            return BaseResult.buildErrorResult(8994,"手机号为空");
        }
        String identifyCode = SmsSample.getCode();
        String returnCode = redisUtil.getString(mobile+"-findP");
        if (!StringUtils.isEmpty(returnCode)) {
            SmsSample.regSendSMS(mobile,returnCode);
        } else {
            if(!redisUtil.set(mobile+"-findP",identifyCode,60*15)){
                return BaseResult.buildErrorResult(8995,"验证码发送失败");
            }
            SmsSample.regSendSMS(mobile,identifyCode);
        }
        return BaseResult.buildSuccessResult();
    }

    /**
     * 校验注册验证码
     * @param mobilePhone
     * @param VerifyCode
     * @return
     */
    public boolean checkRegisterVerifyCode(String mobilePhone,String VerifyCode){
        String regVerCode = redisUtil.getString(mobilePhone+"-register");
        if(regVerCode != null){
            return regVerCode.equals(VerifyCode);
        }
        return false;
    }

    /**
     * 校验登入验证码
     * @param mobilePhone
     * @param VerifyCode
     * @return
     */
    public boolean checkLoginVerifyCode(String mobilePhone,String VerifyCode){
        String regVerCode = redisUtil.getString(mobilePhone+"-login");
        if(regVerCode != null){
            return regVerCode.equals(VerifyCode);
        }
        return false;
    }

    /**
     * 校验找回密码的验证码
     * @param mobilePhone
     * @param VerifyCode
     * @return
     */
    public boolean checkFindPasswordVerifyCode(String mobilePhone,String VerifyCode){
        String regVerCode = redisUtil.getString(mobilePhone+"-findP");
        if(regVerCode != null){
            return regVerCode.equals(VerifyCode);
        }
        return false;
    }
}
