package cn.com.dssp.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.dssp.dao.BaseMapper;
import cn.com.dssp.dao.TbsLoginLogMapper;
import cn.com.dssp.service.TbsLoginLogService;

/**
 * 登陆日志service层，用于事务处理
 * <p>Title: TbsLoginLogServiceImpl</p>
 * <p>Description: </p>
 * @author	fly
 * @date	2017年2月22日下午2:49:35
 * @version 1.0
 */
@Service
public class TbsLoginLogServiceImpl<T> extends BaseServiceImpl<T>	 implements TbsLoginLogService<T>{
	@Resource
	private TbsLoginLogMapper<T> mapper;

	public TbsLoginLogMapper<T> getMapper() {
       return mapper;
	}
	
	

}
