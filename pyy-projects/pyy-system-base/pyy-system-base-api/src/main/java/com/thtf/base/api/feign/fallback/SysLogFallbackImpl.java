package com.thtf.base.api.feign.fallback;

import com.thtf.base.api.SysLogControllerApi;
import com.thtf.base.api.vo.SysLogQueryConditionVO;
import com.thtf.base.api.vo.SysLogSaveOrUpdateVO;
import com.thtf.base.api.vo.SysLogVO;
import com.thtf.common.core.response.Pager;
import com.thtf.common.core.response.ResponseResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * ---------------------------
 * 断容器工厂实现类
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019/12/27 14:45
 * 版本：  v1.0
 * ---------------------------
 */
@Slf4j
@AllArgsConstructor
public class SysLogFallbackImpl implements SysLogControllerApi {
    private final Throwable throwable;

    @Override
    public ResponseResult<Pager<SysLogVO>> findList(int page, int size, SysLogQueryConditionVO queryConditionVO) {
        log.error("feign 查询日志失败:{}", queryConditionVO, throwable);
        return ResponseResult.SERVER_ERROR();
    }

    @Override
    public ResponseResult<SysLogVO> add(SysLogSaveOrUpdateVO sysLogSaveOrUpdateVO) {
        log.error("feign 保存日志失败:{}", sysLogSaveOrUpdateVO, throwable);
        return ResponseResult.SERVER_ERROR();
    }

    @Override
    public ResponseResult delete(String logId) {
        log.error("feign 删除日志失败:{}", logId, throwable);
        return ResponseResult.SERVER_ERROR();
    }
}
