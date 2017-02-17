package cn.com.netty.qq.server;

import javax.xml.bind.ParseConversionEvent;

import cn.com.netty.qq.protocol.MsgDecoder;
import cn.com.netty.qq.protocol.MsgEncoder;
import cn.com.netty.qq.server.handler.ChooseIDHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 自实现一个识别客户端的服务器端
 * <p>Title: NettyQQServer</p>
 * <p>Description: </p>
 * @author	fly
 * @date	2017年1月9日上午9:36:29
 * @version 1.0
 */
public class NettyQQServer implements Runnable{
	private static final MsgEncoder ENCODER=new MsgEncoder();
	public void run() {
		EventLoopGroup parent=new NioEventLoopGroup(5);
		EventLoopGroup workers=new NioEventLoopGroup(20);
		ServerBootstrap server=new ServerBootstrap();
		try{
		server.group(parent, workers)
              .channel(NioServerSocketChannel.class)
              .option(ChannelOption.SO_BACKLOG, 10240)
              .childOption(ChannelOption.SO_KEEPALIVE, true)
              .childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
//					ch.pipeline().addLast("decoder", new LengthFieldBasedFrameDecoder(65535, 0, 2));
					ch.pipeline().addLast("decode",new MsgDecoder());
					ch.pipeline().addLast("encode",new MsgEncoder());
//					ch.pipeline().addLast("encoder", new LengthFieldPrepender(2, 0));
					ch.pipeline().addLast("logger",new LoggingHandler(LogLevel.INFO));
					ch.pipeline().addLast("chooseID",new ChooseIDHandler());
				}

			});
		ChannelFuture future=server.bind(8837).sync();
		future.channel().closeFuture().sync();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			parent.shutdownGracefully();
			workers.shutdownGracefully();
		}
	};
		

}
