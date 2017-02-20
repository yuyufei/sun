package cn.com.dssp.util.core;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * 对Session进行操作
 * <p>Title: SessionUtil</p>
 * <p>Description: </p>
 * @author	fly
 * @date	2017年2月20日下午4:21:56
 * @version 1.0
 */
public class SessionUtil {
	protected static final Logger logger=Logger.getLogger(SessionUtil.class);
	private static final String USER="user"; //用户
	private static final String VERIFY_CODE="verifyCode";//验证码
	
	/**
	 * 设置session的值
	 * <p>Title: setAttr</p>
	 * <p>Description: </p>
	 */
	public static void setAttr(HttpServletRequest request,String key,Object value){
		request.getSession(true).setAttribute(key, value);
	}
	/**
	 * 获取session的值
	 * <p>Title: getAttr</p>
	 * <p>Description: </p>
	 * @return
	 */
	public static Object getAttr(HttpServletRequest request,String key){
		return request.getSession(true).getAttribute(key);
	}
	/**
	 * 删除session的值
	 * <p>Title: removeAttr</p>
	 * <p>Description: </p>
	 */
	public static void removeAttr(HttpServletRequest request,String key){
		request.getSession(true).removeAttribute(key);
	}
	/**
	 * 设置用户信息到session
	 * <p>Title: setUser</p>
	 * <p>Description: </p>
	 */
	public static void setUser(HttpServletRequest request,Object user){
		request.getSession(true).setAttribute(USER, user);
	}
	/**
	 * 从session中获取用户信息
	 * <p>Title: getUser</p>
	 * <p>Description: </p>
	 * @return
	 */
	public static Object getUser(HttpServletRequest request){
		return request.getSession(true).getAttribute(USER);
	}
	/**
	 * 从session中获取用户信息
	 * <p>Title: removeUser</p>
	 * <p>Description: </p>
	 */
	public static void removeUser(HttpServletRequest request){
		removeAttr(request, USER);
	}
	/**
	 * 设置验证码到session
	 * <p>Title: getVerifyCode</p>
	 * <p>Description: </p>
	 * @return
	 */
	public static void setVerifyCode(HttpServletRequest request,String sessionVerifyCode){
		request.getSession(true).setAttribute(VERIFY_CODE, sessionVerifyCode);
	}
	/**
	 * 从session中获取验证码
	 * <p>Title: getVerifyCode</p>
	 * <p>Description: </p>
	 * @return
	 */
	public static String getVerifyCode(HttpServletRequest request){
		return (String) request.getSession(true).getAttribute(VERIFY_CODE);
	}
	/**
	 * 从session中删除验证码
	 * <p>Title: removeVerifyCode</p>
	 * <p>Description: </p>
	 */
	public static void removeVerifyCode(HttpServletRequest request){
		removeAttr(request, VERIFY_CODE);
	}
	/**
	 * 清除所有
	 * <p>Title: removeSessionAll</p>
	 * <p>Description: </p>
	 */
	public static void removeSessionAll(HttpSession session){
		if(session==null){
			Enumeration<?> e=session.getAttributeNames();
			while(e.hasMoreElements()){
				String sessionName=(String) e.nextElement();
				System.out.println("removeSessionName:"+sessionName);
				session.removeAttribute(sessionName);
			}
			
		}
	}

}
