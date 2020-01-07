package com.thtf.base.server.controller;

import com.thtf.base.api.SysMenuControllerApi;
import com.thtf.base.api.vo.SysMenuQueryConditionVO;
import com.thtf.base.api.vo.SysMenuSaveOrUpdateVO;
import com.thtf.base.api.vo.SysMenuTreeVO;
import com.thtf.base.api.vo.SysMenuVO;
import com.thtf.base.server.service.SysMenuService;
import com.thtf.common.core.response.Pager;
import com.thtf.common.core.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * ---------------------------
 * 菜单 (SysMenuController)         
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-01-07 11:13:01
 * 版本：  v1.0
 * ---------------------------
 */
@RestController
public class SysMenuController implements SysMenuControllerApi {

	@Autowired
	private SysMenuService sysMenuService;

    @Override
    public ResponseResult<SysMenuVO> save(SysMenuSaveOrUpdateVO record) {
        return ResponseResult.SUCCESS(sysMenuService.save(record));
    }

    @Override
    public ResponseResult<SysMenuVO> update(String id, SysMenuSaveOrUpdateVO record) {
        return ResponseResult.SUCCESS(sysMenuService.update(id, record));
    }

    @Override
    public ResponseResult delete(String id) {
        sysMenuService.delete(id);
        return ResponseResult.SUCCESS();
    }

    @Override
    public ResponseResult<SysMenuVO> findById(String id) {
        return ResponseResult.SUCCESS(sysMenuService.findById(id));
    }

    @Override
    public ResponseResult<List<SysMenuTreeVO>> getList(SysMenuQueryConditionVO queryConditionVO) {
        return ResponseResult.SUCCESS(sysMenuService.findTreeList(queryConditionVO));
    }

}
