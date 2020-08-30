package com.xypsp.admin.domain.response;

import lombok.Data;

/**
 * @author rp
 */
@Data
public class WelcomeVO {

    private Long orderSale;

    private Long usersCount;

    private Long transferInfoCount;

    public static WelcomeVO build(Long orderSale,Long usersCount,Long transferInfoCount){
        WelcomeVO welcomeVO = new WelcomeVO();
        welcomeVO.setOrderSale(orderSale);
        welcomeVO.setUsersCount(usersCount);
        welcomeVO.setTransferInfoCount(transferInfoCount);
        return welcomeVO;
    }
}
