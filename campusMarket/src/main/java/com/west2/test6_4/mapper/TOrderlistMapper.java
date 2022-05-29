package com.west2.test6_4.mapper;

import cn.hutool.core.date.DateTime;
import com.west2.test6_4.entity.TOrderlist;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 */
@Mapper
public interface TOrderlistMapper extends BaseMapper<TOrderlist> {
    void sell(int O_sellerUID, int O_SID, int O_state);

    void setTime(Date O_sellTime, int PK_OID);

    List<TOrderlist> selectList(int O_buyerUID);

    void changeOneShop(int PK_SID, int state);

    void setOrderlist(int PK_OID, int O_state);

    void deleteById(int O_buyerUID, int O_SID);

    List<TOrderlist> getBuyOrderlists(int O_buyerUID);

    TOrderlist getOneOrder(int O_buyerUID, int O_SID);

    List<TOrderlist> getSellOrderlists(int O_sellerUID);
}
