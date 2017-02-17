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
 * Ueditor�ļ��ϴ�������
 * <p>Title: Uploader</p>
 * <p>Description: </p>
 * @author	fly
 * @date	2017��1��17������4:03:28
 * @version 1.0
 */
public class Uploader {
	//����ļ���ַ
	private String url="";
	//�ϴ��ļ���
	private String fileName="";
	//״̬
	private String state="";
	//�ļ�����
	private String type="";
	//�ļ�ԭʼ��
	private String originalName="";
	//�ļ���С
	private String size="";
	private HttpServletRequest request=null;
	private String title="";

	//����·��
	private String savePath="upload";
	//�ļ������ʽ
	private String[] allowFiles={ ".rar", ".doc", ".docx", ".zip", ".pdf",".txt", ".swf", ".wmv", ".gif", ".png", ".jpg", ".jpeg", ".bmp" };
	//�ļ���С���ƣ���λkb
	private int maxSize=10000;

	private HashMap<String, String> errorInfo=
			new HashMap<>();

	public Uploader(HttpServletRequest request) {
		this.request = request;
		HashMap<String, String> tmp = this.errorInfo;
		tmp.put("SUCCESS", "SUCCESS"); //Ĭ�ϳɹ�
		tmp.put("NOFILE", "δ�����ļ��ϴ���");
		tmp.put("TYPE", "��������ļ���ʽ");
		tmp.put("SIZE", "�ļ���С��������");
		tmp.put("ENTYPE", "��������ENTYPE����");
		tmp.put("REQUEST", "�ϴ������쳣");
		tmp.put("IO", "IO�쳣");
		tmp.put("DIR", "Ŀ¼����ʧ��");
		tmp.put("UNKNOWN", "δ֪����");

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
					//UEֻ�ᴦ�����ϴ�����ɺ��˳�
					break;
				}else{
					String fname=fileItemStream.getFieldName();
					//ֻ����title������������д���
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
	 * ���ղ�������base64��ʽ�ϴ����ļ�
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
	 * �ļ������ж�
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
	 * ��ȡ�ļ���չ��
	 */
	private String getFileExt(String fileName){
		return fileName.substring(fileName.lastIndexOf("."));
	}
	
	/**
	 * ����ԭʼ�ļ����������ļ���
	 */
	private String getName(String fileName){
		Random random=new Random();
		return this.fileName=""+random.nextInt(10000)
		+System.currentTimeMillis()+this.getFileExt(fileName);
	}
	
	/**
	 * �����ַ�����������Ŀ¼�����������ڽ�����Ŀ¼����
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
	 * ���ݴ��������·����ȡ����·��
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
