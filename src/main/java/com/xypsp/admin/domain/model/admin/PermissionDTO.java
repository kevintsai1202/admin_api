package com.xypsp.admin.domain.model.admin;

import lombok.Data;

/**
 * @author rp
 */
@Data
public class PermissionDTO {

    private Long id;
    /**
     * 权限
     * */
    private String permission;
    /**
     * 权限名称
     * */
    private String permissionName;
}
