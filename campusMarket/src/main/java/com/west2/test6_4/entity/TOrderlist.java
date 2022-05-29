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
 * @since 2022-04-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("T_orderlist")
@ApiModel(value="TOrderlist对象", description="")
public class TOrderlist implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单ID", example = "1")
    @TableId(value = "PK_OID", type = IdType.AUTO)
    private Integer pkOid;

    @ApiModelProperty(value = "卖家ID", example = "21")
    @TableField("O_sellerUID")
    private Integer sellerUid;

    @ApiModelProperty(value = "买家ID", example = "1")
    @TableField("O_buyerUID")
    private Integer buyerUid;

    @ApiModelProperty(value = "订单对应的商品ID", example = "1")
    @TableField("O_SID")
    private Integer sid;

    @ApiModelProperty(value = "订单状态", example = "订单状态:  0 正常默认状态 ;2 已经被购买，订单失效 ; 3 商品已经下架，订单失效; 8 用户成功支付  ;9 订单被用户移除出购物车")
    @TableField("O_state")
    private Integer state;

    @ApiModelProperty(value = "订单完成时间", example = "")
    @TableField("O_sellTime")
    private Date sellTime;

    @ApiModelProperty(value = "订单创建时间", example = "")
    @TableField("set_Time")
    private Date setTime;


}
