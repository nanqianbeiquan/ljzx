/**  
* 文件名: BaseDao.java
* 包    名: com.srd.ljzd.dao.base
* 描    述: 数据接口层基础接口
* 作    者： 谭显伦 
* 日    期： 2016年4月5日
* 版    本： V1.0  
*/

package com.srd.ljzd.dao.base;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;





import com.srd.ljzd.util.Page;

public interface BaseDao<T, ID extends Serializable> {

	/**
	* 方法名称: save
	* 描        述: 保存实体
	* 参        数： @param t:保存的实体
	* 返  回 值：当保存成功，返回true；当保存失败，返回false 
	*/
	public boolean save(T t);

	/**
	* 方法名称: saveOrUpdate
	* 描        述: 保存或更新实体
	* 参        数： @param t:要保存或更新实体
	* 返  回 值：当更新成功，返回true；当更新失败，返回false 
	*/
	public boolean saveOrUpdate(T t);

	/**
	* 方法名称:  get
	* 描        述: 加载实体
	* 参        数： @param ID:加载实体的ID
	* 返  回 值： 实体对象 
	*/
	public T get(ID id);

	/**
	* 方法名称:  delete
	* 描        述: 删除实体对象
	* 参        数： @param t:实体对象
	* 返  回 值： 当删除成功，返回true；当删除失败，返回false 
	*/
	public boolean delete(T t);

	/**
	* 方法名称:  delete
	* 描        述: 根据实体对象的ID，删除实体对象
	* 参        数： @param t:实体对象的ID
	* 返  回 值： 当删除成功，返回true；当删除失败，返回false 
	*/
	public boolean deleteById(ID Id);

	/**
	* 方法名称:  deleteAll
	* 描        述: 删除实体集合
	* 参        数： @param entities:实体对象集合
	* 返  回 值：当删除成功，返回true；当删除失败，返回false  
	*/
	public boolean deleteAll(Collection<T> entities);

	/**
	* 方法名称: updateByHql
	* 描       述: 根据查询hql语句和条件，更新实体
	* 参       数： @param hqlString:hql语句；
	*           @param values：不定条件数组
	* 返  回 值：当更新成功，返回true；当更新失败，返回false  
	*/
	public boolean updateByHql(String hqlString, Object... values);
	
	public boolean updateByHql(String hqlString, Map<String,Object> param);
	
	/**
	* 方法名称: updateBySql
	* 描       述: 根据查询sql语句和条件，更新实体
	* 参       数： @param sqlString:hql语句；
	*           @param values：不定条件数组
	* 返  回 值：当更新成功，返回true；当更新失败，返回false  
	*/
	public boolean updateBySql(String sqlString, Object... values);

	/**
	* 方法名称: getByHql
	* 描       述: 根据查询hql语句和条件，得到唯一实体
	* 参       数： @param hqlString:hql语句；
	*           @param values：不定条件数组
	* 返  回 值：实体对象 
	*/
	public T getByHql(String hqlString, Object... values);

	/**
	* 方法名称: getBySql
	* 描       述: 根据查询Sql语句和条件，得到唯一实体对象
	* 参       数： @param sqlString:sql语句；
	*           @param values：不定条件数组
	* 返  回 值：实体对象 
	*/
	public T getBySql(String sqlString, Object... values);

	/**
	* 方法名称: getListByHql
	* 描       述: 根据查询hql语句和条件，得到实体对象列表
	* 参       数： @param hqlString:hql语句；
	*           @param values：不定条件数组
	* 返  回 值：实体对象列表
	*/
	public List<T> getListByHql(String hqlString, Object... values);
	
	public List<T> getListByHql(String hql, Map<String, Object> parameMap);

	/**
	* 方法名称: getListBySql
	* 描       述: 根据查询sql语句和条件，得到实体对象列表
	* 参       数： @param sqlString:hql语句；
	*           @param values：不定条件数组
	* 返  回 值：实体对象列表
	*/ 
	public List<T> getListBySql(String sqlString, Object... values);

	/**
	* 方法名称: update
	* 描       述: 更新实体对象
	* 参       数： @param t:实体对象
	* 返  回 值：当更新成功，返回true；当更新失败，返回false
	*/
	public boolean update(T t);

	/**
	* 方法名称: countByHql
	* 描       述: 根据sql语句以及输入的条件，查询结果记录的总条数
	* 参       数： @param sql:sql语句；
	*           @param values：不定参数数组
	* 返  回 值：总记录条数
	*/
	public Long countByHql(String hql, Object... values);
	
	public BigInteger countBySql(String sql, Object... values);
	
	/**
	* 方法名称: findPageByHql
	* 描       述: 根据sql语句以及输入的条件，查询结果记录的总条数
	* 参       数： @param hql:hql查询语句；
	*           @param countSql：总条数查询语句；
	*           @param  currentPageNo：当前页数；
	*           @param 每页显示条数；
	*           @param values 查询条件的不定数组
	* 返  回 值：封装页信息
	*/
	public Page<T> findPageByHql(String hql, String countSql, int currentPageNo, int pageSize, Object... values);
	
	/** 
	* @Title: countByHql 
	* @Description: 根据hql语句以及输入的条件，查询结果记录的总条数
	* @param @param hql
	* @param @param parameMap
	* @param @return 设定文件 
	* @return Long 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年7月27日 上午9:53:56
	*/
	public Long countByHql(String hql, Map<String, Object> parameMap);
	
	/** 
	* @Title: findPageByHql 
	* @Description: 根据hql语句以及输入的条件，查询结果记录的分页
	* @param @param hql
	* @param @param countSql
	* @param @param currentPageNo
	* @param @param pageSize
	* @param @param parameMap
	* @param @return 设定文件 
	* @return Page<T> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年7月27日 上午9:54:25
	*/
	public Page<T> findPageByHql(String hql, String countSql, int currentPageNo, int pageSize, Map<String, Object> parameMap);

	public  Page<T> findPageBySql2(String hql, String countSql, int currentPageNo, int pageSize, Class clazz,Object... values) ;
}
