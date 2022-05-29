package com.west2.test6_4.mapper;

import com.west2.test6_4.entity.TAdmin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaoge
 * @since 2022-04-17
 */
@Mapper
public interface TAdminMapper extends BaseMapper<TAdmin> {
    void changeAdmin(TAdmin tadmin);
}
