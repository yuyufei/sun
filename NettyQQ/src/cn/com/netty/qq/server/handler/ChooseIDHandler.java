package cn.com.netty.qq.server.handler;


import java.net.ProtocolException;
import java.util.LinkedHashMap;

import cn.com.netty.qq.entity.ServerPer;
import cn.com.netty.qq.protocol.MsgBody;
import cn.com.netty.qq.protocol.MsgHeader;
import cn.com.netty.qq.protocol.Protocol;
import cn.com.netty.qq.protocol.ProtocolConstants;
import cn.com.netty.qq.protocol.ProtocolMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/**
 * ������Ϣ��
 * <p>Title: ChooseIDHandler</p>
 * <p>Description: </p>
 * @author	fly
 * @date	2017��1��9������9:53:42
 * @version 1.0
 */
public class ChooseIDHandler extends ChannelHandlerAdapter{
	private static final InternalLogger LOGGER=
			InternalLoggerFactory.getInstance(ChooseIDHandler.class);
	public static final LinkedHashMap<String, ServerPer> map=
			new LinkedHashMap<String,ServerPer>();
	ServerPer onePercent;
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LOGGER.info("�������˴�������CHANNEL��IDΪ"+ctx.channel().id());
	}
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
			MsgBody body=(MsgBody) msg;
			MsgHeader header=body.getHeader();
			if(!map.containsKey(header.getChannelId()) && header.getChannelId() != null){
				System.out.println(map.containsKey(header.getChannelId()));
				LOGGER.info("��ӿͻ���ID:"+header.getChannelId());
				onePercent=new ServerPer();
				String uuid=ctx.channel().id().toString();
				onePercent.setUuid(uuid);
				onePercent.setChannel(ctx.channel());
				//����ͻ������Ӹ������������ӵ�map
				synchronized (ChooseIDHandler.class) {
					map.put(header.getChannelId(), onePercent);
				}
				
			}else if(null==header.getChannelId()){
				LOGGER.info(body.getContent());
				return;
			}else{
				String id=header.getChannelId();
				LOGGER.info("���������Ƿ����key:"+header.getChannelId()+":"+map.containsKey(id));
				ServerPer channel=map.get(id);
				MsgHeader msgHeader=new MsgHeader(id, 0);
				String content=body.getContent();
				MsgBody msgBody=new MsgBody(msgHeader, content);
				channel.getChannel().writeAndFlush(msgBody);
			}
	}
	/**
	 * ������������
	 * <p>Title: process0</p>
	 * <p>Description: </p>
	 * @param buf
	 */
	private void process0(ByteBuf buf){
        System.out.println("���뵽process����");
		short len = buf.readShort();
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Received heatbeat from client, length: " + len);

		ProtocolMessage message = new ProtocolMessage();
		try {
			Protocol.decode(message, buf);
		} catch (ProtocolException e) {
			LOGGER.error("Cannot decode message.", e);
			return;
		}

		if (message != null) {
			// �Ƿ���������
			if (message.getAction() == ProtocolConstants.A_H) {
				if (LOGGER.isDebugEnabled())
					LOGGER.debug("Received Heartbeat message.");
				
			} else {
				//��������Ӿ���Ĵ������ݵ��߼����ظ��ͻ���
				return;
			}
		}
	
	}
}
