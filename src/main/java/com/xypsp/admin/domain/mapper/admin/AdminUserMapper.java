package com.xypsp.admin.domain.mapper.admin;

import com.xypsp.admin.domain.model.admin.AdminUserDTO;
import com.xypsp.admin.domain.request.admin.AdminBindReq;
import com.xypsp.admin.domain.request.admin.AdminUserInfoReq;
import com.xypsp.admin.domain.request.admin.AdminUserReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author renpei
 */
@Repository
@Mapper
public interface AdminUserMapper {

    /**
     * 查询管理员
     * @param userId 管理员id
     * @return
     */
    AdminUserDTO findById(Integer userId);

    /**
     * 查询用户
     * @param username 账号
     * @return
     */
    Optional<AdminUserDTO> findByUsername(String username);

    /**
     * 更新管理员信息
     * @param adminUserInfoReq 新信息
     * @param username 账号
     */
    void update(@Param("adminUser") AdminUserInfoReq adminUserInfoReq, @Param("username") String username);


    /**
     * 修改密码
     * @param username 账号
     * @param password 密码
     */
    void updatePass(@Param("username") String username,@Param("password") String password);

    /**
     * 查询管理员
     * @param openId 微信用户
     * @return
     */
    AdminUserDTO findByOpenId(String openId);

    /**
     * 微信绑定管理账号
     * @param adminBindReq 绑定数据
     */
    void updateOpenId(@Param("admin") AdminBindReq adminBindReq);


    /**
     * 查询管理员
     * @return 所有管理员
     */
    List<AdminUserDTO> findByAdminUsers();


    /**
     * 搜索管理员
     * @param search 所有关键字
     * @return 管理员信息
     */
    List<AdminUserDTO> findByAdminUsersLike(@Param("search") String search);


    /**
     * 禁用/启用
     * @param id 账号id
     */
    void switchIsBan(Integer id);


    /**
     * 新增管理员
     * @param adminUserReq 管理员信息
     */
    void insert(@Param("adminUser") AdminUserReq adminUserReq);


    /**
     * 批量删除管理员
     * @param userIds 管理员ids
     */
    void deletesByIds(@Param("userIds") List userIds);


    /**
     * 修改管理员信息
     * @param adminUserReq 管理员信息
     */
    void updateAdminUserInfo(@Param("adminUser") AdminUserReq adminUserReq);


}
