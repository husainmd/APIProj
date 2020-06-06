package com.husain.api.restAssured;

import java.util.ArrayList;
import java.util.List;

import com.husain.api.restAssured.files.payload;

import io.restassured.path.json.JsonPath;

public class JsonParseDemo {

	public static void main(String[] args) {
		
		JsonPath jp = new JsonPath(payload.coursePrice);
		
		int courseCount = jp.getInt("courses.size()");
		
		//1. Print No of courses returned by API
		System.out.println("Course count: "+courseCount);
		
		//2.Print Purchase Amount
		int purchaseAmt = jp.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase amt is: "+purchaseAmt);
		
		//3. Print Title of the first course
		String firstCourseTitle = jp.getString("courses[0].title");
		System.out.println("First course title is: "+firstCourseTitle);
		
		//4. Print All course titles and their respective Prices
		List<String> courseTitles = new ArrayList<String>();
		List<Integer> coursePrices = new ArrayList<Integer>();
		for(int i=0; i<courseCount;i++)
		{
			courseTitles.add(jp.getString("courses["+i+"].title"));
			coursePrices.add(jp.getInt("courses["+i+"].price"));
		}
		
		System.out.println("Course titles: "+courseTitles);
		System.out.println("Course prices: "+coursePrices);
		
		//5. Print no of copies sold by RPA Course
		int noOfRPAcopies = 0;
		
		for(int i=0; i<courseCount;i++)
		{
			String title = jp.getString("courses["+i+"].title");
			if(title.equalsIgnoreCase("RPA"))
			{
				noOfRPAcopies = jp.getInt("courses["+i+"].copies");
				break;
			}
		}
		
		
		System.out.println("No of copies of RPA sold: "+noOfRPAcopies);		

	}

}
