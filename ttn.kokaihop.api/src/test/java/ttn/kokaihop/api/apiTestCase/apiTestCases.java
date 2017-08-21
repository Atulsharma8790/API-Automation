package ttn.kokaihop.api.apiTestCase;

import static io.restassured.RestAssured.*;


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
				param("email","atulsharma8790@gmail.com").
				param("password", "abcd1234").
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

}
