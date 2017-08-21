package ttn.kokaihop.api.apiTestCase;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import ttn.kokaihop.api.baseURL.fixedURL;
import ttn.kokaihop.api.endPointURL.endPointURLs;

public class apiTestCases {
	
	public static String token;
	@Test
	public void login_api(){
		
		Response response = given().
				param("email","intelligrape@gmail.com").
				param("password", "1234567a").
				when().
				post(fixedURL.fixed_URL+endPointURLs.login);
		
		System.out.println(response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
		token = response.
				then().
				contentType(ContentType.JSON).
				extract().
				path("token");
		System.out.println(token);
		System.out.println(response.asString());
				
		
	}
	
	@Test
	public void get_cities_list(){
		
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
