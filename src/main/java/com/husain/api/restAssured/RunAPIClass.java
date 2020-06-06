package com.husain.api.restAssured;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import com.husain.api.restAssured.files.payload;

public class RunAPIClass {

	public static void main(String[] args) {
		
		// Add place
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		Header header1 = new Header("Content-Type","application/json");
		
		Response response = given().log().all().queryParam("key", "qaclick123")
		.header(header1).body(payload.addPlacePayload).
		when().post("/maps/api/place/add/json");
		
		System.out.println("\n\nExplicit resp print: \n"+response.getBody().asString());
		
		response.
		then().
		log().all().assertThat().statusCode(200).body("scope",equalTo("APP")).
		header("server","Apache/2.4.18 (Ubuntu)");
		
		String responseJsonString = response.then().extract().response().asString();
		
		JsonPath jsp = new JsonPath(responseJsonString);
		String placeId = jsp.getString("place_id");
		
		System.out.println(placeId);
		
		
		// Update place
		String newAddress = "New updated address from java code";
		given().log().all().queryParam("key", "qaclick123")
		.header(header1)
		.body(payload.updatePlacePayload.replace("PLACE_ID_REPLACEMENT", placeId).
				replace("NEWADD", newAddress)).
		when().put("/maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200)
		.body("msg", equalTo("Address successfully updated"));
		
		
		// Get place
		String getPlaceRespString = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
		.header(header1)
		.when().get("/maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).
		extract().response().asString();
		
		JsonPath jsp1 = new JsonPath(getPlaceRespString);
		String respAddr = jsp1.getString("address");
		System.out.println("Addree received: "+respAddr);
		Assert.assertTrue(newAddress.equals(respAddr));
		
		}
}
