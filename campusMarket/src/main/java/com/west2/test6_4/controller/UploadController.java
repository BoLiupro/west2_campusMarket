package com.west2.test6_4.controller;

import com.west2.test6_4.mapper.TShopinfMapper;
import com.west2.test6_4.response.Result;
import com.west2.test6_4.service.dao.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.west2.test6_4.config.SwaggerConfig.TAG_1;

@RestController
@RequestMapping(value = "/upload", method = {RequestMethod.GET, RequestMethod.POST})
@Api(value = "UploadController", tags = "图片上传")
public class UploadController {
    @Autowired
    TShopinfMapper tShopinfMapper;
    @Autowired
    private UploadService uploadService;

    @ApiOperation("商品照片上传，支持jpg,png,jpeg,bmp,成功message返回“商品添加成功”")
    @PostMapping("ShopImage")
    public Result upload(@ApiParam("商品照片") @RequestParam("file") MultipartFile file,
                         @ApiParam("商品的ID") @RequestParam("PK_SID") int PK_SID) throws Exception {
        String fileName = uploadService.uploadImage(file);
        String baseString = "http://47.107.225.197:8080/sk/image/";
        fileName = baseString + fileName;
        System.out.println(fileName);
        tShopinfMapper.setShopPhoto(PK_SID, fileName);
        Result result = Result.ok().message("商品添加成功");
        return result;
    }

//    @ApiOperation("用户照片上传，支持jpg,png,jpeg,bmp,其他会报500错误（未实装）")
//    @PostMapping("UserImage")
//    public Result uploadUserPhoto(@ApiParam("用户照片") @RequestParam("file") MultipartFile file,
//                                  @ApiParam("用户的ID") @RequestParam("PK_UID") int PK_UID) throws Exception {
//        String fileName = uploadService.uploadImage(file);
////        tShopinfMapper.setShopPhoto(PK_UID,fileName);
//        Result result = Result.ok().message("照片添加成功");
//        return result;
//    }
}
