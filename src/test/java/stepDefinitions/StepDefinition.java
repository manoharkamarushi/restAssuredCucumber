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

public class StepDefinition extends Utils {

	RequestSpecification reqspec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_id; // in a particular run all testcases will refer to same static var -->static.
	//it will not become when 2nd scenario starts

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String address, String lang, String name) throws Exception {

		RestAssured.baseURI = getGlobalValue("baseURL");			
		reqspec = given().spec(requestSpec()).body(data.addPlacePayload(address, lang, name));

	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_post_http_request(String resource, String httpMethod) {

		APIResources apiresource = APIResources.valueOf(resource);

		if (httpMethod.equalsIgnoreCase("POST"))
			response = reqspec.when().post(apiresource.getResource()).then().spec(responseSpec()).extract().response();
		else if (httpMethod.equalsIgnoreCase("GET"))
			response = reqspec.when().get(apiresource.getResource()).then().spec(responseSpec()).extract().response();
		else
			System.out.println("Unhandled methodType recieved");
	}

	@Then("API call got sucess with statuscode as {int}")
	public void api_call_got_sucess_with_statuscode_as(Integer int1) {

		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {

		assertEquals(getJsonPath(response, key), value);

	}

	@Then("verify created place_id maps to {string} using {string}")
	public void verify_created_place_id_maps_to_using(String expectedName, String apiResource) throws Exception {
		
		place_id = getJsonPath(response, "place_id");
		reqspec = given().spec(requestSpec()).param("place_id", place_id);
		user_calls_with_post_http_request(apiResource,"GET"); //reusing existing steps
		String actualName = getJsonPath(response, "name");
		assertEquals(expectedName, actualName);
	}
	
	
	@Given("DeletePlaceAPI payload")
	public void delete_place_api_payload() throws Exception {
	    
		reqspec = given().spec(requestSpec()).body(data.deletePlacePayload(place_id));
	}
}
