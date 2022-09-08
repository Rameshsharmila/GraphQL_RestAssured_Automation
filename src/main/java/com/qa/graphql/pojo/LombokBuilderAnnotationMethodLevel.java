package com.qa.graphql.pojo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LombokBuilderAnnotationMethodLevel {
	
	String name;
	int age;
	boolean isActive;
	String address;
	
	public LombokBuilderAnnotationMethodLevel(String name, int age, boolean isActive, String address) {
		this.name = name;
		this.age = age;
		this.isActive = isActive;
		this.address = address;
	}
	
	
	//Pass only name and age...Default boolean and address fields
	@Builder
	public static LombokBuilderAnnotationMethodLevel createInstance(String name, int age) {
		return new LombokBuilderAnnotationMethodLevel(name,age,true,"USA");
	}
	
	

}
