package com.rays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.UserDAO;
import com.rays.dto.UserDTO;

@Service
@Transactional
public class UserService {
	
	@Autowired
	public UserDAO userDao;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public long add(UserDTO dto) {
		long pk=userDao.add(dto);
		return pk;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(UserDTO dto) {
		userDao.update(dto);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(long id) {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Transactional(readOnly = true)
	public UserDTO findById(long pk) {
		UserDTO dto=userDao.findByPk(pk);
		return dto;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public long save(UserDTO dto) {
		Long id=dto.getId();
		if(id!=null && id>0) {
			update(dto);
		}else {
			id=add(dto);
		}
		return id;
	}
	
	public List<UserDTO> search(UserDTO dto,int pageNo,int pageSize){
		return userDao.search(dto, pageNo, pageSize);
	}
	
}
