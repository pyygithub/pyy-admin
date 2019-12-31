package com.thtf.base.server.controller;

import com.thtf.base.api.LogControllerApi;
import com.thtf.base.api.vo.LogQueryConditionVO;
import com.thtf.base.api.vo.LogSaveOrUpdateVO;
import com.thtf.base.api.vo.LogVO;
import com.thtf.base.server.service.SysLogService;
import com.thtf.common.core.response.Pager;
import com.thtf.common.core.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * ---------------------------
 * 系统操作日志记录Controller
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019/12/27 15:43
 * 版本：  v1.0
 * ---------------------------
 */
@RestController
public class LogController implements LogControllerApi {

    @Autowired
    private SysLogService sysLogService;

    @Override
    public ResponseResult<Pager<LogVO>> findList(int page, int size, LogQueryConditionVO queryConditionVO) {
        return ResponseResult.SUCCESS(sysLogService.findList(page, size, queryConditionVO));
    }

    @Override
    public ResponseResult<LogVO> add(LogSaveOrUpdateVO logSaveOrUpdateVO) {
        return ResponseResult.SUCCESS(sysLogService.add(logSaveOrUpdateVO));
    }
    @Override
    public ResponseResult delete(String logId) {
        sysLogService.delete(logId);
        return ResponseResult.SUCCESS();
    }
}
