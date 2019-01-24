package com.axp.service.newRedPaper;

import javax.servlet.http.HttpServletRequest;

public interface CheckApplyService extends IBaseNewRedPaperService{

	void list(HttpServletRequest request);

	void edit(HttpServletRequest request);

	void save(HttpServletRequest request);

}
