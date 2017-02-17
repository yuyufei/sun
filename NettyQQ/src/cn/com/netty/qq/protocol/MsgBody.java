package cn.com.netty.qq.protocol;

import java.io.Serializable;

public class MsgBody implements Serializable{
	
	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;
	private MsgHeader header;
	private String content;
	public MsgBody(MsgHeader header,String content) {
       this.header=header;
       this.content=content;
	}
	public final MsgHeader getHeader() {
		return header;
	}
	public final void setHeader(MsgHeader header) {
		this.header = header;
	}
	public final String getContent() {
		return content;
	}
	public final void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "MsgBody [header=" + header + ", content=" + content + "]";
	}
	

}
