package com.west2.test6_4.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("T_user")
@ApiModel(value = "TUser对象", description = "")
public class TUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID", example = "1")
    @TableId(value = "PK_UID", type = IdType.AUTO)
    private Integer pkUid;

    @ApiModelProperty(value = "学工号", example = "1")
    @TableField("Unumber")
    private String unumber;

    @ApiModelProperty(value = "姓名", example = "1")
    @TableField("Uname")
    private String uname;

    @ApiModelProperty(value = "密码", example = "1")
    @TableField("Upassword")
    private String upassword;

    @ApiModelProperty(value = "电话", example = "1")
    @TableField("Uphone")
    private String uphone;

    @ApiModelProperty(value = "用户简介", example = "1")
    @TableField("Uintroduction")
    private String Uintroduction;

    @ApiModelProperty(value = "地址", example = "1")
    @TableField("Uaddress")
    private String uaddress;

    @ApiModelProperty(value = "信用等级", example = "1")
    @TableField("Ulevel")
    private Integer ulevel;

    @ApiModelProperty(value = "状态", example = "0是默认用户状态，1是已经被拉黑的用户状态，9是管理员状态")
    @TableField("Ustate")
    private Integer ustate;

    @ApiModelProperty(value = "登记时间", example = "1")
    @TableField("set_Time")
    private Date setTime;
}
