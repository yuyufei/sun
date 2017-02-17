package cn.com.netty.qq.protocol;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * 解码对象
 * <p>Title: ProtocolMessage</p>
 * <p>Description: </p>
 * @author	fly
 * @date	2017年1月11日下午5:10:20
 * @version 1.0
 */
public class ProtocolMessage implements Serializable{
	
	     /** serialVersionUID*/
	private static final long serialVersionUID = 1L;
		// 消息类型
		private byte action;
		// 消息头
		protected Map<String, String> header;
		// 消息体
		protected ByteBuf body;
		
		public ProtocolMessage() {
			header = new HashMap<String, String>(0);
			body = Unpooled.buffer();
		}

		public byte getAction() {
			return action;
		}

		public void setAction(byte action) {
			this.action = action;
		}

		public void setHeader(String k, String v) {
			header.put(k, v);
		}

		public Map<String, String> getHeader() {
			return header;
		}

		public void capacity(int newCapacity) {
			body.capacity(newCapacity);
		}

		public ByteBuf getBody() {
			return body;
		}

		public void setBody(ByteBuf msg) {
			this.body.clear();
			this.body.writeBytes(msg);
		}

}
