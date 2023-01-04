package stepDefinitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils{

	ResponseSpecification resspec;
	RequestSpecification res;
	Response response;
	TestDataBuild data;
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String string, String string2, String string3) throws Exception {


		RestAssured.baseURI = getGlobalValue("baseURL");
		data= new TestDataBuild();
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		res = given().spec(requestSpec()).body(data.addPlacePayload(string, string2, string3));

	}

	@When("user calls {string} with post http request")
	public void user_calls_with_post_http_request(String string) {
		
		APIResources apiresource= APIResources.valueOf(string);
		response = res.when().post(apiresource.getResource()).then().spec(resspec).extract().response();
	}

	@Then("API call got sucess with statuscode as {int}")
	public void api_call_got_sucess_with_statuscode_as(Integer int1) {

		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {

		String resp = response.asString();
		JsonPath json = new JsonPath(resp);
		assertEquals(json.get(key).toString(), value);

	}


}
