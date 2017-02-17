package cn.com.netty.qq.protocol;

import java.io.Serializable;

/**
 * �Զ���Э��header
 * <p>Title: MsgHeader</p>
 * <p>Description: </p>
 * @author	fly
 * @date	2017��1��10������2:12:15
 * @version 1.0
 */
public class MsgHeader implements Serializable{
	
	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;
	private String channelId;
	private int length;
	
	public  MsgHeader(String channelId,int length) {
        this.channelId=channelId;
        this.length=length;
	}
	public final String getChannelId() {
		return channelId;
	}
	public final void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public final int getLength() {
		return length;
	}
	public final void setLength(int length) {
		this.length = length;
	}
	@Override
	public String toString() {
		return "MsgHeader [channelId=" + channelId + ", length=" + length + "]";
	}
	

}
