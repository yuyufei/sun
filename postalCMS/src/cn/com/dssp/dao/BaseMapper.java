package cn.com.dssp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 定义公用的方法
 * <p>Title: BaseMapper</p>
 * <p>Description: </p>
 * @author	fly
 * @date	2017年2月20日下午2:29:41
 * @version 1.0
 */
public interface BaseMapper<T> {
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

}
