package cn.com.dssp.model;

import java.sql.Timestamp;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 角色用户中间表
 * <p>Title: TbsRoleUserModel</p>
 * <p>Description: </p>
 * @author	fly
 * @date	2017年2月20日上午9:48:55
 * @version 1.0
 */
public class TbsRoleUserModel extends BaseModel{
	private String id;//主键
	private String userId;//用户主键
	private String roleId;//角色主键
	private Timestamp createTime;//时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	

}
