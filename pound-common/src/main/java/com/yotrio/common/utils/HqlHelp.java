package com.yotrio.common.utils;

import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * 客户端管理 系统名称：彩猫<br>
 * 模块名称：公共模块<br>
 * 功能说明：智能创建HQL语句<br>
 * 功能描述：<br>
 * 开发人员：lujun <br>
 * 软件著作权：彩猫 caimao.com 版权所有<br>
 * 开发时间：Jun 18, 2012 4:33:36 PM<br>
 * 系统版本：1.0.0<br>
 */
public class HqlHelp {
	private static final String COLUMNS = "COLUMNS";
	private static final String SIGN = "SIGN";
	private Class<?> clsName;
	private String tableName = "";
	private String orderByStr = "";
	// 条件list
	private List<Map<String, Object>> parsList = null;
	// 条件对应的值
	private Vector<Object> ve = null;
	private int count = 0;
	private StringBuffer key = null;
	// private BaseService baseService;
	/**
	 * 创建唯一的实例
	 * @param tableName
	 */
	private HqlHelp() {
		this.parsList = new ArrayList<Map<String, Object>>();
		this.ve = new Vector<Object>();
		this.key = new StringBuffer(tableName);
	}
	public static HqlHelp CreateHQL(Class<?> clsName) {
		HqlHelp hql = new HqlHelp();
		hql.setClsName(clsName);
		hql.setTableName(clsName.getSimpleName());
		return hql;
	}
	public static void main(String[] args) throws Exception {
		String str = "?2123_";
		String startSign = "";
		String endSign = "";
		if(str.startsWith("%")) {
			startSign = "%";
			str = str.substring(1);
		}
		if(str.endsWith("%")) {
			endSign = "%";
			str = str.substring(0, str.length() - 1);
		}
		String val = startSign + likeEscapeBySqlServer(str) + endSign;
		System.out.println(val);
	}
	/**
	 * 添加条件
	 * @param columnName
	 * @param value
	 */
	public void add(String columnName, Object value) {
		add(columnName, value, "=");
	}
	/**
	 * 添加条件 带符号的
	 * @param columnName
	 * @param value
	 */
	public void add(String columnName, Object value, String sign) {
		if (columnName == null || columnName.length() < 0 || value == null)
			return;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(COLUMNS, columnName);
		map.put(columnName, value);
		map.put(SIGN, sign);
		this.key.append(columnName).append(value).append(sign);
		getParsList().add(map);
		if (!"in".equals(sign)) {
			if (value instanceof String) {
				ve.add(value);
			} else if (value instanceof Integer) {
				ve.add(value);
			} else if (value instanceof Boolean) {
				ve.add(value);
			} else if (value instanceof Date) {
				ve.add(value);
			} else if (value instanceof Double) {
				ve.add(value);
			} else if (value instanceof Long) {
				ve.add(value);
			} else {
				throw new IllegalArgumentException("不支持该类型....");
			}
		}
	}
	/**
	 * 添加in条件
	 * @param columnName
	 * @param value 字符串 如 "1,2,3,4,5"
	 */
	public void addIn(String columnName, Object value) {
		if (columnName == null || columnName.length() < 0 || value == null)
			return;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(COLUMNS, columnName);
		map.put(columnName, value);
		map.put(SIGN, "in");
		getParsList().add(map);
		this.key.append(columnName).append(value).append("in");
	}
	/**
	 * 添加like查询 需要自己戴上%%
	 * @param columnName
	 * @param value
	 */
	public void addLike(String columnName, Object value) {
		if (columnName == null || columnName.length() < 0 || value == null)
			return;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(COLUMNS, columnName);
		String str = (String) value;
		
		String startSign = "";
		String endSign = "";
		if(str.startsWith("%")) {
			startSign = "%";
			str = str.substring(1);
		}
		if(str.endsWith("%")) {
			endSign = "%";
			str = str.substring(0, str.length() - 1);
		}
		String val = startSign + likeEscapeBySqlServer(str) + endSign;
		map.put(columnName, val);
		
		map.put(SIGN, "like");
		getParsList().add(map);
		ve.add(val);
		this.key.append(columnName).append(value).append("like");
	}
	
