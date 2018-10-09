package com.yotrio.pound.controller;

import com.yotrio.common.utils.DateUtil;
import com.yotrio.pound.domain.Result;
import com.yotrio.pound.utils.ResultUtil;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * 文件操作接口类
 * 模块名称：projects-parent com.wyq.admin.web.controller
 * 功能说明：<br>
 * MultipartFile对象中常用方法如下：
 * byte[] getBytes()  获取文件数据
 * String getContentType() 获取文件MIME类型，如image/jpeg等
 * InputStream getInputStream() 获取文件流
 * String getName() 获取表单中文件组件的名字
 * String getOriginalFilename() 获取上传文件原名
 * long getSize() 获取文件的字节大小，单位为byte
 * boolean isEmpty() 是否有上传文件
 * void transferTo 将上传文件保存到一个目标文件中
 * 开发人员：wangyiqiang
 * 创建时间： 2018-02-03 下午1:56
 * 系统版本：1.0.0
 **/
@Controller
@RequestMapping("/file")
public class FileController extends BaseController {

    @RequestMapping(value = "/upload", method = {RequestMethod.POST})
    public Result upload(MultipartFile file, HttpServletRequest request) {
        //如果文件不为空，写入上传路径，根据日期来分文件夹
        if (!file.isEmpty()) {
            Date now = new Date();
            String year = String.valueOf(now.getYear());
            String month = String.valueOf(now.getMonth());
            String day = String.valueOf(now.getDay());

            //构建图片存储路径
            StringBuilder path = new StringBuilder();
            path.append(request.getServletContext().getRealPath("/images/upload/"));
            path.append(year).append("/").append(month).append("/").append(day).append("/");
            //获取当前日期
            DateUtil.getCurrentTimeToDay();

            //上传文件名,根据时间戳来命名
            String fileName = String.valueOf(System.currentTimeMillis());
            File filePath = new File(path.toString(), fileName);

            //判断路径是否存在，如果不存在就创建一个
            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
            }

            try {
                //将上传文件保存到一个目标文件当中
                file.transferTo(new File(path + File.separator + fileName));

                //压缩文件scale是可以指定图片的大小，值在0到1之间，1f就是原图大小，0.5就是原图的一半大小，这里的大小是指图片的长宽，outputQuality是图片的质量，值也是在0到1，越接近于1质量越好，越接近于0质量越差
                Thumbnails.of(path + File.separator + fileName)
                        .scale(1f)
                        .outputQuality(0.25f)
                        .toFile(path + File.separator + fileName);

                return ResultUtil.success("图片上传成功!");
            } catch (IOException e) {
                logger.error("图片上传失败:{}", e.toString());
                return ResultUtil.error("图片上传失败:" + e.getMessage());
            }
        }
        return ResultUtil.error("上传文件为空");
    }
}