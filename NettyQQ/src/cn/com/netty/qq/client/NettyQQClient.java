package cn.com.netty.qq.client;

import java.util.concurrent.LinkedBlockingQueue;

import cn.com.netty.qq.client.handler.SimpleClient;
import cn.com.netty.qq.entity.PersonClient;
import cn.com.netty.qq.protocol.MsgDecoder;
import cn.com.netty.qq.protocol.MsgEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

public class NettyQQClient implements Runnable{
	private int id;
	private String name;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private static NettyQQClient client;
	public static final NettyQQClient getInstance(){
		if(client==null){
			synchronized (NettyQQClient.class) {
				if(client==null){
					client=new NettyQQClient();
				}
			}
		}
		return client;
	}
	
	public static final LinkedBlockingQueue<PersonClient> personqueue=
			new LinkedBlockingQueue<PersonClient>();
	private static final MsgEncoder ENCODER=new MsgEncoder();
	
	public void run() {
		NioEventLoopGroup group=new NioEventLoopGroup();
		Bootstrap bootstrap=new Bootstrap();
		try {
			bootstrap.group(group)
			.channel(NioSocketChannel.class)
			.handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast("decode",new MsgDecoder());
//					ch.pipeline().addLast("decoder", new LengthFieldBasedFrameDecoder(65535, 0, 2));
					ch.pipeline().addLast("encode",new MsgEncoder());
//					ch.pipeline().addLast("encoder", new LengthFieldPrepender(2, 0));
					ch.pipeline().addLast("idle",new IdleStateHandler(0, 60, 0));
					ch.pipeline().addLast("logger",new LoggingHandler(LogLevel.INFO));
					ch.pipeline().addLast("chooseID",new SimpleClient());
				}
			});
			ChannelFuture future=bootstrap.connect("localhost",8837);
			PersonClient personClient=new PersonClient();
			personClient.setId(id);
			personClient.setName(name);
			personClient.setClientChannel(future.channel());
			personqueue.add(personClient);
			future.channel().closeFuture().sync();
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}
	};
		

}
