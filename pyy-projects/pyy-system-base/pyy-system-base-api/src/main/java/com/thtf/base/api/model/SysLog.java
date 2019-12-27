package com.thtf.base.api.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统操作日志
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_log")
public class SysLog implements Serializable {

    private static final long serialVersionUID = 8170949026245351562L;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 操作IP
     */
    private String requestIp;

    /**
     * 数据类型 1 操作记录 2异常记录
     */
    private Integer type;

    /**
     * 操作人ID
     */
    private String userId;

    /**
     * 操作人ID
     */
    private String userName;

    /**
     * 操作描述
     */
    private String description;

    /**
     * 请求方法
     */
    private String actionMethod;

    /**
     * 请求url
     */
    private String actionUrl;

    /**
     * 请求参数
     */
    private String params;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 类路径
     */
    private String classPath;

    /**
     * 请求方法
     */
    private String requestMethod;

    /**
     * 操作类型（1查询/获取，2添加，3修改，4删除）
     */
    private Integer operateType;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 完成时间
     */
    private LocalDateTime finishTime;

    /**
     * 消耗时间
     */
    private Long consumingTime;

    /**
     * 异常详情信息 堆栈信息
     */
    private String exDetail;

    /**
     * 异常描述 e.getMessage
     */
    private String exDesc;


}
