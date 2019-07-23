package com.music.config.exception;

import com.alibaba.fastjson.JSONObject;
import com.music.api.APIServiceCode;
import com.music.util.CommonUtil;


/**
 * 功能描述:
 * @param: 本系统使用的自定义错误类
 * 比如在校验参数时,如果不符合要求,可以抛出此错误类
 * 拦截器可以统一拦截此错误,将其中json返回给前端
 * @auther: xiep
 * @date: 2019/5/28 15:08
 */
public class CommonJsonException extends RuntimeException {
	private JSONObject resultJson;

	/**
	 * 调用时可以在任何代码处直接throws这个Exception,
	 * 都会统一被拦截,并封装好json返回给前台
	 *
	 * @param apiServiceCode 以错误的APIServiceCode做参数
	 */
	public CommonJsonException(APIServiceCode apiServiceCode) {
		this.resultJson = CommonUtil.errorJson(apiServiceCode);
	}

	public CommonJsonException(JSONObject resultJson) {
		this.resultJson = resultJson;
	}

	public JSONObject getResultJson() {
		return resultJson;
	}
}
