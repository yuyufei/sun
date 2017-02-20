package cn.com.dssp.model;

import java.sql.Timestamp;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 角色权限中间表
 * <p>Title: TbsRoleMenuModel</p>
 * <p>Description: </p>
 * @author	fly
 * @date	2017年2月20日上午10:49:34
 * @version 1.0
 */
public class TbsRoleMenuModel extends BaseModel{
	
	private String id;//主键
	private String roleId;//角色主键
	private String menuIdFun;//功能主键
	private String menuId;//菜单主键
	private Timestamp createTime;//时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getMenuIdFun() {
		return menuIdFun;
	}
	public void setMenuIdFun(String menuIdFun) {
		this.menuIdFun = menuIdFun;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "TbsRoleMenuModel [id=" + id + ", roleId=" + roleId + ", menuIdFun=" + menuIdFun + ", menuId=" + menuId
				+ ", createTime=" + createTime + "]";
	}
	
	
	
	

}
