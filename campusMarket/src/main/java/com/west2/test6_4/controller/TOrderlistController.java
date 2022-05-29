package com.west2.test6_4.controller;

import com.west2.test6_4.entity.TOrderlist;
import com.west2.test6_4.entity.TShopinf;
import com.west2.test6_4.entity.orderAndShopinf;
import com.west2.test6_4.mapper.TOrderlistMapper;
import com.west2.test6_4.mapper.TShopinfMapper;
import com.west2.test6_4.mapper.TUserMapper;
import com.west2.test6_4.response.Result;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static com.west2.test6_4.config.SwaggerConfig.TAG_5;


//订单状态
//0 正常默认状态
//1 用户成功支付
//2 已经被别人支付，订单失效
//3 商品已经下架，订单失效
//9 订单被用户移除出购物车

/**
 * <p>
 * 前端控制器
 * </p>
 */
@RestController
@RequestMapping(value = "/orderlist", method = {RequestMethod.GET, RequestMethod.POST})
@Api(value = "TOrderlistController", tags = TAG_5)
public class TOrderlistController {

    @Autowired
    TOrderlistMapper tOrderlistMapper;

    @Autowired
    TShopinfMapper tShopinfMapper;

    @Autowired
    TUserMapper tUserMapper;

    //    @ApiOperation("创建上架商品订单")
//    @ApiResponses({
//            @ApiResponse(code=200,message = "",response = TShopinf.class),
//    })
//    @RequestMapping("/sell")
//    public Result sell(@ApiParam("卖家的UID") @RequestParam("O_sellerUID") int O_sellerUID, @ApiParam("商品的ID") @RequestParam("O_SID") int O_SID) {
//        System.out.println("fdsfdsa");
//        tOrderlistMapper.sell(O_sellerUID, O_SID, 0);//0是未修改，1是修改，-1是取消，2是完成
//        Result result = Result.ok().message("success");
//        TShopinf tShopinf = tShopinfMapper.getOneShopinfByID(O_SID);
//        result.data("goods", tShopinf);
//        return result;
//    }
    @ApiOperation("添加到购物车")
    @RequestMapping("/add2Cart")
    public Result add2Cart(@ApiParam("商品的SID") @RequestParam("O_SID") int O_SID, @ApiParam("买家的UID") @RequestParam("O_buyerUID") int O_buyerUID) {
        TOrderlist oneOrder = tOrderlistMapper.getOneOrder(O_buyerUID, O_SID);
        Result result;
        if (oneOrder == null) {
            TOrderlist tOrderlist = new TOrderlist();
            tOrderlist.setSid(O_SID);
            tOrderlist.setBuyerUid(O_buyerUID);
            TShopinf tShopinf = tShopinfMapper.getOneShopinfByID(O_SID);
            Integer Selleruid = tShopinf.getSuid();
            tOrderlist.setSellerUid(Selleruid);
            tOrderlist.setState(0);
            tOrderlistMapper.insert(tOrderlist);
        }else {
            Integer PK_OID = oneOrder.getPkOid();
            tOrderlistMapper.setOrderlist(PK_OID, 0);
        }
        result = Result.ok().message("success");
        return result;
    }

    @ApiOperation("从购物车移除")
    @RequestMapping("/deleteFromCart")
    public Result deleteByID(@ApiParam("商品的SID") @RequestParam("O_SID") int O_SID, @ApiParam("买家的UID") @RequestParam("O_buyerUID") int O_buyerUID) {
        TOrderlist oneOrder = tOrderlistMapper.getOneOrder(O_buyerUID, O_SID);
        tOrderlistMapper.setOrderlist(oneOrder.getPkOid(), 9);
        Result result = Result.ok().message("success");
        return result;
    }

//    @ApiOperation("修改订单信息（未实装）")
//    @RequestMapping("/setOrderlist")
//    public Result setOrderlist(@ApiParam("订单的ID") @RequestParam("PK_OID") int PK_OID) {
//
//        tOrderlistMapper.setOrderlist(PK_OID, 1);
//        Result result = Result.ok().message("success");
//        return result;
//    }

