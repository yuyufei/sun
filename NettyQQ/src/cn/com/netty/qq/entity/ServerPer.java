package cn.com.netty.qq.entity;

import io.netty.channel.Channel;

public class ServerPer {
	private String uuid;
	private Channel channel ;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	

}
