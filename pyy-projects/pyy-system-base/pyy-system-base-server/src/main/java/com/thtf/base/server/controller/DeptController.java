package com.thtf.base.server.controller;

import com.thtf.base.api.DeptControllerApi;
import com.thtf.base.api.vo.DeptQueryConditionVO;
import com.thtf.base.api.vo.DeptSaveOrUpdateVO;
import com.thtf.base.api.vo.DeptVO;
import com.thtf.base.server.service.DeptService;
import com.thtf.common.core.response.Pager;
import com.thtf.common.core.response.ResponseResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
@Api(tags = "DeptController", description = "部门相关接口,提供增删改查功能")
@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
public class DeptController implements DeptControllerApi {

	@Autowired
	private DeptService sysDeptService;

	@Override
    public ResponseResult<DeptVO> save(DeptSaveOrUpdateVO record) {
        return ResponseResult.SUCCESS(sysDeptService.save(record));
    }

    @Override
    public ResponseResult<DeptVO> update(String id, DeptSaveOrUpdateVO record) {
        return ResponseResult.SUCCESS(sysDeptService.update(id, record));
    }

    @Override
    public ResponseResult delete(String id) {
        sysDeptService.delete(id);
        return ResponseResult.SUCCESS();
    }

    @Override
    public ResponseResult<DeptVO> findById(String id) {
        return ResponseResult.SUCCESS(sysDeptService.findById(id));
    }

    @Override
    public ResponseResult<List<DeptVO>> getList(DeptQueryConditionVO queryConditionVO) {
        return ResponseResult.SUCCESS(sysDeptService.findList(queryConditionVO));
    }

    @Override
    public ResponseResult<Pager<DeptVO>> getPageList(DeptQueryConditionVO queryConditionVO, int page, int size) {
        return ResponseResult.SUCCESS(sysDeptService.findList(queryConditionVO, page, size));
    }
	
}
