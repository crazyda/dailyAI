package com.axp.action.taoke;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.model.PartnerAdminuserPidDistribute;
import com.axp.model.Users;
import com.axp.service.taoke.CityPidDistributeService;
import com.axp.service.taoke.PartnerPidDistributeService;
import com.axp.util.QueryModel;

@Controller
@RequestMapping("/taoke/Mypid")
public class MyPidAction extends BaseAction{
	@Resource 
	private CityPidDistributeService cityPidDistributeService;
	@Resource
	private PartnerPidDistributeService partnerPidDistributeService;
	@NeedPermission(permissionName="我的PID",classifyName="淘客管理")
	@IsItem(firstItemName="淘客管理",secondItemName="我的PID")
	@RequestMapping("/getlist")
	public String getList(HttpServletRequest request,HttpServletResponse response){
		cityPidDistributeService.getList(request, response);
		return "taoke/myPid/list";
	}
	
	//分配
	@RequestMapping("/isdistribute")
	public String  isDistribute(Integer ids,HttpServletRequest request,HttpServletResponse response){
		partnerPidDistributeService.isDistribute(ids, request, response);
		return "taoke/myPid/distribute";
	}
	
	//取消分配
	@RequestMapping("/ofdistribute")
	public String ofDistribute(Integer id,HttpServletRequest request,HttpServletResponse response){
		partnerPidDistributeService.ofDistribute(id, request, response);
		return "redirect:getlist";
	}
	
	//保存分配
	@RequestMapping("/savedistribute")
	public String saveDistribute(Integer id,String pidId,String username,Integer adminuserId,Timestamp createtime,
			String name,HttpServletRequest request,HttpServletResponse response){
		partnerPidDistributeService.saveDistribute(id, pidId, username, adminuserId, createtime, name, request, response);
		return "redirect:getlist";
	}
	
	//判断用户是否存在
	@RequestMapping("/checkUserNameByAjax")
	@ResponseBody
	public List<String> checkUserNameByAjax(HttpServletRequest request){
		String name = request.getParameter("name")==null?"":request.getParameter("name");
		List<String> list = new ArrayList<String>();
		QueryModel queryModel = new QueryModel();
		if (StringUtils.isNotBlank(name)) {
			queryModel.clearQuery();
			queryModel.combPreEquals("isValid", true);
			queryModel.combPreEquals("name", name);
			Users users = (Users) dateBaseDao.findOne(Users.class, queryModel);
			//是否存在该用户
			if (users!=null) {
				//判断该用户是否已被分配
				queryModel.clearQuery();
				queryModel.combPreEquals("usersId", users.getId());
				queryModel.combPreEquals("isValid", true);
				PartnerAdminuserPidDistribute partnerAdminuserPidDistribute=(PartnerAdminuserPidDistribute) dateBaseDao.findOne(PartnerAdminuserPidDistribute.class, queryModel);
				if (partnerAdminuserPidDistribute!=null) {
					if (partnerAdminuserPidDistribute.getUsers().getName().equals(name)) {
						//该用户已被分配返回"2"
						list.add("2");
						return list;
					}
				}
			}else{
				//不存在该粉丝账号则返回"1"
				list.add("1");
				return list;
			}
			
		}
		list.add("0");
		return list;
	}
}
