package com.xypsp.admin.service.admin.impl;

import com.xypsp.admin.configuration.security.JwtUserService;
import com.xypsp.admin.configuration.wx.WxOpenProperties;
import com.xypsp.admin.domain.exception.CodeMsgException;
import com.xypsp.admin.domain.exception.CustomizeException;
import com.xypsp.admin.domain.model.admin.AdminUserDTO;
import com.xypsp.admin.domain.model.admin.RoleDTO;
import com.xypsp.admin.domain.model.admin.RoleMenuDTO;
import com.xypsp.admin.enums.ResponseEnum;
import com.xypsp.admin.service.admin.AdminUserService;
import com.xypsp.admin.utils.TreeHelper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xypsp.admin.domain.mapper.admin.*;
import com.xypsp.admin.domain.request.admin.*;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.open.api.WxOpenService;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author rp
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {

    private final JwtUserService jwtUserService;

    private final AdminUserMapper adminUserMapper;

    private final RolePermissionMapper rolePermissionMapper;

    private final UserRolePermissionMapper userRolePermissionMapper;

    private final RoleMapper roleMapper;

    private final RoleMenuMapper roleMenuMapper;

    private final WxOpenProperties wxOpenProperties;

    private final WxOpenService wxOpenService;

    public AdminUserServiceImpl(JwtUserService jwtUserService, AdminUserMapper adminUserMapper, RolePermissionMapper rolePermissionMapper, UserRolePermissionMapper userRolePermissionMapper, RoleMapper roleMapper, RoleMenuMapper roleMenuMapper, WxOpenProperties wxOpenProperties, WxOpenService wxOpenService) {
        this.jwtUserService = jwtUserService;
        this.adminUserMapper = adminUserMapper;
        this.rolePermissionMapper = rolePermissionMapper;
        this.userRolePermissionMapper = userRolePermissionMapper;
        this.roleMapper = roleMapper;
        this.roleMenuMapper = roleMenuMapper;
        this.wxOpenProperties = wxOpenProperties;
        this.wxOpenService = wxOpenService;
    }

    @Override
    public Optional<AdminUserDTO> findByUsername(String username) {
        return adminUserMapper.findByUsername(username);
    }

    @Override
    public AdminUserDTO update(AdminUserInfoReq adminUserInfoReq, String username) {
        adminUserMapper.update(adminUserInfoReq,username);
        return adminUserMapper.findByUsername(username).get();
    }

    @Override
    public String updatePass(AdminUserPassReq adminUserDTO) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(adminUserDTO.getPassword());
        adminUserMapper.updatePass(adminUserDTO.getUsername(),password);
        AdminUserDTO user = new AdminUserDTO();
        user.setUsername(adminUserDTO.getUsername());
        user.setPassword(adminUserDTO.getPassword());
        return jwtUserService.saveUserLoginInfo(user);
    }

    @Override
    public String authQrCode(String code) {
        try {
            WxMpOAuth2AccessToken accessToken = wxOpenService.getWxOpenComponentService().oauth2getAccessToken(wxOpenProperties.getAppId(), code);
            AdminUserDTO adminUser = adminUserMapper.findByOpenId(accessToken.getOpenId());
            if (StringUtils.isEmpty(adminUser)){
                throw new CodeMsgException(ResponseEnum.WX_LOGIN_BINDING_ERROR.getCode(),accessToken.getOpenId());
            }
            return jwtUserService.saveUserLoginInfo(adminUser);
        } catch (WxErrorException e) {
            throw new CustomizeException(ResponseEnum.WX_LOGIN_ERROR);
        }
    }

    @Override
    public String bindingWx(AdminBindReq adminBindReq) {
        AdminUserDTO adminUserDTO= adminUserMapper.findByUsername(adminBindReq.getUsername()).get();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean matches = passwordEncoder.matches(adminBindReq.getPassword(), adminUserDTO.getPassword());
        if (!matches){
            throw new CustomizeException(ResponseEnum.USER_PASSWORD_VALID);
        }
        adminUserMapper.updateOpenId(adminBindReq);
        return jwtUserService.saveUserLoginInfo(adminUserDTO);
    }

    @Override
    public PageInfo<AdminUserDTO> findByAdminUsers(Integer page, Integer pageSize, String search) {
        PageHelper.startPage(page,pageSize);
        List<AdminUserDTO> adminUsers;
        if (StringUtils.isEmpty(search)){
            adminUsers = adminUserMapper.findByAdminUsers();
        }else{
            adminUsers = adminUserMapper.findByAdminUsersLike(search);
        }
        return new PageInfo<>(adminUsers);
    }

    @Override
    public void switchIsBan(Integer id) {
        adminUserMapper.switchIsBan(id);
    }

    @Override
    public void createAdminUser(AdminUserReq adminUserReq) {
        //创建管理员·加密密码
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(adminUserReq.getPassword());
        adminUserReq.setPassword(password);
        RoleDTO roleDTO = roleMapper.findById(adminUserReq.getRoleId());
        adminUserReq.setRole(roleDTO.getRole());
        adminUserMapper.insert(adminUserReq);
        List<Integer> rolePermissionIds = rolePermissionMapper.findPermissionIdsByRoleId(adminUserReq.getRoleId());
        //创建权限关系
        userRolePermissionMapper.inserts(adminUserReq.getId(),adminUserReq.getRoleId(),rolePermissionIds);
    }

    @Override
    public void deletes(String ids) {
        String[] split = ids.split(",");
        List userIds = Arrays.asList(split);
        userRolePermissionMapper.deletesByUserIds(userIds);
        adminUserMapper.deletesByIds(userIds);
    }

    @Override
    public void updateAdminUserInfo(AdminUserReq adminUserReq) {
        RoleDTO roleDTO = roleMapper.findById(adminUserReq.getRoleId());
        adminUserReq.setRole(roleDTO.getRole());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(adminUserReq.getPassword());
        adminUserReq.setPassword(password);
        adminUserMapper.updateAdminUserInfo(adminUserReq);
        //删除已有权限
        AdminUserDTO userDTO = adminUserMapper.findById(adminUserReq.getId());
        if (!userDTO.getRole().equals(roleDTO.getRole())){
            userRolePermissionMapper.deletesByUserIdAndRoleId(adminUserReq.getId(),userDTO.getRoles().get(0).getId());
            List<Integer> rolePermissionIds = rolePermissionMapper.findPermissionIdsByRoleId(adminUserReq.getRoleId());
            //创建权限关系
            userRolePermissionMapper.inserts(adminUserReq.getId(),adminUserReq.getRoleId(),rolePermissionIds);
        }
    }

    @Override
    public void patchAdminUserPermission(AdminUserPermissionDTO adminUserPermissionDTO) {
        List<Integer> permissionIds = userRolePermissionMapper.findByUserId(adminUserPermissionDTO.getId());
        List<Integer> integers = Arrays.asList((Integer[]) ConvertUtils.convert(adminUserPermissionDTO.getPermissionIds(), Integer.class));
        //相同权限不变
        List<Integer> saveList = integers.stream().filter(permissionIds::contains).collect(Collectors.toList());
        List<Integer> missionIds = new ArrayList<>(integers);
        missionIds.removeAll(saveList);
        //多余权限新增
        if (missionIds.size() != 0){
            userRolePermissionMapper.inserts(adminUserPermissionDTO.getId(),0,missionIds);
        }
        permissionIds.removeAll(saveList);
        if (permissionIds.size() != 0){
            userRolePermissionMapper.deletesByPermissionIds(permissionIds,adminUserPermissionDTO.getId());
        }
    }

    @Override
    public List<RoleMenuDTO> findByMenus(String username) {
        List<RoleMenuDTO> roleMenus = roleMenuMapper.findMenusByUsername(username);
        return TreeHelper.getSortedNodes(roleMenus);
    }
}
