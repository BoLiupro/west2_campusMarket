package com.west2.test6_4.service.impl;

import com.west2.test6_4.entity.TUser;
import com.west2.test6_4.mapper.TUserMapper;
import com.west2.test6_4.service.dao.TUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaoge
 * @since 2022-04-12
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements TUserService {

}
