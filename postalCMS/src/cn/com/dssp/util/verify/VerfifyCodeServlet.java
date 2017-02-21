package cn.com.dssp.util.verify;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 向浏览器输出验证码
 * <p>Title: VerfifyCodeServlet</p>
 * <p>Description: </p>
 * @author	fly
 * @date	2017年2月21日上午9:30:46
 * @version 1.0
 */
public class VerfifyCodeServlet extends HttpServlet{

	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;
	
	public static final Logger log=Logger.getLogger(VerfifyCodeServlet.class);
	public VerfifyCodeServlet() {
        super();
	}
	@Override
	public void destroy() {
		super.destroy();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置浏览器不缓存本页
		resp.setDateHeader("Expires", 0);
		resp.setHeader("Cache-Control", "no-store,no-cache,must-revalidate");
		resp.addHeader("Cache-Control", "post-check=0,pre-check=0");
		resp.setHeader("Pragma", "no-cache");
		//生成验证码，写入用户session
		String verifyCode=VerifyCode.generateTextCode(VerifyCode.TYPE_NUM_UPPER, 4, "Oidd");
		req.getSession().setAttribute(VerifyCode.VERIFY_TYPE_COMMENT,verifyCode );
		log.debug("verifyCode = "+verifyCode);
		//输出验证码到客户端
		resp.setContentType("image/jpeg");
		/*
	    textCode 文本验证码
		width 图片宽度
		height 图片高度
		interLine 图片中干扰线的条数
		randomLocation 每个字符的高低位置是否随机
		backColor 图片颜色，若为null，则采用随机颜色
		foreColor 字体颜色，若为null，则采用随机颜色
		lineColor 干扰线颜色，若为null，则采用随机颜色
	*/
		BufferedImage bim=VerifyCode.generateImageCode(verifyCode, 70, 22, 15,true,Color.WHITE,Color.BLACK,null);
		ServletOutputStream stream=resp.getOutputStream();
		ImageIO.write(bim, "JPEG", stream);
		try{
			stream.flush();
		}finally{
			stream.close();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
	@Override
	public void init() throws ServletException {
	}
	
	

}
