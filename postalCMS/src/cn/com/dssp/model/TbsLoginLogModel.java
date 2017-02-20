package cn.com.dssp.model;

import java.sql.Timestamp;

/**
 * 登录日志
 * <p>Title: TbsLoginLogModel</p>
 * <p>Description: </p>
 * @author	fly
 * @date	2017年2月20日上午10:57:47
 * @version 1.0
 */
public class TbsLoginLogModel extends BaseModel{
	
	private String id;//主键
	private String username;//用户名
	private String password;//密码
	private Timestamp createTime;//时间
	private String ip;//ip
	private String userAgent;//设备
	private Integer status;//状态
	private String msg;//消息
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "TbsLoginLogModel [id=" + id + ", username=" + username + ", password=" + password + ", createTime="
				+ createTime + ", ip=" + ip + ", userAgent=" + userAgent + ", status=" + status + ", msg=" + msg + "]";
	}
	
	

}
