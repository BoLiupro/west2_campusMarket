package com.west2.test6_4.controller;


import com.west2.test6_4.entity.TAdmin;
import com.west2.test6_4.entity.TRole;
import com.west2.test6_4.entity.TUser;
import com.west2.test6_4.mapper.TAdminMapper;
import com.west2.test6_4.mapper.TRoleMapper;
import com.west2.test6_4.mapper.TUserMapper;
import com.west2.test6_4.response.Result;
import com.west2.test6_4.mapper.userInfMapper;
import com.west2.test6_4.util.RandCode;
import io.swagger.annotations.*;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.west2.test6_4.config.SwaggerConfig.TAG_4;


@RestController
@Api(value = "IndexController", tags = TAG_4)
public class IndexController {

    @Autowired
    TUserMapper tUserMapper;

    @Autowired
    TRoleMapper tRoleMapper;

    @Autowired
    TAdminMapper tAdminMapper;

    @Autowired
    userInfMapper tuserInfMapper;

    @Autowired
    private JavaMailSender mailSender;
    RandCode r = new RandCode();

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * 注册界面
     *
     * @return
     */
//    @ApiOperation("简单用户注册,存在同名用户访问冲突问题")
//    @PostMapping("/registerUser")
//    public Result registerUser(@ApiParam("用户名") @RequestParam("Uname") String Uname, @ApiParam("密码") @RequestParam("Upassword") String Upassword) {
//        TUser tUser = new TUser();
//        tUser.setUname(Uname);
//        tUser.setUpassword(passwordEncoder.encode(Upassword));
//        TRole tRole = new TRole();
//        tRole.setUsername(Uname);
//        tRole.setRole("ROLE_user");
//        tUserMapper.insert(tUser);
//        tRoleMapper.insert(tRole);
//        Result result = Result.ok().message("user注册成功");
//        System.out.println(result);
//        return result;
//    }
    @ApiOperation("按学工号获取单个用户信息,data里返回一个名称为“user”，用户")
//    @PostMapping("/getOneUser")
    @RequestMapping(value = {"getUserInf"}, method = {RequestMethod.GET})
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = TUser.class),
    })
    public Result getOneUser(@ApiParam("用户学号") @RequestParam("number") String Unumber) {
        TUser tUser = tUserMapper.getUserInf(Unumber);
        Result result = Result.ok().data("user", tUser);
        return result;
    }

    @ApiOperation(value = "邮箱验证，成功后data里返回名为RandCode的六位验证码，网速较慢时会花较长时间才能返回数据")
    @PostMapping("/mail")
    public Result sendMail(@ApiParam("用户邮箱") @RequestParam("email") String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        String randcode = r.generateFixedLengthNum(6);//6位数验证码
        message.setFrom("1249237461@qq.com");
        message.setTo(email);
        message.setSubject("healthcare验证码");
        message.setText("你好，我是healthcare，这是你的验证码：" + randcode);
        mailSender.send(message);
        Result result = Result.ok().message("邮件已发送");
        result.data("RandCode", randcode);
        return result;
    }

    @ApiOperation(value = "用户注册，学工号在数据库是唯一元素，成功message返回“用户注册成功”，否则返回“该学工号已注册“")
    @PostMapping("/registerUserWithPhone")
    @ApiResponses({
            @ApiResponse(code = 200, message = "用户注册成功", response = TUser.class),
            @ApiResponse(code = 999, message = "该学工号已注册", response = TUser.class)
    })
    public Result registerUser(@ApiParam("姓名") @RequestParam("Uname") String Uname,
                               @ApiParam("密码") @RequestParam("Upassword") String Upassword,
                               @ApiParam("电话") @RequestParam("Uphone") String Uphone,
                               @ApiParam("学号") @RequestParam("Unumber") String Unumber) throws IOException {
        if (null == tuserInfMapper.checkExist(Unumber)) {
            TUser tUser = new TUser();
            tUser.setUname(Uname);
            tUser.setUnumber(Unumber);
            tUser.setUphone(Uphone);
            tUser.setUstate(0);
            tUser.setUlevel(60);
            //用户默认的注册状态为0，等级为60
            tUser.setUpassword(passwordEncoder.encode(Upassword));
            TRole tRole = new TRole();
            tRole.setUsername(Unumber);
            tRole.setRole("ROLE_user");
            tUserMapper.insert(tUser);
            tRoleMapper.insert(tRole);
            Result result = Result.ok().message("用户注册成功");
            return result;
        } else {
            Result result = Result.error().message("该学工号已注册");
            return result;
        }

    }

    @ApiOperation("管理员注册（只有管理员才能注册新管理员），成功message返回“admin注册成功”，否则返回“该学工号已注册“")
    @PreAuthorize("hasRole('admin')")//只有admin才可以注册admin
    @PostMapping("/registerAdmin")
    public Result registerAdmin(@ApiParam("管理员姓名") @RequestParam("Aname") String Aname,
                                @ApiParam("管理员手机号") @RequestParam("Aphone") String Aphone,
                                @ApiParam("管理员学工号") @RequestParam("Anumber") String Anumber,
                                @ApiParam("管理员密码") @RequestParam("Apassword") String Apassword) {

        if (null == tuserInfMapper.checkAdminExist(Anumber)) {
            TUser tAdmin = new TUser();
            tAdmin.setUname(Aname);
            tAdmin.setUstate(9);
            tAdmin.setUnumber(Anumber);
            tAdmin.setUphone(Aphone);
            tAdmin.setUpassword(passwordEncoder.encode(Apassword));
            TRole tRole = new TRole();
            tRole.setUsername(Anumber);
            tRole.setRole("ROLE_admin");
            tUserMapper.insert(tAdmin);
            tRoleMapper.insert(tRole);
            Result result = Result.ok().message("admin注册成功");
            return result;
        } else {
            Result result = Result.error().message("该学工号已注册");
            return result;
        }
    }
}
