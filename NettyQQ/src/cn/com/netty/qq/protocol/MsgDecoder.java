package cn.com.netty.qq.protocol;

import java.util.List;

import cn.com.netty.qq.utils.ByteBufToBytes;
import cn.com.netty.qq.utils.ByteObjConverter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * 自定义解码器
 * <p>Title: MsgDecoder</p>
 * <p>Description: </p>
 * @author	fly
 * @date	2017年1月10日下午2:16:38
 * @version 1.0
 */
public class MsgDecoder extends ByteToMessageDecoder{


	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		ByteBufToBytes bufToBytes=new ByteBufToBytes();
		Object obj=ByteObjConverter.ByteToObject(bufToBytes.read(in));
		out.add(obj);
/*		//获取sessionId
		byte[] bytes=new byte[36];
		in.readBytes(bytes);
		String channelId=bytes.toString();
		//获取消息长度
		int length=in.readInt();
		//构建消息
		MsgHeader header=new MsgHeader(channelId, length);
		//获取消息体
		byte[] content=in.readBytes(in.readableBytes()).array();
		MsgBody body=new MsgBody(header, new String(content));
		out.add(body);
*/	}


	
}
