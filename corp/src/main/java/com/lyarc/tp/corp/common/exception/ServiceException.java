package com.lyarc.tp.corp.common.exception;

import com.lyarc.tp.corp.common.ResultVo;

/**
 * 业务层异常,每个业务模块自定义异常都需要继承此异常 TODO 继续封装业务异常消息，记录更详细信息，when/who/where
 * 
 * @version 1.0
 * @since JDK1.8
 * @author yangjunxiao
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年2月10日 下午12:04:32
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -4614774556543378208L;
	private ResultVo msgWrapper;

	public <T> ServiceException(ResultVo<T> msgWrapper) {
		this.msgWrapper = msgWrapper;
	}

	public ServiceException(Integer code, String message) {
		this.msgWrapper = ResultVo.build(code, message);
	}

	/**
	 * 返回错误代码的字符串表示。
	 * 
	 * @return 错误代码的字符串表示。
	 */
	public Integer getErrorCode() {
		return msgWrapper.getCode();
	}

	/**
	 * 返回业务错误信息
	 * 
	 * @return 业务错误信息
	 * 
	 * @author yangjunxiao
	 * @date 2017年2月10日 下午1:38:20
	 */
	@Override
	public String getMessage() {
		return msgWrapper.getMessage();
	}

}
