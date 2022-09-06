package com.qa.graphql.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.graphql.pojo.GraphQLQuery;
import com.qa.graphql.pojo.GraphQLQueryVariables;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;


public class GraphQLQueryTests {
	
	@Test
	public void getAllFilmsDetailTest() {
		//Basic Query to view all Film details
		
		RestAssured.baseURI = "https://swapi-graphql.netlify.app";
		String query = "{\"query\":\"{\\n  allFilms {\\n    films {\\n      title\\n    }\\n  }\\n}\\n\",\"variables\":null}";	
		
		given().log().all()
			.contentType("application/json")
				.body(query)
					.when().log().all()
						.post("/.netlify/functions/index")
							.then().log().all()
								.assertThat()
									.statusCode(200)
										.and()
											.body("data.allFilms.films[0].title", equalTo("A New Hope"));
		
	}
	
	
	@Test
	public void getAllUsersfromHasuraTest() {
		//Query to view the user details based on some parameters
		RestAssured.baseURI="https://hasura.io";
		String query = "{\"query\":\"{\\n  users(limit: 10) {\\n    id\\n    name\\n  }\\n}\\n\",\"variables\":null}";
	
		given().log().all()
			.contentType("application/json")
			.header("Authorization","Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDYzMTEwMzgwNmE4YTU4MjJkN2JiZDE3NyJ9LCJuaWNrbmFtZSI6InNoYXJtaWxhLnN0dWR5ZG9jcyIsIm5hbWUiOiJzaGFybWlsYS5zdHVkeWRvY3NAZ21haWwuY29tIiwicGljdHVyZSI6Imh0dHBzOi8vcy5ncmF2YXRhci5jb20vYXZhdGFyL2I0MDA3ZTAyYjU3ZjIwY2ZiMjQ4ZTJjZDE2YTA1N2E3P3M9NDgwJnI9cGcmZD1odHRwcyUzQSUyRiUyRmNkbi5hdXRoMC5jb20lMkZhdmF0YXJzJTJGc2gucG5nIiwidXBkYXRlZF9hdCI6IjIwMjItMDktMDRUMDA6MzE6MjAuODM3WiIsImlzcyI6Imh0dHBzOi8vZ3JhcGhxbC10dXRvcmlhbHMuYXV0aDAuY29tLyIsInN1YiI6ImF1dGgwfDYzMTEwMzgwNmE4YTU4MjJkN2JiZDE3NyIsImF1ZCI6IlAzOHFuRm8xbEZBUUpyemt1bi0td0V6cWxqVk5HY1dXIiwiaWF0IjoxNjYyNDgyNDUzLCJleHAiOjE2NjI1MTg0NTMsImF0X2hhc2giOiJ0T2xjYnVGNl9waGxya0l0Y2dkSk93Iiwic2lkIjoiTW9mVGZFRXkzY0U0TDZYek4zcHc4QmJEX1hDU3NUTDciLCJub25jZSI6ImZpdlliNnBjNUNMcnROa1h0aWR2MEljY0VLN19BRHd1In0.QF_j9mpj1gJC4VxX_K6PfflGSTXHJr1sWVZqmhs3SAAXxuCQt91GLNx845XinIcXG9p96-bvvWa5AAAoRDFvVQZZdyMalcsMVGH2mvzHoxPVSj4YloOEIToMCcXsQ2FdUD96ZREsn86hDLhbByBPce7mdL5mX09naKGUEWwlf4TokBV5gqPfAy1VgHObLqCnkhKhVOToVUAJc8FsaefFQ3971O3c_5fBn5e4qZKKibSldo9AHPOQbImuUP_xNfL9XYWN001rOochGOKnx4THU-zmC8srzZxtnvP60dozItp_FBM1ULCajafTyXzW-sK9jRNGG5YUQivUMcdDOiHd4Q")
			.body(query)	
				.when().log().all()
					.post("/learn/graphql")
						.then().log().all()	
							.assertThat()
								.statusCode(200)
								.body("data.users[0].name",equalTo("tui.glen"));
				
	}
	
	
	@DataProvider
	public Object[][] getQueryData() {
		return new Object[][] {
								{"10","tisni"},
								{"2","tui.glen"}
							  };
		
	}
	
