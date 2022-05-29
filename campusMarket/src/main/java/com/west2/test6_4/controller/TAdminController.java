package com.west2.test6_4.controller;


import com.west2.test6_4.entity.TShopinf;
import com.west2.test6_4.entity.TUser;
import com.west2.test6_4.mapper.TAdminMapper;
import com.west2.test6_4.mapper.TShopinfMapper;
import com.west2.test6_4.mapper.TUserMapper;
import com.west2.test6_4.response.Result;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

import static com.west2.test6_4.config.SwaggerConfig.TAG_3;

/**
 * <p>
 * 前端控制器
 * </p>
 */
@RestController
@RequestMapping(value = "/admin", method = {RequestMethod.GET, RequestMethod.POST})
@Api(value = "TAdminController", tags = TAG_3)
public class TAdminController {
    @Autowired
    TShopinfMapper tShopinfMapper;
    @Autowired
    TAdminMapper tAdminMapper;
    @Autowired
    TUserMapper tUserMapper;

    @ApiOperation("获取所有商品列表,data里返回一个名称为“goods”，商品的list")
    @ApiResponses({
            @ApiResponse(code = 200, message = "", response = TShopinf.class),
    })
    @RequestMapping("/getShopinf")//查看所有商品列表
    public Result AdminGetShopinf() {
        List<TShopinf> goods = tShopinfMapper.getShopinf();
        Result result = Result.ok().data("goods", goods);
        return result;
    }

    @ApiOperation("获取所有已经通过审核商品列表,data里返回一个名称为“goods”，商品的list")
    @ApiResponses({
            @ApiResponse(code = 200, message = "", response = TShopinf.class),
    })
    @RequestMapping("/getPassedShopinf")
    public Result AdminGetPassedShopinf() {
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

    @ApiOperation("获取所有没有经过审核的商品列表,data里返回一个名称为“goods”，商品的list")
    @ApiResponses({
            @ApiResponse(code = 200, message = "", response = TShopinf.class),
    })
    @RequestMapping("/getUnpassedShopinf")
    public Result AdminGetUnpassedShopinf() {
        List<TShopinf> goods = tShopinfMapper.getShopinf();
        Result result;
        if (goods != null) {
            Iterator<TShopinf> iterator = goods.iterator();
            while (iterator.hasNext()) {
                TShopinf good = iterator.next();
                if (good.getState() != 0) {
                    iterator.remove();//使用迭代器的删除方法删除
                }
            }
            result = Result.ok().data("goods", goods);
        } else {
            result = Result.ok().message("没有查询到任何信息");
        }
        return result;
    }


    @ApiOperation("精准搜索,查看单个名称的商品详情，data里返回一个名称为“goods”，商品")
    @ApiResponses({
            @ApiResponse(code = 200, message = "", response = TShopinf.class),
    })
    @RequestMapping("/getOneShopinf")//精准搜索,查看单个商品详情页
    public Result AdminGetOneShopinf(@ApiParam("商品全名") @RequestParam("key") String key) {
        List<TShopinf> goods = tShopinfMapper.getOneShopinf(key);
        Result result = Result.ok().data("goods", goods);
        return result;
    }

    @ApiOperation("管理员审核商品")
    @RequestMapping("/setShopinfState")
    public Result setShopinfState(@ApiParam("商品的ID") @RequestParam("PK_SID") int PK_SID,
                                  @ApiParam("是否通过") @RequestParam("state") boolean state) {
        Result result;
        if (state) {
            tShopinfMapper.setShopState(PK_SID, 1);
            result = Result.ok().message("审核通过，审核完成");
        } else {
            tShopinfMapper.setShopState(PK_SID, 4);
            result = Result.ok().message("审核不通过，审核完成");
        }
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

    @ApiOperation("拉黑某一个用户，将其状态设置为1")
//    @PostMapping("/getOneUser")
    @RequestMapping(value = {"deleteOneUser"}, method = {RequestMethod.GET})
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = TUser.class),
    })
    public Result deleteOneUser(@ApiParam("用户UID") @RequestParam("PK_UID") int PK_UID) {
        tUserMapper.changeOneUserState(1, PK_UID);
        Result result = Result.ok().message("拉黑成功");
        return result;
    }

    @ApiOperation("获取所有用户信息,data里返回一个名称为“user”，用户")
//    @PostMapping("/getOneUser")
    @RequestMapping(value = {"getAllUser"}, method = {RequestMethod.GET})
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = TUser.class),
    })
    public Result getAllUser(@ApiParam("用户UID") @RequestParam("PK_UID") int PK_UID) {
        List<TUser> allUsers = tUserMapper.getAllUsers();
        Result result = Result.ok().data("user", allUsers);
        return result;
    }

    @ApiOperation("获取正常用户信息,data里返回一个名称为“user”，用户")
//    @PostMapping("/getOneUser")
    @RequestMapping(value = {"getableUser"}, method = {RequestMethod.GET})
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = TUser.class),
    })
    public Result getableUser(@ApiParam("用户UID") @RequestParam("PK_UID") int PK_UID) {
        List<TUser> allUsers = tUserMapper.getAllUsers();
        Result result;
        if (allUsers != null) {
            Iterator<TUser> iterator = allUsers.iterator();
            while (iterator.hasNext()) {
                TUser good = iterator.next();
                if (good.getUstate() != 0) {
                    iterator.remove();//使用迭代器的删除方法删除
                }
            }
            result = Result.ok().data("user", allUsers);
        } else {
            result = Result.ok().message("没有查询到任何信息");
        }
        return result;
    }
//    @ApiOperation("按AID更改用户信息")
//    @PostMapping("/updateAdminInf")
//    public Result updateUserInf(@ApiParam("用户UID") @RequestParam("PK_AID") int PK_AID,
//                                @ApiParam("姓名") @RequestParam("Aname") String Uname,
//                                @ApiParam("电话") @RequestParam("Aphone") String Uphone,
//                                @ApiParam("地址") @RequestParam("Aaddress") String Uaddress
//    ) {
//        TAdmin tAdmin = new TAdmin();
//        tAdmin.setAname(Uname);
//        tAdmin.setPkAid(PK_AID);
//        tAdmin.setAphone(Uphone);
//        tAdmin.setAaddress(Uaddress);
//        Result result = Result.ok().message("success");
//        tAdminMapper.changeAdmin(tAdmin);
//        return result;
//    }
}

