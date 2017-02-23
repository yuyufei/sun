package cn.com.dssp.service;

import java.util.List;
import java.util.Map;

/**
 * 菜单service
 * <p>Title: TbsMenuService</p>
 * <p>Description: </p>
 * @author	fly
 * @date	2017年2月23日下午4:37:19
 * @version 1.0
 */
public interface TbsMenuService<T> extends BaseService<T> {
	
	public List<Map<String, Object>> selectByMenuISMenu(Map<String, Object> map) throws Exception;
	public List<Map<String, Object>> selectByMenuOther(Map<String,Object> map) throws Exception;
	public List<T> selectByButtons(Map<String, Object> map) throws Exception;

}
