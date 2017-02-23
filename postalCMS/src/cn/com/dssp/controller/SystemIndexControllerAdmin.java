package cn.com.dssp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.dssp.model.TbsLoginLogModel;
import cn.com.dssp.model.TbsMenuModel;
import cn.com.dssp.model.TbsUserModel;
import cn.com.dssp.service.TbsLoginLogService;
import cn.com.dssp.service.TbsMenuService;
import cn.com.dssp.service.TbsUserService;
import cn.com.dssp.util.core.MethodUtil;
import cn.com.dssp.util.core.SessionUtil;

@Controller
@RequestMapping("/admin")
public class SystemIndexControllerAdmin {
	private static final Logger logger=Logger.getLogger(SystemIndexControllerAdmin.class);
	public static MethodUtil util=new MethodUtil();
	private StringBuffer sb=new StringBuffer();
	@Autowired
	private TbsLoginLogService<TbsLoginLogModel> logService;
	@Autowired
	private TbsUserService<TbsUserModel> userService;
	@Autowired
	private TbsMenuService<TbsMenuModel> menuService;
	@RequestMapping(value="login.html",method=RequestMethod.GET)
	public String login(){
		return "admin/login";
	}
	
	/**
	 * 登录
	 * <p>Title: login</p>
	 * <p>Description: </p>
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="submit.html",method=RequestMethod.POST)
	public void login(TbsUserModel userModel,TbsLoginLogModel logModel,HttpServletRequest request,HttpServletResponse response) throws Exception{
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
			logService.insert(logModel);
			return;
		}
		if(!sessionVerifyCode.toUpperCase().equals(verifyCode.toUpperCase())){
			msg="验证码错误";
			util.toJsonMsg(response, 2, msg);
			logModel.setMsg(msg);
			logService.insert(logModel);
			return;
		}
		Map<String, Object> map=new HashMap<>();
		map.put("username", userModel.getUsername());
		map.put("password", util.getDES("desKey!@#", userModel.getPassword(), 0));
		List<TbsUserModel> ltub=   userService.selectByMap(map);
		if(null==ltub || ltub.size()!=1){
			msg="用户名密码有误";
			util.toJsonMsg(response, 1, msg);
			logModel.setMsg(msg);
			logService.insert(logModel);
			return;
		}
		userModel=ltub.get(0);
		Integer isAdmin=userModel.getIsAdmin()==null ? 1 :userModel.getIsAdmin();
		SessionUtil.setAttr(request, "isAdmin", ""+isAdmin);
		SessionUtil.setAttr(request, "tbsUserModel", userModel);
		List<String> authUrls=new ArrayList<>();
	    authUrls.add("/admin/index.html");
	    SessionUtil.setAttr(request, "authUrls", authUrls);
	    util.toJsonMsg(response, 0, null);
	    logModel.setStatus(0);//成功
	    logModel.setMsg("登陆成功，"+(isAdmin==0 ? "超级管理员":"授权管理员"));
	    logService.insert(logModel);
	    return;
	}
	
	/**
	 * 主页
	 * <p>Title: index</p>
	 * <p>Description: </p>
	 * @return
	 */
	@RequestMapping("/index.html")
	public String index(HttpServletRequest request,ModelMap modelMap) throws Exception{
		Map<String, Object> map=new HashMap<>();
		map.put("andCondition", "parentId IS NULL");
		map.put("orderCondition", "sortNumber");
		List<TbsMenuModel> parentMenu=menuService.selectByMap(map);
		String isAdmin=(String) SessionUtil.getAttr(request, "isAdmin");
        if(null!=isAdmin && isAdmin.equals("0")){//管理员
        	
        }
		return "admin/login";
	}

}
