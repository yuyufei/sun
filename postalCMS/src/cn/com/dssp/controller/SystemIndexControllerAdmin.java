package cn.com.dssp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.dssp.model.TbsLoginLogModel;
import cn.com.dssp.model.TbsUserModel;
import cn.com.dssp.util.core.MethodUtil;

@Controller
@RequestMapping("/admin")
public class SystemIndexControllerAdmin {
	private static final Logger LOG=Logger.getLogger(SystemIndexControllerAdmin.class);
	public static MethodUtil util=new MethodUtil();
	private StringBuffer sb=new StringBuffer();
	
	
	/**
	 * 登录
	 * <p>Title: login</p>
	 * <p>Description: </p>
	 * @return
	 */
	@RequestMapping(value="login.html",method=RequestMethod.POST)
	public String login(TbsUserModel userModel,TbsLoginLogModel logModel,HttpServletRequest request,HttpServletResponse response){
		
		return "admin/login";
	}
	

}
