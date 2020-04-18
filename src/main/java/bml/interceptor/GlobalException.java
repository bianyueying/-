package bml.interceptor;

import bml.others.BmlResult;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;

/**
 * @author 月影
 * Date 2020/4/16 15:40
 * 全局异常处理 目前还不太熟悉怎么用这玩意...
 */
@ControllerAdvice
public class GlobalException {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalException.class);

    @ExceptionHandler(ClassCastException.class)
    public void classCastException() {
        LOG.info("点击了Swagger2文档链接,可能会出现异常。");
    }

    @ExceptionHandler(ParseException.class)
    public void parseException() {
        LOG.info("时间转换异常，可能是用户输入的时间格式不对");
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public BmlResult<Object> unauthorizedException() {
        LOG.info("普通用户企图越权删除信息");
        return new BmlResult<>(401,"权限不足,删除失败");
    }

    @ExceptionHandler(AuthenticationException.class)
    public void authenticationException() {
        LOG.info("授权逻辑异常");
    }

}
