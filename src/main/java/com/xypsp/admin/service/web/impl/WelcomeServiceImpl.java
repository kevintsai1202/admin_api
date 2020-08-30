package com.xypsp.admin.service.web.impl;

import com.xypsp.admin.domain.mapper.web.OrderMapper;
import com.xypsp.admin.domain.mapper.web.TransferInfoMapper;
import com.xypsp.admin.domain.mapper.web.UserMapper;
import com.xypsp.admin.domain.response.WelcomeVO;
import com.xypsp.admin.service.web.WelcomeService;
import org.springframework.stereotype.Service;

/**
 * @author rp
 */
@Service
public class WelcomeServiceImpl implements WelcomeService {

    private final UserMapper userMapper;

    private final TransferInfoMapper transferInfoMapper;

    private final OrderMapper orderMapper;

    public WelcomeServiceImpl(UserMapper userMapper, TransferInfoMapper transferInfoMapper, OrderMapper orderMapper) {
        this.userMapper = userMapper;
        this.transferInfoMapper = transferInfoMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public WelcomeVO welcomeCount() {
        Long userCount = userMapper.countUsers();
        Long transferInfoCount = transferInfoMapper.countTransferInfos();
        Long orderSale = orderMapper.countSales();
        return WelcomeVO.build(orderSale,userCount,transferInfoCount);
    }
}
