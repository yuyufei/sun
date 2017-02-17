package cn.com.netty.qq.protocol;

import java.util.List;

import cn.com.netty.qq.utils.ByteBufToBytes;
import cn.com.netty.qq.utils.ByteObjConverter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * �Զ��������
 * <p>Title: MsgDecoder</p>
 * <p>Description: </p>
 * @author	fly
 * @date	2017��1��10������2:16:38
 * @version 1.0
 */
public class MsgDecoder extends ByteToMessageDecoder{


	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		ByteBufToBytes bufToBytes=new ByteBufToBytes();
		Object obj=ByteObjConverter.ByteToObject(bufToBytes.read(in));
		out.add(obj);
/*		//��ȡsessionId
		byte[] bytes=new byte[36];
		in.readBytes(bytes);
		String channelId=bytes.toString();
		//��ȡ��Ϣ����
		int length=in.readInt();
		//������Ϣ
		MsgHeader header=new MsgHeader(channelId, length);
		//��ȡ��Ϣ��
		byte[] content=in.readBytes(in.readableBytes()).array();
		MsgBody body=new MsgBody(header, new String(content));
		out.add(body);
*/	}


	
}
