package cn.com.dssp.dao;

import java.util.List;
import java.util.Map;

/**
 * 菜单
 * <p>Title: TbsMenuMapper</p>
 * <p>Description: </p>
 * @author	fly
 * @date	2017年2月23日下午4:32:49
 * @version 1.0
 */
public interface TbsMenuMapper<T> extends BaseMapper<T>{
	
	public List<Map<String, Object>> selectByMenuISMenu(Map<String, Object> map) throws Exception;
	public List<Map<String, Object>> selectByMenuOther(Map<String,Object> map) throws Exception;
	public List<T> selectByButtons(Map<String, Object> map) throws Exception;

}
