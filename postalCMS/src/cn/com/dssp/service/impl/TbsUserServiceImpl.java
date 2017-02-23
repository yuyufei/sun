package cn.com.dssp.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.dssp.dao.TbsUserMapper;
import cn.com.dssp.service.TbsUserService;

@Service
public class TbsUserServiceImpl<T> extends BaseServiceImpl<T> implements TbsUserService<T>{
	@Resource
	private TbsUserMapper<T> mapper;
	public TbsUserMapper<T> getMapper() {
		return mapper;
	}

	@Override
	public List<Map<String, Object>> selectByRoleUrls(Map<?, ?> map) throws Exception {
		return mapper.selectByRoleUrls(map);
	}

	
}
