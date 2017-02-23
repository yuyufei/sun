package cn.com.dssp.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.dssp.dao.BaseMapper;
import cn.com.dssp.dao.TbsMenuMapper;
import cn.com.dssp.service.TbsMenuService;
@Service
public class TbsMenuServiceImpl<T> extends BaseServiceImpl<T> implements TbsMenuService<T>{
	@Resource
	private TbsMenuMapper<T> mapper;
	
	@Override
	public BaseMapper<T> getMapper() {
       return mapper;
	}

	@Override
	public List<Map<String, Object>> selectByMenuISMenu(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> selectByMenuOther(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> selectByButtons(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
