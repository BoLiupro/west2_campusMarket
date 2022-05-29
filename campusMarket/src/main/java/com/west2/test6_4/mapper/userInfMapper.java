package com.west2.test6_4.mapper;


import com.west2.test6_4.entity.TOrderlist;
import com.west2.test6_4.entity.TShopinf;
import com.west2.test6_4.entity.TUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface userInfMapper {

    /*
    对用户表进行操作的方法
    */
    //插入新的用户信息,只需要用户名，密码和手机号
    void addUserInf(TUser u1);

    //根据UID，获得用户的信息储存到user里面
    TUser getUserByUID(int UID);

    //查看当前手机号用户是否已经存在，返回一个Integer类型，当不存在时返回null
    Integer checkExist(String name);

    Integer checkAdminExist(String name);

    //判断用户密码是否与手机号匹配
    String checkUphone2Password(String phone);

    //判断用户密码是否与手机号匹配
    String checkUphone2PasswordSafe(Map<String, Object> m1);

    //根据注册电话电话找UID
    Integer checkUIDbyPhone(String phone);


    //封禁账户，将用户状态状态设置为显示2
    void banUState(int UID);

/*
对商品表进行操作
*/

    //    找到自增主键SID的下一位，保证不会产生名称的冲突
    int getNextId();

    //添加新的商品
    void addNewGoods(TShopinf s1);

    //根据SID，获得商品的信息储存到shop里面
    TShopinf getGoodsByUID(int PK_SID);

    //模糊搜索商品
    List<TShopinf> getGoodsByName(String Sname);

    //分页查询+模糊查询商品内容
    List<TShopinf> getGoodsByNameInPage(Map<String, Object> m1);

    //查找符合条件的商品的总数目
    int getGoodsLength(String name);

    //按照商品状态查询，1为未审核，2为通过，3为未通过
    List<TShopinf> getGoodsByState(int id);

    /*Map m1 = new HashMap();
    m1.put("Sstate",Name);
    m1.put("PK_SID",2);
    List bookInfByPage = mapper.changeGoodsState(m1);*/
    //更改商品状态，1为未审核，2为通过，3为未通过
    void changeGoodsState(Map<String, Object> m1);

    //审核不通过，将商品状态状态设置为显示3
    void noPassGoods(int SID);

    //按照ID删除商品数据库信息
    void deleteGoodsByID(int PK_SID);

/*
对管理员表进行操作
*/

    //查看管理员对应密码
    String checkAdminPassword(String Aname);

/*
对订单表进行操作
*/

    void addOrder(TOrderlist o1);


}
