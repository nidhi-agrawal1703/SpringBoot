package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rays.dto.RoleDTO;
import com.rays.dto.UserDTO;

@Repository
public class UserDAO {
	
	@Autowired
	RoleDAO roleDao;
	
	@PersistenceContext
	public EntityManager entityManager;
	
	public UserDTO populate(UserDTO dto) {
		if(dto.getRoleId()!=null && dto.getRoleId()>0) {
			RoleDTO roleDto=roleDao.findByPk(dto.getRoleId());
			dto.setRoleName(roleDto.getName());
		}
		return dto;
	}
	
	public long add(UserDTO dto) {
		dto=populate(dto);
		entityManager.persist(dto);
		return dto.getId();
	}
	
	public void update(UserDTO dto) {
		entityManager.merge(dto);
	}
	
	public UserDTO findByPk(long pk) {
		UserDTO dto=entityManager.find(UserDTO.class,pk);
		return dto;
	}
	
	public List<UserDTO> search(UserDTO dto,int pageNo,int pageSize){
		
		List<UserDTO> list=null;
		
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		
		CriteriaQuery<UserDTO> cq=builder.createQuery(UserDTO.class);
		
		List<Predicate> predicateList=new ArrayList<Predicate>();
		
		Root<UserDTO> qRoot=cq.from(UserDTO.class);
		
		if(dto!=null) {
			if(dto.getFirstName()!=null && dto.getFirstName().length()>0) {
				predicateList.add(builder.like(qRoot.get("firstName"), dto.getFirstName() + "%"));
			}
			if(dto.getLastName() != null && dto.getLastName().length() > 0) {
				predicateList.add(builder.like(qRoot.get("lastName"), dto.getLastName() + "%"));
			}
		}
		
		cq.where(predicateList.toArray(new Predicate[predicateList.size()]));
		TypedQuery<UserDTO> tq = entityManager.createQuery(cq);

		if (pageSize > 0) {
			tq.setFirstResult(pageNo * pageSize);
			tq.setMaxResults(pageSize);
		}

		list = tq.getResultList();

		return list;
		
	}
	
	public UserDTO findByUniqueKey(String attribute,String value) {
		
		List<UserDTO> list=null;
		
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		
		CriteriaQuery<UserDTO> cq=builder.createQuery(UserDTO.class);
		
		Root<UserDTO> qRoot=cq.from(UserDTO.class);
		
		Predicate condition=builder.equal(qRoot.get(attribute),value);
		
		cq.where(condition);
		
		TypedQuery<UserDTO> tq = entityManager.createQuery(cq);
		
		list=tq.getResultList();
		
		UserDTO dto=null;
		
		if(list.size()>0) {
			dto=list.get(0);
		}
		
		return dto;
	}
	
}
