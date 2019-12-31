package com.thtf.base.server.service;

import com.thtf.base.api.vo.LogQueryConditionVO;
import com.thtf.base.api.vo.LogSaveOrUpdateVO;
import com.thtf.base.api.vo.LogVO;
import com.thtf.common.core.response.Pager;

/**
 * ---------------------------
 * 系统日志Service接口
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019/12/27 15:44
 * 版本：  v1.0
 * ---------------------------
 */
public interface SysLogService {

    Pager<LogVO> findList(int page, int size, LogQueryConditionVO queryConditionVO);

    LogVO add(LogSaveOrUpdateVO logSaveOrUpdateVO);

    void delete(String logId);

}
