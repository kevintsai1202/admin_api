package com.xypsp.admin.service.admin;

import com.xypsp.admin.domain.model.admin.RoleMenuDTO;
import com.xypsp.admin.domain.model.admin.TreeRoleMenuDTO;
import com.xypsp.admin.domain.request.admin.MenuReq;

import java.util.List;

/**
 * @author rp
 */
public interface RoleMenuService {

    /**
     * 分页查询权限菜单
     * @param search 搜索关键字
     * @return
     */
    List<RoleMenuDTO> findByRoleMenus(String search);


    /**
     * 查询所有树形结构菜单
     * @return
     */
    List<TreeRoleMenuDTO> findRoleMenus();


    /**
     * 创建菜单
     * @param menuReq 菜单信息
     * @return boolean
     */
    boolean creatMenu(MenuReq menuReq);


    /**
     * 删除菜单
     * @param id 菜单id
     */
    void deleteById(String id);


    /**
     * 修改菜单
     * @param menuReq 菜单信息
     * @return boolean
     */
    boolean updateMenu(MenuReq menuReq);


}
