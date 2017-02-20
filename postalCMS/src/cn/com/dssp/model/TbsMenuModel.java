package cn.com.dssp.model;

import java.sql.Timestamp;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 权限菜单
 * <p>Title: TbsMenuModel</p>
 * <p>Description: </p>
 * @author	fly
 * @date	2017年2月20日上午10:52:59
 * @version 1.0
 */
public class TbsMenuModel extends BaseModel{
	
	private String id;//主键
	private String parentId;//父节点
	private String name;//名称
	private Integer isMenu;//菜单类型
	private Integer type;//加载方式
	private Integer sortNumber;//排序
	private String url;//地址
	private String button;//按钮
	private Integer status;//状态
	private Timestamp createTime;//时间
	private List<TbsMenuModel> listTbsMenuModel;
	private String index1;
	private String index2;
	
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getIsMenu() {
		return isMenu;
	}
	public void setIsMenu(Integer isMenu) {
		this.isMenu = isMenu;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getSortNumber() {
		return sortNumber;
	}
	public void setSortNumber(Integer sortNumber) {
		this.sortNumber = sortNumber;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getButton() {
		return button;
	}
	public void setButton(String button) {
		this.button = button;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public List<TbsMenuModel> getListTbsMenuModel() {
		return listTbsMenuModel;
	}
	public void setListTbsMenuModel(List<TbsMenuModel> listTbsMenuModel) {
		this.listTbsMenuModel = listTbsMenuModel;
	}
	@Override
	public String toString() {
		return "TbsMenuModel [id=" + id + ", parentId=" + parentId + ", name=" + name + ", isMenu=" + isMenu + ", type="
				+ type + ", sortNumber=" + sortNumber + ", url=" + url + ", button=" + button + ", status=" + status
				+ ", createTime=" + createTime + ", listTbsMenuModel=" + listTbsMenuModel + "]";
	}
	
	

}
