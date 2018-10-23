package com.yotrio.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * 图片处理工具
 * 模块名称：projects-parent com.yotrio.pound.utils
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-10-17 11:16
 * 系统版本：1.0.0
 **/

public class ImageUtil {
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    /**
     * 将base64图片字符串保存到本地
     *
     * @param imgStr
     * @param filePath
     * @param fileName
     * @return 本地图片路径
     */
    public static String saveBase64Img(String imgStr, String filePath, String fileName) {

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //base64解码
            byte[] bytes = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < bytes.length; ++i) {
                // 调整异常数据
                if (bytes[i] < 0) {
                    bytes[i] += 256;
                }
            }

            File file = new File(filePath, fileName);
            //判断路径是否存在，如果不存在就创建一个
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            // 生成jpg图片
            String imgFilePath = filePath + fileName;// 新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(bytes);
            out.flush();
            out.close();

            return imgFilePath;
        } catch (IOException e) {
            logger.error("base64 解码失败：{}", e);
            return null;
        }
    }

    /**
     * 根据图片地址转换为base64编码字符串
     *
     * @param imgFile 本地图片路径  E:/yotrio-pound/images/1539762376305.jpg
     * @return
     */
    public static String getImageBase64Str(String imgFile) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgFile);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            logger.error("base64 加密失败：{}", e);
            return null;
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    public static void main(String[] args) {
        String imgFile = "E:/yotrio-pound/images/1539762376305.jpg";
        String imageBase64Str = ImageUtil.getImageBase64Str(imgFile);
        System.out.println(imageBase64Str);
    }
}