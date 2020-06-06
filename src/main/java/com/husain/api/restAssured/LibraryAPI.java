package com.husain.api.restAssured;

import org.testng.annotations.Test;

import com.husain.api.restAssured.files.payload;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class LibraryAPI {

	@Test
	public void addBook()
	{
		RestAssured.baseURI = "http://216.10.245.166";
		
		String addBookResp = RestAssured.given().log().all().header("Content-Type","application/json").
		body(payload.addBookPayload).when().post("/Library/Addbook.php").
		then().log().all().assertThat().statusCode(200).
		extract().response().asString();
		
		JsonPath jp = new JsonPath(addBookResp);
		String id = jp.getString("ID");
		System.out.println("Book added successfully with id: "+id);
	}
}
