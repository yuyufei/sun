package cn.com.dssp.util.core;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import sun.misc.BASE64Decoder;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

/**
 * Ueditor文件上传辅助类
 * <p>Title: Uploader</p>
 * <p>Description: </p>
 * @author	fly
 * @date	2017年1月17日下午4:03:28
 * @version 1.0
 */
public class Uploader {
	//输出文件地址
	private String url="";
	//上传文件名
	private String fileName="";
	//状态
	private String state="";
	//文件类型
	private String type="";
	//文件原始名
	private String originalName="";
	//文件大小
	private String size="";
	private HttpServletRequest request=null;
	private String title="";

	//保存路径
	private String savePath="upload";
	//文件允许格式
	private String[] allowFiles={ ".rar", ".doc", ".docx", ".zip", ".pdf",".txt", ".swf", ".wmv", ".gif", ".png", ".jpg", ".jpeg", ".bmp" };
	//文件大小限制，单位kb
	private int maxSize=10000;

	private HashMap<String, String> errorInfo=
			new HashMap<>();

	public Uploader(HttpServletRequest request) {
		this.request = request;
		HashMap<String, String> tmp = this.errorInfo;
		tmp.put("SUCCESS", "SUCCESS"); //默认成功
		tmp.put("NOFILE", "未包含文件上传域");
		tmp.put("TYPE", "不允许的文件格式");
		tmp.put("SIZE", "文件大小超出限制");
		tmp.put("ENTYPE", "请求类型ENTYPE错误");
		tmp.put("REQUEST", "上传请求异常");
		tmp.put("IO", "IO异常");
		tmp.put("DIR", "目录创建失败");
		tmp.put("UNKNOWN", "未知错误");

	}

	public void upload(){
		boolean isMultipart=
				ServletFileUpload.isMultipartContent(this.request);
		if(!isMultipart){
			this.state=this.errorInfo.get("NOFILE");
			return;
		}
		DiskFileItemFactory dff=new DiskFileItemFactory();
		String savePath=this.getFolder(this.savePath);
		dff.setRepository(new File(savePath));
		try {
			ServletFileUpload sfu=new ServletFileUpload(dff);
			sfu.setSizeMax(this.maxSize*1024);
			sfu.setHeaderEncoding("utf-8");
			FileItemIterator fileItemIterator=sfu.getItemIterator(this.request);
			while(fileItemIterator.hasNext()){
				FileItemStream fileItemStream=fileItemIterator.next();
				if(!fileItemStream.isFormField()){
					this.originalName=fileItemStream.getName().substring(fileItemStream.getName().lastIndexOf(System.getProperty("file.separator"))+1);
					if(!this.checkFileType(this.originalName)){
						this.state=this.errorInfo.get("TYPE");
						continue;
					}
					this.fileName=this.getName(this.originalName);
					this.type=this.getFileExt(this.fileName);
					this.url=savePath+"/"+this.fileName;
					BufferedInputStream inputStream=new BufferedInputStream(fileItemStream.openStream());
					FileOutputStream outputStream=new FileOutputStream(new File(this.getPhysicalPath(this.url)));
					BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(outputStream);
					Streams.copy(inputStream, bufferedOutputStream, true);
					this.state=this.errorInfo.get("SUCCESS");
					//UE只会处理单张上传，完成后退出
					break;
				}else{
					String fname=fileItemStream.getFieldName();
					//只处理title，其余表单请自行处理
					if(!fname.equals("pictitle")){
						continue;
					}
					BufferedInputStream bufferedInputStream=new BufferedInputStream(fileItemStream.openStream());
					BufferedReader reader=new BufferedReader(new InputStreamReader(bufferedInputStream)
							);
					StringBuffer result=new StringBuffer();
					while(reader.ready()){
						result.append((char)reader.read());
					}
					this.title=new String(result.toString().getBytes(),"utf-8");
					reader.close();
				}
			}
		} catch (Exception e) {
		}

	}
	
	/**
	 * 接收并保存以base64格式上传的文件
	 * 
	 */
	public void uploadBase64(String fieldName){
		String savePath=this.getFolder(this.savePath);
		String base64Data=this.request.getParameter(fieldName);
		this.fileName=this.getName("test.png");
		this.url=savePath+"/"+this.fileName;
		BASE64Decoder decoder=new BASE64Decoder();
		try {
            File outFile=new File(this.getPhysicalPath(this.url));
            OutputStream outputStream=new FileOutputStream(outFile);
            byte[] bs=decoder.decodeBuffer(base64Data);
            for(int i=0;i<bs.length;i++){
            	if(bs[i]<0){
            		bs[i]+=256;
            	}
            }
            outputStream.write(bs);
            outputStream.flush();
            outputStream.close();
            this.state=this.errorInfo.get("SUCCESS");
		} catch (Exception e) {
		}
	}
	
	
	
	
	/**
	 * 文件类型判断
	 */
	private boolean checkFileType(String fileName){
		Iterator<String> type=Arrays.asList(this.allowFiles).iterator();
		while(type.hasNext()){
			String ext=type.next();
			if(fileName.toLowerCase().endsWith(ext)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 获取文件扩展名
	 */
	private String getFileExt(String fileName){
		return fileName.substring(fileName.lastIndexOf("."));
	}
	
	/**
	 * 依据原始文件名生成新文件名
	 */
	private String getName(String fileName){
		Random random=new Random();
		return this.fileName=""+random.nextInt(10000)
		+System.currentTimeMillis()+this.getFileExt(fileName);
	}
	
	/**
	 * 根据字符串创建本地目录，并按照日期建立子目录返回
	 */
	private String getFolder(String path){
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
		path+="/"+format.format(new Date());
		File dir=new File(this.getPhysicalPath(path));
		if(!dir.exists()){
			try {
				dir.mkdirs();
			} catch (Exception e) {
              this.state=this.errorInfo.get("DIR");
              return "";
			}
		}
		return path;
	}
	
	/**
	 * 根据传入的虚拟路径获取物理路径
	 */
	private String getPhysicalPath(String path){
		String servletPath=this.request.getServletPath();
		String realPath=this.request.getSession().getServletContext()
				.getRealPath(servletPath);
		return new File(realPath).getParent()+"/"+path;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public void setAllowFiles(String[] allowFiles) {
		this.allowFiles = allowFiles;
	}

	public void setMaxSize(int size) {
		this.maxSize = size;
	}

	public String getSize() {
		return this.size;
	}

	public String getUrl() {
		return this.url;
	}

	public String getFileName() {
		return this.fileName;
	}

	public String getState() {
		return this.state;
	}
	
	public String getTitle() {
		return this.title;
	}

	public String getType() {
		return this.type;
	}

	public String getOriginalName() {
		return this.originalName;
	}

}
