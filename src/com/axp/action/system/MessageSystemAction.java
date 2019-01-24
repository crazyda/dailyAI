package com.axp.action.system;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.dao.AdminUserDAO;
import com.axp.dao.MessageTypeDao;
import com.axp.dao.ProvinceEnumDAO;
import com.axp.model.AdminUser;
import com.axp.model.MessageType;
import com.axp.model.ProvinceEnum;
import com.axp.service.professional.UserService;
import com.axp.util.QueryModel;

@RequestMapping("/system/message")
@Controller
public class MessageSystemAction extends BaseAction{
	@Resource
	private UserService userService;
	@Autowired
	private ProvinceEnumDAO provinceEnumDAO;
	@Autowired
	private AdminUserDAO adminUserDAO;
	@Autowired
	private MessageTypeDao messageTypeDao;
	@IsItem(firstItemName = "系统管理", secondItemName = "消息类别")
	@NeedPermission(permissionName = "消息类别", classifyName = "系统管理")
	@RequestMapping("/list")
	public String list(HttpServletRequest request,HttpServletResponse response){
		messageTypeService.list(request, response);
		return "professional/message/MessageTypeOflist";
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,HttpServletResponse response,Integer id){
		messageTypeService.add(id, request);
		return "professional/message/addMessageType";
	}
	
	@RequestMapping("/save")
	public String save(HttpServletRequest request,Integer id,Integer level,Integer isorder,String iconPic){
		messageTypeService.save(id, level, isorder, iconPic, request);
		return "redirect:list";
	}
	
	@RequestMapping("/del")
	public String del(HttpServletRequest request,Integer id){
		messageTypeService.del(id, request);
		return "redirect:list";
	}
	
	
	@IsItem(firstItemName = "系统管理", secondItemName = "消息中心")
	@NeedPermission(permissionName = "消息中心", classifyName = "系统管理")
	@RequestMapping("/MessageList")
	public String MessageList(HttpServletRequest request,HttpServletResponse response){		
		userSystemMessageService.list(request, response);
		return "professional/message/messagelist";
	}
	
	
	@RequestMapping("/messagesave")
	public String savemessage(HttpServletRequest request,Integer orderId,Integer userId,String typeId,String content,String title){
		String province = request.getParameter("province")==null?"":request.getParameter("province");
		String city = request.getParameter("city")==null?"":request.getParameter("city");
		String county = request.getParameter("county")==null?"":request.getParameter("county");
		String image = request.getParameter("image")==null?"":request.getParameter("image");
		String icon = request.getParameter("icon")==null?"":request.getParameter("icon");
		if(typeId.equals("13")){
			userSystemMessageService.saveMessages(request, orderId, userId, typeId, content, title, province, city, county,image,icon);
		}else{
			userSystemMessageService.saveallmessage(userId, orderId, typeId, content, title, request);;
		}
		return "redirect:MessageList";
	}
	
	
	@RequestMapping("/sendmsg")
	public String sendmsg(HttpServletRequest request,HttpServletResponse response,Integer id){
		Integer level =  (Integer) request.getSession().getAttribute("userLevel");
		Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		List<ProvinceEnum> zoneList=null;
		List<ProvinceEnum> distrtList=null;
		AdminUser au = adminUserDAO.findById(current_user_id);
		if(level>=95){
				zoneList = userService.getZone(1);
			}else if(level==75){
				if (au.getProvinceEnum().getLevel()==3) {
					zoneList=provinceEnumDAO.findByPropertyWithValid("id", au.getProvinceEnum().getId());
				}else{
					zoneList =provinceEnumDAO.findByPropertyWithValid("provinceEnum.id", au.getProvinceEnum().getId());
				}
			}else{
				zoneList =provinceEnumDAO.findByPropertyWithValid("provinceEnum.id", au.getProvinceEnum().getId()); 
				
			}
		request.setAttribute("zoneList", zoneList);
		request.setAttribute("distrtList", distrtList);
		request.setAttribute("level", level);
		request.setAttribute("au", au);
		userSystemMessageService.add(id, request);
		return "professional/message/sendMessage";
	}
	
	@RequestMapping("/msgajax")
	@ResponseBody
	public List getMessageList(Integer id,HttpServletRequest request,HttpServletResponse response){		
		ArrayList list = new ArrayList();
		QueryModel queryModel = new QueryModel();
		queryModel.clearQuery();
		queryModel.combEquals("parentId", id);
		queryModel.combPreEquals("isValid", true);
		if(id!=null){
		List<MessageType> mlist1= dateBaseDao.findLists(MessageType.class, queryModel);
		
		
		for (MessageType messageType : mlist1) {
			Map<String,Object> slist =new HashMap<String, Object>();
			slist.put("para1",messageType.getTitle()!=null?messageType.getTitle():"暂无" );
			slist.put("value2", messageType.getId()!=null?messageType.getId():0);
			list.add(slist);
		}
		
		}
				
		return  list;
	}
	
	@RequestMapping("/changeZone")
	@ResponseBody
	public Map<String,String> changeZone(Integer zonesid) {
		List<String> keys = new ArrayList<String>();
		keys.add("isValid = ?");
		keys.add("provinceEnum.id = ?");
		List<Object> values = new ArrayList<Object>();
		values.add(true);
		values.add(zonesid);
		List<ProvinceEnum> list = provinceEnumDAO.findListByCustom(ProvinceEnum.class,
				keys, values, null, null, null);
		Map<String,String> map = new HashMap<String,String>();
		
		for (ProvinceEnum s : list) {
			map.put(s.getId().toString(), s.getName());
		}
		return map;
	}
	

	@RequestMapping("/changeType")
	@ResponseBody
	public Map<String, String> changeType(Integer typeId){
		List<String> keys = new ArrayList<String>();
		keys.add("isValid = ?");
		keys.add("messageType.id = ?");
		List<Object> values = new ArrayList<Object>();
		values.add(true);
		values.add(typeId);
		List<MessageType> list = messageTypeDao.findListByCustom(MessageType.class,
				keys, values, null, null, null);
		Map<String,String> map = new HashMap<String,String>();
		
		for (MessageType m : list) {
			map.put(m.getId().toString(), m.getTitle());
		}
		return map;
	}
}
