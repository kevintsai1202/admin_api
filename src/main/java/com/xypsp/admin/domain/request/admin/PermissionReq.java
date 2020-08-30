package com.xypsp.admin.domain.request.admin;

import lombok.Data;

/**
 * @author rp
 */
@Data
public class PermissionReq {

    private Integer id;
    /**
     * 权限
     * */
    private String permission;
    /**
     * 权限名称
     * */
    private String permissionName;


}
