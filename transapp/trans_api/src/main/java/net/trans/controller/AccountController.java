package net.trans.controller;

import net.trans.common.BaseResult;
import net.trans.common.enums.CodeEnum;
import net.trans.common.utils.CheckFormatUtil;
import net.trans.pojo.bo.AccountBo;
import net.trans.service.TransUserService;
import net.trans.service.serviceImpl.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author Cx
 * @Date 2021-02-14
 * @Version 1.0
 */

@RestController
@RequestMapping("account")
public class AccountController {


    @Autowired
    private TransUserService transUserService;

    @Autowired
    private SmsService smsService;

    /**
     * 校验手机号
     * @param mobileNo
     * @return
     */
    @GetMapping("isMobileNo")
    public BaseResult checkMobileNo(@RequestParam("mobileNo") String mobileNo){
        if(CheckFormatUtil.checkMobile(mobileNo)){
            return BaseResult.buildSuccessResult();
        }
        return BaseResult.buildErrorResult(8994,"null");
    }

    /**
     * 校验密码
     * @param password
     * @return
     */
    public BaseResult checkPassword(@RequestParam("password") String password){
        if(CheckFormatUtil.checkPassword(password)){
            return BaseResult.buildSuccessResult();
        }
        return BaseResult.buildErrorResult(8994,"null");
    }

    /**
     * 校验邮箱
     * @param email
     * @return
     */
    public BaseResult checkEmail(@RequestParam("email") String email){
        if(CheckFormatUtil.checkEmail(email)){
            return BaseResult.buildSuccessResult();
        }
        return BaseResult.buildErrorResult(8994,null);
    }

    /**
     * 判断手机号是否已存在
     * @param mobileNo
     * @return
     */
    @GetMapping("mobileNoIsExist")
    public BaseResult MobileIsExist(@RequestParam("mobileNo") String mobileNo){
        if(transUserService.mobileIsExist(mobileNo)){
            return BaseResult.buildErrorResult(990506,null);
        }
        return BaseResult.buildSuccessResult();
    }

    /**
     * 发送注册验证码
     * @return
     */
    @PostMapping("getRegisterVerifyCoder")
    public BaseResult getRegisterVerifyCoder(@RequestBody Map<String, String> map){
        return  smsService.sendRegisterMessage(map.get("mobilePhone"));
    }

    /**
     * 发送登入验证码
     * @return
     */
    @PostMapping("getLoginVerifyCoder")
    public BaseResult getVerifyCoder(@RequestBody Map<String, String> map){
        return  smsService.sendLoginMessage(map.get("mobilePhone"));
    }

//    /**
//     * 校验验证码
//     * @param map
//     * @return
//     */
//    @PostMapping("checkVerifyCoder")
//    public BaseResult checkVerifyCoder(@RequestBody Map<String, String> map){
//        String phone = map.get("phone");
//        String verifyCode = map.get("verifyCode");
//        if(smsService.checkVerifyCode(phone,verifyCode)){
//            return BaseResult.buildSuccessResult();
//        }
//        return BaseResult.buildErrorResult(CodeEnum.code990504);
//    }

    /**
     * 注册
     * @param accountBo
     * @return
     */
    @PostMapping("register")
    public BaseResult register(@RequestBody AccountBo accountBo){
        if (!CheckFormatUtil.checkMobile(accountBo.getMobilePhone())){
            return BaseResult.buildErrorResult(8994,"手机号错误");
        }
        if (!CheckFormatUtil.checkPassword(accountBo.getPassword())){
            return BaseResult.buildErrorResult(8994,"密码不符合要求");
        }
        if (!CheckFormatUtil.checkEmail(accountBo.getEmail())){
            return BaseResult.buildErrorResult(8994,"邮箱错误");
        }
        if(transUserService.mobileIsExist(accountBo.getMobilePhone())){
            return BaseResult.buildErrorResult(990506,"手机号已存在");
        }
        if(smsService.checkRegisterVerifyCode(accountBo.getMobilePhone(),accountBo.getVerifyCode())){
            if(transUserService.register(accountBo)==1){
                return BaseResult.buildSuccessResult();
            }
        }else {
            return BaseResult.buildErrorResult(CodeEnum.code990504);
        }
        if(transUserService.register(accountBo)==1){
            return BaseResult.buildSuccessResult();
        }
        return BaseResult.buildErrorResult(CodeEnum.code990505);
    }

    @PostMapping("mobileLogin")
    public BaseResult mobileLogin(@RequestBody Map<String, String> map){
        String phone = map.get("phone");
        String verifyCode = map.get("verifyCode");
        if(smsService.checkLoginVerifyCode(phone,verifyCode)){
            return BaseResult.buildSuccessResult(transUserService.getAccountToken(phone));
        }
        return BaseResult.buildErrorResult(CodeEnum.code990504);
    }

    @PostMapping("passwordLogin")
    public BaseResult passwordLogin(@RequestBody Map<String, String> map){
        String phone = map.get("mobilePhone");
        String password = map.get("password");
        if(transUserService.checkPassword(phone,password)){
            return BaseResult.buildSuccessResult("登入成功");
//            return BaseResult.buildSuccessResult(transUserService.getAccountToken(phone));
        }
        return BaseResult.buildErrorResult(CodeEnum.code990504);
    }

    @PostMapping("updataPassword")
    public BaseResult updataPassword(@RequestBody Map<String, String> map){
        String phone = map.get("mobilePhone");
        String password = map.get("password");
        if (transUserService.mobileIsExist(phone)){
            if (CheckFormatUtil.checkPassword(password)) {
                return BaseResult.buildSuccessResult(transUserService.updataPassword(phone,password));
            }
        }
        return BaseResult.buildErrorResult(CodeEnum.code990505);
    }
}