	/**
	 * like查询 特殊字符转义
	 * @param value
	 * @return
	 */
	public static String likeEscapeBySqlServer(String value){
		/*
		下划线：用于代替一个任意字符（相当于正则表达式中的 ? ）
		百分号：用于代替任意数目的任意字符（相当于正则表达式中的 * ）
		方括号：用于转义（事实上只有左方括号用于转义，右方括号使用最近优先原则匹配最近的左方括号）
		尖号：用于排除一些字符进行匹配（这个与正则表达式中的一样）
		以下是一些匹配的举例，需要说明的是，只有like操作才有这些特殊字符，=操作是没有的。
		*/
		if(StringUtil.isNotEmpty(value)) {
			value = value.replace("[", "[[]");
			value = value.replace("_", "[_]");
			value = value.replace("%", "[%]");
			value = value.replace("^", "[^]");
		}
		return value;
	}
	
	/**
	 * 
	 * @param hql
	 * @param pars
	 * @param paramName
	 * @param value
	 * @param queryBlurry
	 */
	public static void appendHql(StringBuffer hql, List<Object> pars, String paramName, String value, Integer queryBlurry) {
		if (value != null && !"".equals(value)) {
			if (queryBlurry != null && queryBlurry == 1) {
				hql.append(" and " + paramName + " like ? ");
				pars.add("%" + likeEscapeBySqlServer(value) + "%");
			} else {
				hql.append(" and " + paramName + " = ? ");
				pars.add(value);
			}
		}
	}
	
