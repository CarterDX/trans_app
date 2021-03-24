package net.trans.common;

import net.trans.common.enums.CodeEnum;

/**
 * Json响应格式
 * @Author Cx
 * @Date 2021-02-03
 * @Version 1.0
 */
public class BaseResult {

    /**
     * 响应码
     */
    private Integer code = CodeEnum.code8200.getCode();

    /**
     * 响应提示信息
     */
    private String msg;
    /**
     * 分页的时候才会有值
     */
    private long total;
    /**
     * 响应结果数据
     */
    private Object result;

    public static BaseResult buildSuccessResult(Object result) {
        BaseResult dubboResult = new BaseResult();
        dubboResult.setCode(CodeEnum.code8200.getCode());
        dubboResult.setResult(result);
        return dubboResult;
    }


    public static BaseResult buildSuccessResult(Object result,long total) {
        BaseResult dubboResult = new BaseResult();
        dubboResult.setCode(CodeEnum.code8200.getCode());
        dubboResult.setResult(result);
        dubboResult.setTotal(total);
        return dubboResult;
    }

    public static BaseResult buildSuccessResult() {
        BaseResult dubboResult = new BaseResult();
        dubboResult.setCode(CodeEnum.code8200.getCode());
        dubboResult.setResult(true);
        return dubboResult;
    }

    public static BaseResult buildSuccessResult(CodeEnum codeEnum) {
        BaseResult dubboResult = new BaseResult();
        dubboResult.setCode(codeEnum.getHttpCode());
        dubboResult.setMsg(codeEnum.getDesc());
        dubboResult.setResult(true);
        return dubboResult;
    }

    public static BaseResult buildErrorResult(CodeEnum codeEnum) {
        BaseResult dubboResult = new BaseResult();
        dubboResult.setCode(codeEnum.getCode());
        dubboResult.setMsg(codeEnum.getDesc());
        return dubboResult;
    }

    public static BaseResult buildErrorResult(int errorCode, String msg) {
        BaseResult dubboResult = new BaseResult();
        dubboResult.setCode(errorCode);
        dubboResult.setMsg(msg);
        dubboResult.setResult(false);
        return dubboResult;
    }

    public static BaseResult buildTipsErrorResult( String msg) {
        BaseResult dubboResult = new BaseResult();
        dubboResult.setCode(CodeEnum.code8996.getCode());
        dubboResult.setMsg(msg);
        return dubboResult;
    }
    public static BaseResult buildErrorResult(Object result) {
        BaseResult dubboResult = new BaseResult();
        dubboResult.setCode(CodeEnum.code8994.getCode());
        dubboResult.setMsg((String) result);
        return dubboResult;
    }

    public BaseResult setCode(Integer code) {
        this.code = code;
        return this;
    }

    public BaseResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public BaseResult setResult(Object result) {
        this.result = result;
        return this;
    }

    public BaseResult setTotal(long total) {
        this.total = total;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public long getTotal() {
        return total;
    }

    public Object getResult() {
        return result;
    }
}
