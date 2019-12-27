package com.thtf.base.api.feign;

import com.thtf.base.api.vo.SysLogQueryConditionVO;
import com.thtf.base.api.vo.SysLogSaveOrUpdateVO;
import com.thtf.base.api.vo.SysLogVO;
import com.thtf.base.api.feign.factory.SysLogFallbackFactory;
import com.thtf.common.core.response.Pager;
import com.thtf.common.core.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * ---------------------------
 * 操作日志ControllerApi接口
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019/12/27 11:55
 * 版本：  v1.0
 * ---------------------------
 */
@Api(value="操作日志管理接口", description = "操作日志管理接口，提供操作的管理、查询")
@RequestMapping("/v1")
@FeignClient(contextId = "sysLogControllerApi", value = "pyy-system-base-server", fallbackFactory = SysLogFallbackFactory.class)
public interface SysLogControllerApi {


    @GetMapping("/sysLog/{page}/{size}")
    ResponseResult<Pager<SysLogVO>> findList(@PathVariable("page")int page, @PathVariable("size")int size, SysLogQueryConditionVO queryConditionVO);

    @ApiOperation("保存日志信息")
    @PostMapping("/sysLog")
    ResponseResult<SysLogVO> add(@RequestBody SysLogSaveOrUpdateVO sysLogSaveOrUpdateVO);

    @ApiOperation("根据id删除日志")
    @DeleteMapping("/sysLog/{logId}")
    ResponseResult delete(@PathVariable("logId") String logId);
}
