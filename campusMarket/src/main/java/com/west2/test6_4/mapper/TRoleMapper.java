package com.west2.test6_4.mapper;

import com.west2.test6_4.entity.TRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaoge
 * @since 2022-04-16
 */
@Mapper
public interface TRoleMapper extends BaseMapper<TRole> {
    List<String> getRoleCodeByUserName(String username);
}
