package com.yotrio.pound.controller;

import com.yotrio.common.utils.DateUtil;
import com.yotrio.common.utils.StringUtil;
import com.yotrio.pound.domain.Result;
import com.yotrio.pound.domain.SystemProperties;
import com.yotrio.pound.model.PoundLog;
import com.yotrio.pound.service.IPoundLogService;
import com.yotrio.pound.utils.ImageUtil;
import com.yotrio.pound.utils.ResultUtil;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

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

    /**
     * 上传图片类型 1：称毛重图片 2：称皮重图片
     */
    private static final int UPLOAD_TYPE_GROSS_IMG = 1;
    private static final int UPLOAD_TYPE_TARE_IMG = 2;

    @Autowired
    private SystemProperties systemProperties;
    @Autowired
    private IPoundLogService poundLogService;

    /**
     * Base64字符串转图片 上传
     *
     * @param imgStr     base64字符串
     * @param poundLogNo 过磅单号
     * @param uploadType 上传图片类型 1：称毛重图片 2：称皮重图片
     * @return imgUrl
     */
    @RequestMapping(value = "/uploadBase64Img", method = {RequestMethod.POST})
    @ResponseBody
    public Result uploadBase64Img(String imgStr, String poundLogNo, Integer uploadType) {
        if (StringUtils.isEmpty(imgStr)) {
            return ResultUtil.error("图像数据为空");
        }
        if (StringUtil.isEmpty(poundLogNo)) {
            return ResultUtil.error("过磅单号不能为空");
        }
        PoundLog logInDB = poundLogService.findByPoundLogNo(poundLogNo);
        if (logInDB == null) {
            return ResultUtil.error("找不到您要更新的过磅记录");
        }

        //构建图片存储路径
        StringBuilder filePath = new StringBuilder();
        String basePath = DateUtil.getFilePathByDate(systemProperties.getFileLocation() + "/images/upload/");
        filePath.append(basePath).append(poundLogNo).append("/");

        //生成上传图片名
        String fileName = System.currentTimeMillis() + ".jpg";

        //base64字符串转图片并保存
        String imgLocalPath = ImageUtil.saveBase64Img(imgStr, filePath.toString(), fileName);
        if (StringUtils.isEmpty(imgLocalPath)) {
            return ResultUtil.error("文件上传失败");
        }
        //转换成http图片地址
        String imgUrl = "http://localhost:" + systemProperties.getServerPort() + imgLocalPath.substring(systemProperties.getFileLocation().length());

        // TODO: 2018-10-17 存入数据库
        if (uploadType == null) {
            return ResultUtil.error("上传图片类型为空");
        }
        if (uploadType == UPLOAD_TYPE_GROSS_IMG) {
            logInDB.setGrossImgUrl(imgUrl);
        } else if (uploadType == UPLOAD_TYPE_TARE_IMG) {
            logInDB.setTareImgUrl(imgUrl);
        } else {
            return ResultUtil.error("未知上传图片类型");
        }

        int result = poundLogService.update(logInDB);
        if (result < 1) {
            return ResultUtil.error("图片保存失败");
        }
        return ResultUtil.success(imgUrl);
    }

    /**
     * 文件上传
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/upload", method = {RequestMethod.POST})
    @ResponseBody
    public Result upload(MultipartFile file, HttpServletRequest request) {
        //如果文件不为空，写入上传路径，根据日期来分文件夹
        if (file.isEmpty()) {
            return ResultUtil.error("上传文件为空");
        }

        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH + 1);
        int day = now.get(Calendar.DAY_OF_MONTH);

        //构建图片存储路径
        StringBuilder path = new StringBuilder();
        path.append(request.getServletContext().getRealPath("/images/upload/"));
        path.append(year).append("/").append(month).append("/").append(day).append("/");

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


}