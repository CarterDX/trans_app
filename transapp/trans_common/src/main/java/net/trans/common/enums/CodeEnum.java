package net.trans.common.enums;

/**
 *  @Auther Cx
 *  @Date 2021-02-03
 *  @Version 1.0
 */
public enum CodeEnum {

    /**
     * 服务端系统级固定响应码
     */
    code8200(200, 200, "执行成功"),
    code8401(401, 401, "请求需要HTT身份验证"),
    code8403(403,403,"没有权限"),
    code8994(500, 8994, "校验字段错误"),
    code8995(500, 8995, "验证码发送失败"),
    code8996(500, 8996, "提示错误"),
    code8997(500, 8997, "参数错误"),
    code8998(500, 8998, "数据库错误"),
    code8999(500, 8999, "服务器错误"),
    /**
     * API接口级固定响应码
     */
    code990501(201, 990501, "请求参数无效(缺失，或为空，或不符合规则)"),
    code990502(201, 990502, "密码错误"),
    code990503(201, 990503, "请求权限无效"),
    code990504(201, 990504, "验证码错误"),
    code990505(201, 990505, "请求业务执行失败"),
    code990506(201, 990506, "没有响应数据，即响应结果数据(result)不存在"),
    code990507(201, 990507, "请求已经过期"),
    code990508(201, 990508, "请求签名认证无效"),
    code990509(201, 990509, "请求签名认证无效");

    /**
     * 对应的httpcode，参照用
     */
    private int httpCode;

    /**
     * 业务code
     */
    private int code;

    /**
     * 描述信息
     */
    private String desc;


    CodeEnum(int httpCode, int code, String desc) {
        this.httpCode = httpCode;
        this.code = code;
        this.desc = desc;
    }

    public static boolean isSuccess(int code) {
        if (code == code8200.getCode()) {
            return true;
        }
        return false;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    public static int getHttpCodeByCode(int code) {
        CodeEnum[] codes = CodeEnum.values();
        for (CodeEnum c : codes) {
            if (c.getCode() == code) {
                return c.getHttpCode();
            }
        }
        return CodeEnum.code8999.httpCode;
    }
}
