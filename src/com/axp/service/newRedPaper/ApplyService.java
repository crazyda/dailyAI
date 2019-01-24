package com.axp.service.newRedPaper;

import javax.servlet.http.HttpServletRequest;

public interface ApplyService extends IBaseNewRedPaperService {

	void saveApplyLog(HttpServletRequest request);

	void list(HttpServletRequest request);

}
