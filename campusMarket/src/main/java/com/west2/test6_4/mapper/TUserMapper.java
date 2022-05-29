package com.west2.test6_4.mapper;

import com.west2.test6_4.entity.TUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 */
@Mapper
public interface TUserMapper extends BaseMapper<TUser> {
    TUser getOneUser(int PK_UID);

    List<TUser> getAllUsers();

    TUser getUserInf(String Unumber);

    void changeUser(String Uname, String Uphone, String Uintroduction, String Uaddress, int PK_UID);
    void changeOneUserState(int Ustate ,int PK_UID);
}
