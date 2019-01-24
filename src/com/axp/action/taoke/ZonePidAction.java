package com.axp.action.taoke;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.dao.AdminUserDAO;
import com.axp.model.AdminUser;
import com.axp.util.UrlUtil;

@Controller
@RequestMapping("/taoke/zonePid")
public class ZonePidAction {
	
	@Autowired
	AdminUserDAO adminUserDAO;
	//访问权限KEY
	String key= "8f387fa40ad6ba508d50f262936983f3";
	
	@NeedPermission(permissionName = "代理收益", classifyName = "淘客管理")
	@IsItem(firstItemName = "淘客管理", secondItemName = "代理收益")
	@RequestMapping("/list")
	public String list(HttpServletRequest request){
		Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		request.setAttribute("uri", "http://hhh.aixiaoping.cn/home/Partner/index?&adminuserId="+current_user_id);
		GetData gd=new GetData(current_user_id); 
        gd.start();
		return "taoke/show";
	}
	

	
	
	@NeedPermission(permissionName = "优买吧商品", classifyName = "淘客管理")
	@IsItem(firstItemName = "淘客管理", secondItemName = "优买吧商品")
	@RequestMapping("/list1")
	public String  list1(HttpServletRequest request){
		request.setAttribute("uri", "http://www.518wtk.com/admin.php/Collect/impcollectListymb?&key="+key);
		return "taoke/show";
	}
	
	@NeedPermission(permissionName = "大淘客商品", classifyName = "淘客管理")
	@IsItem(firstItemName = "淘客管理", secondItemName = "大淘客商品")
	@RequestMapping("/list2")
	public String  list2(HttpServletRequest request){
		request.setAttribute("uri", "http://www.518wtk.com/admin.php/Collect/impcollectList?&key="+key);
		return "taoke/show";
	}
	
	
	@NeedPermission(permissionName = "当前数据", classifyName = "淘客管理")
	@IsItem(firstItemName = "淘客管理", secondItemName = "当前数据")
	@RequestMapping("/list3")
	public String  list3(HttpServletRequest request){
		request.setAttribute("uri", "http://www.518wtk.com/admin.php/Collect/impcollectLists?&key="+key);
		return "taoke/show";
	}
	
	
	@NeedPermission(permissionName = "品牌列表", classifyName = "淘客管理")
	@IsItem(firstItemName = "淘客管理", secondItemName = "品牌列表")
	@RequestMapping("/list4")
	public String  list4(HttpServletRequest request){
		request.setAttribute("uri", "http://www.518wtk.com/admin.php/Brand/impindex?&key="+key);
		return "taoke/show";
	}
	
	
	@NeedPermission(permissionName = "添加品牌", classifyName = "淘客管理")
	@IsItem(firstItemName = "淘客管理", secondItemName = "添加品牌")
	@RequestMapping("/list5")
	public String  list5(HttpServletRequest request){
		request.setAttribute("uri", "http://www.518wtk.com/admin.php/Brand/impedit?&key="+key);
		return "taoke/show";
	}
	
	
	@NeedPermission(permissionName ="YMB_Session设置",classifyName ="淘客管理")
	@IsItem(firstItemName="淘客管理" , secondItemName ="YMB_Session设置")
	@RequestMapping("list6")
	public String list6(HttpServletRequest request){
		Integer current_adminuserId = (Integer) request.getSession().getAttribute("currentUserId");
		AdminUser adminUser=adminUserDAO.findById(current_adminuserId);
		if (adminUser.getLevel()>=95) {
			request.setAttribute("uri", "http://www.518wtk.com/admin.php/Collect/session?&key="+key);
		}else{
			request.getSession().setAttribute("errorstr", "您没有访问权限！");
	         return "error/taoke_error";
		}
		return "taoke/show";
	}
	
	@NeedPermission(permissionName ="YMB_Session修改",classifyName ="淘客管理")
	@IsItem(firstItemName="淘客管理" , secondItemName ="YMB_Session修改")
	@RequestMapping("list7")
	public String list7(HttpServletRequest request){
		Integer current_adminuserId = (Integer) request.getSession().getAttribute("currentUserId");
		AdminUser adminUser=adminUserDAO.findById(current_adminuserId);
		if (adminUser.getLevel()>=95) {
			request.setAttribute("uri", "http://www.518wtk.com/admin.php/Collect/keyedit?&key="+key);
		}else{
			 request.getSession().setAttribute("errorstr", "您没有访问权限！");
	         return "error/taoke_error";
		}
		
		return "taoke/show";
	}
}

class GetData extends Thread{  
    private Integer userId;  
    public GetData(Integer userId) {  
       this.userId=userId;  
    }  
    public void run() {  
    	System.out.println("线程启动："+userId);
    	UrlUtil.getTaoKeToMap("http://hhh.aixiaoping.cn/home/taoke/indexDaili?&adminuserId="+userId);
    	System.out.println("线程结束："+userId);
         
    }  
}  
