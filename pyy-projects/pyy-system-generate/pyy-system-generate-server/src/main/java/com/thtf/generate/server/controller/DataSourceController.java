package com.thtf.generate.server.controller;

import com.thtf.common.core.response.Pager;
import com.thtf.common.core.response.ResponseResult;
import com.thtf.common.log.annotation.SysOperateLog;
import com.thtf.generate.api.DataSourceControllerApi;
import com.thtf.generate.api.model.DataSource;
import com.thtf.generate.api.vo.DataSourceQueryConditionVO;
import com.thtf.generate.server.service.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ---------------------------
 * 代码自动生成数据源管理Controller
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019-12-13 14:30:05
 * 版本：  v1.0
 * ---------------------------
 */
@RestController
@RequestMapping("/v1")
public class DataSourceController implements DataSourceControllerApi {

    @Autowired
    private DataSourceService dataSourceService;

    @Override
    public ResponseResult<List<DataSource>> findList(DataSourceQueryConditionVO conditionVO) {
        return ResponseResult.SUCCESS(dataSourceService.findList(conditionVO));
    }

    @Override
    public ResponseResult<Pager<DataSource>> findList(int page, int size, DataSourceQueryConditionVO conditionVO) {
        return ResponseResult.SUCCESS(dataSourceService.findList(page, size, conditionVO));
    }

    @Override
    @SysOperateLog(descrption = "添加数据源")
    public ResponseResult<DataSource> add(DataSource dataSource) {
        return ResponseResult.SUCCESS(dataSourceService.add(dataSource));
    }

    @Override
    public ResponseResult<DataSource> findById(String id) {
        return ResponseResult.SUCCESS(dataSourceService.findById(id));
    }

    @Override
    @SysOperateLog(descrption = "修改数据源")
    public ResponseResult<DataSource> edit(String id, DataSource dataSource) {
        return ResponseResult.SUCCESS(dataSourceService.update(id, dataSource));
    }

    @Override
    @SysOperateLog(descrption = "删除数据源")
    public ResponseResult delete(String id) {
        dataSourceService.delete(id);
        return ResponseResult.SUCCESS();
    }
}
