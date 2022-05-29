package com.west2.test6_4.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2022-04-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("T_role")
@ApiModel(value="TRole对象", description="")
public class TRole implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    private String role;

    private static final long serialVersionUID = 1L;

}