    @ApiOperation("结算购物车商品")
    @RequestMapping("/buy")
    public Result buy(@ApiParam("商品的SID") @RequestParam("O_SID") int O_SID, @ApiParam("买家的UID") @RequestParam("O_buyerUID") int O_buyerUID) {
        TOrderlist oneOrder = tOrderlistMapper.getOneOrder(O_buyerUID, O_SID);
        Integer PK_OID = oneOrder.getPkOid();
        Date date = new Date(System.currentTimeMillis());
        tOrderlistMapper.changeOneShop(O_SID, 2);
        tOrderlistMapper.setOrderlist(PK_OID, 1);
        tOrderlistMapper.setTime(date, PK_OID);
        tShopinfMapper.setShopState(O_SID, 5);
        Result result = Result.ok().message("success");
        return result;
    }

    @ApiOperation("根据订单ID获取一个订单")
    @RequestMapping("/getOneOrderlist")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = TOrderlist.class),
    })
    public Result getOneOrderlist(@ApiParam("订单的ID") @RequestParam("PK_OID") int PK_OID) {
        TOrderlist tOrderlist = tOrderlistMapper.selectById(PK_OID);
        int buyer = tOrderlist.getBuyerUid();
        int seller = tOrderlist.getSellerUid();
        String buyer_phone = tUserMapper.selectById(buyer).getUphone();
        String seller_phone = tUserMapper.selectById(seller).getUphone();
        Result result = Result.ok().data("order", tOrderlist);
        result.data("buyer_phone", buyer_phone);
        result.data("seller_phone", seller_phone);
        return result;
    }

    @ApiOperation("获取买家的所有订单列表，包含已经失效的订单,不包含添加后被用户主动删除的订单")
    @RequestMapping("/getOrderlists")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = orderAndShopinf.class),
    })
    public Result getOrderlists(@ApiParam("买家的用户ID") @RequestParam("O_buyerUID") int O_buyerUID) {
        List<TOrderlist> tOrderlist = tOrderlistMapper.selectList(O_buyerUID);
        Iterator<TOrderlist> iterator = tOrderlist.iterator();
        List<orderAndShopinf> l1 = new ArrayList<>();
        while (iterator.hasNext()) {
            TOrderlist good = iterator.next();
            if (good.getState() == 9) {
                iterator.remove();//使用迭代器的删除方法删除
            } else {
                TShopinf oneShopinfByID = tShopinfMapper.getOneShopinfByID(good.getSid());
                l1.add(new orderAndShopinf(good, oneShopinfByID));
            }
        }
        Result result = Result.ok().data("order", l1);
        return result;
    }

    @ApiOperation("获取买家的所有有效订单")
    @RequestMapping("/getAbleOrderLists")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = orderAndShopinf.class),
    })
    public Result getAbleOrderLists(@ApiParam("买家的用户ID") @RequestParam("O_buyerUID") int O_buyerUID) {
        List<TOrderlist> tOrderlist = tOrderlistMapper.selectList(O_buyerUID);
        Iterator<TOrderlist> iterator = tOrderlist.iterator();
        List<orderAndShopinf> l1 = new ArrayList<>();
        while (iterator.hasNext()) {
            TOrderlist good = iterator.next();
            if (good.getState() != 0) {
                iterator.remove();//使用迭代器的删除方法删除
            } else {
                TShopinf oneShopinfByID = tShopinfMapper.getOneShopinfByID(good.getSid());
                l1.add(new orderAndShopinf(good, oneShopinfByID));
            }
        }
        Result result = Result.ok().data("order", l1);
        return result;
    }

    @ApiOperation("获取买家的所有成功购买的订单,order里返回订单和对应的商品")
    @RequestMapping("/getbuyOrderLists")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = orderAndShopinf.class),
    })
    public Result getbuyOrderLists(@ApiParam("买家的用户ID") @RequestParam("O_buyerUID") int O_buyerUID) {
        List<TOrderlist> tOrderlist = tOrderlistMapper.selectList(O_buyerUID);
        Iterator<TOrderlist> iterator = tOrderlist.iterator();
        List<orderAndShopinf> l1 = new ArrayList<>();
        while (iterator.hasNext()) {
            TOrderlist good = iterator.next();
            if (good.getState() != 1) {
                iterator.remove();//使用迭代器的删除方法删除
            } else {
                TShopinf oneShopinfByID = tShopinfMapper.getOneShopinfByID(good.getSid());
                l1.add(new orderAndShopinf(good, oneShopinfByID));
            }
        }
        Result result = Result.ok().data("order", l1);
        return result;
    }
}

