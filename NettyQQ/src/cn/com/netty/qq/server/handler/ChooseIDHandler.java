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
 * 区分消息器
 * <p>Title: ChooseIDHandler</p>
 * <p>Description: </p>
 * @author	fly
 * @date	2017年1月9日上午9:53:42
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
        LOGGER.info("服务器端创建链接CHANNEL，ID为"+ctx.channel().id());
	}
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
			MsgBody body=(MsgBody) msg;
			MsgHeader header=body.getHeader();
			if(!map.containsKey(header.getChannelId()) && header.getChannelId() != null){
				System.out.println(map.containsKey(header.getChannelId()));
				LOGGER.info("添加客户端ID:"+header.getChannelId());
				onePercent=new ServerPer();
				String uuid=ctx.channel().id().toString();
				onePercent.setUuid(uuid);
				onePercent.setChannel(ctx.channel());
				//保存客户端链接跟服务器端链接的map
				synchronized (ChooseIDHandler.class) {
					map.put(header.getChannelId(), onePercent);
				}
				
			}else if(null==header.getChannelId()){
				LOGGER.info(body.getContent());
				return;
			}else{
				String id=header.getChannelId();
				LOGGER.info("服务器端是否包含key:"+header.getChannelId()+":"+map.containsKey(id));
				ServerPer channel=map.get(id);
				MsgHeader msgHeader=new MsgHeader(id, 0);
				String content=body.getContent();
				MsgBody msgBody=new MsgBody(msgHeader, content);
				channel.getChannel().writeAndFlush(msgBody);
			}
	}
	/**
	 * 处理心跳数据
	 * <p>Title: process0</p>
	 * <p>Description: </p>
	 * @param buf
	 */
	private void process0(ByteBuf buf){
        System.out.println("进入到process方法");
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
			// 是否心跳数据
			if (message.getAction() == ProtocolConstants.A_H) {
				if (LOGGER.isDebugEnabled())
					LOGGER.debug("Received Heartbeat message.");
				
			} else {
				//后续可添加具体的处理数据的逻辑返回给客户端
				return;
			}
		}
	
	}
}
