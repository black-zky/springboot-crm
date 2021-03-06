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

    public static final ResultDto UPLOAD_FILE_SUCCESS=new ResultDto(1002,"上传文件成功");
    public static final ResultDto UPLOAD_FILE_FAIL=new ResultDto(-1002,"上传文件失败");

    public static final ResultDto USER_ADD_SUCCESS=new ResultDto(1003,"添加用户成功");
    public static final ResultDto USER_ADD_FAIL=new ResultDto(-1003,"添加用户失败");
    public static final ResultDto USER_EXITS=new ResultDto(-1004,"用户已存在");

    public static final ResultDto USER_ROLE_GRANT_SUCCESS =new ResultDto(1005,"分配用户角色成功");
    public static final ResultDto USER_ROLE_GRANT_FAIL =new ResultDto(-1005,"分配用户角色失败");

    public static final ResultDto ROLE_PERMISSION_GRANT_SUCCESS=new ResultDto(1006,"分配角色权限成功");
    public static final ResultDto ROLE_PERMISSION_GRANT_FAIL=new ResultDto(-1006,"分配用户权限失败");

    private int code;
    private String msg;
    private Object data;

    public ResultDto(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
