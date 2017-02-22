package cn.com.dssp.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.dssp.util.verify.VerifyCode;

/**
 * 生成验证码
 * @author fly
 *
 */
@Controller
@RequestMapping("/admin")
public class SystemVerifyControllerAdmin {
	public static final Logger logger=Logger.getLogger(SystemVerifyControllerAdmin.class);
	
	@RequestMapping("/verifyCode")
	public void verifyCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//设置浏览器不缓存本页
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-store,no-cache,must-revalidate");
		response.setHeader("Cache-Control", "post-check=0,pre-check=0");
		response.setHeader("Pragma", "no-cache");
		//生成验证码，写入用户session
		String verifyCode=VerifyCode.generateTextCode(VerifyCode.TYPE_NUM_UPPER, 4, "UUBiind");
		request.getSession().setAttribute(VerifyCode.VERIFY_TYPE_COMMENT, verifyCode);
//		System.out.println("生成验证码并写入Session："+verifyCode);
		logger.debug("生成验证码并写入Session："+verifyCode);
		//输出验证码到客户端
		response.setContentType("image/jpeg");
		/**
		 *      textCode 文本验证码
				width 图片宽度
				height 图片高度
				interLine 图片中干扰线的条数
				randomLocation 每个字符的高低位置是否随机
				backColor 图片颜色，若为null，则采用随机颜色
				foreColor 字体颜色，若为null，则采用随机颜色
				lineColor 干扰线颜色，若为null，则采用随机颜色
		 */
		BufferedImage bim=VerifyCode.generateImageCode(verifyCode, 65, 22, 8,true,Color.WHITE,Color.BLACK,null);
		ServletOutputStream stream=response.getOutputStream();
		ImageIO.write(bim, "JPEG", stream);
		try{
			stream.flush();
		}finally{
			stream.close();
		}
	}

}
