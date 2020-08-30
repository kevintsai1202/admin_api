package com.xypsp.admin.utils;


import com.xypsp.admin.domain.model.admin.RoleMenuDTO;
import com.xypsp.admin.domain.model.admin.TreeRoleMenuDTO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author rp
 */
public class TreeHelper {

    public static List<RoleMenuDTO> getSortedNodes(List<RoleMenuDTO> datas) {
        List<RoleMenuDTO> nodes = convertData2Node(datas);
        // 拿到根节点
        return getRootNodes(nodes);
    }

    private static List<RoleMenuDTO> convertData2Node(List<RoleMenuDTO> nodes) {
        for (int i = 0; i < nodes.size(); i++) {
            RoleMenuDTO n = nodes.get(i);
            for (int j = i + 1; j < nodes.size(); j++) {
                RoleMenuDTO m = nodes.get(j);
                if (Objects.equals(m.getParentId(), n.getId())) {
                    if (StringUtils.isEmpty(n.getChildren())){
                        n.setChildren(new ArrayList<>());
                    }
                    n.getChildren().add(m);
                }
            }
        }
        return nodes;
    }

    private static List<RoleMenuDTO> getRootNodes(List<RoleMenuDTO> nodes) {
        List<RoleMenuDTO> root = new ArrayList<>();
        for (RoleMenuDTO node : nodes) {
            if (node.getParentId() == null || node.getParentId() == 0) {
                root.add(node);
            }
        }
        return root;
    }

    private static void addNode(List<RoleMenuDTO> nodes, RoleMenuDTO node, int currentLevel) {
        nodes.add(node);
        if (node.getChildren().size() == 0) {
            return;
        }
        for (int i = 0; i < node.getChildren().size(); i++) {
            addNode(nodes, node.getChildren().get(i), currentLevel + 1);
        }
    }


    public static List<TreeRoleMenuDTO> getSortedTreeNodes(List<TreeRoleMenuDTO> datas) {
        List<TreeRoleMenuDTO> nodes = convertData2TreeNode(datas);
        // 拿到根节点
        return getTreeRootNodes(nodes);
    }

    private static List<TreeRoleMenuDTO> convertData2TreeNode(List<TreeRoleMenuDTO> nodes) {
        for (int i = 0; i < nodes.size(); i++) {
            TreeRoleMenuDTO n = nodes.get(i);
            for (int j = i + 1; j < nodes.size(); j++) {
                TreeRoleMenuDTO m = nodes.get(j);
                if (Objects.equals(m.getParentId(), n.getValue())) {
                    if (StringUtils.isEmpty(n.getChildren())){
                        n.setChildren(new ArrayList<>());
                    }
                    n.getChildren().add(m);
                }
            }
        }
        return nodes;
    }
    private static List<TreeRoleMenuDTO> getTreeRootNodes(List<TreeRoleMenuDTO> nodes) {
        List<TreeRoleMenuDTO> root = new ArrayList<>();
        for (TreeRoleMenuDTO node : nodes) {
            if (node.getParentId() == null || node.getParentId() == 0) {
                root.add(node);
            }
        }
        return root;
    }
}
