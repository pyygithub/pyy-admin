package com.thtf.base.server.controller;

import com.thtf.base.api.SysDeptControllerApi;
import com.thtf.base.api.vo.SysDeptSaveOrUpdateVO;
import com.thtf.base.api.vo.SysDeptTreeVO;
import com.thtf.base.server.service.SysDeptService;
import com.thtf.common.core.response.ResponseResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ---------------------------
 * 部门 (DeptController)         
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019-12-31 16:10:54
 * 版本：  v1.0
 * ---------------------------
 */
@Api(tags = "SysSysDeptController", description = "部门相关接口,提供增删改查功能")
@RestController
public class SysSysDeptController implements SysDeptControllerApi {

	@Autowired
	private SysDeptService sysDeptService;

	@Override
    public ResponseResult<SysDeptTreeVO> save(SysDeptSaveOrUpdateVO record) {
        return ResponseResult.SUCCESS(sysDeptService.save(record));
    }

    @Override
    public ResponseResult<SysDeptTreeVO> update(String id, SysDeptSaveOrUpdateVO record) {
        return ResponseResult.SUCCESS(sysDeptService.update(id, record));
    }

    @Override
    public ResponseResult delete(String id) {
        sysDeptService.delete(id);
        return ResponseResult.SUCCESS();
    }

    @Override
    public ResponseResult<SysDeptTreeVO> findById(String id) {
        return ResponseResult.SUCCESS(sysDeptService.findById(id));
    }

    @Override
    public ResponseResult<List<SysDeptTreeVO>> getTreeList() {
        return ResponseResult.SUCCESS(sysDeptService.getTreeList());
    }


}
