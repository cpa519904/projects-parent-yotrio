package com.yotrio.pound.handles;


import com.yotrio.pound.domain.Result;
import com.yotrio.pound.enums.ResultEnum;
import com.yotrio.pound.exceptions.TaskException;
import com.yotrio.pound.exceptions.UserException;
import com.yotrio.pound.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理类
 * 模块名称：study-boot com.wangyq.handles
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-09-03 下午2:02
 * 系统版本：1.0.0
 **/
@ControllerAdvice
public class ExceptionHandle {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = UserException.class)
    @ResponseBody
    public Result handleUserExc(Exception e) {
        if (e instanceof UserException) {
            UserException userException = (UserException) e;
            return ResultUtil.error(userException.getCode(), userException.getMessage());
        } else {
            logger.error("【系统异常】{}", e);
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR.getCode(), ResultEnum.UNKNOWN_ERROR.getMsg());
        }
    }

    @ExceptionHandler(value = TaskException.class)
    @ResponseBody
    public Result handleTaskExc(Exception e) {
        if (e instanceof TaskException) {
            TaskException taskException = (TaskException) e;
            return ResultUtil.error(taskException.getCode(), taskException.getMessage());
        } else {
            logger.error("【系统异常】{}", e);
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR.getCode(), ResultEnum.UNKNOWN_ERROR.getMsg());
        }
    }
}
