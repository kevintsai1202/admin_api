package com.xypsp.admin.domain.mapper.web;

import com.xypsp.admin.domain.model.web.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lj on 2019/11/20 15:14
 */
@Mapper
@Repository
public interface UserMapper {

    /**
     * 通过用户id查询用户信息
     * @param userId 用户ID
     * @return 用户信息
     */
    UserDTO findByUserId(Long userId);


    List<UserDTO> selectAllUsers();

    /**
     * 统计用户总数
     * @return
     */
    Long countUsers();


}
