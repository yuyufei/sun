package cn.com.dssp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 生成验证码
 * @author fly
 *
 */
@Controller
@RequestMapping("/admin")
public class SystemVerifyControllerAdmin {
	@RequestMapping("/verifyCode")
	public void verifyCode(HttpServletRequest request,HttpServletResponse response){
		//设置浏览器不缓存本页
		
	}

}
