package com.thtf.base.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thtf.base.api.model.SysLog;
import com.thtf.base.api.vo.LogQueryConditionVO;
import com.thtf.base.api.vo.LogSaveOrUpdateVO;
import com.thtf.base.api.vo.LogVO;
import com.thtf.base.server.enums.BaseServerCode;
import com.thtf.base.server.mapper.SysLogMapper;
import com.thtf.base.server.service.SysLogService;
import com.thtf.common.core.exception.ExceptionCast;
import com.thtf.common.core.response.CommonCode;
import com.thtf.common.core.response.Pager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ---------------------------
 * 系统日志service实现类
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019/12/27 15:44
 * 版本：  v1.0
 * ---------------------------
 */
@Slf4j
@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public Pager<LogVO> findList(int pages, int size, LogQueryConditionVO queryConditionVO) {
        if (queryConditionVO == null) {
            queryConditionVO = new LogQueryConditionVO();
        }
        // 查询条件
        LambdaQueryWrapper<SysLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(queryConditionVO.getType() != null, SysLog::getType, queryConditionVO.getType())
                .eq(queryConditionVO.getOperateType() != null, SysLog::getOperateType, queryConditionVO.getOperateType())
                .gt(queryConditionVO.getStartTime() != null, SysLog::getStartTime, queryConditionVO.getStartTime())
                .lt(queryConditionVO.getFinishTime() != null, SysLog::getFinishTime, queryConditionVO.getFinishTime())
                .like(StringUtils.isNotBlank(queryConditionVO.getUserName()), SysLog::getUserName, queryConditionVO.getUserName());
        // 分页条件
        IPage<SysLog> page = sysLogMapper.selectPage(new Page(pages, size), queryWrapper);

        long total = page.getTotal();
        List<SysLog> records = page.getRecords();
        // 转换VO对象
        List<LogVO> logVOList = new ArrayList<>(pages);
        records.forEach(record -> {
            LogVO logVO = new LogVO();
            BeanUtils.copyProperties(record, logVO);
            logVOList.add(logVO);
        });
        // 返回结果
        return new Pager(total, logVOList);
    }

    @Override
    public LogVO add(LogSaveOrUpdateVO logSaveOrUpdateVO) {
        if (logSaveOrUpdateVO == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        // VO转换为Model
        SysLog sysLog = new SysLog();
        BeanUtils.copyProperties(logSaveOrUpdateVO, sysLog);
        sysLog.setId(null);
        int row = sysLogMapper.insert(sysLog);
        if (row != 1) {
            ExceptionCast.cast(BaseServerCode.SAVE_ERROR);
        }
        // 返回保存后对象
        LogVO logVO = new LogVO();
        BeanUtils.copyProperties(sysLog, logVO);
        return logVO;
    }

    @Override
    public void delete(String logId) {
        if (StringUtils.isBlank(logId)) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        int row = sysLogMapper.deleteById(logId);
        if (row != 1) {
            ExceptionCast.cast(BaseServerCode.DELETE_ERROR);
        }
    }
}
