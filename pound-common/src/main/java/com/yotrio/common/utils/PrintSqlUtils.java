package com.yotrio.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PrintSqlUtils {
	public static void println(String hql, List<Object> pars){
		String[] hs = hql.split("\\?");
		StringBuffer buffer = new StringBuffer();
		for(int i=0;i<hs.length-1;i++){
			buffer.append(hs[i]);
			Object obj = pars.get(i);
			if(obj instanceof String){
				buffer.append("'").append(String.valueOf(obj)).append("'");
			}else if(obj instanceof Number){
				buffer.append(String.valueOf(obj));
			}else if(obj instanceof Date){
				Date date = (Date)obj;
				buffer.append("'").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)).append("'");
			}else{
				buffer.append("'").append(String.valueOf(obj)).append("'");
			}
			buffer.append("\r\n");
		}
		buffer.append(hs[hs.length-1]);
		System.out.println(buffer.toString());
	}
}
