package com.item.gen.mapper;

import com.item.gen.model.Admin;
import java.util.List;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    Admin find(Admin record);

    List<Admin> list(Admin record);

    List<Admin> pageList(Admin record);

    Integer test2(Admin where);
}