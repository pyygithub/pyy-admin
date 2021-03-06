package com.thtf.common.auth.core;

import com.thtf.common.core.constant.SecurityConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ---------------------------
 * 登录类型 现在有用户名 短信 社交
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019/12/27 15:11
 * 版本：  v1.0
 * ---------------------------
 */
@Getter
@AllArgsConstructor
public enum LoginType {
    /**
     * 用户密码登录
     */
    normal(1, "用户密码登录"),

    /**
     * 短信密码
     */
    sms(2, "短信密码"),

    /**
     * 社交登录
     */
    qq(3, SecurityConstant.LOGIN_QQ),
    weixin(4, SecurityConstant.LOGIN_WEIXIN),
    gitee(5, SecurityConstant.LOGIN_GITEE),
    github(6, SecurityConstant.LOGIN_GITHUB);

    private int type;
    private String description;
}
