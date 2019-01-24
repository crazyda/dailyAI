package com.axp.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description：查询模型
 * @Time Mar 13, 2015 4:16:23 PM create
 * @version 1.0
 * @author zhangpeng
 */
public class QueryModel {
	/**
	 * 比较类型，大于">"
	 */
	public static final int GREATER = 1;

	/**
	 * 比较类型，大于等于">="
	 */
	public static final int GREATER_EQUALS = 2;

/**
	 * 比较类型，小于"<"
	 */
	public static final int LESS = 3;

	/**
	 * 比较类型，小于等于"<="
	 */
	public static final int LESS_EQUALS = 4;

	/** 比较类型，不等于"<>" */
	public static final int NOT_EQUALS = 5;

	/** 比较类型，等于"=" */
	public static final int EQUALS = 0;

	/** 比较类型，为空"is null" */
	public static final int IS_NULL = 6;

	public static final int MATCH_FRONT = 0;

	public static final int MATCH_BACK = 1;

	public static final int MATCH_ALL = 2;

	public static final int MATCH_PART = 3;

	/** 不包含"not like" */
	public static final int NOT_MATCH = 4;

	/**
	 * 排序方式
	 */
	private String order = "";

	/**
	 * 分组方式
	 */
	private String group = "";

	/**
	 * 查询字段
	 */
	private String colums;

	/**
	 * 查询条件
	 */
	private Map<String, Object> preConditionMap = new HashMap<String, Object>();

	private StringBuffer sb;// 直接条件查询

	private StringBuffer placeholder;// 占位符查询

	private ArrayList<Object> placeholderValues;// 占位符值

	public QueryModel() {
		sb = new StringBuffer();
	}

	/**
	 * 构造方法，带排序
	 * 
	 * @param order
	 *            排序字段
	 */
	public QueryModel(String order) {
		sb = new StringBuffer();
		setOrder(order);
	}

