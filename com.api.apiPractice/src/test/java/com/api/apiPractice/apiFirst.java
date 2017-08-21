package com.api.apiPractice;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import myDataPackage.complexDataInfo;
import myDataPackage.myDataInfo;
import myDataPackage.myDataSource;



public class apiFirst {

	//@Test
	public void getStarted(){

		Response resp =get("http://api.openweathermap.org/data/2.5/weather?q=Faridabad,IN&appid=fa93c6d4d1cfbf5a05da8f634e4d2fbe");

		System.out.println(resp.getStatusCode());

	}

	//@Test
	public void case_1(){

		Response resp =get("http://api.openweathermap.org/data/2.5/weather?q=Faridabad,IN&appid=fa93c6d4d1cfbf5a05da8f634e4d2fbe");

		System.out.println(resp.getStatusCode());
		Assert.assertEquals(resp.getStatusCode(), 200);
	}

	//@Test
	public void case_2(){

		Response resp =given().
				param("q","Faridabad,IN").
				param("appid","fa93c6d4d1cfbf5a05da8f634e4d2fbe").
				when().
				get("http://api.openweathermap.org/data/2.5/weather");

		System.out.println(resp.getStatusCode());
		Assert.assertEquals(resp.getStatusCode(), 200);
	}


	//@Test
	public void case_3(){

		given().
		param("q","Faridabad,IN").
		param("appid","fa93c6d4d1cfbf5a05da8f634e4d2fbe").
		when().
		get("http://api.openweathermap.org/data/2.5/weather").
		then().
		assertThat().statusCode(200);

	}

	//@Test
	public void case_4(){ //Intentionally failed test case

		given().
		param("q","Faridabad,IN").
		param("appid","fa93c6d4d1csdfbf5a05da8f634e4d2fbe").
		when().
		get("http://api.openweathermap.org/data/2.5/weather").
		then().
		assertThat().statusCode(401);

	}	

	//@Test
	public void case_5(){

		Response resp =given().
				param("q","Faridabad,IN").
				param("appid","fa93c6d4d1cfbf5a05da8f634e4d2fbe").
				when().
				get("http://api.openweathermap.org/data/2.5/weather");

		System.out.println(resp.asString());
		Assert.assertEquals(resp.getStatusCode(), 200);
	}

	//@Test
	public void case_6(){

		Response resp =given().
				param("q","Mumbai,IN").
				param("appid","fa93c6d4d1cfbf5a05da8f634e4d2fbe").
				when().
				get("http://api.openweathermap.org/data/2.5/weather");

		String desc = resp.
				then().
				contentType(io.restassured.http.ContentType.JSON).
				extract().
				path("weather[0].description");



		System.out.println(resp.asString());
		Assert.assertEquals(resp.getStatusCode(), 200);

		System.out.println("Description is: "+ desc);
	}

	//@Test
	public void case_7(){

		Response resp =given().
				body("{ \"id\":\"3\", \"title\":\"This is my additional record\",\"author\":\"Atul Sharma\"}").
				when().
				contentType(ContentType.JSON).
				post("http://localhost:3000/posts")
				;



		System.out.println(resp.asString());
	}

	//@Test
	public void case_8(){

		myDataSource data = new myDataSource();
		data.setID("2");
		data.setTitle("Game of Thrones");
		data.setAuthor("My Author");
		Response resp =given().
				body(data).
				when().
				contentType(ContentType.JSON).
				post("http://localhost:3000/posts")
				;
		System.out.println(resp.asString());
	}


	//@Test
	public void case_9(){

		myDataSource data = new myDataSource();
		data.setID("2");
		data.setTitle("Game of Thrones");
		data.setAuthor("George R. R. Martin");

		Response resp =given().
				body(data).
				when().
				contentType(ContentType.JSON).
				put("http://localhost:3000/posts/2")
				;
		System.out.println(resp.asString());
	}

	//@Test
	public void case_10(){
		Response resp =given().
				body("{\"title\":\"This is new Updated Book\"}").
				when().
				contentType(ContentType.JSON).
				patch("http://localhost:3000/posts/3")
				;
		System.out.println(resp.asString());
	}

	//@Test
	public void case_11()
	{
		Response resp = given().
				when().
				delete("http://localhost:3000/posts/5");

		System.out.println(resp.asString());
		System.out.println(resp.getStatusCode());
	}


	//@Test
	public void case_12()
	{
		myDataInfo info = new myDataInfo();
		info.setEmail("abc@abc.cbc");
		info.setAddress("Address Address");
		info.setPhone("1111111111");

		complexDataInfo data = new complexDataInfo();
		data.setID("5");
		data.setAuthor("Abhijit Roy");
		data.setTitle("Dream Job in 90 Days");
		data.setInfo(info);
		Response resp = given().
				when().
				contentType(ContentType.JSON).
				body(data).
				post("http://localhost:3000/posts");

		System.out.println(resp.asString());



	}
	
	@Test
	public void case_13()
	{
		ArrayList<String> cities = given().
				when().
				get("https://staging-kokaihop.herokuapp.com/v1/api/cities").
				then().
				contentType(ContentType.JSON).extract().
				path("cities.name");
		
		System.out.println("Total cities are: "+cities.size());
		for(int i=0;i<cities.size();i++)
		System.out.println(cities.get(i).toString());
				
				
	}

}
