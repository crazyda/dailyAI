package com.axp.dao;

import com.axp.model.ChangeNote;
import com.axp.query.PageResult;

public interface ReChangeNoteDao extends IBaseDao<ChangeNote>{
	PageResult<ChangeNote> getCheckPageresult(Integer currentPage, Integer pageSize);
}
