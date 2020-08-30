package com.xypsp.admin.domain.request.admin;

import lombok.Data;

/**
 * @author rp
 */
@Data
public class AdminUserReq {

    private Integer id;

    private String username;

    private String password;

    private String name;

    private String phone;

    private String role;

    private Integer roleId;

    private Boolean isBan;


}
