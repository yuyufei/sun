package cn.com.netty.qq.protocol;

import cn.com.netty.qq.utils.ByteObjConverter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;


public class MsgEncoder extends MessageToByteEncoder<MsgBody>{

	@Override
	protected void encode(ChannelHandlerContext ctx, MsgBody msg, ByteBuf out) throws Exception {
       byte[] datas=ByteObjConverter.ObjectToByte(msg);
       out.writeBytes(datas);
       ctx.flush();
		
		/*//将message转为byte数据
		MsgHeader header=msg.getHeader();
		out.writeBytes(header.getChannelId().getBytes());
		out.writeInt(header.getLength());
		//写入主体消息
		out.writeBytes(msg.getContent().getBytes());
		ctx.flush();*/
	}

}
