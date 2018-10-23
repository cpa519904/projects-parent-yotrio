package com.yotrio.common.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;

/**
 * 所有的domain包下model的基类
 * 功能说明：<br>
 * 模块名称：CaimooModel<br>
 * 功能描述：_beans<br>
 * 文件名称: _beans.java<br>
 * 系统名称：彩猫彩票<br>
 * 软件著作权：上海重彩网络科技有限公司[caimao.com]版权所有<br>
 * 开发人员：lujun <br>
 * 开发时间：Nov 18, 2014 5:04:46 PM<br>
 * 系统版本：1.0.0<br>
 */
public class BaseBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Override
	public String toString(){
		try{
			return JSON.toJSONString(this, SerializerFeature.WriteDateUseDateFormat);
		}catch (Exception e) {
			return super.toString();
		}
	}
}
