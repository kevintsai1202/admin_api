package com.xypsp.admin.controller.web;

import com.xypsp.admin.domain.mapper.web.TransferInfoMapper;
import com.xypsp.admin.domain.model.web.TransferInfoDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author rp
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransferInfoControllerTest {

    @Autowired
    private TransferInfoMapper transferInfoMapper;

    @Test
    public void findByTransferInfos() {
        List<TransferInfoDTO> infoDTOList = transferInfoMapper.findPageByTransferInfos(1, 10);
        System.out.println(infoDTOList.size());
    }
}