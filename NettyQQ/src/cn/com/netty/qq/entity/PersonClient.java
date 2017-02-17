package cn.com.netty.qq.entity;

import io.netty.channel.Channel;

public class PersonClient {
	private int id;
	private String name;
	private Channel  ClientChannel;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Channel getClientChannel() {
		return ClientChannel;
	}
	public void setClientChannel(Channel clientChannel) {
		ClientChannel = clientChannel;
	}

	

}
