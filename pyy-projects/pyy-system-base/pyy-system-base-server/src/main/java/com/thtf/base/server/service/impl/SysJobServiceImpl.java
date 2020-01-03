package com.thtf.base.server.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thtf.base.api.model.SysJob;
import com.thtf.base.api.vo.SysJobQueryConditionVO;
import com.thtf.base.api.vo.SysJobSaveOrUpdateVO;
import com.thtf.base.api.vo.SysJobVO;
import com.thtf.base.server.enums.BaseServerCode;
import com.thtf.base.server.mapper.SysJobMapper;
import com.thtf.base.server.service.SysJobService;
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
 * 岗位 (SysJobServiceImpl)         
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-01-03 16:01:11
 * 版本：  v1.0
 * ---------------------------
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
public class SysJobServiceImpl implements SysJobService {

	@Autowired
	private SysJobMapper sysJobMapper;

    /**
     * 岗位保存
     * @param sysJobSaveOrUpdateVO
     */
	@Override
	public SysJobVO save(SysJobSaveOrUpdateVO sysJobSaveOrUpdateVO) {
        // 初始化Model对象
        SysJob sysJob = new SysJob();
        // 属性赋值
        BeanUtils.copyProperties(sysJobSaveOrUpdateVO, sysJob);
        sysJob.setId(null); // 确保ID为null，默认使用mybatis-plus ID生成策略
        // 执行保存
        int row = sysJobMapper.insert(sysJob);
        if (row != 1) {
            ExceptionCast.cast(BaseServerCode.SAVE_ERROR);
        }
        // 转换为VO对象
        SysJobVO sysJobVO = new SysJobVO();
        BeanUtils.copyProperties(sysJob, sysJobVO);
        log.info("### 岗位保存完毕 ###");
        // 返回保存后结果
        return sysJobVO;
	}

    /**
     * 岗位删除
     * @param id
     */
    @Override
    public void delete(String id) {
        // 参数校验
        if (StringUtils.isBlank(id)) {
           ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        // 根据ID查询岗位
        SysJob sysJob = sysJobMapper.selectById(id);
        if (sysJob == null) {
            ExceptionCast.cast(BaseServerCode.RESULT_DATA_NONE);
        }
        // 执行删除
        int row = sysJobMapper.deleteById(id);
        if (row != 1) {
           ExceptionCast.cast(BaseServerCode.DELETE_ERROR);
        }
        log.info("### 岗位删除完毕 ###");
    }

	/**
     * 岗位修改
     * @param id
     * @param sysJobSaveOrUpdateVO
     */
    @Override
    public SysJobVO update(String id, SysJobSaveOrUpdateVO sysJobSaveOrUpdateVO) {
        // 参数校验
        if (StringUtils.isBlank(id) || sysJobSaveOrUpdateVO == null) {
           ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        // 根据ID查询岗位
        SysJob sysJob = sysJobMapper.selectById(id);
        if (sysJob == null) {
            ExceptionCast.cast(BaseServerCode.RESULT_DATA_NONE);
        }

        // 属性赋值
        BeanUtils.copyProperties(sysJobSaveOrUpdateVO, sysJob);
        sysJob.setId(id);
        // 执行修改
        int row = sysJobMapper.updateById(sysJob);
        if (row != 1) {
           ExceptionCast.cast(BaseServerCode.DELETE_ERROR);
        }
        // 转换为VO对象
        SysJobVO sysJobVO = new SysJobVO();
        BeanUtils.copyProperties(sysJob, sysJobVO);
         log.info("### 岗位修改完毕 ###");
        // 返回保存后结果
        return sysJobVO;
    }

   /**
    * 根据岗位ID查询
    * @param id
    */
	@Override
	public SysJobVO findById(String id) {
	    // 参数校验
        if (StringUtils.isBlank(id)) {
           ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        // 根据ID查询岗位
		SysJob sysJob = sysJobMapper.selectById(id);
		if (sysJob == null) {
            return null;
        }
        // 转换为VO对象
        SysJobVO sysJobVO = new SysJobVO();
        BeanUtils.copyProperties(sysJob, sysJobVO);
        log.info("### 岗位查询完毕 ###");
        // 返回保存后结果
        return sysJobVO;
	}

   /**
     * 岗位模糊查询
     * @param queryConditionVO
     * @return
     */
    @Override
    public List<SysJobVO> findList(SysJobQueryConditionVO queryConditionVO) {
        // 参数校验
        if (queryConditionVO == null) {
          queryConditionVO = new SysJobQueryConditionVO();
        }
        // 查询条件
        LambdaQueryWrapper<SysJob> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(queryConditionVO.getName()), SysJob::getName, queryConditionVO.getName());
        // 执行查询
        List<SysJob> sysJobList = sysJobMapper.selectList(queryWrapper);
        log.info("### 岗位Model模糊查询完毕，总条数：{}条###", sysJobList.size());
        // 岗位转换VO数据
        List<SysJobVO> sysJobVOList = new ArrayList<>();
        sysJobList.forEach(sysJob -> {
            SysJobVO sysJobVO = new SysJobVO();
            BeanUtils.copyProperties(sysJob, sysJobVO);
            sysJobVOList.add(sysJobVO);
        });
        log.info("### 岗位转换VO数据完毕###");
        return sysJobVOList;
    }

    /**
     * 岗位分页模糊查询
     * @param queryConditionVO
     * @param page
     * @param size
     * @return
     */
	@Override
    public Pager<SysJobVO> findList(SysJobQueryConditionVO queryConditionVO, int page, int size) {
        // 参数校验
        if (queryConditionVO == null) {
          queryConditionVO = new SysJobQueryConditionVO();
        }
        // 查询条件
        LambdaQueryWrapper<SysJob> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(queryConditionVO.getName()), SysJob::getName, queryConditionVO.getName());
        // 分页条件
        Page<SysJob> pageInfo = new Page(page, size);
        // 执行查询
        IPage<SysJob> sysJobPage = sysJobMapper.selectPage(pageInfo, queryWrapper);
        long total = sysJobPage.getTotal();
        List<SysJob> sysJobList = sysJobPage.getRecords();
        // 岗位转换VO数据
        List<SysJobVO> sysJobVOList = new ArrayList<>();
        sysJobList.forEach(sysJob -> {
            SysJobVO sysJobVO = new SysJobVO();
            BeanUtils.copyProperties(sysJob, sysJobVO);
            sysJobVOList.add(sysJobVO);
        });
        log.info("### 岗位转换VO数据完毕###");
        // 分装分页查询结果
        return new Pager(total, sysJobVOList);
    }
}
