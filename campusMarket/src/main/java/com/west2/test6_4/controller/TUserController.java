package com.west2.test6_4.controller;

import com.west2.test6_4.entity.TOrderlist;
import com.west2.test6_4.entity.TShopinf;
import com.west2.test6_4.entity.TUser;
import com.west2.test6_4.mapper.TOrderlistMapper;
import com.west2.test6_4.mapper.TShopinfMapper;
import com.west2.test6_4.mapper.TUserMapper;
import com.west2.test6_4.response.Result;
import com.west2.test6_4.service.dao.UploadService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Iterator;
import java.util.List;

import static com.west2.test6_4.config.SwaggerConfig.TAG_1;

/**
 * <p>
 * 前端控制器
 * </p>
 */
@RestController
@RequestMapping(value = "/user", method = {RequestMethod.GET, RequestMethod.POST})
@Api(value = "TUserController", tags = TAG_1)
public class TUserController {
    @Autowired
    TShopinfMapper tShopinfMapper;
    @Autowired
    TUserMapper tUserMapper;
    @Autowired
    TOrderlistMapper tOrderlistMapper;
    @Autowired
    private UploadService uploadService;

    @ApiOperation("按名称模糊搜索商品，只显示已经通过的商品,data里返回一个名称为“goods”，商品的list")
    @ApiResponses({
            @ApiResponse(code = 200, message = "", response = TShopinf.class),
    })
    @RequestMapping("/searchByKey")//模糊搜索
    public Result searchByKey(@ApiParam("商品名称") @RequestParam("key") String key) {
        List<TShopinf> goods = tShopinfMapper.searchGoods("%" + key + "%");
        Iterator<TShopinf> iterator = goods.iterator();
        while (iterator.hasNext()) {
            TShopinf good = iterator.next();
            if (good.getState() != 1) {
                iterator.remove();//使用迭代器的删除方法删除
            }
        }
        Result result = Result.ok().data("goods", goods);
        return result;
    }

    @ApiOperation("获得所有商品列表，只显示已经通过的商品,data里返回一个名称为“goods”，商品的list")
    @ApiResponses({
            @ApiResponse(code = 200, message = "", response = TShopinf.class),
    })
    @RequestMapping("/userGetShopinf")//查看所有商品列表
    public Result UserGetShopinf() {
        List<TShopinf> goods = tShopinfMapper.getShopinf();
        Result result;
        if (goods != null) {
            Iterator<TShopinf> iterator = goods.iterator();
            while (iterator.hasNext()) {
                TShopinf good = iterator.next();
                if (good.getState() != 1) {
                    iterator.remove();//使用迭代器的删除方法删除
                }
            }
            result = Result.ok().data("goods", goods);
        } else {
            result = Result.ok().message("没有查询到任何信息");
        }
        return result;
    }

    @ApiOperation("精准搜索,查看单个名称的商品详情页，data里返回一个名称为“goods”，商品")
    @ApiResponses({
            @ApiResponse(code = 200, message = "", response = TShopinf.class),
    })
    @RequestMapping("/userGetOneShopinf")//精准搜索,查看单个商品详情页
    public Result UserGetOneShopinf(@ApiParam("商品的全名") @RequestParam("key") String key) {
        List<TShopinf> goods = tShopinfMapper.getOneShopinf(key);
        Iterator<TShopinf> iterator = goods.iterator();
        while (iterator.hasNext()) {
            TShopinf good = iterator.next();
            if (good.getState() != 1) {
                iterator.remove();//使用迭代器的删除方法删除
            }
        }
        Result result = Result.ok().data("goods", goods);
        return result;
    }

