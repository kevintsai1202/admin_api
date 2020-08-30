package com.xypsp.admin.domain.model.admin;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rp
 */
@Data
public class TreeRoleMenuDTO {

    private Integer value;

    private Integer parentId;

    private Integer level;

    private String key;
    /**
     * 名称
     * */
    private String title;

    public List<TreeRoleMenuDTO> children = null;


}
