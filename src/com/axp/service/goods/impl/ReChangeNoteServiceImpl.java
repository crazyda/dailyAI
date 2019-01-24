package com.axp.service.goods.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axp.dao.ReChangeNoteDao;
import com.axp.model.ChangeNote;
import com.axp.query.PageResult;
import com.axp.service.goods.ReChangeNoteService;
import com.axp.service.invoke.impl.BaseServiceImpl;

@Service
public class ReChangeNoteServiceImpl extends BaseServiceImpl implements ReChangeNoteService{

	@Autowired
	ReChangeNoteDao reChangeNoteDao;
	@Override
	public PageResult<ChangeNote> getCheckPageresult(Integer currentPage, Integer pageSize) {
		return reChangeNoteDao.getCheckPageresult(currentPage, pageSize);
	}
	@Override
	public ChangeNote get(Integer checkId) {
		return reChangeNoteDao.findById(checkId);
	}
	@Override
	public Map<String, Object> doCheck(Map<String, Object> returnMamp, Boolean isPass, Integer checkId,String checkRemark)
			throws Exception {
		ChangeNote changeNote = reChangeNoteDao.findById(checkId);
		if (isPass) {
			changeNote.setCheckStatus(true);
		}else{
			changeNote.setCheckStatus(null);
		}
		changeNote.setCheckRemark(checkRemark);
		reChangeNoteDao.saveOrUpdate(changeNote);
		returnMamp.put("success", true);
        returnMamp.put("message", "操作完成；");
        return returnMamp;
	}

}
