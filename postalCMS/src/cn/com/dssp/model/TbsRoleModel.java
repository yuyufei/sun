package cn.com.dssp.model;

import java.sql.Timestamp;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 角色表
 * <p>Title: TbsRoleModel</p>
 * <p>Description: </p>
 * @author	fly
 * @date	2017年2月20日上午9:53:21
 * @version 1.0
 */
public class TbsRoleModel extends BaseModel{

	private String id;//主键
	private String name;//角色
	private String text;//所有权限
	private Timestamp createTime;//时间
	private String index1;
	private String index2;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
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
	@Override
	public String toString() {
		return "TbsRoleModel [id=" + id + ", name=" + name + ", text=" + text + ", createTime=" + createTime
				+ ", index1=" + index1 + ", index2=" + index2 + "]";
	}
	
	
	
}
