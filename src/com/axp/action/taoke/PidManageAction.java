package com.axp.action.taoke;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.model.AdminUser;
import com.axp.model.TkldPid;
import com.axp.query.PageResult;
import com.axp.service.taoke.TaokePidService;
import com.axp.service.taoke.TkldPidService;
import com.axp.util.ResponseResult;

/**
 * 录入Pid信息
 */
@Controller
@RequestMapping("/taoke/pid/")
public class PidManageAction extends BaseAction {

	@NeedPermission(permissionName = "用户留言", classifyName = "淘客管理")
	@IsItem(firstItemName = "淘客管理", secondItemName = "用户留言")
	@RequestMapping("SharePartnerList")
	public String findSharePartnerList(HttpServletRequest request) {
		sharePartnerService.findSharePartnerList(request);
		return "taoke/pidManage/sharePartnerList";
	}

	@NeedPermission(permissionName = "PID列表", classifyName = "淘客管理")
	@IsItem(firstItemName = "淘客管理", secondItemName = "PID列表")
	@RequestMapping("toPidList")
	public String findTkldPidList(HttpServletRequest request) {
		tkldPidService.findTkldPidList(request);
		return "taoke/pidManage/list";
	}

	@NeedPermission(permissionName = "PID查询", classifyName = "淘客管理")
	@IsItem(firstItemName = "淘客管理", secondItemName = "PID查询")
	@RequestMapping("toSearchPid")
	public String toSearchPid(HttpServletRequest request) {
		
		tkldPidService.SearchPid(request);
		
		request.setAttribute("userName", "");
		return "taoke/pidManage/searchPid";
	}

	@NeedPermission(permissionName = "新增PID", classifyName = "淘客管理")
	@IsItem(firstItemName = "淘客管理", secondItemName = "新增PID")
	@RequestMapping("toAddJsp")
	public String toAddJsp(HttpServletResponse response) {
		return "taoke/pidManage/addPid";
	}
	
	@NeedPermission(permissionName = "新增PID(新版)", classifyName = "淘客管理")
	@IsItem(firstItemName = "淘客管理", secondItemName = "新增PID(新版)")
	@RequestMapping("toAddJsps")
	public String toAddJsps(HttpServletResponse response) {
		return "taoke/pidManage/addPidNew";
	}

	@RequestMapping("checkPid")
	public void checkPid(String pid, HttpServletResponse response) {
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			// 判断是否唯一PID
			pw.print(tkldPidService.checkPid(pid));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("findParentTkldPid")
	public void findParentTkldPid(Integer level, HttpServletResponse response) {
		List<TkldPid> list = new ArrayList<TkldPid>();
		PrintWriter pw = null;
		response.setCharacterEncoding("UTF-8");
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			pw = response.getWriter();
			list = tkldPidService.findParentTkldPid(level);
			for (TkldPid t : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("key", t.getRemark());
				map.put("val", t.getId());
				listMap.add(map);
			}
			String json = JSONObject.toJSONString(listMap);
			pw.print(json);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("saveTkldPid")
	public void saveTkldPid(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			boolean flag = tkldPidService.saveTkldPid(request);
			pw.print(flag == true ? "1" : "0");
		} catch (Exception e) {
		}
	}
	
	
	@RequestMapping("saveTKldPid2")
	public void saveTKldPid2(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			boolean flag = tkldPidService.saveTkldPid(request);
			pw.print(flag == true ? "1" : "0");
		} catch (Exception e) {
		}
	}
	

	@RequestMapping("todistributePid")
	public String todistributePid(Integer id, HttpServletRequest request) {
		TkldPid tkldPid = tkldPidService.getTkldPidById(id);
		request.setAttribute("tkldPid", tkldPid);
		if (tkldPid.getLevel() == 1) {
			request.setAttribute("list",
					tkldPidService.findAdminUserByParentId(null));
			return "taoke/pidManage/distributePid";
		}
		return "taoke/pidManage/distributePartnerPid";
	}

	/**
	 * 分配时下拉级联
	 */
	@RequestMapping("changeSelect")
	@ResponseBody
	public List<Map<String, Object>> changeSelect(Integer id,
			HttpServletResponse response) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			List<AdminUser> list = tkldPidService.findAdminUserByParentId(id);
			for (AdminUser t : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("key", t.getLoginname());
				map.put("val", t.getId());
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listMap;
	}

