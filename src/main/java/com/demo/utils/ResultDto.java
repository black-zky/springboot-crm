package com.demo.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto {
    public static final ResultDto LOGIN_SUCCESS=new ResultDto(200,"登录成功");
    public static final ResultDto LOGIN_ERROR_PWD=new ResultDto(1001,"用户名或密码不正确");

    private int code;
    private String msg;
    private Object data;

    public ResultDto(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
