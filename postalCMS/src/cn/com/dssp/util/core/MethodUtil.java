package cn.com.dssp.util.core;

import javax.servlet.http.HttpServletRequest;

public class MethodUtil {
	/**
	 * 获取登录用户的ip地址
	 * <p>Title: getIpAddr</p>
	 * <p>Description: </p>
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request){
		String ip=request.getHeader("x-forwarded-for");
		if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
			ip=request.getHeader("Proxy-Client-for");
		}
		if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
			ip=request.getHeader("WL-Proxy-Client-IP");
		}
		if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
			ip=request.getRemoteAddr();
		}
		if(ip.equals("0:0:0:0:0:0:0:1")){
			ip="本地";
		}
		return ip;
	}

}
