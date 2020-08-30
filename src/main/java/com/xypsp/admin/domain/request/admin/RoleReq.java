package com.xypsp.admin.domain.request.admin;

import lombok.Data;

/**
 * @author rp
 */
@Data
public class RoleReq {

    private Integer id;

    private String role;

    private String roleName;
    /**
     * 权限ids
     * */
    private String[] permissionIds;

}
