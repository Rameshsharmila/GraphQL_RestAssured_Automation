package com.qa.graphql.pojo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LombokBuilderAnnotationConstructorLevel {
	
		String name;
		int age;
		boolean isActive;
		String address;
		
		@Builder
		public LombokBuilderAnnotationConstructorLevel(String name, int age) {
			this.name=name;
			this.age=age;
		}


}
