package com.lyarc.tp.corp.common.handler;


import com.lyarc.tp.corp.common.ResultVo;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
@ResponseBody
@Order(Ordered.LOWEST_PRECEDENCE)
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResultVo<String> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        logger.error(ex.getMessage(), ex);
        return ResultVo.failure();
    }

    @ExceptionHandler(value = Exception.class)
    public ResultVo<String> defaultErrorHandler(HttpServletRequest request, Exception throwable) {
        logger.error(throwable.getMessage(), throwable);
        if (request.getParameter("debug") != null) {
			return ResultVo.failure(500, ExceptionUtils.getStackTrace(throwable));
		}
        return ResultVo.failure();
    }

}
