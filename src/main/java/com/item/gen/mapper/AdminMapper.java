package com.item.gen.mapper;

import com.item.gen.model.Admin;
import java.util.List;

public interface AdminMapper {
    /***
     * 
     * @param id
     * @return int
     */
    int deleteByPrimaryKey(Long id);

    /***
     * 
     * @param record
     * @return int
     */
    int insert(Admin record);

    /***
     * 
     * @param record
     * @return int
     */
    int insertSelective(Admin record);

    /***
     * 
     * @param id
     * @return Admin
     */
    Admin selectByPrimaryKey(Long id);

    /***
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(Admin record);

    /***
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(Admin record);

    /***
     * 
     * @param record
     * @return Admin
     */
    Admin find(Admin record);

    /***
     * 
     * @param record
     * @return List<Admin>
     */
    List<Admin> list(Admin record);

    /***
     * 
     * @param record
     * @return List<Admin>
     */
    List<Admin> pageList(Admin record);

    /***
     * 
     * @param where
     * @return Integer
     */
    Integer test2(Admin where);
}