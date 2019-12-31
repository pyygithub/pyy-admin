package com.thtf.base.api;

import com.thtf.base.api.vo.DeptQueryConditionVO;
import com.thtf.base.api.vo.DeptSaveOrUpdateVO;
import com.thtf.base.api.vo.DeptVO;
import com.thtf.common.core.response.Pager;
import com.thtf.common.core.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * ---------------------------
 * 部门管理Controller接口
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019/12/31 16:47
 * 版本：  v1.0
 * ---------------------------
 */
@Api(value="部门管理接口", description = "部门管理接口，提供部门的增删改查操作")
@RequestMapping("/v1")
public interface DeptControllerApi {
    /**
     * 保存部门
     * @param record
     * @return
     */
    @ApiOperation(value = "保存部门", notes = "创建新部门")
    @ApiImplicitParam(name = "record", value = "部门对象", required = true, dataType = "DeptSaveOrUpdateVO", paramType = "body")
    @PostMapping("/dept")
    ResponseResult<DeptVO> save(@Valid @RequestBody DeptSaveOrUpdateVO record);

    /**
     * 修改部门
     * @param id
     * @param record
     * @return
     */
    @ApiOperation(value = "修改部门", notes = "根据ID修改部门")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "部门ID", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "record", value = "部门对象", required = true, dataType = "DeptSaveOrUpdateVO", paramType = "body")
    })
    @PutMapping("/dept/{id}")
    ResponseResult<DeptVO> update(@Valid @PathVariable(value = "id") String id, @RequestBody DeptSaveOrUpdateVO record);

    /**
     * 删除部门
     * @param id
     * @return
     */
    @ApiOperation(value = "删除部门", notes = "根据ID部门")
    @ApiImplicitParam(name = "id", value = "部门ID", required = true, dataType = "String", paramType = "path")
    @DeleteMapping("/dept/{id}")
    ResponseResult delete(@Valid @PathVariable(value = "id") String id);

    /**
     * 单个部门查询
     * @param id
     * @return
     */
    @ApiOperation(value = "部门查询", notes = "根据ID部门查询")
    @ApiImplicitParam(name = "id", value = "部门ID", required = true, dataType = "String", paramType = "path")
    @GetMapping("/dept/{id}")
    ResponseResult<DeptVO> findById(@Valid @PathVariable("id") String id);

    /**
     * 部门模糊查询
     * @param queryConditionVO
     * @return
     */
    @ApiOperation(value = "部门模糊查询", notes = "部门不带分页模糊查询")
    @GetMapping("/depts")
    ResponseResult<List<DeptVO>> getList(DeptQueryConditionVO queryConditionVO);

    /**
     * 部门分页查询
     * @param queryConditionVO
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "部门分页查询", notes = "部门分页模糊查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "分页尺寸", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping("/depts/page")
    ResponseResult<Pager<DeptVO>> getPageList(DeptQueryConditionVO queryConditionVO,
                                              @RequestParam(value = "page", defaultValue = "1") int page,
                                              @RequestParam(value = "size", defaultValue = "10") int size);
}
