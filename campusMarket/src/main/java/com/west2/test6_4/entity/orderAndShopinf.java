package com.west2.test6_4.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "orderAndShopinf对象", description = "")
public class orderAndShopinf {

    @ApiModelProperty(value = "订单类对象"   )
    private TOrderlist tOrderlist;

    @ApiModelProperty(value = "商品类对象" )
    private TShopinf tShopinf;


    public orderAndShopinf(TOrderlist tOrderlist, TShopinf tShopinf) {
        this.tOrderlist = tOrderlist;
        this.tShopinf = tShopinf;
    }

    public TOrderlist gettOrderlist() {
        return tOrderlist;
    }

    public void settOrderlist(TOrderlist tOrderlist) {
        this.tOrderlist = tOrderlist;
    }

    public TShopinf gettShopinf() {
        return tShopinf;
    }

    public void settShopinf(TShopinf tShopinf) {
        this.tShopinf = tShopinf;
    }
}
