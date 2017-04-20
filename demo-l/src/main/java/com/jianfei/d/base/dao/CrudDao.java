/**
  *project demo-l
  *@author changchun.wu
  *2017��4��17������10:59:31
  */
package com.jianfei.d.base.dao;

import java.util.List;

import com.jianfei.d.entity.common.Page;


public interface CrudDao<T> extends BaseDao{
	/**
	 * ��ȡ�������
	 * @param id
	 * @return
	 */
	public T get(long id);
	/**
	 * ��ȡ�������
	 * @param entity
	 * @return
	 */
	public T getEntity(T entity);
	
	/**
	 * ��ȡ����
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity);
	
	/**
	 * ��ҳ��ѯ
	 * @param entity
	 * @return
	 */
	public Page<T> findPage(T entity);
	
	/**
	 * ��ѯ��������б�
	 * @param entity
	 * @return
	 */
	public List<T> findAllCond(T entity);
	
	/**
	 * ��ѯ�������
	 * @return
	 */
	public List<T> findAll();
	
	/**
	 * �������
	 * @param entity
	 * @return
	 */
	public int insert(T entity);
	
	/**
	 * �����������
	 * @param entitys
	 * @return
	 */
	public int insertBatch(List<T> entitys);
	
	/**
	 * �������
	 * @param entity
	 * @return
	 */
	public int update(T entity);
	
	/**
	 * ��������
	 * @param entitys
	 * @return
	 */
	public int updateBatch(List<T> entitys);
	
	/**
	 * ɾ�����
	 * @param entity
	 * @return
	 */
	public int deleteEntity(T entity);
	
	/**
	 * ɾ�����
	 * @param id
	 * @return
	 */
	public int delete(Long id);
	
	/**
	 * ɾ������
	 * @return
	 */
	public int deleteAll();
}
