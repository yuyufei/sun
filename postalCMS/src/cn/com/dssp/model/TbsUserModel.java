package cn.com.dssp.model;

import java.sql.Timestamp;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 用户表
 * <p>Title: TbsUserModel</p>
 * <p>Description: </p>
 * @author	fly
 * @date	2017年2月20日上午9:26:19
 * @version 1.0
 */
public class TbsUserModel extends BaseModel{
	private String id;//主键
	private String username;//用户名
	private String password;//密码
	private Timestamp createTime;//时间
	private String ip;//ip
	private Integer count;//次数
	private Integer isLock;//锁定
	private Timestamp lockTime;//锁定时间
	private Integer failCount;//失败次数
	private Integer isAdmin;//权限类型
	private String index1;
	private String index2;
	private String index3;
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
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
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
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getIsLock() {
		return isLock;
	}
	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	public Timestamp getLockTime() {
		return lockTime;
	}
	public void setLockTime(Timestamp lockTime) {
		this.lockTime = lockTime;
	}
	public Integer getFailCount() {
		return failCount;
	}
	public void setFailCount(Integer failCount) {
		this.failCount = failCount;
	}
	public Integer getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getIndex1() {
		return index1;
	}
	public void setIndex1(String index1) {
		this.index1 = index1;
	}
	public String getIndex2() {
		return index2;
	}
	public void setIndex2(String index2) {
		this.index2 = index2;
	}
	public String getIndex3() {
		return index3;
	}
	public void setIndex3(String index3) {
		this.index3 = index3;
	}
	@Override
	public String toString() {
		return "TbsUserModel [id=" + id + ", username=" + username + ", password=" + password + ", createTime="
				+ createTime + ", ip=" + ip + ", count=" + count + ", isLock=" + isLock + ", lockTime=" + lockTime
				+ ", failCount=" + failCount + ", isAdmin=" + isAdmin + ", index1=" + index1 + ", index2=" + index2
				+ ", index3=" + index3 + "]";
	}
	
	
	

}
