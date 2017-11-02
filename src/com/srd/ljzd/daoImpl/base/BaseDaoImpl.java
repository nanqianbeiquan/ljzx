/**  
 * 文件名: BaseDaoImpl.java
 * 包    名: com.srd.ljzd.daoImpl.base
 * 描    述: 数据接口层基础接口
 * 作    者： 谭显伦 
 * 日    期： 2016年4月5日
 * 版    本： V1.0  
 */
package com.srd.ljzd.daoImpl.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.srd.ljzd.dao.base.BaseDao;
import com.srd.ljzd.util.Page;
import com.srd.ljzd.util.PageUtil;
import com.srd.ljzd.util.LoggerUtils;
import com.srd.ljzd.util.StringUtils;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class BaseDaoImpl<T, ID extends Serializable> implements BaseDao<T, ID> {

	@Autowired
	private SessionFactory sessionFactory;
	
	protected Class<T> entityClass;

	public BaseDaoImpl() {

	}

	protected Class getEntityClass() {
		if (entityClass == null) {
			entityClass = (Class<T>) ((ParameterizedType) getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return entityClass;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	* 方法名称: save
	* 描        述: 保存实体
	* 参        数： @param t:保存的实体
	* 返  回 值：当保存成功，返回true；当保存失败，返回false
	*/
	@Override
	public boolean save(T t) {
		try {
			this.getSession().save(t);
			
			return true;
		} catch (Exception e) {
			LoggerUtils.error(e.getMessage(), e);

			return false;
		}
	}

	/**
	* 方法名称: saveOrUpdate
	* 描        述: 保存或更新实体
	* 参        数： @param t:要保存或更新实体
	* 返  回 值：当更新成功，返回true；当更新失败，返回false
	*/
	@Override
	public boolean saveOrUpdate(T t) {
		try {
			this.getSession().saveOrUpdate(t);
			
			return true;
		} catch (Exception e) {
			LoggerUtils.error(e.getMessage(), e);
			
			return false;
		}
	}

	/**
	* 方法名称:  get
	* 描        述: 加载实体
	* 参        数： @param ID:加载实体的ID
	* 返  回 值： 实体对象 
	*/
	@Override
	public T get(ID id) {
		T load = (T) this.getSession().get(getEntityClass(), id);
		
		return load;
	}

	/**
	* 方法名称:  delete
	* 描        述: 删除实体对象
	* 参        数： @param t:实体对象
	* 返  回 值： 当删除成功，返回true；当删除失败，返回false
	*/
	@Override
	public boolean delete(T t) {
		try {
			this.getSession().delete(t);
			return true;
		} catch (Exception e) {
			LoggerUtils.error(e.getMessage(), e);

			return false;
		}
	}

	/**
	* 方法名称:  delete
	* 描        述: 根据实体对象的ID，删除实体对象
	* 参        数： @param t:实体对象的ID
	* 返  回 值： 当删除成功，返回true；当删除失败，返回false 
	*/
	@Override
	public boolean deleteById(ID Id) {
		try {
			T t = get(Id);
			if (t == null) {
				return false;
			}
			delete(t);
			
			return true;
		} catch (Exception e) {
			LoggerUtils.error(e.getMessage(), e);
			
			return false;
		}
	}

	/**
	* 方法名称:  deleteAll
	* 描        述: 删除实体集合
	* 参        数： @param entities:实体对象集合
	* 返  回 值：当删除成功，返回true；当删除失败，返回false  
	*/
	@Override
	public boolean deleteAll(Collection<T> entities) {
		try {
			for (Object entity : entities) {
				this.getSession().delete(entity);
			}
			
			return true;
		} catch (Exception e) {
			LoggerUtils.error(e.getMessage(), e);
			
			return false;
		}
	}

	/**
	* 方法名称: updateByHql
	* 描       述: 根据查询hql语句和条件，更新实体
	* 参       数： @param sqlString:hql语句；
	*          @param values：不定条件数组
	* 返  回 值：当更新成功，返回true；当更新失败，返回false  
	*/
	@Override
	public boolean updateByHql(String hqlString, Object... values) {
		try {
			Query query = this.getSession().createQuery(hqlString);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					query.setParameter(i, values[i]);
				}
			}
			query.executeUpdate();
			
			return true;
		} catch (HibernateException e) {
			LoggerUtils.error(e.getMessage(), e);
			
			return false;
		}
	}
	
	/**
	* 方法名称: updateByHql
	* 描       述: 根据查询hql语句和条件，批量更新实体
	* 参       数： @param sqlString:hql语句；
	*          @param param：map数组
	* 返  回 值：当更新成功，返回true；当更新失败，返回false  
	*/
	public boolean updateByHql(String hqlString, Map<String,Object> param) {
		try {
			Query query = this.getSession().createQuery(hqlString);
			if (param != null) {
				for (String key : param.keySet()) {  
					if (param.get(key) instanceof Collection<?>) {
						query.setParameterList(key, (Collection) param.get(key));
					}
					else if (param.get(key) instanceof Object[]) {
						query.setParameterList(key,(Object[])param.get(key));
					}
					else{
					query.setParameter(key, param.get(key));
					}
				}
			}
			query.executeUpdate();
			return true;
		} catch (HibernateException e) {
			LoggerUtils.error(e.getMessage(), e);
			
			return false;
		}
	}
	/**
	* 方法名称: updateBySql
	* 描       述: 根据查询sql语句和条件，更新实体
	* 参       数： @param sqlString:hql语句；
	*           @param values：不定条件数组
	* 返  回 值：当更新成功，返回true；当更新失败，返回false  
	*/
	@Override
	public boolean updateBySql(String sqlString, Object... values) {
		try {
			Query query = this.getSession().createSQLQuery(sqlString);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					query.setParameter(i, values[i]);
				}
			}
			query.executeUpdate();
			return true;
		} catch (HibernateException e) {
			LoggerUtils.error(e.getMessage(), e);
			
			return false;
		}
	}

	
	/**
	* 方法名称: update
	* 描       述: 更新实体对象
	* 参       数： @param t:实体对象
	* 返  回 值：当更新成功，返回true；当更新失败，返回false
	*/
	@Override
	public boolean update(T t) {
		try {
			this.getSession().update(t);
			
			return true;
		} catch (Exception e) {
			LoggerUtils.error(e.getMessage(), e);
			
			return false;
		}
	}
	
	/**
	* 方法名称: getByHql
	* 描       述: 根据查询Hql语句和条件，得到唯一实体对象
	* 参       数： @param sqlString:sql语句；
	*           @param values：不定条件数组
	* 返  回 值：实体对象 
	*/
	@Override
	public T getByHql(String hqlString, Object... values) {
		Query query = this.getSession().createQuery(hqlString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		
		try{
			return (T) query.uniqueResult();
		}catch(Exception e){
			LoggerUtils.error(e.getMessage(), e);
			
			return null;
		}
	}

	/**
	* 方法名称: getBySql
	* 描       述: 根据查询Sql语句和条件，得到唯一实体对象
	* 参       数： @param sqlString:sql语句；
	*           @param values：不定条件数组
	* 返  回 值：实体对象 
	*/
	@Override
	public T getBySql(String sqlString, Object... values) {
		Query query = this.getSession().createSQLQuery(sqlString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		
		try{
			return (T) query.uniqueResult();
		}catch(Exception e){
			LoggerUtils.error(e.getMessage(), e);
			
			return null;
		}
	}

	/**
	* 方法名称: getListByHql
	* 描       述: 根据查询hql语句和条件，得到实体对象列表
	* 参       数： @param hqlString:hql语句；
	*           @param values：不定条件数组
	* 返  回 值：实体对象列表
	*/
	@Override
	public List<T> getListByHql(String hqlString, Object... values) {
		Query query = this.getSession().createQuery(hqlString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		
		List<T> list = null;
		
		try{
			list = query.list();
		}catch(Exception e){
			LoggerUtils.error(e.getMessage(), e);
		}
		
		if(list == null){
			list = new ArrayList<T>();
		}

		return list;
	}
	
	@Override
	public List<T> getListByHql(String hql, Map<String, Object> parameMap) {
		
		Query query = this.getSession().createQuery(hql);
		
		if (null != parameMap) {
			query.setProperties(parameMap);
		}
		
		List<T> itemList = null;
		
		try{
			itemList = query.list();
		}catch(Exception e){
			LoggerUtils.error(e.getMessage(), e);
		}
		
		if (itemList == null) {
			itemList = new ArrayList<T>();
		}
		
		return itemList;
	}

	/**
	* 方法名称: getListBySql
	* 描       述: 根据查询sql语句和条件，得到实体对象列表
	* 参       数： @param sqlString:hql语句；
	*           @param values：不定条件数组
	* 返  回 值：实体对象列表
	*/
	@Override
	public List<T> getListBySql(String sqlString, Object... values) {
		Query query = this.getSession().createSQLQuery(sqlString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		
		List<T> list = null;
		
		try{
			list = query.list();
		}catch(Exception e){
			LoggerUtils.error(e.getMessage(), e);
		}
		
		if(list == null){
			list = new ArrayList<T>();
		}
		
		return list;
	}

	/**
	* 方法名称: countByHql
	* 描       述: 根据sql语句以及输入的条件，查询结果记录的总条数
	* 参       数： @param sql:sql语句；@param values：不定参数数组
	* 返  回 值：总记录条数
	*/
	@Override
	public Long countByHql(String hql, Object... values) {
		Query query = this.getSession().createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		
		try{
			return (Long) query.uniqueResult();
		}catch(Exception e){
			LoggerUtils.error(e.getMessage(), e);
			
			return new Long((long)0);
		}
	}
	@Override
	public BigInteger countBySql(String sql, Object... values) {
		Query query = this.getSession().createSQLQuery(sql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		
		try{
			return (BigInteger) query.uniqueResult();
		}catch(Exception e){
			LoggerUtils.error(e.getMessage(), e);
			
			return new BigInteger("0");
		}
	}
	/**
	* 方法名称: findPageByHql
	* 描       述: 根据sql语句以及输入的条件，查询结果记录的总条数
	* 参       数： @param sql:sql查询语句；
	*           @param countSql：总条数查询语句；
	*           @param  currentPageNo：当前页数；
	*           @param 每页显示条数；
	*           @param values 查询条件的不定数组
	* 返  回 值：封装页信息
	*/
	@Override
	public Page<T> findPageByHql(String hql, String countSql,
			int currentPageNo, int pageSize, Object... values) {
		Query query = this.getSession().createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		int currentpNo = currentPageNo > 1 ? currentPageNo : 1;
		Long count = 0L;
		if (countSql == null) {
			ScrollableResults results = query.scroll();
			results.last();
			count = (results.getRowNumber() + 1L);
		} else {
			count = countByHql(countSql, values);
		}

		Page<T> retValue = (new PageUtil()).createPage(pageSize, count,
				currentpNo);
		
		List<T> itemList = null;
		
		try{
			itemList = query.setFirstResult((retValue.getCurrentPageNo() - 1) * pageSize).setMaxResults(pageSize).list();
		}catch(Exception e){
			LoggerUtils.error(e.getMessage(), e);
		}
		
		if (itemList == null) {
			itemList = new ArrayList<T>();
		}
		
		retValue.setResults(itemList);

		return retValue;
	}

	@Override
	public Long countByHql(String hql, Map<String, Object> parameMap) {
		Query query = this.getSession().createQuery(hql);
		
		Long result = new Long(0);
		
		if (null != parameMap) {
			query.setProperties(parameMap);
		}
		
		try{
			return (Long) query.uniqueResult();
		}catch(Exception e){
			LoggerUtils.error(e.getMessage(), e);
		}
		
		return result;
	}

	@Override
	public Page<T> findPageByHql(String hql, String countSql, int currentPageNo, int pageSize, Map<String, Object> parameMap) {
		Query query = this.getSession().createQuery(hql);
		
		if (null != parameMap) {
			query.setProperties(parameMap);
		}
		
		int currentpNo = currentPageNo > 1 ? currentPageNo : 1;
		
		Long count = 0L;
		
		if (StringUtils.isEmpty(countSql)) {
			ScrollableResults results = query.scroll();
			results.last();
			count = (results.getRowNumber() + 1L);
		} else {
			count = countByHql(countSql, parameMap);
		}

		Page<T> retValue = (new PageUtil()).createPage(pageSize, count, currentpNo);
		
		List<T> itemList = null;
		
		try{
			itemList = query.setFirstResult((retValue.getCurrentPageNo() - 1) * pageSize).setMaxResults(pageSize).list();
		}catch(Exception e){
			LoggerUtils.error(e.getMessage(), e);
		}
		
		if (itemList == null) {
			itemList = new ArrayList<T>();
		}
		
		retValue.setResults(itemList);

		return retValue;
	}

	@Override
	public Page<T> findPageBySql2(String sql, String countSql,
			int currentPageNo, int pageSize, Class clazz,Object... values){
	
		Query query = this.getSession().createSQLQuery(sql).addEntity(clazz);
		//对于参数的处理因为有可能会有list类型的要区分
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				if(values[i] instanceof Map ){
					HashMap map=(HashMap<String, Object>)values[i];
					Set set = map.keySet();
					Iterator iter = set.iterator();
					String key=null;
					while (iter.hasNext()) {
						key = (String) iter.next();
						break;
					}
					query.setParameterList(key, (List)map.get(key));
				}else{
					query.setParameter(i, values[i]);
				}
			}
		}
	
		int currentpNo = currentPageNo > 1 ? currentPageNo : 1;
		
		long count = 0;
		
		if (countSql == null) {
			ScrollableResults results = query.scroll();
			
			results.last();
			
			count = (results.getRowNumber() + 1L);
		} else {
			count = countBySql(countSql, values).longValue();
		}

		Page<T> retValue = (new PageUtil()).createPage(pageSize, count,
				currentpNo);

		List<T> itemList = null;

		try {
			itemList = query
					.setFirstResult(
							(retValue.getCurrentPageNo() - 1) * pageSize)
					.setMaxResults(pageSize).list();
		} catch (Exception e) {
			LoggerUtils.error(e.getMessage() + sql, e);
		}

		if (itemList == null) {
			itemList = new ArrayList<T>();
		}

		retValue.setResults(itemList);

		return retValue;
	}
}
