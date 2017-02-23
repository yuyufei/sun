package cn.com.dssp.dao;

import java.util.List;
import java.util.Map;

/**
 * 用户类Mapper
 * <p>Title: TbsUserMapper</p>
 * <p>Description: </p>
 * @author	fly
 * @date	2017年2月23日上午9:31:07
 * @version 1.0
 */
public interface TbsUserMapper<T> extends BaseMapper<T> {
	
	public List<Map<String, Object>> selectByRoleUrls(Map<?, ?> map) throws Exception;
	

}
