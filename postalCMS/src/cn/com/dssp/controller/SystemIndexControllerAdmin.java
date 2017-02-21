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
import cn.com.dssp.util.core.SessionUtil;

@Controller
@RequestMapping("/admin")
public class SystemIndexControllerAdmin {
	private static final Logger logger=Logger.getLogger(SystemIndexControllerAdmin.class);
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
		String sessionVerifyCode=(String) SessionUtil.getAttr(request, "VERIFY_TYPE_COMMENT");
		SessionUtil.removeAttr(request, "VERIFY_TYPE_COMMENT");
		String verifyCode=request.getParameter("verifyCode");//提交的验证码
		logger.debug("tbsLoginModel:"+logModel.toString());
		String msg;
		String ip=MethodUtil.getIpAddr(request);
		userModel.setIp(ip);
		logModel.setIp(ip);
		logModel.setId(util.getUid());
		if(null==sessionVerifyCode || null==verifyCode || verifyCode.trim().length()!=4){
			msg="验证码长度有误或者已经失效";
			util.toJsonMsg(response, 2, msg);
			logModel.setMsg(msg);
			
		}
		return "admin/login";
	}
	

}
