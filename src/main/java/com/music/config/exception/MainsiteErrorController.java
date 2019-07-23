package com.music.config.exception;

import com.alibaba.fastjson.JSONObject;
import com.music.api.APIServiceCode;
import com.music.util.CommonUtil;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 功能描述:系统错误拦截, 主要是针对404的错误
 * @param:
 * @return:
 * @auther: xiep
 * @date: 2019/5/28 16:51
 */
@Controller
public class MainsiteErrorController implements ErrorController {

	private static final String ERROR_PATH = "/error";

	/**
	 * 主要是登陆后的各种错误路径  404页面改为返回此json
	 * 未登录的情况下,大部分接口都已经被shiro拦截,返回让用户登录了
	 *
	 * @return 501的错误信息json
	 */
	@RequestMapping(value = ERROR_PATH)
	@ResponseBody
	public JSONObject handleError() {
		return CommonUtil.errorJson(APIServiceCode.SYSTEM_PARAM_IS_INVALID);
	}

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}
}

