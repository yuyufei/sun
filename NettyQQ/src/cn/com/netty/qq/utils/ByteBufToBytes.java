package cn.com.netty.qq.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class ByteBufToBytes {
	private ByteBuf temp;
	private boolean end=true;
	public ByteBufToBytes() {
	}
	public ByteBufToBytes(int length) {
	 temp=Unpooled.buffer(length);
	}
	/**
	 * 将一个Bytebuf复制到另一个特定的ByteBuf
	 * 将end设置成false
	 * <p>Title: reading</p>
	 * <p>Description: </p>
	 * @param datas
	 */
	public void reading(ByteBuf datas){
		datas.readBytes(temp,datas.readableBytes());
		if(this.temp.writableBytes()!=0){
			end=false;
		}else{
			end=true;
		}
	}
	
	public boolean idEnd(){
		return end;
	}
	
	public byte[] readFull(){
		if(end){
			byte[] contentByte=new byte[this.temp.readableBytes()];
			this.temp.readBytes(contentByte);
			this.temp.release();
			return contentByte;
		}else{
			return null;
		}
	}
	/**
	 * 将ByteBuf里面的数据转换成byte数组
	 * <p>Title: read</p>
	 * <p>Description: </p>
	 * @param datas
	 * @return
	 */
	public byte[] read(ByteBuf datas){
		byte[] bytes=new byte[datas.readableBytes()];
		datas.readBytes(bytes);
		return bytes;
	}

}
