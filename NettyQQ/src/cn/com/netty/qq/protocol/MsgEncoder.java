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
		
		/*//��messageתΪbyte����
		MsgHeader header=msg.getHeader();
		out.writeBytes(header.getChannelId().getBytes());
		out.writeInt(header.getLength());
		//д��������Ϣ
		out.writeBytes(msg.getContent().getBytes());
		ctx.flush();*/
	}

}
