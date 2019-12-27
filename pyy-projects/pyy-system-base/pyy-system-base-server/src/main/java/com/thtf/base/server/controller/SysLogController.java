package com.thtf.base.server.controller;

import com.thtf.base.api.feign.SysLogControllerApi;
import com.thtf.base.api.vo.SysLogQueryConditionVO;
import com.thtf.base.api.vo.SysLogSaveOrUpdateVO;
import com.thtf.base.api.vo.SysLogVO;
import com.thtf.base.server.service.SysLogService;
import com.thtf.common.core.response.Pager;
import com.thtf.common.core.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(value="操作日志管理接口", description = "操作日志管理接口，提供操作的管理、查询")
@RestController
public class SysLogController implements SysLogControllerApi {

    @Autowired
    private SysLogService sysLogService;

    @ApiOperation("分页查询日志列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码", required=true, paramType="path", dataType="int"),
            @ApiImplicitParam(name="size",value = "每页记录数", required=true, paramType="path", dataType="int")
    })
    @Override
    public ResponseResult<Pager<SysLogVO>> findList(int page, int size, SysLogQueryConditionVO queryConditionVO) {
        return ResponseResult.SUCCESS(sysLogService.findList(page, size, queryConditionVO));
    }

    @ApiOperation("保存日志信息")
    @Override
    public ResponseResult<SysLogVO> add(SysLogSaveOrUpdateVO sysLogSaveOrUpdateVO) {
        return ResponseResult.SUCCESS(sysLogService.add(sysLogSaveOrUpdateVO));
    }

    @ApiOperation("根据id删除日志")
    @Override
    public ResponseResult delete(String logId) {
        sysLogService.delete(logId);
        return ResponseResult.SUCCESS();
    }
}
