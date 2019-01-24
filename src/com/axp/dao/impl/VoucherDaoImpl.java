package com.axp.dao.impl;

import org.springframework.stereotype.Repository;

import com.axp.dao.VoucherDao;
import com.axp.model.Voucher;
@Repository("voucherDao")
public class VoucherDaoImpl extends BaseDaoImpl<Voucher> implements VoucherDao{

}
