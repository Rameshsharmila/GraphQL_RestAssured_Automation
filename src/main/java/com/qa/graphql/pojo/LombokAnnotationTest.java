package com.qa.graphql.pojo;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@EqualsAndHashCode
public class LombokAnnotationTest {
	private String name;
	private int age;
	private String city;
	private boolean isActive;
	
	

}
