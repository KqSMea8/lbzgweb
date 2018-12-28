package com.lyarc.tp.corp.common.handler;

import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.exception.ServiceException;
import org.omg.CORBA.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ServiceExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(ServiceExceptionHandler.class);

    @ExceptionHandler({UserException.class, ServiceException.class})
    @ResponseBody
    public ResultVo<String> processValidationError(ServiceException ex) {
        // XXX 需要ServiceException中完善更详细信息
        logger.warn(ex.getMessage(), ex);
        return ResultVo.failure(ex.getErrorCode(), ex.getMessage());
    }
}