	/**
	 * 通用条件判空方法
	 * 
	 * @param obj
	 *            待判定条件
	 * @return 为空返回true，不为空返回false
	 */
	private static boolean isValEmpty(Object obj) {
		if (null == obj) {
			return true;
		}

		if (obj instanceof String) {
			String str = (String) obj;
			if (null != str && !"".equals(str.trim())) {
				return false;
			}
		}

		if (obj instanceof Integer) {
			Integer num = (Integer) obj;
			if (null != num) {
				return false;
			}
		}
		if (obj instanceof Double) {
			Double num = (Double) obj;
			if (num != null) {
				return false;
			}
		}

		if (obj instanceof BigDecimal) {
			BigDecimal num = (BigDecimal) obj;
			if (num != null) {
				return false;
			}
		}
		if (obj instanceof Date) {
			Date date = (Date) obj;
			if (date != null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 返回比较类型的字符串表现形式
	 * 
	 * @param CompareType
	 *            比较类型
	 * @return 返回字符串表现形式，例如：">"、"<="等
	 */
	private String getSymbol(int CompareType) {
		String symbol = null;
		switch (CompareType) {
		case GREATER:
			symbol = ">";
			break;
		case GREATER_EQUALS:
			symbol = ">=";
			break;
		case LESS:
			symbol = "<";
			break;
		case LESS_EQUALS:
			symbol = "<=";
			break;
		case NOT_EQUALS:
			symbol = "<>";
			break;
		case EQUALS:
			symbol = "=";
		}
		return symbol;
	}

	/**
	 * 加入Like比较
	 * 
	 * @param fieldName
	 *            字段名
	 * @param fieldValue
	 *            字段值
	 * @return 返回like比较条件的SqlBuffer
	 */
	public QueryModel combLike(String fieldName, String fieldValue) {
		if (!isValEmpty(fieldValue)) {
			sb.append(" AND " + fieldName + " like '%"
					+ StringUtil.replaceStr(fieldValue.trim()) + "%'");
		}
		return this;
	}

	/**
	 * 加入Like比较
	 * 
	 * @param fieldName
	 *            字段名
	 * @param fieldValue
	 *            字段值
	 * @param matchType
	 *            匹配方式（前向匹配：QueryModel.MATCH_FRONT；后向匹配：QueryModel.MATCH_BACK；
	 *            全向匹配：QueryModel.MATCH_ALL）
	 * @return 返回like比较条件的SqlBuffer
	 */
	public QueryModel combLike(String fieldName, String fieldValue,
			int matchType) {
		if (!isValEmpty(fieldValue)) {
			if (QueryModel.MATCH_FRONT == matchType) {
				sb.append(" AND " + fieldName + " like '"
						+ StringUtil.replaceStr(fieldValue.trim()) + "%'");
			} else if (QueryModel.MATCH_BACK == matchType) {
				sb.append(" AND " + fieldName + " like '%"
						+ StringUtil.replaceStr(fieldValue.trim()) + "'");
			} else if (QueryModel.MATCH_ALL == matchType) {
				sb.append(" AND " + fieldName + " like '%"
						+ StringUtil.replaceStr(fieldValue.trim()) + "%'");
			} else {
				sb.append(" AND " + fieldName + " not like '%"
						+ StringUtil.replaceStr(fieldValue.trim()) + "%'");
			}

		}
		return this;
	}

	/**
	 * 加入Like比较，占位符模式，推荐使用
	 * 
	 * @param fieldName
	 *            字段名
	 * @param fieldValue
	 *            字段值
	 * @return 返回like比较条件的SqlBuffer
	 */
	public QueryModel combPreLike(String fieldName, String fieldValue) {
		return this.combPreLike(fieldName, fieldValue, QueryModel.MATCH_ALL);
	}

	public QueryModel combPreLike(String fieldName, String fieldValue,
			String otherName) {
		sb.append(" AND " + fieldName + " like:" + otherName.trim());
		preConditionMap.put(otherName, "%"
				+ replacePreSqlStr(fieldValue.trim()) + "%");
		return this;
	}

	/**
	 * 加入Like比较，占位符模式，推荐使用
	 * 
	 * @param fieldName
	 *            字段名
	 * @param fieldValue
	 *            字段值
	 * @param matchType
	 *            匹配方式（前向匹配：QueryModel.MATCH_FRONT；后向匹配：QueryModel.MATCH_BACK；
	 *            全向匹配：QueryModel.MATCH_ALL）
	 * @return 返回like比较条件的SqlBuffer
	 */
	public QueryModel combPreLike(String fieldName, String fieldValue,
			int matchType) {

		return this.combPreLike(fieldName, fieldValue, matchType, fieldName);
	}

	public QueryModel combPreLike(String fieldName, String fieldValue,
			int matchType, String parameterName) {
		if (!isValEmpty(fieldValue)) {
			if (QueryModel.NOT_MATCH == matchType) {
				sb.append(" AND " + fieldName + " not like:" + parameterName);
			} else {
				sb.append(" AND " + fieldName + " like:" + parameterName.trim());
			}
			if (QueryModel.MATCH_FRONT == matchType) {
				preConditionMap.put(parameterName,
						replacePreSqlStr(fieldValue.trim()) + "%");
			} else if (QueryModel.MATCH_BACK == matchType) {
				preConditionMap.put(parameterName, "%"
						+ replacePreSqlStr(fieldValue.trim()));
			} else if (QueryModel.MATCH_ALL == matchType) {
				preConditionMap.put(parameterName, "%"
						+ replacePreSqlStr(fieldValue.trim()) + "%");
			} else if (QueryModel.MATCH_PART == matchType) {
				preConditionMap.put(parameterName,
						replacePreSqlStr(fieldValue.trim()));
			} else if (QueryModel.NOT_MATCH == matchType) {
				preConditionMap.put(parameterName, "%"
						+ replacePreSqlStr(fieldValue.trim()) + "%");
			}
		}
		return this;
	}

	/**
	 * 加入判断条件，整型是否相等
	 * 
	 * @param fieldName
	 *            字段名
	 * @param fieldValue
	 *            字段值
	 * @return 返回整型相等条件的SqlBuffer
	 */
	public QueryModel combEquals(String fieldName, Integer fieldValue) {
		if (!isValEmpty(fieldValue)) {
			sb.append(" AND " + fieldName + " = " + fieldValue);
		}
		return this;
	}

	/**
	 * 加入判断条件，实型是否相等
	 * 
	 * @param fieldName
	 *            字段名
	 * @param fieldValue
	 *            字段值
	 * @return 返回整型相等条件的SqlBuffer
	 */
	public QueryModel combEquals(String fieldName, Double fieldValue) {
		if (!isValEmpty(fieldValue)) {
			sb.append(" AND " + fieldName + " = " + fieldValue);
		}
		return this;
	}

	/**
	 * 加入判断条件，判断日期型是否相等
	 * 
	 * @Time 2009-11-5 下午07:31:04 create
	 * @param fieldName
	 * @param arrangeDate
	 * @author dufazuo
	 */
	public QueryModel combEquals(String fieldName, Date fieldValue) {
		if (!isValEmpty(fieldValue)) {
			sb.append(" AND " + fieldName + " = " + fieldValue);
		}
		return this;
	}

	/**
	 * 加入判断条件，判断字符型是否相等
	 * 
	 * @param fieldName
	 *            字段名
	 * @param fieldValue
	 *            字段值
	 * @return 返回字符型相等条件的SqlBuffer
	 */
	public QueryModel combEquals(String fieldName, String fieldValue) {
		if (!isValEmpty(fieldValue)) {
			// placeholder.append(" AND " + fieldName + " = ?'" +
			// fieldValue.trim() + "'");
			// placeholderValues.add(fieldValue);
			sb.append(" AND " + fieldName + " = '" + fieldValue.trim() + "'");
		}
		return this;
	}

	/**
	 * 加入判断条件，判断字符型是否相等，占位符模式，推荐使用
	 * 
	 * @param fieldName
	 *            字段名
	 * @param fieldValue
	 *            字段值
	 * @return 返回字符型相等条件的SqlBuffer
	 */
	public QueryModel combPreEquals(String fieldName, String fieldValue) {
		if (!isValEmpty(fieldValue)) {
			sb.append(" AND " + fieldName + " = :" + fieldName);
			preConditionMap.put(fieldName, fieldValue.trim());
		}
		return this;
	}

	public QueryModel combPreEquals(String fieldName, String fieldValue,
			String parameterName) {
		if (!isValEmpty(fieldValue)) {
			sb.append(" AND " + fieldName + " =:" + parameterName);
			preConditionMap.put(parameterName, fieldValue.trim());
		}
		return this;
	}

	/**
	 * boolean
	 * 
	 * @param fieldName
	 * @param fieldValue
	 * @param parameterName
	 * @return
	 * @author zhangpeng
	 * @time 2015-7-27
	 */
	public QueryModel combPreEquals(String fieldName, Boolean fieldValue,
			String parameterName) {
		sb.append(" AND " + fieldName + " =:" + parameterName);
		preConditionMap.put(parameterName, fieldValue);
		return this;
	}

	/**
	 * 加入判断条件，判断字符型是否相等，占位符模式，推荐使用
	 * 
	 * @param fieldName
	 *            字段名
	 * @param fieldValue
	 *            字段值
	 * @return 返回字符型相等条件的SqlBuffer
	 */
	public QueryModel combPreEquals(String fieldName, Integer fieldValue) {
		if (!isValEmpty(fieldValue)) {
			sb.append(" AND " + fieldName + " =:" + fieldName);
			preConditionMap.put(fieldName, fieldValue);
		}

		return this;
	}

	/**
	 * 加入判断条件，判断字符型是否相等，占位符模式，推荐使用
	 * 
	 * @param fieldName
	 *            字段名
	 * @param fieldValue
	 *            字段值
	 * @return 返回字符型相等条件的SqlBuffer
	 */
	public QueryModel combPreEquals(String fieldName, Boolean fieldValue) {
		sb.append(" AND " + fieldName + " =:" + fieldName);
		preConditionMap.put(fieldName, fieldValue);
		return this;
	}

	public QueryModel combPreEquals(String fieldName, Integer fieldValue,
			String parameterName) {
		if (!isValEmpty(fieldValue)) {
			sb.append(" AND " + fieldName + " = :" + parameterName);
			preConditionMap.put(parameterName, fieldValue);
		}

		return this;
	}

	/**
	 * 加入判断条件，判断字符型是否不等，占位符模式，推荐使用
	 * 
	 * @Time 2012-1-6 下午03:14:29 create
	 * @param fieldName
	 *            字段名
	 * @param fieldValue
	 *            字段值
	 * @return 返回字符型相等条件的SqlBuffer
	 * @author lizhenqiang
	 */
	public QueryModel combPreNotEquals(String fieldName, String fieldValue) {
		if (!isValEmpty(fieldValue)) {
			sb.append(" AND " + fieldName + " <> :" + fieldName);
			preConditionMap.put(fieldName, fieldValue.trim());
		}
		return this;
	}

	public QueryModel combPreNotEquals(String fieldName, String fieldValue,
			String parameterName) {
		if (!isValEmpty(fieldValue)) {
			sb.append(" AND " + fieldName + " <> :" + parameterName);
			preConditionMap.put(parameterName, fieldValue.trim());
		}
		return this;
	}
	
	public QueryModel combPreNotEquals(String fieldName, Integer fieldValue,
			String parameterName) {
		if (!isValEmpty(fieldValue)) {
			sb.append(" AND " + fieldName + " <> :" + parameterName);
			preConditionMap.put(parameterName, fieldValue);
		}
		return this;
	}

	/**
	 * 加入查询条件通用方法
	 * 
	 * @param condition
	 *            查询条件语句
	 * @return 返回查询条件SqlBuffer
	 */
	public QueryModel combCondition(String condition) {
		if (!isValEmpty(condition)) {
			sb.append(" AND " + condition);
		}
		return this;
	}

	/**
	 * 加入查询条件通用方法
	 * 
	 * @param model
	 *            查询条件model
	 * @return 返回查询条件SqlBuffer
	 */
	public QueryModel combCondition(QueryModel model) {
		String modelCondition = model.getQueryCondition();
		if (!isValEmpty(modelCondition)) {
			sb.append(" AND ("
					+ modelCondition.trim().substring(3,
							modelCondition.trim().length()) + ")");
			preConditionMap.putAll(model.getPreConditionMap());
		}
		return this;
	}

	/**
	 * 加入比较
	 * 
	 * @param fieldName
	 *            字段名
	 * @param fieldValue
	 *            字段值
	 * @param compareType
	 *            比较类型
	 * @return 返回比较条件的SqlBuffer
	 */
	public QueryModel combCompare(String fieldName, Object fieldValue,
			int compareType) {
		if (!isValEmpty(fieldValue)) {
			sb.append(" AND " + fieldName + " " + getSymbol(compareType) + " "
					+ fieldValue);
		}
		return this;
	}

	/**
	 * 加入比较
	 * 
	 * @param fieldName
	 *            字段名
	 * @param fieldValue
	 *            字段值
	 * @param compareType
	 *            比较类型
	 * @return 返回比较条件的SqlBuffer
	 */
	public QueryModel combPreCompare(String fieldName, Object fieldValue,
			int compareType) {
		if (!isValEmpty(fieldValue)) {
			sb.append(" AND " + fieldName + getSymbol(compareType) + ":"
					+ fieldName);
			preConditionMap.put(fieldName, fieldValue);
			// sb.append(" AND " + fieldName + " " + getSymbol(compareType) +
			// " " + fieldValue);
		}
		return this;
	}

	/**
	 * 加入IN条件
	 * 
	 * @param fieldName
	 *            字段名
	 * @param vals
	 *            字段值，逗号分割的字符串
	 * @return 返回IN条件的SqlBuffer
	 */
	public QueryModel combIn(String fieldName, String vals) {
		if (!isValEmpty(vals)) {
			int num = vals.length();
			if (num < 500)
				sb.append(" AND " + fieldName + " IN (" + vals + ")");
			else {
				StringBuffer subValstr = new StringBuffer();
				subValstr.append(vals);
				String subResult = subVals(fieldName, subValstr);
				sb.append(" AND  ( " + subResult + " ) ");

			}
		}
		return this;
	}

	/**
	 * 查询条件in里面字符串长度过长的问题解决
	 * 
	 * @Time 2012-2-4 下午4:28:49 create
	 * @param fieldName
	 *            字段名称
	 * @param subVals
	 *            要处理的值
	 * @return in sql
	 * @author wangyijing
	 */
	public String subVals(String fieldName, StringBuffer subVals) {
		// 截取出前500个字符以内的id段
		int beginIndex = 0;
		int endIndex = 0;
		if (subVals.length() < 500) {// 如果当前字符串小于500，按当前字符串长度截取
			endIndex = subVals.length();
		} else {// 如果当前字符串大于500，截取500以内字符串
			endIndex = 500 - 1;
		}

		String tempVals = subVals.substring(beginIndex, endIndex);// 初次截取的长度500以内的值
		String thisVals = null;
		if (tempVals.lastIndexOf(",") > 0) {
			endIndex = tempVals.lastIndexOf(",");
			thisVals = tempVals.substring(0, endIndex);
		} else {
			thisVals = tempVals;
		}

		String sql = null;
		if (endIndex < subVals.length()) {
			sql = fieldName + " in (" + thisVals + ") or ";
			StringBuffer endVals = new StringBuffer(subVals.substring(
					endIndex + 1, subVals.length()));// 截取前500长度后剩余的id段
			return sql + subVals(fieldName, endVals);
		} else {
			sql = fieldName + " in (" + thisVals + ") ";
			return sql;
		}

	}

	/**
	 * 加入NOT IN条件
	 * 
	 * @param fieldName
	 *            字段名
	 * @param vals
	 *            字段值，逗号分割的字符串
	 * @return 返回NOT IN条件的SqlBuffer
	 */
	public QueryModel combNotIn(String fieldName, String vals) {
		if (!isValEmpty(vals)) {
			sb.append(" AND " + fieldName + " NOT IN(" + vals + ")");
		}
		return this;
	}

	/**
	 * 加入IS NULL条件
	 * 
	 * @param fieldName
	 *            字段名
	 * @param vals
	 *            字段值，逗号分割的字符串
	 * @return 返回IS NULL条件的SqlBuffer
	 */
	public QueryModel combIsNull(String fieldName) {
		sb.append(" AND " + fieldName + " IS NULL");
		return this;
	}

	// ********************************OR
	// condition****************************************

	/**
	 * 加入Like比较
	 * 
	 * @param fieldName
	 *            字段名
	 * @param fieldValue
	 *            字段值
	 * @return 返回like比较条件的SqlBuffer
	 */
	public QueryModel orLike(String fieldName, String fieldValue) {
		if (!isValEmpty(fieldValue)) {
			sb.append(" OR " + fieldName + " like '%"
					+ StringUtil.replaceStr(fieldValue.trim()) + "%'");
		}
		return this;
	}

	/**
	 * 加入Like比较
	 * 
	 * @param fieldName
	 *            字段名
	 * @param fieldValue
	 *            字段值
	 * @param matchType
	 *            匹配方式（前向匹配：QueryModel.MATCH_FRONT；后向匹配：QueryModel.MATCH_BACK；
	 *            全向匹配：QueryModel.MATCH_ALL）
	 * @return 返回like比较条件的SqlBuffer
	 */
	public QueryModel orLike(String fieldName, String fieldValue, int matchType) {
		if (!isValEmpty(fieldValue)) {
			if (QueryModel.MATCH_FRONT == matchType) {
				sb.append(" OR " + fieldName + " like '"
						+ StringUtil.replaceStr(fieldValue.trim()) + "%'");
			} else if (QueryModel.MATCH_BACK == matchType) {
				sb.append(" OR " + fieldName + " like '%"
						+ StringUtil.replaceStr(fieldValue.trim()) + "'");
			} else {
				sb.append(" OR " + fieldName + " like '%"
						+ StringUtil.replaceStr(fieldValue.trim()) + "%'");
			}

		}
		return this;
	}

	/**
	 * 加入Like比较，占位符模式，推荐使用
	 * 
	 * @param fieldName
	 *            字段名
	 * @param fieldValue
	 *            字段值
	 * @return 返回like比较条件的SqlBuffer
	 */
	public QueryModel orPreLike(String fieldName, String fieldValue) {
		if (!isValEmpty(fieldValue)) {
			sb.append(" OR " + fieldName + " like :" + fieldName);
			preConditionMap.put(fieldName,
					"%" + replacePreSqlStr(fieldValue.trim()) + "%");
		}
		return this;
	}

	public StringBuffer getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(StringBuffer placeholder) {
		this.placeholder = placeholder;
	}

	public ArrayList<Object> getPlaceholderValues() {
		return placeholderValues;
	}

	public void setPlaceholderValues(ArrayList<Object> placeholderValues) {
		this.placeholderValues = placeholderValues;
	}

	/**
	 * 加入Like比较，占位符模式，推荐使用
	 * 
	 * @param fieldName
	 *            字段名
	 * @param fieldValue
	 *            字段值
	 * @param matchType
	 *            匹配方式（前向匹配：QueryModel.MATCH_FRONT；后向匹配：QueryModel.MATCH_BACK；
	 *            全向匹配：QueryModel.MATCH_ALL）
	 * @return 返回like比较条件的SqlBuffer
	 */
	public QueryModel orPreLike(String fieldName, String fieldValue,
			int matchType) {
		if (!isValEmpty(fieldValue)) {
			sb.append(" OR " + fieldName + " like :" + fieldName);
			if (QueryModel.MATCH_FRONT == matchType) {
				preConditionMap.put(fieldName,
						replacePreSqlStr(fieldValue.trim()) + "%");
			} else if (QueryModel.MATCH_BACK == matchType) {
				preConditionMap.put(fieldName, "%"
						+ replacePreSqlStr(fieldValue.trim()));
			} else {
				preConditionMap.put(fieldName, "%"
						+ replacePreSqlStr(fieldValue.trim()) + "%");
			}

		}
		return this;
	}

	/**
	 * 加入判断条件，整型是否相等
	 * 
	 * @param fieldName
	 *            字段名
	 * @param fieldValue
	 *            字段值
	 * @return 返回整型相等条件的SqlBuffer
	 */
	public QueryModel orEquals(String fieldName, Integer fieldValue) {
		if (!isValEmpty(fieldValue)) {
			sb.append(" OR " + fieldName + " = " + fieldValue);
		}
		return this;
	}

	public StringBuffer getSb() {
		return sb.append(" " + getOrder());
	}

	public void setSb(StringBuffer sb) {
		this.sb = sb;
	}

	/**
	 * 加入判断条件，判断字符型是否相等
	 * 
	 * @param fieldName
	 *            字段名
	 * @param fieldValue
	 *            字段值
	 * @return 返回字符型相等条件的SqlBuffer
	 */
	public QueryModel orEquals(String fieldName, String fieldValue) {
		if (!isValEmpty(fieldValue)) {
			sb.append(" OR " + fieldName + " = '" + fieldValue.trim() + "'");
		}
		return this;
	}

	/**
	 * 加入判断条件，判断字符型是否相等，占位符模式，推荐使用
	 * 
	 * @param fieldName
	 *            字段名
	 * @param fieldValue
	 *            字段值
	 * @return 返回字符型相等条件的SqlBuffer
	 */
	public QueryModel orPreEquals(String fieldName, String fieldValue) {
		if (!isValEmpty(fieldValue)) {
			sb.append(" OR " + fieldName + " = :" + fieldName);
			preConditionMap.put(fieldName, fieldValue.trim());
		}
		return this;
	}

	/**
	 * 加入判断条件，判断字符型是否相等，占位符模式，推荐使用
	 * 
	 * @param fieldName
	 *            字段名
	 * @param fieldValue
	 *            字段值
	 * @return 返回字符型相等条件的SqlBuffer
	 */
	public QueryModel orPreEquals(String fieldName, Integer fieldValue) {
		if (!isValEmpty(fieldValue)) {
			sb.append(" OR " + fieldName + " = :" + fieldName);
			preConditionMap.put(fieldName, fieldValue);
		}

		return this;
	}

	/**
	 * 加入查询条件通用方法
	 * 
	 * @param condition
	 *            查询条件语句
	 * @return 返回查询条件SqlBuffer
	 */
	public QueryModel orCondition(String condition) {
		if (!isValEmpty(condition)) {
			sb.append(" OR " + condition);
		}
		return this;
	}

	/**
	 * 加入查询条件通用方法
	 * 
	 * @param model
	 *            查询条件model
	 * @return 返回查询条件SqlBuffer
	 */
	public QueryModel orCondition(QueryModel model) {
		String modelCondition = model.getQueryCondition();
		if (!isValEmpty(modelCondition)) {

			sb.append(" OR ("
					+ modelCondition.trim().substring(3,
							modelCondition.trim().length()) + ")");
			preConditionMap.putAll(model.getPreConditionMap());
		}
		return this;
	}

	/**
	 * 加入比较
	 * 
	 * @param fieldName
	 *            字段名
	 * @param fieldValue
	 *            字段值
	 * @param compareType
	 *            比较类型
	 * @return 返回比较条件的SqlBuffer
	 */
	public QueryModel orCompare(String fieldName, Object fieldValue,
			int compareType) {
		if (!isValEmpty(fieldValue)) {
			sb.append(" OR " + fieldName + " " + getSymbol(compareType) + " "
					+ fieldValue);
		}
		return this;
	}

	/**
	 * 加入IN条件
	 * 
	 * @param fieldName
	 *            字段名
	 * @param vals
	 *            字段值，逗号分割的字符串
	 * @return 返回IN条件的SqlBuffer
	 */
	public QueryModel orIn(String fieldName, String vals) {
		if (!isValEmpty(vals)) {
			sb.append(" OR " + fieldName + " IN(" + vals + ")");
		}
		return this;
	}

	/**
	 * 为preLike方法使用的SQL语句转义
	 * 
	 * @param str
	 *            SQL语句
	 * @return 转义后的SQL语句
	 * @author lizhenqiang
	 */
	public static String replacePreSqlStr(String str) {
		if (str == null) {
			return "";
		}
		String dbType = "MYSQL";
		// for mysql
		if ("MYSQL".equals(dbType)) {
			String replaceStr = str.replace("\\", "\\\\\\\\");
			replaceStr = replaceStr.replace("_", "\\_");
			replaceStr = replaceStr.replace("%", "\\%");
			replaceStr = replaceStr.replace("'", "''");
			return replaceStr;
		}
		// else if("ORACLE".equals(dbType)){
		//
		// String replaceStr = str.replaceAll("'", "''");
		// replaceStr = replaceStr.replaceAll("&", "'||'&'||'");
		// //replaceStr = replaceStr.replace("{", "'||'{'||'");
		// replaceStr = replaceStr.replace("￥", "'||'￥'||'");
		// replaceStr = replaceStr.replace("{", "[{]");
		// return replaceStr;
		// }
		// for sql server
		else {
			String replaceStr = str.replace("[", "[[]");// 此句一定要在最前面
			// replaceStr = replaceStr.replace("_", "[_]");
			replaceStr = replaceStr.replace("%", "[%]");
			replaceStr = replaceStr.replace("'", "\'");
			replaceStr = replaceStr.replace("‘", "\\‘");
			replaceStr = replaceStr.replace("’", "\\’");
			replaceStr = replaceStr.replace("{", "[{]");
			return replaceStr;
		}
	}

	public Map<String, Object> getPreConditionMap() {
		return preConditionMap;
	}

	/**
	 * 获取排序
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * 获取QueryModel对象的字符串表达式，包含查询条件和排序
	 */
	public String toString() {
		return sb.toString() + getOrder();
	}

	/**
	 * 获取查询条件，不包含排序
	 */
	public String getQueryCondition() {
		return sb.toString();
	}

	/**
	 * 设置排序条件
	 * 
	 * @param orderStr
	 *            排序字符串
	 * @return 返回order by条件的SqlBuffer
	 */
	public QueryModel setOrder(String orderStr) {
		this.order = " order by " + orderStr;
		return this;
	}

	/**
	 * 添加对例如uuid集合String类型的in的支持 加入IN条件，字段值为字符串情况下用
	 * 
	 * @Time 2012-3-9 下午12:20:21 create
	 * @param fieldName
	 * @param vals
	 * @return
	 * @author wangyijing
	 */
	public QueryModel combInStr(String fieldName, String vals) {
		if (!isValEmpty(vals)) {
			String uuids = null;
			String[] temp = vals.split(",");
			StringBuffer str = new StringBuffer();
			for (String uuid : temp) {
				str.append(",'" + uuid.trim() + "'");
			}
			if (str.length() > 0)
				uuids = str.toString().replaceFirst(",", "");

			if (uuids.length() < 500) {
				sb.append(" AND " + fieldName + " in ( ");
				sb.append(uuids);
				sb.append(" ) ");
			} else {
				StringBuffer subVals = new StringBuffer();
				subVals.append(uuids);
				String subResult = subVals(fieldName, subVals);
				sb.append(" AND  ( " + subResult + " ) ");
			}

		}
		return this;
	}

	/**
	 * 添加checkBox多选框条件查询 checkbox做查询条件情况下，经常需要构造例如 where 1=1 AND ( type like
	 * '%001%' or type like '%002%' or type like '%003%' )的查询条件
	 * 
	 * @Time 2012-10-15 下午3:17:14 create
	 * @param fieldName
	 * @param vals
	 * @return
	 * @author wangyijing
	 */
	public QueryModel combOrLikes(String fieldName, String vals) {
		StringBuffer condtion = new StringBuffer();
		String[] valsAry = vals.split(",");
		if (valsAry.length > 1) {
			for (String fieldValue : valsAry) {
				condtion.append(" OR (");
				condtion.append(fieldName + " like '%"
						+ StringUtil.replaceStr(fieldValue.trim()) + "%'");
				condtion.append(") ");
			}
			sb.append(" AND ( ");
			sb.append(condtion.toString().replaceFirst("OR", ""));
			sb.append(" ) ");
		} else {
			sb.append(" AND " + fieldName + " like '%"
					+ StringUtil.replaceStr(vals.trim()) + "%'");
		}

		return this;
	}

	/**
	 * 清空查询条件
	 * 
	 * @param condition
	 *            查询条件语句
	 * @return 返回查询条件SqlBuffer
	 */
	public QueryModel clearQuery() {
		this.order = "";
		sb.setLength(0);
		preConditionMap.clear();
		return this;
	}

	public String getColums() {
		return colums;
	}

	public void setColums(String colums) {
		this.colums = colums;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		if (!isValEmpty(group)) {
			sb.append(" " + group);
		}
		//return this;
		//this.group = group;
	}
}