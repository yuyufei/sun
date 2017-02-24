package cn.com.dssp.util.core;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Map;

/**
 * MapBeanUtil
 * <p>Title: MapBeanUtil</p>
 * <p>Description: </p>
 * @author	fly
 * @date	2017年2月24日上午10:03:30
 * @version 1.0
 */
public class MapBeanUtil {
	
	public static <T> T mapToBean(Map<?, ?> map,Class<T> bean) throws IntrospectionException, InstantiationException, IllegalAccessException{
		BeanInfo beanInfo=Introspector.getBeanInfo(bean);
		T obj=bean.newInstance();
		PropertyDescriptor[] propertyDescriptors=beanInfo.getPropertyDescriptors();
		
		return null;
	}

}
