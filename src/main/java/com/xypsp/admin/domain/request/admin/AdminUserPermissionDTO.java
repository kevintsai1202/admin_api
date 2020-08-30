package com.xypsp.admin.domain.request.admin;

import lombok.Data;

/**
 * @author rp
 */
@Data
public class AdminUserPermissionDTO {

    private Integer id;

    private String[] permissionIds;
}
