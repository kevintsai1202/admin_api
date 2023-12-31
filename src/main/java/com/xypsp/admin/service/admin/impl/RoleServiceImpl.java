package com.xypsp.admin.service.admin.impl;

import com.xypsp.admin.domain.exception.CustomizeException;
import com.xypsp.admin.domain.mapper.admin.RoleMapper;
import com.xypsp.admin.domain.mapper.admin.RolePermissionMapper;
import com.xypsp.admin.domain.mapper.admin.UserRolePermissionMapper;
import com.xypsp.admin.domain.model.admin.RoleDTO;
import com.xypsp.admin.domain.request.admin.RoleReq;
import com.xypsp.admin.enums.ResponseEnum;
import com.xypsp.admin.service.admin.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rp
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

    private final RolePermissionMapper rolePermissionMapper;

    private final UserRolePermissionMapper userRolePermissionMapper;

    public RoleServiceImpl(RoleMapper roleMapper, RolePermissionMapper rolePermissionMapper, UserRolePermissionMapper userRolePermissionMapper) {
        this.roleMapper = roleMapper;
        this.rolePermissionMapper = rolePermissionMapper;
        this.userRolePermissionMapper = userRolePermissionMapper;
    }

    @Override
    public PageInfo<RoleDTO> findByRoles(Integer page, Integer pageSize, String search) {
        PageHelper.startPage(page,pageSize);
        List<RoleDTO> roles;
        if (StringUtils.isEmpty(search)){
            roles = roleMapper.findByRoles();
        }else{
            roles = roleMapper.findByRolesLike(search);
        }
        return new PageInfo<>(roles);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createRole(RoleReq roleReq) {
        roleMapper.createRole(roleReq);
        if (roleReq.getId() <= 0){
            throw new CustomizeException(ResponseEnum.PERMISSION_CREATE_ERROR);
        }
        Integer rows = rolePermissionMapper.inserts(roleReq);
        return rows > 0;
    }

    @Override
    public void deletes(String ids) {
        //删除角色权限表，用户角色权限表，角色表
        String[] split = ids.split(",");
        List<String> roleIds = Arrays.asList(split);
//        if (rolePermissionMapper.countByRoleIds(roleIds) > 0){
//            throw new CustomizeException(ResponseEnum.PERMISSION_DELETES_ERROR);
//        }
        if (userRolePermissionMapper.countByRoleIds(roleIds) > 0){
            throw new CustomizeException(ResponseEnum.PERMISSION_DELETES_ERROR);
        }
        rolePermissionMapper.deletesByRoleIds(roleIds);
        userRolePermissionMapper.deletesByRoleIds(roleIds);
        roleMapper.deletesByIds(roleIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateRole(RoleReq roleReq) {
        roleMapper.updateRole(roleReq);
        List<Integer> permissionIds = rolePermissionMapper.findPermissionIdsByRoleId(roleReq.getId());
        //相同权限不变
        List<Integer> integers = Arrays.asList((Integer[]) ConvertUtils.convert(roleReq.getPermissionIds(), Integer.class));
        List<Integer> saveList = integers.stream().filter(permissionIds::contains).collect(Collectors.toList());
        List<Integer> missionIds = new ArrayList<>(integers);
        missionIds.removeAll(saveList);
        //多余权限新增
        if (missionIds.size() != 0){
            Integer rows = rolePermissionMapper.insertList(missionIds,roleReq.getId());
            if (rows <= 0){
                throw new CustomizeException(ResponseEnum.PERMISSION_UPDATE_ERROR);
            }
        }
        //少余权限删除
        permissionIds.removeAll(saveList);
        if (permissionIds.size() != 0){
            rolePermissionMapper.deletesByPermissionIds(permissionIds,roleReq.getId());
        }
        return true;
    }

    @Override
    public List<RoleDTO> findAll() {
        return roleMapper.findByRoles();
    }
}
