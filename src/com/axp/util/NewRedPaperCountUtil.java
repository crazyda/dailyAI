package com.axp.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.axp.dao.DateBaseDAO;
import com.axp.form.newRedPaper.NewRedPaperTotal;
import com.axp.model.NewRedPaperAddendum;
import com.axp.model.NewRedPaperSetting;





//统计红包情况
public class NewRedPaperCountUtil {

	private DateBaseDAO dateBaseDAO;
	public NewRedPaperCountUtil(){
		dateBaseDAO = (DateBaseDAO) ToolSpring.getBean("dateBaseDAO");
	}
	
	public NewRedPaperTotal getTotal(String name ,List<NewRedPaperSetting> list,Timestamp beginTime){
		List<NewRedPaperAddendum> nrpaList = new ArrayList<NewRedPaperAddendum>();
		double usertotalMoney =0;
		NewRedPaperTotal nrpt = new NewRedPaperTotal();
		QueryModel qm = new QueryModel();
		
		try {
			for(NewRedPaperSetting n : list){
				
				nrpt.setTotalNum(nrpt.getTotalNum()+n.getAllNum());
				nrpt.setAlreadyReceiveNum(nrpt.getAlreadyReceiveNum()+n.getAllNumColl());
				//nrpt.setNotReceiveNum(nrpt.getNotReceiveNum()+n.getAllNumUsed());
				nrpt.setTotalMoney(CalcUtil.add(nrpt.getTotalMoney(),n.getAllMoney(),2));
				nrpt.setAlreadyReceiveMoney(CalcUtil.add(nrpt.getAlreadyReceiveMoney()+n.getAllMoneyUsed(),2));
				qm.clearQuery();
				qm.combPreEquals("setting.id", n.getId(),"sett");
				nrpaList = null;
				nrpaList = dateBaseDAO.findLists(NewRedPaperAddendum.class, qm);
				for(NewRedPaperAddendum a : nrpaList){
					usertotalMoney = CalcUtil.add(usertotalMoney, a.getAvail(),2);
					
				}
				if(n.getBeginTime().getTime()>beginTime.getTime()){					
					nrpt.setAddMoney(CalcUtil.add(nrpt.getAddMoney(),n.getAllMoney(),2));
					nrpt.setAddNum(nrpt.getAddNum()+n.getAllNum());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nrpt.setName(name);
		nrpt.setNotReceiveNum(nrpt.getTotalNum()-nrpt.getAlreadyReceiveNum());
		nrpt.setNotReceiveMoney(CalcUtil.sub(nrpt.getTotalMoney(),nrpt.getAlreadyReceiveMoney()));
		nrpt.setUserMoney(CalcUtil.sub(nrpt.getAlreadyReceiveMoney(),usertotalMoney));

		return nrpt;
	}
}