	@Test(dataProvider="getQueryData")
	public void getAllUsersfromHasuraWithDataTest(String limit, String name) {
		//Query to view the details of users with the help of data provider
		RestAssured.baseURI="https://hasura.io";
		String query = "{\"query\":\"{\\n  users(limit: "+limit+", where: {name: {_eq: \\\""+name+"\\\"}}) {\\n    id\\n    name\\n  }\\n}\\n\",\"variables\":null}";
	
		given().log().all()
			.contentType("application/json")
			.header("Authorization","Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDYzMTEwMzgwNmE4YTU4MjJkN2JiZDE3NyJ9LCJuaWNrbmFtZSI6InNoYXJtaWxhLnN0dWR5ZG9jcyIsIm5hbWUiOiJzaGFybWlsYS5zdHVkeWRvY3NAZ21haWwuY29tIiwicGljdHVyZSI6Imh0dHBzOi8vcy5ncmF2YXRhci5jb20vYXZhdGFyL2I0MDA3ZTAyYjU3ZjIwY2ZiMjQ4ZTJjZDE2YTA1N2E3P3M9NDgwJnI9cGcmZD1odHRwcyUzQSUyRiUyRmNkbi5hdXRoMC5jb20lMkZhdmF0YXJzJTJGc2gucG5nIiwidXBkYXRlZF9hdCI6IjIwMjItMDktMDRUMDA6MzE6MjAuODM3WiIsImlzcyI6Imh0dHBzOi8vZ3JhcGhxbC10dXRvcmlhbHMuYXV0aDAuY29tLyIsInN1YiI6ImF1dGgwfDYzMTEwMzgwNmE4YTU4MjJkN2JiZDE3NyIsImF1ZCI6IlAzOHFuRm8xbEZBUUpyemt1bi0td0V6cWxqVk5HY1dXIiwiaWF0IjoxNjYyNDgyNDUzLCJleHAiOjE2NjI1MTg0NTMsImF0X2hhc2giOiJ0T2xjYnVGNl9waGxya0l0Y2dkSk93Iiwic2lkIjoiTW9mVGZFRXkzY0U0TDZYek4zcHc4QmJEX1hDU3NUTDciLCJub25jZSI6ImZpdlliNnBjNUNMcnROa1h0aWR2MEljY0VLN19BRHd1In0.QF_j9mpj1gJC4VxX_K6PfflGSTXHJr1sWVZqmhs3SAAXxuCQt91GLNx845XinIcXG9p96-bvvWa5AAAoRDFvVQZZdyMalcsMVGH2mvzHoxPVSj4YloOEIToMCcXsQ2FdUD96ZREsn86hDLhbByBPce7mdL5mX09naKGUEWwlf4TokBV5gqPfAy1VgHObLqCnkhKhVOToVUAJc8FsaefFQ3971O3c_5fBn5e4qZKKibSldo9AHPOQbImuUP_xNfL9XYWN001rOochGOKnx4THU-zmC8srzZxtnvP60dozItp_FBM1ULCajafTyXzW-sK9jRNGG5YUQivUMcdDOiHd4Q")
			.body(query)	
				.when().log().all()
					.post("/learn/graphql")
						.then().log().all()	
							.assertThat()
								.statusCode(200)
								.body("data.users[0].name",equalTo(name));
		
	}
	
	@Test
	public void getAllUsersfromHasuraWithPojosTest() {
		RestAssured.baseURI="https://hasura.io";
		GraphQLQuery graphqlquery = new GraphQLQuery();
		
		graphqlquery.setQuery("query ($limit: Int!, $name:String!) {\n"
				+ "  users(limit: $limit, where: {name: {_eq: $name}}) {\n"
				+ "    id\n"
				+ "    name\n"
				+ "  }\n"
				+ "}");
		GraphQLQueryVariables variable = new GraphQLQueryVariables();
		variable.setLimit(5);
		variable.setName("tui.glen");
		
		graphqlquery.setVariables(variable);
		
		given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization","Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDYzMTEwMzgwNmE4YTU4MjJkN2JiZDE3NyJ9LCJuaWNrbmFtZSI6InNoYXJtaWxhLnN0dWR5ZG9jcyIsIm5hbWUiOiJzaGFybWlsYS5zdHVkeWRvY3NAZ21haWwuY29tIiwicGljdHVyZSI6Imh0dHBzOi8vcy5ncmF2YXRhci5jb20vYXZhdGFyL2I0MDA3ZTAyYjU3ZjIwY2ZiMjQ4ZTJjZDE2YTA1N2E3P3M9NDgwJnI9cGcmZD1odHRwcyUzQSUyRiUyRmNkbi5hdXRoMC5jb20lMkZhdmF0YXJzJTJGc2gucG5nIiwidXBkYXRlZF9hdCI6IjIwMjItMDktMDRUMDA6MzE6MjAuODM3WiIsImlzcyI6Imh0dHBzOi8vZ3JhcGhxbC10dXRvcmlhbHMuYXV0aDAuY29tLyIsInN1YiI6ImF1dGgwfDYzMTEwMzgwNmE4YTU4MjJkN2JiZDE3NyIsImF1ZCI6IlAzOHFuRm8xbEZBUUpyemt1bi0td0V6cWxqVk5HY1dXIiwiaWF0IjoxNjYyNDgyNDUzLCJleHAiOjE2NjI1MTg0NTMsImF0X2hhc2giOiJ0T2xjYnVGNl9waGxya0l0Y2dkSk93Iiwic2lkIjoiTW9mVGZFRXkzY0U0TDZYek4zcHc4QmJEX1hDU3NUTDciLCJub25jZSI6ImZpdlliNnBjNUNMcnROa1h0aWR2MEljY0VLN19BRHd1In0.QF_j9mpj1gJC4VxX_K6PfflGSTXHJr1sWVZqmhs3SAAXxuCQt91GLNx845XinIcXG9p96-bvvWa5AAAoRDFvVQZZdyMalcsMVGH2mvzHoxPVSj4YloOEIToMCcXsQ2FdUD96ZREsn86hDLhbByBPce7mdL5mX09naKGUEWwlf4TokBV5gqPfAy1VgHObLqCnkhKhVOToVUAJc8FsaefFQ3971O3c_5fBn5e4qZKKibSldo9AHPOQbImuUP_xNfL9XYWN001rOochGOKnx4THU-zmC8srzZxtnvP60dozItp_FBM1ULCajafTyXzW-sK9jRNGG5YUQivUMcdDOiHd4Q")
			.body(graphqlquery)	
				.when().log().all()
					.post("/learn/graphql")
						.then().log().all()	
							.assertThat()
								.statusCode(200)
								.body("data.users[0].name",equalTo("tui.glen"));
	}

}
