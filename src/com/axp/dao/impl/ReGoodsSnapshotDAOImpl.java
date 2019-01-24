package com.axp.dao.impl;

import org.springframework.stereotype.Repository;

import com.axp.dao.ReGoodsSnapshotDAO;
import com.axp.model.ReGoodsOfBase;
import com.axp.model.ReGoodsSnapshot;

@Repository
public class ReGoodsSnapshotDAOImpl extends BaseDaoImpl<ReGoodsSnapshot> implements ReGoodsSnapshotDAO {

    @Override
    public ReGoodsSnapshot saveByBaseGoods(Integer id, ReGoodsOfBase baseGoods) {

        ReGoodsSnapshot snapshot;
        if (id == null) {//保存；
            snapshot = new ReGoodsSnapshot();
        } else {
            snapshot = findById(baseGoods.getSnapshotId());
        }

        snapshot.setSign(baseGoods.getSign());
        snapshot.setName(baseGoods.getName());
        snapshot.setCoverPic(baseGoods.getCoverPic());
        snapshot.setType(baseGoods.getType());
        snapshot.setLables(baseGoods.getLables());
        snapshot.setDescriptionPics(baseGoods.getDescriptionPics());
        snapshot.setSeller(baseGoods.getSeller());
        snapshot.setIntro(baseGoods.getIntro());
        snapshot.setDetails(baseGoods.getDetails());
        snapshot.setBaseGoodsId(baseGoods.getId());
        save(snapshot);
        return snapshot;
    }

}
