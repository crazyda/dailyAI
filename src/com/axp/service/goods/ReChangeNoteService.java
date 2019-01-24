package com.axp.service.goods;


import java.util.Map;

import com.axp.model.ChangeNote;
import com.axp.query.PageResult;
public interface ReChangeNoteService {

	PageResult<ChangeNote> getCheckPageresult(Integer currentPage, Integer pageSize);
	
	ChangeNote get(Integer checkId);
	
	 Map<String, Object> doCheck(Map<String, Object> returnMamp, Boolean isPass, Integer checkId,String checkRemark) throws Exception;
}
