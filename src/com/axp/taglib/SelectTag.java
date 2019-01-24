/**
 * 
 */
package com.axp.taglib;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.commons.lang.StringUtils;

import com.axp.dao.DateBaseDAO;
import com.axp.util.ToolSpring;


/*** 下拉标签
 *<p>Title: </p>
 *<p>Description: </p> 
 *<p>Company: 广州爱小屏信息科技有限公司</p>
 * @author zhangpeng* @date 2015-6-13
 */
@SuppressWarnings("serial")
public class SelectTag extends TagSupport{
	private String name;//名字
	private String tableName;//表名
	private String optionValue;//值
	private String defaultOptionText;//默认
	private String key;//传出值
	private String style;//样式
	private String size;
	private String onChange;//改变方法
	private String id;
	private String where;//条件
	private String order;//排序
	private String showProperty;//显示文本属性
	private String parentId;//父级级联标签id
	private String parentValue;//父级值
	private String parentIdName;//父级关联id名字
	private String defalutValue ;
	
	public int doStartTag() {
		//初始化默认值
		if(defalutValue == null)
			defalutValue = "";
		StringBuffer sb = new StringBuffer(200);
		JspWriter out = pageContext.getOut();
		sb.append(createJs());
		//创建下拉标签
		sb.append(createSelectHtml());
		StringBuffer option = new StringBuffer(100);
		String text = "";//被选择上文本内容
		
		List<Object> lists = getList();
		for (Object object : lists) {
			Object[] obj = (Object[]) object;
			String keyString = String.valueOf(obj[0]);
			String textString =String.valueOf(obj[1]);
			//得到值得文本内容
			if(optionValue != null && keyString.equals(optionValue))
			{
				text = textString;
				continue;
			}
			//添加下拉标签
			option.append("<option value='"+keyString+"' >"+textString+"</option>");
		}
		//添加默认
		if(!StringUtils.isBlank(optionValue)&&Integer.parseInt(optionValue)!=-1)
			sb.append("<option selected='selected' value='"+optionValue+"'  >"+text+"</option>");
		if(!StringUtils.isBlank(defaultOptionText))
			sb.append("<option value='"+defalutValue+"' >"+defaultOptionText+"</option>");
		sb.append(option);
		//收尾
		sb.append("</select>");
		try {
			out.print(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;// 标签执行完后，继续执行后面的
	}
	/**创建下拉js
	 * @return
	 *@author zhangpeng
	 *@time 2015-6-24
	 */
	private StringBuffer createJs() {
		StringBuffer sb = new StringBuffer("");
		if(StringUtils.isBlank(parentId))
		 return sb;
		ServletRequest request =  pageContext.getRequest();
		String basePath = request.getServletContext().getAttribute("BASEPATH_IN_SESSION").toString();
		
		//构造js
		sb.append("<script type='text/javascript'>");
		sb.append(" $(function(){ $('#"+parentId+"').change(function(){");
		sb.append("$('#"+id+" option').remove();");
		sb.append("var parentVal = $('#"+parentId+"').val();");
		sb.append("var url = '"+basePath+"tag/selectTag/tag';");
		sb.append("var val = {'tableName':'"+tableName+"',");
		sb.append("'defaultOptionText':'"+defaultOptionText+"',");
		sb.append("'defalutValue':'"+defalutValue+"',");
		sb.append("'parentProperty':'"+parentIdName+"',");
		sb.append("'showProperty':'"+showProperty+"',");
		sb.append("'where':'"+where+"',");
		sb.append("'parentIdVal':parentVal,");
		sb.append("'key':'"+key+"'");
		sb.append("};");
		sb.append("$.post(url,val,function(data){");
		sb.append("$('#"+id+"').append(data);");
		sb.append("});});});</script>");
		
		return sb;
	}
	/**
	 * 下拉标签
	 *@author zhangpeng
	 *@time 2015-6-23
	 */
	private StringBuffer createSelectHtml() {
		StringBuffer sb = new StringBuffer(100);
		sb.append(" <select  name='"+name+"' id = '"+id+"' ");//头部
		if(!StringUtils.isBlank(style))
			sb.append("style = '"+style+"'");
		if(!StringUtils.isBlank(size))
			sb.append("size = '"+size+"'");
		if(!StringUtils.isBlank(style))
			sb.append("onChange = '"+onChange+"'");
		sb.append(">");
		return sb;
	}
	/**得到符合条件的集合
	 * @return
	 *@author zhangpeng
	 *@time 2015-6-23
	 */
	private List<Object > getList() {
		//拼接sql
		String sql = "select " + key + "," + showProperty + " from "+tableName;
		sql += " where 1=1 and  isvalid = 1";
		//如果父级有值
		if(StringUtils.isNotBlank(parentId) && StringUtils.isNotBlank(parentValue) && StringUtils.isNotBlank(parentIdName))
			sql += " and "+parentIdName+" = "+parentValue;
		
		if(!StringUtils.isBlank(where)) 
		 sql += " and "+where;

		if(!StringUtils.isBlank(order)) 
			sql += " order by "+order;
		DateBaseDAO dateBaseDAO = (DateBaseDAO) ToolSpring.getBean("dateBaseDAO");
		return dateBaseDAO.findBySql(sql);
	}

	public int doEndTag() {
	  return SKIP_BODY;// 标签执行完后，不继续执行后面的
	 }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getOptionValue() {
		return optionValue;
	}

	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}

	public String getDefaultOptionText() {
		return defaultOptionText;
	}

	public void setDefaultOptionText(String defaultOptionText) {
		this.defaultOptionText = defaultOptionText;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getOnChange() {
		return onChange;
	}

	public void setOnChange(String onChange) {
		this.onChange = onChange;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getShowProperty() {
		return showProperty;
	}

	public String getDefalutValue() {
		return defalutValue;
	}
	public void setDefalutValue(String defalutValue) {
		
		this.defalutValue = defalutValue;
	}
	public void setShowProperty(String showProperty) {
		this.showProperty = showProperty;
	}
	public String getParentId() {
		return parentId;
	}
	public String getParentIdName() {
		return parentIdName;
	}
	public void setParentIdName(String parentIdName) {
		this.parentIdName = parentIdName;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getParentValue() {
		return parentValue;
	}
	public void setParentValue(String parentValue) {
		this.parentValue = parentValue;
	}
}
