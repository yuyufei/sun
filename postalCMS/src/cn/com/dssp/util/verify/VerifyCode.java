package cn.com.dssp.util.verify;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 验证码生成器，可生成数字、小写、大写字母三者混合的验证码
 * <p>Title: VerifyCode</p>
 * <p>Description: </p>
 * @author	fly
 * @date	2017年2月20日下午4:40:51
 * @version 1.0
 */
public class VerifyCode {
	//验证码类型
	public static final String VERIFY_TYPE_COMMENT="VERIFY_TYPE_COMMENT";
	//验证码类型仅数字
	public static final int TYPE_NUM_ONLY=0;
	//验证码为字母
	public static final int TYPE_LETTER_ONLY=1;
	//验证码为数字、大写小写字母混合
	public static final int TYPE_ALL_MIXED=2;
	/**
	 * 验证码类型为数字、大写字母混合
	 */
	public static final int TYPE_NUM_UPPER=3;		

	/**
	 * 验证码类型为数字、小写字母混合
	 */
	public static final int TYPE_NUM_LOWER=4;	

	/**
	 * 验证码类型为仅大写字母
	 */
	public static final int TYPE_UPPER_ONLY=5;

	/**
	 * 验证码类型为仅小写字母
	 */
	public static final int TYPE_LOWER_ONLY=6;
	/**
	 * 生成验证码字符串
	 * <p>Title: generateTextCode</p>
	 * <p>Description: </p>
	 * @return
	 */
	public static String generateTextCode(int type ,int length,String exChars){
		if(length<=0) return "";
		StringBuffer code=new StringBuffer();
		int i=0;
		Random r=new Random();
		switch(type){
		//仅数字
		case TYPE_NUM_ONLY:
			while(i<length){
				int t=r.nextInt(10);
				if(exChars==null || exChars.indexOf(t+"")<0){//排除特殊字符
					code.append(t);
					i++;
				}
			}
			break;
			//仅字母
		case TYPE_LETTER_ONLY:
			while(i<length){
				int t=r.nextInt(123);
				if((t>=97 || (t>=65 && t<=90))&&(exChars==null || exChars.indexOf((char)t)<0)){
					code.append((char)t);
					i++;
				}
			}
			break;
			//数字、大写字母、小写字母混合
		case TYPE_ALL_MIXED:
			while(i<length){
				int t=r.nextInt(123);
				if((t>=97||(t>=65&&t<=90)||(t>=48&&t<=57))&&(exChars==null||exChars.indexOf((char)t)<0)){
					code.append((char)t);
					i++;
				}
			}
			break;	

			//数字、大写字母混合
		case TYPE_NUM_UPPER:
			while(i<length){
				int t=r.nextInt(91);
				if((t>=65||(t>=48&&t<=57))&&(exChars==null||exChars.indexOf((char)t)<0)){
					code.append((char)t);
					i++;
				}
			}
			break;		

			//数字、小写字母混合
		case TYPE_NUM_LOWER:
			while(i<length){
				int t=r.nextInt(123);
				if((t>=97||(t>=48&&t<=57))&&(exChars==null||exChars.indexOf((char)t)<0)){
					code.append((char)t);
					i++;
				}
			}
			break;		

			//仅大写字母
		case TYPE_UPPER_ONLY:
			while(i<length){
				int t=r.nextInt(91);
				if((t>=65)&&(exChars==null||exChars.indexOf((char)t)<0)){
					code.append((char)t);
					i++;
				}
			}
			break;				

			//仅小写字母
		case TYPE_LOWER_ONLY:
			while(i<length){
				int t=r.nextInt(123);
				if((t>=97)&&(exChars==null||exChars.indexOf((char)t)<0)){
					code.append((char)t);
					i++;
				}
			}
			break;				

		}

		return code.toString();
	}
	/**
	 * 生成验证码图片
	 * <p>Title: generateImageCode</p>
	 * <p>Description: </p>
	 * @return
	 */
	public static BufferedImage generateImageCode(String textCode,int width,int height,int interLine,boolean randomLocation,Color backColor,Color foreColor,Color lineColor){


		BufferedImage bim=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics g=bim.getGraphics();
		//画背景图
		g.setColor(backColor==null?getRandomColor():backColor);
		g.fillRect(0,0,width,height);

		//画干扰线
		Random r=new Random();
		if(interLine>0){

			int x=0,y=0,x1=width,y1=0;
			for(int i=0;i<interLine;i++){
				g.setColor(lineColor==null?getRandomColor():lineColor);
				y=r.nextInt(height);
				y1=r.nextInt(height);

				g.drawLine(x,y,x1,y1);
			}
		}

		//写验证码

		//g.setColor(getRandomColor());
		//g.setColor(isSimpleColor?Color.BLACK:Color.WHITE);

		//字体大小为图片高度的80%
		int fsize=(int)(height*0.8);
		int fx=height-fsize;
		int fy=fsize;

		g.setFont(new Font("Times New Roman", Font.BOLD, 20));

		//写验证码字符
		for(int i=0;i<textCode.length();i++){
			fy=randomLocation?(int)((Math.random()*0.3+0.6)*height):fy;//每个字符高低是否随机
			g.setColor(foreColor==null?getRandomColor():foreColor);
			g.drawString(textCode.charAt(i)+"",fx,fy);
			fx+=fsize*0.9;
		}


		g.dispose();

		return bim;
	}

	/**
	 * 生成图片验证码，结合体
	 * <p>Title: generateImageCode</p>
	 * <p>Description: </p>
	 * @param type
	 * @param length
	 * @param exChars
	 * @param width
	 * @param height
	 * @param interLine
	 * @param randomLocation
	 * @param backColor
	 * @param foreColor
	 * @param lineColor
	 * @return
	 */
	public static BufferedImage generateImageCode(int type,int length,String exChars,int width,int height,int interLine,boolean randomLocation,Color backColor,Color foreColor,Color lineColor){

		String textCode=generateTextCode(type,length,exChars);
		BufferedImage bim=generateImageCode(textCode,width,height,interLine,randomLocation,backColor,foreColor,lineColor);
		return bim;
	}
	/**
	 * 产生随机颜色
	 * @return
	 */
	private static Color getRandomColor(){
		Random r=new Random();
		Color c=new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
		return c;
	}

}