	/**
	 * 两者之间的，如时间
	 * @param columnName
	 * @param start
	 * @param end
	 */
	public void between(String columnName, Object start, Object end) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(COLUMNS, columnName);
		map.put(SIGN, "between");
		getParsList().add(map);
		ve.add(start);
		ve.add(end);
		if (start instanceof Date) {
			this.key.append(((Date) start).getTime());
		} else if (start instanceof Integer) {
			this.key.append(((Integer) start).intValue());
		}
		if (end instanceof Date) {
			this.key.append(((Date) end).getTime());
		} else if (end instanceof Integer) {
			this.key.append(((Integer) end).intValue());
		}
	}
	/**
	 * 添加or条件 中间是用AND 连接
	 * @param columnName
	 * @param items
	 */
	public void addOr(String columnName, String[] items) {
		if (items == null || items.length < 1)
			return;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(COLUMNS, columnName);
		StringBuilder sb = new StringBuilder();
		for (String item : items) {
			sb.append(columnName).append(" = ").append(item).append(" or ");
		}
		String v = sb.substring(0, sb.length() - 4);
		map.put(columnName, v);
		map.put(SIGN, "or");
		this.key.append(v);
		getParsList().add(map);
	}
	/**
	 * 所有的条件都是并列的or条件
	 * @param columnName
	 * @param items
	 * @param sign
	 */
	public void or(String columnName, Object val) {
		or(columnName, val, "=");
	}
	public void or(String columnName, Object val, String sign) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(COLUMNS, columnName);
		map.put(columnName, val);
		map.put(SIGN, sign);
		this.key.append(String.valueOf(val));
		getParsList().add(map);
		ve.add(val);
	}
	/**
	 * 创建动态脚本HQL脚本
	 * @return
	 */
	public String toWhereHQL() {
		if (parsList.size() == 0) {
			return "from " + tableName;
		} else {
			StringBuilder sb = new StringBuilder("from " + tableName + " where ");
			for (int i = 0; i < parsList.size(); i++) {
				Map<String, Object> par = parsList.get(i);
				String column = String.valueOf(par.get(COLUMNS));
				Object v = par.get(column);
				String sign = (String) par.get(SIGN);
				if ("in".equals(sign)) {
					sb.append(column).append(" ").append(par.get(SIGN)).append(" ").append("(").append(v).append(")");
				} else if ("between".equals(sign)) {
					sb.append("(").append(column).append(" ").append(par.get(SIGN)).append(" ? and ?").append(")");
				} else if ("or".equals(sign)) {
					sb.append("(").append(v).append(")");
				} else if ("like".equals(sign)) {
					sb.append(column).append(" like ?");
				} else {
					sb.append(column).append(sign).append("?");
				}
				sb.append(" and ");
			}
			return sb.substring(0, sb.lastIndexOf("and")).trim();
		}
	}
	/** 创建动态OR脚本HQL脚本 **/
	public String toWhereOrHQL() {
		if (parsList.size() == 0) {
			return "from " + tableName;
		} else {
			StringBuilder sb = new StringBuilder("from " + tableName + " where ");
			for (int i = 0; i < parsList.size(); i++) {
				Map<String, Object> par = parsList.get(i);
				String column = String.valueOf(par.get(COLUMNS));
				sb.append(column).append(" " + par.get(SIGN)).append(" ?");
				sb.append(" or ");
			}
			return sb.substring(0, sb.lastIndexOf("or")).trim();
		}
	}
	/**
	 * 获取统计的HQL
	 * @param className
	 * @param columns
	 * @param pars
	 * @return
	 */
	public String toSelectHQL(String selectObjs) {
		String fromSql = this.toWhereHQL();
		fromSql = "select " + selectObjs + " " + fromSql;
		if (orderByStr != null && orderByStr.length() > 0) {
			fromSql += " order by " + getOrderByStr();
		}
		return fromSql;
	}
	/**
	 * 获取统计的HQL
	 * @param className
	 * @param columns
	 * @param pars
	 * @return
	 */
	public String toCountHql() {
		return toCountHql("id");
	}
	/**
	 * 获取统计的HQL
	 * @param className
	 * @param columns
	 * @param pars
	 * @return
	 */
	public String toCountHql(String countName) {
		String sql = this.toWhereHQL();
		sql = "select count(" + countName + ") " + sql;
		return sql;
	}
	public Vector<Object> getVe() {
		return ve;
	}
	/**
	 * 按照当前条件检索id
	 * @param selectObjs
	 * @return
	 */
	// public List<?> qryListByIds(){
	// return qryListByIds(clsName);
	// }
	// @SuppressWarnings("unchecked")
	// public <T> List<T> qryListByIds(Class<T> beanClass){
	// Integer[] ids = getIDS();
	// List<T> lst = new ArrayList<T>();
	// if(ids==null || ids.length==0)return lst;
	// if(Globals.QRY_ONE_BY_ONE){
	// if(ids==null || ids.length==0)return lst;
	// for(Integer id:ids){
	// lst.add(baseService.getModel(beanClass,id));
	// }
	// return lst;
	// }
	// String idsSql = joinOrSql(ids,"id");
	// String fromSql = "from "+beanClass.getSimpleName()+" where "+idsSql;
	// List<T> lstObjs = (List<T>)baseService.getList(fromSql,0,count,null);
	// return lstObjs;
	// }
	/**
	 * 打印hql
	 */
	public void printHql() {
		System.out.println("CountHQL>>>:" + toCountHql() + getClass());
		System.out.println("HQL>>>:" + toWhereHQL() + getClass());
	}
	/**
	 * 获取key值，可以做缓存
	 * @return
	 */
	public String md5Key() {
		return new MD5().calcMD5(this.key.toString());
	}
	/**
	 * 返回sql的or字符串
	 * @param ids
	 * @param what 如 {"1","2","3"} 返回 (id=1 or id=2 or id=3)
	 * @return
	 */
	public static String joinOrSql(List<Object> lst, String what) {
		Object[] ids = new Object[lst.size()];
		lst.toArray(ids);
		String sql = StringUtils.join(ids, " or " + what + "=");
		return "(" + what + "=" + sql + ")";
	}
	public static String joinOrSql(Object[] ids, String what) {
		String sql = StringUtils.join(ids, " or " + what + "=");
		return "(" + what + "=" + sql + ")";
	}
	// //pojo
	private List<Map<String, Object>> getParsList() {
		return parsList;
	}
	public String getOrderByStr() {
		return orderByStr;
	}
	public void setOrderByStr(String orderByStr) {
		this.orderByStr = orderByStr;
		this.key.append(orderByStr);
	}
	public Object[] getArray() {
		if (ve.size() == 0)
			return null;
		else
			return ve.toArray();
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.key.append(count);
		this.count = count;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Class<?> getClsName() {
		return clsName;
	}
	public void setClsName(Class<?> clsName) {
		this.clsName = clsName;
	}
	@Override
	public String toString() {
		return this.toSelectHQL("*");
	}
}
