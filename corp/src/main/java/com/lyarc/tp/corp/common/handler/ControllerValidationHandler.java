package com.lyarc.tp.corp.common.handler;

import com.lyarc.tp.corp.common.ResultVo;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 处理controller方法里@valid 注解生成的错误异常，消息内容和代码定义到form或query bean相关javax.validation注解中。message格式为：msg|code
 * @version 1.0
 * @since JDK1.8
 * @author yangjunxiao
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年2月13日 上午9:38:25
 */
@ControllerAdvice
public class ControllerValidationHandler {
//  @Autowired
//  private MessageSource msgSource;

  @ExceptionHandler({BindException.class})
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public ResultVo<String> processValidationError(BindException ex) {
    BindingResult result = ex.getBindingResult();
    return processValidationMsg(result);
  }

  @ExceptionHandler({MethodArgumentNotValidException.class})
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public ResultVo<String> processValidationError(MethodArgumentNotValidException ex) {
    BindingResult result = ex.getBindingResult();
    return processValidationMsg(result);
  }
  
  private ResultVo<String> processValidationMsg(BindingResult result) {
      FieldError error = result.getFieldError();
      String[] msgAndCode = error.getDefaultMessage().split("\\|");
      if (msgAndCode.length!=2) {
          return ResultVo.failure(error.getDefaultMessage());
      }
      
      return ResultVo.failure(Integer.parseInt(msgAndCode[1]),msgAndCode[0]);
  }
}