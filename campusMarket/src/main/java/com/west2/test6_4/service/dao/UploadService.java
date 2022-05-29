package com.west2.test6_4.service.dao;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    /**
     * 上传图片
     * @param file
     * @return
     * @throws Exception
     */
    String uploadImage(MultipartFile file) throws Exception;
}