	@RequestMapping("distributePid")
	public void distributePid(Integer userId, Integer daiLiPid, String desc,
			HttpServletResponse response) {
		PrintWriter printWriter = null;
		String result = null;
		try {
			printWriter = response.getWriter();
			// tkldPidService.checkPidByAdminUserId(userId);

			boolean flag = tkldPidService.updateDistributePid(userId, daiLiPid,
					desc);
			result = flag == true ? "1" : "0";
			printWriter.print(result);
			printWriter.flush();
			printWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("toEditPid")
	public String toEditPid(Integer id, HttpServletRequest request) {
		TkldPid tkldPid = tkldPidService.getTkldPidById(id);
		if (tkldPid.getLevel() == 2) {
			request.setAttribute("tkldPid", tkldPid);
			return "taoke/pidManage/distributePartnerPid";
		}
		tkldPidService.findAdminUserSelect(id, request);
		return "taoke/pidManage/distributePid";
	}

	@RequestMapping("toAddEditPid")
	public String toAddEditPid(Integer id, HttpServletRequest request) {
		tkldPidService.editAddPid(id, request);
		return "taoke/pidManage/addPid";
	}

	@RequestMapping("cancelDistributePid")
	public void cancelDistributePid(Integer id, HttpServletResponse response) {
		PrintWriter printWriter = null;
		String result = null;
		try {

			printWriter = response.getWriter();
			boolean flag = tkldPidService.cancelDistributePid(id, response);
			result = flag == true ? "1" : "0";
			printWriter.print(result);
			printWriter.flush();
			printWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("delTkldPid")
	public void delTkldPid(Integer id, HttpServletResponse response) {
		PrintWriter printWriter = null;
		String result = null;
		try {
			printWriter = response.getWriter();
			boolean flag = tkldPidService.delTkldPid(id);
			result = flag == true ? "1" : "0";
			printWriter.print(result);
			printWriter.flush();
			printWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("saveOrUpdatePartnerPid")
	@ResponseBody
	public Map<String, String> saveOrUpdatePartnerPid(
			HttpServletRequest request, Integer id, String userPhone,
			String usersRemark, Integer checkbox) {
		Integer current_user_id = (Integer) request.getSession().getAttribute(
				"currentUserId");
		Map<String, String> map = tkldPidService.saveOrUpdatePartnerPid(id,
				userPhone, usersRemark, checkbox, current_user_id);
		return map;
	}

	@RequestMapping("currentCityIsValid")
	@ResponseBody
	public Map<String, Integer> currentCityIsValid(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Integer> map = null;
		try {
			map = tkldPidService.currentCityIsValid(request);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@RequestMapping("findPidByCondition")
	public String findPidByCondition(HttpServletRequest request) {
		tkldPidService.findPidByCondition(request);
		return "taoke/pidManage/searchPid";
	}

	@NeedPermission(permissionName = "补充合伙人", classifyName = "淘客管理")
	@IsItem(firstItemName = "淘客管理", secondItemName = "补充合伙人")
	@RequestMapping("partnerList")
	public String partnerList(HttpServletRequest request) {
		partnerInformService.findAll(request);
		return "taoke/pidManage/partnerList";
	}

	@RequestMapping("addPartner")
	public void addPartner(Integer id, HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter printWriter = null;
		String result = null;
		try {
			printWriter = response.getWriter();
			boolean flag = partnerInformService.addPartner(id);
			result = flag == true ? "1" : "0";
			printWriter.print(result);
			printWriter.flush();
			printWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("partnerDetail")
	public String partnerDetail(Integer id, HttpServletRequest request) {

		tkldPidService.partnerDetail(id, request);

		return "taoke/pidManage/partnerDetail";
	}

	@RequestMapping("upgradeCareer")
	public void upgradeCareer(HttpServletRequest request,
			HttpServletResponse response) {
		boolean flag = tkldPidService.upgradeCareer(request);
		ResponseResult.printResult(response, flag, null);

	}
	
	
	@RequestMapping("findNextLevelByUserId")
	public String findNextLevelByUserId(HttpServletRequest request,Integer currentPage, Integer pageSize,Integer userId) {
		
		try {
			
		
		 if (currentPage == null || currentPage < 1) {
	            currentPage = 1;
	        }
	        if (pageSize == null || pageSize < 1) {
	            pageSize = 10;
	        }
	        request.setAttribute("pageResult",  tkldPidService.findNextLevelByUserId(request,currentPage, pageSize,userId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	      return "taoke/pidManage/fansDetails";
		
	}
	
	
}
