package com.qa.graphql.pojo;

/**
 * This is the main POJO class for the query and variables
 * @author SharmilaRamesh
 *
 */

public class GraphQLQuery {
	
	private String query;
	private Object variables;
	
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public Object getVariables() {
		return variables;
	}
	public void setVariables(Object variables) {
		this.variables = variables;
	}
	
	

}
