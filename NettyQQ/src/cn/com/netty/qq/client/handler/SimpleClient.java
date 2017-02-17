package cn.com.netty.qq.client.handler;

import java.util.concurrent.LinkedBlockingQueue;

import cn.com.netty.qq.entity.ClientPer;
import cn.com.netty.qq.protocol.MsgBody;
import cn.com.netty.qq.protocol.MsgHeader;
import cn.com.netty.qq.protocol.Protocol;
import cn.com.netty.qq.protocol.ProtocolConstants;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

public class SimpleClient extends ChannelHandlerAdapter{
	private static final InternalLogger LOGGER=
			InternalLoggerFactory.getInstance(SimpleClient.class);
	public static final LinkedBlockingQueue<ClientPer> queue=
			new LinkedBlockingQueue<ClientPer>();
	ClientPer per;
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
          per=new ClientPer();
          per.setUuid(ctx.channel().id().toString());
          per.setChannel(ctx.channel());
          synchronized (SimpleClient.class) {
        	  queue.add(per);
		  }
          String channelId=ctx.channel().id().toString();
          int length=0;
          String content=null;
          MsgHeader header=new MsgHeader(channelId, length);
          MsgBody body=new MsgBody(header, content);
          ctx.channel().writeAndFlush(body);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		LOGGER.info("客户端发来信息"+msg);
	}
	/**
	 * 空闲过长，发心跳
	 * <p>Title: userEventTriggered</p>
	 * <p>Description: </p>
	 * @param ctx
	 * @param evt
	 * @throws Exception
	 * @see io.netty.channel.ChannelHandlerAdapter#userEventTriggered(io.netty.channel.ChannelHandlerContext, java.lang.Object)
	 */
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

		if (evt instanceof IdleStateEvent) {
			IdleStateEvent e = (IdleStateEvent) evt;
			if (e.state() == IdleState.WRITER_IDLE) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Heartbeat, write message to Server.");
				}
				
				MsgHeader header=new MsgHeader(null,0);
				MsgBody body=new MsgBody(header, ctx.channel().id().toString()+":发来心跳数据");

				ctx.writeAndFlush(body);
			}
		}
	
	}
}
