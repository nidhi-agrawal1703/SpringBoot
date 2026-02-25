package com.rays.common;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Id; 

@MappedSuperclass
public class BaseDTO implements DropDownListInt {
	
	@Id
	@GeneratedValue(generator="ncsPk")
	@GenericGenerator(name="ncsPk",strategy="native")
	@Column(name="ID",unique=true,nullable=false)
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
