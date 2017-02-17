package cn.com.netty.qq;

import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import cn.com.netty.qq.client.NettyQQClient;
import cn.com.netty.qq.client.handler.SimpleClient;
import cn.com.netty.qq.entity.ClientPer;
import cn.com.netty.qq.entity.PersonClient;
import cn.com.netty.qq.protocol.MsgBody;
import cn.com.netty.qq.protocol.MsgHeader;
import cn.com.netty.qq.server.NettyQQServer;
import io.netty.channel.Channel;
import io.netty.handler.codec.EncoderException;

public class Test {
	public static void main(String[] args) throws InterruptedException {
		Thread thread=new Thread(new NettyQQServer());
		thread.start();
//		Executor executor=Executors.newFixedThreadPool(10);
//		executor.execute(new NettyQQClient());
//		executor.execute(new NettyQQClient());
//		executor.execute(new NettyQQClient());
//		executor.execute(new NettyQQClient());
//		executor.execute(new NettyQQClient());
//		executor.execute(new NettyQQClient());
//		executor.execute(new NettyQQClient());
//		executor.execute(new NettyQQClient());
//		executor.execute(new NettyQQClient());
//		Thread client1=new Thread(new NettyQQClient(1, "xiaohong"));
//		Thread client2=new Thread(new NettyQQClient(2, "xiaoming"));
//		Thread client3=new Thread(new NettyQQClient(3, "xiaolan"));
//		client1.start();
//		client2.start();
//		client3.start();
		Thread.sleep(10000);
		LinkedBlockingQueue<ClientPer> q=SimpleClient.queue;
		LinkedBlockingQueue<PersonClient> p=NettyQQClient.personqueue;
		Iterator<PersonClient> i=p.iterator();
		Iterator<PersonClient> iterator=p.iterator();
		while(i.hasNext()){
			PersonClient pClient=i.next();
			if(pClient.getId()==1){
				while(iterator.hasNext()){
					PersonClient per=iterator.next();
					if(per.getId()==3){
						String channelId=per.getClientChannel().id().toString();
						System.out.println(channelId);
						MsgHeader header=new MsgHeader(channelId, 0);
						MsgBody body=new MsgBody(header, "ÄãÊÇË­");
						pClient.getClientChannel().writeAndFlush(body);
					}
				}
			}
		}
		
		
	}

}
