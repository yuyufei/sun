package cn.com.dssp.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 处理登录index的各种事件
 * <p>Title: SystemIndexControllerAdmin</p>
 * <p>Description: </p>
 * @author	fly
 * @date	2017年1月17日上午9:58:59
 * @version 1.0
 */
@Controller
@RequestMapping("/admin")
public class SystemIndexControllerAdmin {
	private static final Logger LOG=Logger.getLogger(SystemIndexControllerAdmin.class);
	
	/**
	 * 登录，get方式
	 */
	@RequestMapping(value="login.html",method=RequestMethod.GET)
	public String login(){
		return "admin/login";
	}

}
