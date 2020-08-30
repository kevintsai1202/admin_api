package com.xypsp.admin.domain.model.web;

import lombok.Data;

/**
 * @author lj on 2019/11/20 14:55
 */
@Data
public class UserDTO {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户unionId
     */
    private String unionId;

}