    @ApiOperation("按UID获取单个用户信息,data里返回一个名称为“user”，用户")
//    @PostMapping("/getOneUser")
    @RequestMapping(value = {"getOneUser"}, method = {RequestMethod.GET})
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = TUser.class),
    })
    public Result getOneUser(@ApiParam("用户UID") @RequestParam("PK_UID") int PK_UID) {
        TUser tUser = tUserMapper.getOneUser(PK_UID);
        Result result = Result.ok().data("user", tUser);
        return result;
    }

    @ApiOperation("按UID获取该用户所有的购买订单,data里返回一个名称为“buy_orderlists”，订单的list")
    @PostMapping("/getUserBuyOrderlists")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = TOrderlist.class),
    })
    public Result getUserBuyOrderlist(@ApiParam("用户UID") @RequestParam("PK_UID") int PK_UID) {
        List<TOrderlist> tOrderlists = tOrderlistMapper.getBuyOrderlists(PK_UID);
        Result result = Result.ok().data("buy_orderlists", tOrderlists);
        return result;
    }

    @ApiOperation("按UID获取该用户所有的出售订单，data里返回一个名称为“sell_orderlists”，订单的list")
    @PostMapping("/getUserSellOrderlists")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = TOrderlist.class),
    })
    public Result getUserSellOrderlist(@ApiParam("用户UID") @RequestParam("PK_UID") int PK_UID) {
        List<TOrderlist> tOrderlists = tOrderlistMapper.getSellOrderlists(PK_UID);
        Result result = Result.ok().data("sell_orderlists", tOrderlists);
        return result;
    }

    @ApiOperation("按UID更改用户信息")
    @PostMapping("/updateUserInf")
    public Result updateUserInf(@ApiParam("用户UID") @RequestParam("PK_UID") int PK_UID,
                                @ApiParam("姓名") @RequestParam("Uname") String Uname,
                                @ApiParam("电话") @RequestParam("Uphone") String Uphone,
                                @ApiParam("备注") @RequestParam(value = "Uintroduction") String Uintroduction,
                                @ApiParam("地址") @RequestParam("Uaddress") String Uaddress
    ) {
        TUser tUser = new TUser();
        tUser.setUname(Uname);
        tUser.setPkUid(PK_UID);
        tUser.setUphone(Uphone);
        tUser.setUintroduction(Uintroduction);
        tUser.setUaddress(Uaddress);
        Result result = Result.ok().message("success");
        tUserMapper.changeUser(Uname, Uphone, Uintroduction, Uaddress, PK_UID);
        return result;
    }


    @ApiOperation("上架新商品,成功后message返回“商品添加成功”")
    @PostMapping("/sellOneShop")
    public Result sellOneShop(@ApiParam("商品信息简介(60字以内)") @RequestParam("introduction") String introduction,
                              @ApiParam("商品名称") @RequestParam("name") String name,
                              @ApiParam("卖家用户UID") @RequestParam("UID") int UID,
                              @ApiParam("商品价格") @RequestParam("price") float price,
                              @ApiParam("商品照片") @RequestParam("file") MultipartFile file) throws Exception {
        String fileName = uploadService.uploadImage(file);
        String baseString = "http://47.107.225.197:8080/sk/image/";
        fileName = baseString + fileName;
        TShopinf tShopinf = new TShopinf();
        tShopinf.setSname(name);
        tShopinf.setState(0);
        tShopinf.setSprice(price);
        tShopinf.setSphoto(fileName);
        tShopinf.setSuid(UID);
        tShopinf.setSintroduction(introduction);
        tShopinfMapper.insert(tShopinf);
        Result result = Result.ok().message("商品添加成功");
        return result;
    }

    @ApiOperation("下架商品,message返回“商品下架成功”")
    @PostMapping("/deleteOneShop")
    public Result deleteOneShop(@ApiParam("卖家用户UID") @RequestParam("S_UID") int S_UID,
                                @ApiParam("商品ID") @RequestParam("SID") int SID) {
        TShopinf tShopinf = tShopinfMapper.getOneShopinfByID(SID);
        Integer sUid = tShopinf.getSuid();
        Result result;
        if (sUid == S_UID) {
            tShopinfMapper.setShopState(SID, 3);
            tOrderlistMapper.changeOneShop(SID, 3);
            result = Result.ok().message("商品下架成功");
        } else {
            result = Result.ok().message("没有权限");
        }
        return result;
    }
//    @ApiOperation("上架新商品,成功后message返回“商品添加成功”")
//    @PostMapping("/sellOneShop")
//    public Result sellOneShop(@ApiParam("商品信息简介(60字以内)") @RequestParam("introduction") String introduction,
//                              @ApiParam("商品名称") @RequestParam("name") String name,
//                              @ApiParam("卖家用户UID") @RequestParam("UID") int UID,
//                              @ApiParam("商品价格") @RequestParam("price") float price) {
//        TShopinf tShopinf = new TShopinf();
//        tShopinf.setSname(name);
//        tShopinf.setState(0);
//        tShopinf.setSprice(price);
//        tShopinf.setSUid(UID);
//        tShopinf.setSintroduction(introduction);
//        tShopinfMapper.insert(tShopinf);
//        Result result = Result.ok().message("商品添加成功");
//        System.out.println(result);
//        return result;
//    }
}

