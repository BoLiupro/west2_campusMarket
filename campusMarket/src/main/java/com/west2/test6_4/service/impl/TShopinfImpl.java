package com.west2.test6_4.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.west2.test6_4.entity.TShopinf;
import com.west2.test6_4.entity.TUser;
import com.west2.test6_4.mapper.TShopinfMapper;
import com.west2.test6_4.mapper.TUserMapper;
import com.west2.test6_4.service.dao.TShopinfService;
import org.springframework.stereotype.Service;

@Service
public class TShopinfImpl extends ServiceImpl<TShopinfMapper, TShopinf> implements TShopinfService {
}
