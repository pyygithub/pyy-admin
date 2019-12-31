package com.thtf.base.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thtf.base.api.model.Dept;
import com.thtf.base.api.vo.DeptQueryConditionVO;
import com.thtf.base.api.vo.DeptSaveOrUpdateVO;
import com.thtf.base.api.vo.DeptVO;
import com.thtf.base.server.enums.BaseServerCode;
import com.thtf.base.server.mapper.DeptMapper;
import com.thtf.base.server.service.DeptService;
import com.thtf.common.core.exception.ExceptionCast;
import com.thtf.common.core.response.CommonCode;
import com.thtf.common.core.response.Pager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * ---------------------------
 * 部门 (DeptServiceImpl)         
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019-12-31 16:10:54
 * 版本：  v1.0
 * ---------------------------
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptMapper deptMapper;

    /**
     * 部门保存
     * @param deptSaveOrUpdateVO
     */
	@Override
	public DeptVO save(DeptSaveOrUpdateVO deptSaveOrUpdateVO) {
        // 初始化Model对象
        Dept dept = new Dept();
        // 属性赋值
        BeanUtils.copyProperties(deptSaveOrUpdateVO, dept);
        dept.setId(null); // 确保ID为null，默认使用mybatis-plus ID生成策略
        // 执行保存
        int row = deptMapper.insert(dept);
        if (row != 1) {
            ExceptionCast.cast(BaseServerCode.SAVE_ERROR);
        }
        // 转换为VO对象
        DeptVO deptVO = new DeptVO();
        BeanUtils.copyProperties(dept, deptVO);
        log.info("### 部门保存完毕 ###");
        // 返回保存后结果
        return deptVO;
	}

    /**
     * 部门删除
     * @param id
     */
    @Override
    public void delete(String id) {
        // 参数校验
        if (StringUtils.isBlank(id)) {
           ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        // 根据ID查询部门
        Dept dept = deptMapper.selectById(id);
        if (dept == null) {
            ExceptionCast.cast(BaseServerCode.RESULT_DATA_NONE);
        }
        // 执行删除
        int row = deptMapper.deleteById(id);
        if (row != 1) {
           ExceptionCast.cast(BaseServerCode.DELETE_ERROR);
        }
        log.info("### 部门删除完毕 ###");
    }

	/**
     * 部门修改
     * @param id
     * @param deptSaveOrUpdateVO
     */
    @Override
    public DeptVO update(String id, DeptSaveOrUpdateVO deptSaveOrUpdateVO) {
        // 参数校验
        if (StringUtils.isBlank(id) || deptSaveOrUpdateVO == null) {
           ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        // 根据ID查询部门
        Dept dept = deptMapper.selectById(id);
        if (dept == null) {
            ExceptionCast.cast(BaseServerCode.RESULT_DATA_NONE);
        }
        // 属性赋值
        BeanUtils.copyProperties(deptSaveOrUpdateVO, dept);
        dept.setId(id);
        // 执行修改
        int row = deptMapper.updateById(dept);
        if (row != 1) {
           ExceptionCast.cast(BaseServerCode.DELETE_ERROR);
        }
        // 转换为VO对象
        DeptVO deptVO = new DeptVO();
        BeanUtils.copyProperties(dept, deptVO);
         log.info("### 部门修改完毕 ###");
        // 返回保存后结果
        return deptVO;
    }

   /**
    * 根据部门ID查询
    * @param id
    */
	@Override
	public DeptVO findById(String id) {
	    // 参数校验
        if (StringUtils.isBlank(id)) {
           ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        // 根据ID查询部门
		Dept dept = deptMapper.selectById(id);
		if (dept == null) {
            return null;
        }
        // 转换为VO对象
        DeptVO deptVO = new DeptVO();
        BeanUtils.copyProperties(dept, deptVO);
        log.info("### 部门查询完毕 ###");
        // 返回保存后结果
        return deptVO;
	}

   /**
     * 部门模糊查询
     * @param queryConditionVO
     * @return
     */
    @Override
    public List<DeptVO> findList(DeptQueryConditionVO queryConditionVO) {
        // 参数校验
        if (queryConditionVO == null) {
          queryConditionVO = new DeptQueryConditionVO();
        }
        // 查询条件
        LambdaQueryWrapper<Dept> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(queryConditionVO.getName()), Dept::getName, queryConditionVO.getName());
        // 执行查询
        List<Dept> deptList = deptMapper.selectList(queryWrapper);
        log.info("### 部门Model模糊查询完毕，总条数：{}条###", deptList.size());
        // 部门转换VO数据
        List<DeptVO> deptVOList = new ArrayList<>();
        deptList.forEach(dept -> {
            DeptVO deptVO = new DeptVO();
            BeanUtils.copyProperties(dept, deptVO);
            deptVOList.add(deptVO);
        });
        log.info("### 部门转换VO数据完毕###");
        return deptVOList;
    }

    /**
     * 部门分页模糊查询
     * @param queryConditionVO
     * @param page
     * @param size
     * @return
     */
	@Override
    public Pager<DeptVO> findList(DeptQueryConditionVO queryConditionVO, Integer page, Integer size) {
        // 参数校验
        if (queryConditionVO == null) {
            queryConditionVO = new DeptQueryConditionVO();
        }
        // 查询条件
        LambdaQueryWrapper<Dept> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(queryConditionVO.getName()), Dept::getName, queryConditionVO.getName());
        // 分页条件
        Page<Dept> pageInfo = new Page(page, size);
        // 执行查询
        IPage<Dept> deptPage = deptMapper.selectPage(pageInfo, queryWrapper);
        long total = deptPage.getTotal();
        List<Dept> deptList = deptPage.getRecords();
        // 部门转换VO数据
        List<DeptVO> deptVOList = new ArrayList<>();
        deptList.forEach(dept -> {
            DeptVO deptVO = new DeptVO();
            BeanUtils.copyProperties(dept, deptVO);
            deptVOList.add(deptVO);
        });
        log.info("### 部门转换VO数据完毕###");
        // 分装分页查询结果
        return new Pager(total, deptVOList);
    }
}
