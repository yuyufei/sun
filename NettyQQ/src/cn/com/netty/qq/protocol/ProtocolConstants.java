package cn.com.netty.qq.protocol;

import java.io.Serializable;

/**
 * 协议常值
 * <p>Title: ProtocolConstants</p>
 * <p>Description: </p>
 * @author	fly
 * @date	2017年1月11日下午5:09:35
 * @version 1.0
 */
public class ProtocolConstants implements Serializable{
	
	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;
	public static final byte P_M_S = 0x10; // Protocol Message Start
	public static final byte P_H_S = 0x11; // Protocol Header Start
	public static final byte P_H_K = 0x12; // Protocol Header Key
	public static final byte P_H_V = 0x13; // Protocol Header Value
	public static final byte P_H_E = 0x14; // Protocol Header End
	public static final byte P_B_S = 0x15; // Protocol Body Start
	public static final byte P_B_E = 0x16; // Protocol Body End
	public static final byte P_M_E = 0x17; // Protocol Message End

	public static final byte A_H = 0x01; // Action Heart beat
	public static final byte A_I = 0x02; // Action In
	public static final byte A_O = 0x03; // Action Out
	public static final byte A_C_S = 0x04; // Action Control Start
	public static final byte A_C_C = 0x05; // Action Control Check
	public static final byte A_R_S = 0x06; // Action Response Successful
	public static final byte A_R_F = 0x07; // Action Response Failed
	public static final byte A_R_R = 0x08; // Action Response retrieve next one

	public static final ProtocolMessage HEATBEAT = new ProtocolMessage();
	static {
		HEATBEAT.setAction(A_H);
	}

}
