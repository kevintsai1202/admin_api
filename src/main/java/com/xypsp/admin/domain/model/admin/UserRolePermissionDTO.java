package com.xypsp.admin.domain.model.admin;

import lombok.Data;

/**
 * @author rp
 */
@Data
public class UserRolePermissionDTO {

    private Integer id;
    /**
     * 用户ID
     * */
    private Integer userId;
    /**
     * 角色ID
     * */
    private Integer roleId;
    /**
     * 权限ID
     * */
    private Integer permissionId;

    /**
     * 角色信息
     * */
    private RoleDTO role;

    /**
     * 权限信息
     * */
    private PermissionDTO permission;
    /**
     * 管理员
     * */
    private AdminUserDTO adminUser;

}
