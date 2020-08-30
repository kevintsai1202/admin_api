package com.xypsp.admin.domain.request.admin;

import lombok.Data;

/**
 * @author rp
 */
@Data
public class MenuReq {

    private Integer id;

    /**
     * 菜单id
     * */
    private Integer menuId;

    private Integer level;

    /**
     * 组键页面
     * */
    private String key;
    /**
     * 名称
     * */
    private String name;
    /**
     *  图标 设置该路由的图标
     * */
    private String icon;
    /**
     * 权限id
     * */
    private Integer permissionId;

    private Integer sort;

}
