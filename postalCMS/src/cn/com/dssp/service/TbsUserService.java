package cn.com.dssp.service;

import java.util.List;
import java.util.Map;

public interface TbsUserService<T> extends BaseService<T>{
	public List<Map<String, Object>> selectByRoleUrls(Map<?, ?> map) throws Exception;

}
