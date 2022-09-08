package com.qa.graphql.tests;

import com.qa.graphql.pojo.LombokAnnotationTest;
import com.qa.graphql.pojo.LombokBuilderAnnotationClassLevel;
import com.qa.graphql.pojo.LombokBuilderAnnotationConstructorLevel;
import com.qa.graphql.pojo.LombokBuilderAnnotationMethodLevel;

public class LombokTests {

	public static void main(String[] args) {
		LombokAnnotationTest test = new LombokAnnotationTest("Sharmila",40,"Leander",true);
		System.out.println(test.getAge()); //340
		
		LombokAnnotationTest test2 = new LombokAnnotationTest();
		System.out.println(test2.getAge()); //0 since its not defined
		
		
		/////////////////////////////////
		LombokBuilderAnnotationClassLevel User1 = LombokBuilderAnnotationClassLevel.builder()
											.name("Sharmila")
											.age(40)
												.build();
		
		System.out.println(User1.getName() + "  "+ User1.getAge()); //Sharmila  40
		
		////////////////////////////////////
		
		LombokBuilderAnnotationConstructorLevel User2 = LombokBuilderAnnotationConstructorLevel.builder()
															.name("Vivek")
																.build();
		System.out.println(User2.getName() + "  "+ User2.getAge());
		////////////////////////////////////
		
		LombokBuilderAnnotationMethodLevel User3 = LombokBuilderAnnotationMethodLevel.builder()
														.name("Adi")
														.age(9)
															.build();
		
		System.out.println(User3.getName() + "  "+ User3.getAddress()); //Adi USA
	}

}
