package com.thtf.base.api.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

/**
 * ---------------------------
 * 公共model字段
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019-12-31 16:10:54
 * 版本：  v1.0
 * ---------------------------
 */
@Data
@ApiModel(value = "CommonModel",description = "通用model类")
public class CommonModel {

    /** 创建人ID */
    @TableField(fill = FieldFill.INSERT)
    private String createId;

    /** 创建人名称 */
    @TableField(fill = FieldFill.INSERT)
    private String createName;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** 修改人编码 */
    @TableField(fill = FieldFill.UPDATE)
    private String updateId;

    /** 修改人名称 */
    @TableField(fill = FieldFill.UPDATE)
    private String updateName;

    /** 修改时间 */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    /** 删除标记 */
    private Integer deletedFlag = 0;
}