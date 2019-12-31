package com.thtf.base.server.service;

import com.thtf.base.api.vo.*;
import com.thtf.common.core.response.Pager;
import com.thtf.common.core.response.ResponseResult;

import java.util.List;

/**
 * ---------------------------
 * 部门 (DeptService)         
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019-12-31 16:10:54
 * 版本：  v1.0
 * ---------------------------
 */
public interface DeptService {

    /**
     * 部门保存
     * @param sysDeptSaveOrUpdateVO
     */
    DeptVO save(DeptSaveOrUpdateVO sysDeptSaveOrUpdateVO);

	/**
     * 部门修改
     * @param id
     * @param sysDeptSaveOrUpdateVO
     */
    DeptVO update(String id, DeptSaveOrUpdateVO sysDeptSaveOrUpdateVO);

    /**
     * 部门删除
     * @param id
     */
    void delete(String id);

    /**
     * 根据部门ID查询
     * @param id
     */
    DeptVO findById(String id);

    /**
     * 部门模糊查询
     * @param queryConditionVO
     * @return
     */
    List<DeptVO> findList(DeptQueryConditionVO queryConditionVO);

    /**
     * 部门分页模糊查询
     * @param queryConditionVO
     * @param page
     * @param size
     * @return
     */
    Pager<DeptVO> findList(DeptQueryConditionVO queryConditionVO, Integer page, Integer size);
}
