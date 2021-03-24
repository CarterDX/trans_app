package net.trans.mapper;

import net.trans.pojo.TransUserTable;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;


import java.util.List;


public interface UserDao {

	
    @Select("select * from trans_user_table where u_phone = #{phone}")
    @Results({
            @Result(id = true, property = "uUid", column = "u_uid"),
            @Result(property = "roles", column = "u_uid", javaType = List.class,
                many = @Many(select = "RoleDao.findByUid"))
    })
    public TransUserTable findByPhone(String phone);

}
