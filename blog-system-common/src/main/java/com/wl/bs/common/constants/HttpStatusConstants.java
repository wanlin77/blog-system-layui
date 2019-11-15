package com.wl.bs.common.constants;

/**
 * @description: Http状态常量
 * @author: wanlin
 * @version: 1.0
 * @createtime: 2019/9/28 12:41
 */
public enum HttpStatusConstants {
    OK(200,"成功"),
    BAD_REQUEST(400,"请求参数有误"),
    UNAUTHORIZED(401,"登录失败"),
    INTERNAL_SERVER_ERROR(500,"服务器遇到了一个未曾预料的状况"),
    BAD_GATEWAY(502,"从上游服务器接收到无效的响应");

    private final int code;
    private final String content;

    HttpStatusConstants(int code, String content) {
        this.code = code;
        this.content = content;
    }

    public int getCode() {
        return code;
    }

    public String getContent() {
        return content;
    }
}
