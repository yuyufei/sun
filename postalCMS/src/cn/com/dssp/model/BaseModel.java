package cn.com.dssp.model;

import cn.com.dssp.util.core.PageUtil;

/**
 * 处理分页查询功能
 * <p>Title: BaseModel</p>
 * <p>Description: </p>
 * @author	fly
 * @date	2017年2月20日上午9:28:36
 * @version 1.0
 */
public class BaseModel {
	
	private PageUtil pageUtil=new PageUtil();

	public PageUtil getPageUtil() {
		return pageUtil;
	}

	public void setPageUtil(PageUtil pageUtil) {
		this.pageUtil = pageUtil;
	}
	
	

}
