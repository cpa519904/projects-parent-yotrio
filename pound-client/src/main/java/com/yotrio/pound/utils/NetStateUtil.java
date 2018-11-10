package com.yotrio.pound.utils;

import com.yotrio.common.utils.PropertiesFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 判断网络连接状况.
 *
 * @author nagsh
 */
public class NetStateUtil {
    private static Logger logger = LoggerFactory.getLogger(NetStateUtil.class);

    private static String SERVER_IP = PropertiesFileUtil.getInstance("application").get("server.ip");

    public static boolean isConnect() {
        boolean connect = false;
        Runtime runtime = Runtime.getRuntime();
        Process process;
        try {
            process = runtime.exec("ping " + SERVER_IP);
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
//	        System.out.println("返回值为:"+sb);

            is.close();
            isr.close();
            br.close();

            if (null != sb && !sb.toString().equals("")) {
                String logString = "";
                if (sb.toString().indexOf("TTL") > 0) {
                    // 网络畅通
                    connect = true;
                } else {
                    // 网络不畅通
                    connect = false;
                }
            }
        } catch (IOException e) {
            logger.error(e.toString());
        }
        return connect;
    }

    public static void main(String[] args) {
        System.out.println(NetStateUtil.isConnect());
    }

}
