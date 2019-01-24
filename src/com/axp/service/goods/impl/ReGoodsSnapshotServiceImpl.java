package com.axp.service.goods.impl;

import org.springframework.stereotype.Service;

import com.axp.model.ReGoodsSnapshot;
import com.axp.service.goods.ReGoodsSnapshotService;
import com.axp.service.system.impl.BaseServiceImpl;

@Service
public class ReGoodsSnapshotServiceImpl extends BaseServiceImpl implements ReGoodsSnapshotService {

    @Override
    public ReGoodsSnapshot get(Integer id) {
        return reGoodsSnapshotDAO.findById(id);
    }
}
