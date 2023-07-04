package com.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserException extends RuntimeException {
	
	String resourceName;
	String fieldName;
	long fieldValue;
	
	public UserException(String resourceName,String fieldName,long fieldValue) {
		super(String.format("%s not found with %s : %d",resourceName,fieldName,fieldValue));
		this.resourceName=resourceName;
		this.fieldName=fieldName;
		this.fieldValue=fieldValue;
	}

}
