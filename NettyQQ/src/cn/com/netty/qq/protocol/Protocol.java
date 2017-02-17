package cn.com.netty.qq.protocol;

import java.io.Serializable;
import java.net.ProtocolException;
import java.util.Map.Entry;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * 内部协议  保留后续使用
 * 
 * 协议构成： <br>
 * <code>
 * [P_M_S, Message Start]<br>
 * 		Message Action<br>
 * 		[P_H_S, Header Start]<br>
 * 			{ loop:<br>
 * 				[P_H_K, Header key]<br>
 * 					key length<br>
 * 					key<br>
 * 				[P_H_V, Header value]<br>
 * 					value length<br>
 * 					value<br>
 * 			}<br>
 * 		[P_H_E, Header End]<br>
 * 		[P_B_S, Body Start]<br>
 * 			body length<br>
 * 			body<br>
 * 		[P_B_E, Body End]<br>
 * [P_M_E, Message End]<br>
 * </code>
 */
public class Protocol {
	
	/**
	 * 协议编码器
	 */
	public static ByteBuf encode(ProtocolMessage message){
		ByteBuf buf=Unpooled.buffer();
		//message start
		buf.writeByte(ProtocolConstants.P_M_S);
		//message action
		buf.writeByte(message.getAction());
		//header start
		buf.writeByte(ProtocolConstants.P_H_S);
		if(message.getHeader()!=null){
			for(Entry<String, String> entry: message.getHeader().entrySet()){
				//header key start
				buf.writeByte(ProtocolConstants.P_H_K);
				//header key length
				buf.writeShort(entry.getKey().length());
				//header key
				buf.writeBytes(entry.getKey().getBytes());
				//header value start
				buf.writeByte(ProtocolConstants.P_H_V);
				//header value length
				buf.writeShort(entry.getValue().length());
				//header value
				buf.writeBytes(entry.getValue().getBytes());
			}
		}
		//header end
		buf.writeByte(ProtocolConstants.P_H_E);
		// Body Start
		buf.writeByte(ProtocolConstants.P_B_S);
		// Body Length
		buf.writeShort(message.getBody().readableBytes());
		// Body
		buf.writeBytes(message.getBody());
		// Body End
		buf.writeByte(ProtocolConstants.P_B_E);

		// Message End
		buf.writeByte(ProtocolConstants.P_M_E);

		return buf.copy();
	}
	/**
	 * 协议解码器
	 * 
	 * @param message
	 *            解码结果
	 * @param buf
	 *            解码数据
	 * @throws ProtocolException
	 *             解码异常
	 */
	public static void decode(ProtocolMessage message, ByteBuf buf) throws ProtocolException {
		byte[] _b = new byte[512];

		// Message Start
		assert0(buf.readByte(), ProtocolConstants.P_M_S);
		// Message Action
		message.setAction(buf.readByte());

		// Header Start
		assert0(buf.readByte(), ProtocolConstants.P_H_S);

		byte n;
		while (true) {
			n = buf.readByte();
			if (n == ProtocolConstants.P_H_K) {
				// Header Key
				short len = buf.readShort();
				buf.readBytes(_b, 0, len);
				String key = new String(_b, 0, len);
				assert0(buf.readByte(), ProtocolConstants.P_H_V);
				len = buf.readShort();
				buf.readBytes(_b, 0, len);
				String value = new String(_b, 0, len);
				message.setHeader(key, value);
			} else {
				assert0(n, ProtocolConstants.P_H_E);
				break;
			}
		}

		assert0(buf.readByte(), ProtocolConstants.P_B_S);
		short len = buf.readShort();
		message.capacity(len);
		buf.readBytes(message.getBody(), len);
		assert0(buf.readByte(), ProtocolConstants.P_B_E);
		assert0(buf.readByte(), ProtocolConstants.P_M_E);
	}
	/**
	 * 协议分隔符校验
	 * 
	 * @param b1
	 *            实际值
	 * @param b2
	 *            理论值
	 * @throws ProtocolException
	 *             解码异常
	 */
	private static void assert0(byte b1, byte b2) throws ProtocolException {
		if (b1 != b2)
			throw new ProtocolException();
	}

}
