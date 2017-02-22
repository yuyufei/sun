package cn.com.dssp.service.impl;

import java.util.List;
import java.util.Map;

import cn.com.dssp.dao.BaseMapper;
import cn.com.dssp.service.BaseService;

public class BaseServiceImpl<T> implements BaseService<T>{
	
	private BaseMapper<T> mapper;
	
	public BaseMapper<T> getMapper() {
		return mapper;
	}

	@Override
	public T selectByPrimaryKey(Object key) throws Exception {
		return null;
	}

	@Override
	public Integer updateByPrimaryKey(T t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteByPrimaryKey(Object key) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insert(T t) throws Exception {
		return getMapper().insertModel(t);
	}

	@Override
	public Integer deleteByEntity(T entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> selectBySql(String sql) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateBySql(String sql) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteBySql(String sql) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insertBySql(String sql) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer selectByModelCount(T model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> selectByModel(T model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer selectByMapPagingCount(Map<?, ?> map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> selectByMapPaging(Map<?, ?> map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> selectByEntity(T entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer selectByMapCount(Map<?, ?> map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> selectByMap(Map<?, ?> map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<?, ?>> charts(Map<?, ?> map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> selectByChild(T model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteByPrimaryKeys(Object... keys) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
