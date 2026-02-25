package com.rays.dto;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

import com.rays.common.BaseDTO;


@Entity
@Table(name="st_attachment")
public class AttachmentDTO extends BaseDTO {
	
	@Column(name="name",length=100)
	protected String name=null;
	
	@Column(name="type",length=100)
	protected String type=null;
	
	@Column(name="description",length=100)
	protected String description=null;
	
	@Column(name="user_id")
	protected Long userId=null;
	
	@Lob
	@Column(name="doc")
	private byte[] doc;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public byte[] getDoc() {
		return doc;
	}

	public void setDoc(byte[] doc) {
		this.doc = doc;
	}
		
	public AttachmentDTO() {}
	
	public AttachmentDTO(MultipartFile file) {
		
		name=file.getOriginalFilename();
		type=file.getContentType();
		
		try {
			doc=file.getBytes();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
