package com.axp.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.axp.dao.DateBaseDAO;
import com.axp.dao.ReChangeNoteDao;
import com.axp.model.ChangeNote;
import com.axp.query.PageResult;
import com.axp.util.QueryModel;

@Repository
public class ReChangeDaoImpl extends BaseDaoImpl<ChangeNote> implements ReChangeNoteDao{
	@Autowired
 	private  DateBaseDAO  dateBaseDAO;
	@Override
	public PageResult<ChangeNote> getCheckPageresult(Integer currentPage, Integer pageSize) {
        List<ChangeNote> list= null;
        QueryModel queryModel = new QueryModel();
        queryModel.combPreEquals("isValid", true);
        queryModel.combPreEquals("checkStatus", false);
        Integer count = dateBaseDAO.findCount(ChangeNote.class, queryModel);
		list=	dateBaseDAO.findPageList(ChangeNote.class, queryModel, (currentPage-1)*pageSize, pageSize);
        //返回结果；
        return new PageResult<>(Integer.parseInt(count.toString()), pageSize, currentPage, list);
	}

}
