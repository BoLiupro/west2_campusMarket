package com.west2.test6_4.mapper;

import com.west2.test6_4.entity.TShopinf;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 */
@Mapper
public interface TShopinfMapper extends BaseMapper<TShopinf> {
    List<TShopinf> searchGoods(String key);

    List<TShopinf> getShopinf();

    List<TShopinf> getOneShopinf(String key);

    void setShopState(int PK_SID, int state);

    TShopinf getOneShopinfByID(int PK_SID);

    void setShopPhoto(int PK_SID, String Sphoto);
}
