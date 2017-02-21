package cn.com.dssp.util.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Key;
import java.security.MessageDigest;
import java.security.Security;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

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
	
	/**
	 * MD5加密方法
	 */
	public String getMD5(String str,String encoding,int no_Lower_Upper){
		if(null==encoding) encoding="utf-8";
		StringBuffer sb=new StringBuffer();
		try{
			MessageDigest md=MessageDigest.getInstance("MD5");
			byte[] array=md.digest(str.getBytes(encoding));
			for(int i=0;i<array.length;i++){
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		if(no_Lower_Upper==0){
			return sb.toString();
		}
		if(no_Lower_Upper==1){
			return sb.toString().toLowerCase();
		}
		if(no_Lower_Upper==2){
			return sb.toString().toUpperCase();
		}
		return null;
	}
	
	/**
	 * DES
	 * <p>Title: getKey</p>
	 * <p>Description: </p>
	 * @return
	 */
	public Key getKey(byte[] arrBtmp){
		byte[] arrB=new byte[8]; //创建一个空的8字节数组
		for(int i=0;i<arrBtmp.length && i<arrB.length;i++){
			arrB[i]=arrBtmp[i];
		}
		Key key=new SecretKeySpec(arrB,"DES");//生成密钥
		return key;
	}
	/**
	 * DES
	 * <p>Title: hexStr2ByteArr</p>
	 * <p>Description: </p>
	 * @return
	 */
	private static byte[] hexStr2ByteArr(String strIn){
		byte[] arrB=strIn.getBytes();
		int iLen=arrB.length;
		//两个字节表示一个字符，所以字节长度是字符串长度*2
		byte[] arrOut=new byte[iLen/2];
		for(int i=0;i<iLen;i=i+2){
			String strTmp=new String(arrB, i, 2);//取arrB数组的第0个字节的后两位
			arrOut[i/2]=(byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
		
	}
	
	public static String byteArr2HexStr(byte[] arrB){
		int iLen=arrB.length;
		StringBuffer sb=new StringBuffer(iLen*2);
		for(int i=0;i<iLen;i++){
			int intTmp=arrB[i];
			//将负数转换为正数
			while(intTmp<0){
				intTmp=intTmp+256;
			}
			//小于OF的数需要在前面补0
			if(intTmp<16){
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp,16));
		}
		return sb.toString();
	}
	
	/**
	 * DES
	 * <p>Title: getDES</p>
	 * <p>Description: </p>
	 * @return
	 */
	public String getDES(String deskey,String str,int type){
		Cipher encryptCipher=null;
		Cipher decryptCipher=null;
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		try{
			Key key=getKey(deskey.getBytes());
			encryptCipher=Cipher.getInstance("DES");
			encryptCipher.init(Cipher.ENCRYPT_MODE, key);
			decryptCipher=Cipher.getInstance("DES");
			decryptCipher.init(Cipher.DECRYPT_MODE, key);
			if(type==0){ //0为加密
				return byteArr2HexStr(encryptCipher.doFinal(str.getBytes()));
			}else{
				return new String(decryptCipher.doFinal(hexStr2ByteArr(str)));
			}
		}catch(Exception e){
			return null;
		}
	}
	
	/**
	 * 静态初始化
	 * <p>Title: getInstance</p>
	 * <p>Description: </p>
	 * @return
	 */
	public static MethodUtil getInstance(){
		return new MethodUtil();
	}
	
	/**
	 * 自创随机数
	 * <p>Title: getRandom</p>
	 * <p>Description: </p>
	 * @param min
	 * @param max
	 * @return
	 */
	public int getRandom(int min,int max){
		return (int) (Math.random()*(max-min)+min);
	}
	/**
	 * 获得随机数
	 * <p>Title: getRandom</p>
	 * <p>Description: </p>
	 * @param number
	 * @return
	 */
	public int getRandom(int number){
		int max=9;
		int min=1;
		for(int i=1;i<number;i++){
			min=min*10;
			max=max*10-9;
		}
		return this.getRandom(min, max);
	}
	
	/**
	 * 获取日期
	 * <p>Title: getDate</p>
	 * <p>Description: </p>
	 * @return
	 */
	public String getDate(int type,String formatStr){
		Date date=new Date();
		SimpleDateFormat format=null;
		if(null!=formatStr){
			format=new SimpleDateFormat(formatStr);
		}else if(type==0){
			format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}else if(type==1){
			format=new SimpleDateFormat("yyyyMMddHHmmss");
		}else if(type==2){
			format=new SimpleDateFormat("yyyyMMdd");
		}
		String str=format.format(date);
		return str;
	}
	
	/**
	 * 时间差
	 * <p>Title: getDateCompare</p>
	 * <p>Description: </p>
	 * @return
	 */
	public long getDateCompare(String current_time,String compare_time){
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long time=0;
		try{
			Date c_time=sf.parse(current_time);
			Date com_time=sf.parse(compare_time);
			long l=c_time.getTime()-com_time.getTime()>0 ? c_time.getTime()-com_time.getTime() : com_time.getTime()-c_time.getTime();
			time=l/1000;//计算超时秒数
		}catch(Exception e){
			e.printStackTrace();
		}
		return time;
	}
	
	/**
	 * 处理时间的加减运算,0为+，1为-
	 * <p>Title: getDateAdd</p>
	 * <p>Description: </p>
	 * @return
	 */
	public long getDateAdd(String startTime,String endTime ,int type){
		long time=0l;
		try{
			Date date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime);
			Date addLong=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime);
			switch(type){
			case 0:
				time=(date.getTime()/1000)+(addLong.getTime()/1000);
				break;
			case 1:
				time =(date.getTime()/1000)-(addLong.getTime()/1000);
				break;
			default:
				time=(date.getTime()/1000)+(addLong.getTime()/1000);
				break;
			}
			date.setTime(time*1000);
			time=date.getTime();
		}catch(Exception e){
			e.printStackTrace();
		}
		return time;
	}
	
	/**
	 * 获取一个月最大一天
	 * <p>Title: getMaxMonth</p>
	 * <p>Description: </p>
	 * @return
	 */
	public Object[] getMaxMonth(String time){
		Object[] obj=new Object[2];
		Date date=null;
			try {
				date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		Calendar a=Calendar.getInstance();
		a.setTime(date);
		a.set(Calendar.DATE, 1);//将日期设置为当月第一天
		a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
		int maxMonth=a.get(Calendar.DATE);
		a.roll(Calendar.DATE, 1);
		time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(a.getTime());
		obj[0]=maxMonth;
		obj[1]=time;
		return obj;
	}
	
	/**
	 * 20位可用于UUID，订单号
	 * <p>Title: getUid</p>
	 * <p>Description: </p>
	 * @return
	 */
	public String getUid(){
		return new SimpleDateFormat("yyMMddHHmmss").format(new Date())+getRandom(8);
	}
	
	/**
	 * 输出json,0、1
	 * <p>Title: toJsonMsg</p>
	 * <p>Description: </p>
	 */
	public void toJsonMsg(HttpServletResponse response,int type,String msg){
		Map<String, Object> map=new HashMap<>();
		map.put("state", type);
		if(type==0){
			map.put("success", true);
			if(msg==null){
				map.put("msg", "成功");
			}else{
				map.put("msg", msg);
			}
		}else{
			map.put("success", false);
            if(msg==null){
            	map.put("msg", "失败");
            }else{
            	map.put("msg", msg);
            }
		}
		this.toJsonPrint(response, JSON.toJSONString(map));
	}
	
	/**
	 * 打印json
	 * <p>Title: toJsonPrint</p>
	 * <p>Description: </p>
	 */
	public void toJsonPrint(HttpServletResponse response,String str){
		this.writer(response,str);
	}
	
	/**
	 * 打印
	 * <p>Title: writer</p>
	 * <p>Description: </p>
	 * @param response
	 * @param str
	 */
	public void writer(HttpServletResponse response,String str){
		try{
			//设置页面不缓存
			response.setHeader("Prama", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out=null;
			out=response.getWriter();
			System.out.println("print:"+str);
			out.println(str);
			out.flush();
			out.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 输出脚本
	 * <p>Title: toScript</p>
	 * <p>Description: </p>
	 */
	public void toScript(HttpServletResponse response,String str){
		StringBuffer sb=new StringBuffer();
		sb.append("<script type=\"text/javascript\">");
		sb.append(str);
		sb.append("</script>");
		response.setContentType("text/html");
		this.writer(response, sb.toString());

	}
	
	public static void main(String[] args) {
//		byte[] s=MethodUtil.hexStr2ByteArr("123456");
//		System.out.println(s.length);
		
		MethodUtil util=new MethodUtil();
////		String s=util.getMD5("we", "utf-8", 2);
//		byte[] bytes=new byte[5];
//		bytes[0]='c';
//		bytes[1]='d';
//		bytes[2]='e';
//		bytes[3]='f';
//		bytes[4]='g';
//		Key s=util.getKey(bytes);
//		System.out.println(s.getEncoded());
		System.out.println(util.getRandom(8));
	}

}
