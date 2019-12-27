package com.thtf.base.api.feign.factory;

import com.thtf.base.api.feign.SysLogControllerApi;
import com.thtf.base.api.feign.fallback.SysLogFallbackImpl;
import feign.hystrix.FallbackFactory;

/**
 * ---------------------------
 * 断容器工厂类
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019/12/27 14:45
 * 版本：  v1.0
 * ---------------------------
 */
public class SysLogFallbackFactory implements FallbackFactory<SysLogControllerApi> {
    @Override
    public SysLogControllerApi create(Throwable throwable) {
        return new SysLogFallbackImpl(throwable);
    }
}
