package cn.com.dssp.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.com.dssp.dao.BaseMapper;

public interface BaseService<T>{
	/*crud操作*/
	public T selectByPrimaryKey(Object key) throws Exception;
	public Integer updateByPrimaryKey(T t)throws Exception;
	public Integer deleteByPrimaryKey(Object key)throws Exception;
	public Integer insert(T t)throws Exception;
	
	public Integer deleteByEntity(T entity)throws Exception;
	
	public List<T> selectBySql(@Param(value="sql")String sql)throws Exception;
	public Integer updateBySql(@Param(value="sql")String sql)throws Exception;
	public Integer deleteBySql(@Param(value="sql")String sql)throws Exception;
	public Integer insertBySql(@Param(value="sql")String sql)throws Exception;
	
	/*分页查询操作*/
	public Integer selectByModelCount(T model)throws Exception;
	public List<T> selectByModel(T model)throws Exception;
	public Integer selectByMapPagingCount(Map<?, ?> map)throws Exception;
	public List<T> selectByMapPaging(Map<?, ?> map)throws Exception;
	/*查询不分页*/
	public List<T> selectByEntity(T entity)throws Exception;
	public Integer selectByMapCount(Map<?,?> map)throws Exception;
	public List<T> selectByMap(Map<?, ?> map)throws Exception;
	/*图表*/
	public List<Map<?,?>> charts(Map<?, ?> map)throws Exception;
	/*递归查询*/
	public List<T> selectByChild(T model)throws Exception;
	
	/*删除多条记录*/
	public Integer deleteByPrimaryKeys(Object... keys) throws Exception;

}
