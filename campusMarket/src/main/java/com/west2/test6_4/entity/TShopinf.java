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
 *
 * @author xiaoge
 * @since 2022-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("T_shopinf")
@ApiModel(value = "TShopinf对象", description = "")
public class TShopinf implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品ID", example = "1")
    @TableId(value = "PK_SID", type = IdType.AUTO)
    private Integer pkSid;

    @ApiModelProperty(value = "售价", example = "300.00")
    @TableField("Sprice")
    private Float sprice;

    @ApiModelProperty(value = "商品简介", example = "这是一本书")
    @TableField("Sintroduction")
    private String sintroduction;

    @ApiModelProperty(value = "照片的URL", example = "http://47.107.225.197:8080/sk/image/demo.jpg")
    @TableField("Sphoto")
    private String sphoto;

    @ApiModelProperty(value = "商品名", example = "笔记本")
    @TableField("Sname")
    private String sname;

    @ApiModelProperty(value = "商品状态", example = " 订单状态:  0 默认状态（未审核） ;1 审核通过 ; 3 已经被卖家下架; 4 审核不通过; 5 已经被购买")
    @TableField("Sstate")
    private Integer state;

    @ApiModelProperty(value = "卖家ID", example = "1")
    @TableField("S_UID")
    private Integer suid;

    @ApiModelProperty(value = "商品上架时间", example = "")
    @TableField("set_Time")
    private Date setTime;

}
