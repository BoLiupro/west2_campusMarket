package com.west2.test6_4.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
@TableName("T_admin")
@ApiModel(value = "TAdmin对象", description = "")
public class TAdmin implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "管理员ID", example = "1")
    @TableId(value = "PK_AID", type = IdType.AUTO)
    private Integer pkAid;

    @ApiModelProperty(value = "管理员名称", example = "root")
    @TableField("Aname")
    private String aname;

    @ApiModelProperty(value = "管理员密码", example = "123456")
    @TableField("Apassword")
    private String apassword;

    @ApiModelProperty(value = "管理员住址", example = "1号楼111")
    @TableField("Aaddress")
    private String aaddress;

    @ApiModelProperty(value = "管理员职工号", example = "20020202")
    @TableField("Anumber")
    private String anumber;

    @ApiModelProperty(value = "管理员手机号", example = "13312344321")
    @TableField("Aphone")
    private String aphone;
}
