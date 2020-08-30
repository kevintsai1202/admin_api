package com.xypsp.admin.service.admin.impl;

import com.xypsp.admin.domain.exception.CustomizeException;
import com.xypsp.admin.domain.mapper.admin.PermissionMapper;
import com.xypsp.admin.domain.mapper.admin.RoleMenuMapper;
import com.xypsp.admin.domain.mapper.admin.RolePermissionMapper;
import com.xypsp.admin.domain.mapper.admin.UserRolePermissionMapper;
import com.xypsp.admin.domain.model.admin.PermissionDTO;
import com.xypsp.admin.domain.request.admin.PermissionReq;
import com.xypsp.admin.enums.ResponseEnum;
import com.xypsp.admin.service.admin.PermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author rp
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionMapper permissionMapper;

    private final RoleMenuMapper roleMenuMapper;

    private final RolePermissionMapper rolePermissionMapper;

    private final UserRolePermissionMapper userRolePermissionMapper;

    public PermissionServiceImpl(PermissionMapper permissionMapper, RoleMenuMapper roleMenuMapper, RolePermissionMapper rolePermissionMapper, UserRolePermissionMapper userRolePermissionMapper) {
        this.permissionMapper = permissionMapper;
        this.roleMenuMapper = roleMenuMapper;
        this.rolePermissionMapper = rolePermissionMapper;
        this.userRolePermissionMapper = userRolePermissionMapper;
    }

    @Override
    public PageInfo<PermissionDTO> findByPermissions(Integer page, Integer pageSize, String search) {
        PageHelper.startPage(page,pageSize);
        List<PermissionDTO> permissions;
        if (StringUtils.isEmpty(search)){
            permissions = permissionMapper.findByPermissions();
        }else{
            permissions = permissionMapper.findPermissionsLikeBySearch(search);
        }
        return new PageInfo<>(permissions);
    }

    @Override
    public boolean createPermission(PermissionReq permissionReq) {
        Optional<PermissionDTO> permission = permissionMapper.findByPermission(permissionReq.getPermission());
        if (permission.isPresent()){
            throw new CustomizeException(ResponseEnum.PERMISSION_EXIST_ERROR);
        }
        Integer row = permissionMapper.insert(permissionReq);
        return row > 0;
    }

    @Override
    public void deletes(String ids) {
        String[] split = ids.split(",");
        List permissionIds = Arrays.asList(split);
        //删除关联菜单
        Integer roleMenu = roleMenuMapper.countByPermissionIds(permissionIds);
        if (roleMenu > 0){
            throw new CustomizeException(ResponseEnum.PERMISSION_DELETES_ERROR);
        }
        //删除角色权限
        Integer role = rolePermissionMapper.countByPermissionIds(permissionIds);
        if (role > 0){
            throw new CustomizeException(ResponseEnum.PERMISSION_DELETES_ERROR);
        }
        //删除用户权限
        Integer userRole = userRolePermissionMapper.countByPermissionIds(permissionIds);
        if (userRole > 0){
            throw new CustomizeException(ResponseEnum.PERMISSION_DELETES_ERROR);
        }
        //删除权限
        permissionMapper.deletes(permissionIds);
    }

    @Override
    public boolean update(PermissionReq permissionReq) {
        Optional<PermissionDTO> permission = permissionMapper.findByPermissionAndId(permissionReq);
        if (permission.isPresent()){
            throw new CustomizeException(ResponseEnum.PERMISSION_EXIST_ERROR);
        }
        Integer row = permissionMapper.update(permissionReq);
        return row > 0;
    }

    @Override
    public List<PermissionDTO> findAll() {
        return permissionMapper.findAll();
    }
}
