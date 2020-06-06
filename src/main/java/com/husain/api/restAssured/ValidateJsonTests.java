package com.husain.api.restAssured;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.husain.api.restAssured.files.payload;
import io.restassured.path.json.JsonPath;

public class ValidateJsonTests {

	// 6. Verify if Sum of all Course prices matches with Purchase Amount
	@Test
	public void validatePurchaseAmount() {
		JsonPath jp = new JsonPath(payload.coursePrice);
		int courseCount = jp.getInt("courses.size()");

		int sum = 0;
		for (int i = 0; i < courseCount; i++) {
			int copies = jp.getInt("courses[" + i + "].copies");
			int coursePrice = jp.getInt("courses[" + i + "].price");
			sum = sum + (copies * coursePrice);
		}

		int purchaseAmt = jp.getInt("dashboard.purchaseAmount");

		Assert.assertTrue(purchaseAmt == sum,
				"Purchase amount is inaccurate, expected: " + purchaseAmt + " actual: " + sum);
	}

}
