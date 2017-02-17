package cn.com.dssp.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class SystemIndexControllerAdmin {
	private static final Logger LOG=Logger.getLogger(SystemIndexControllerAdmin.class);
	
	@RequestMapping(value="login.html",method=RequestMethod.GET)
	public String login(){
		return "admin/login";
	}

}
