package resources;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {

	protected RequestSpecification requestSpecification;
	protected ResponseSpecification responseSpecification;

	public RequestSpecification requestSpec() throws FileNotFoundException {
		
		PrintStream printToFile = new PrintStream(new FileOutputStream("log.txt"));

		return requestSpecification = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON)
				.addFilter(RequestLoggingFilter.logRequestTo(printToFile))
				.addFilter(ResponseLoggingFilter.logResponseTo(printToFile))
				.build();

	}

	public ResponseSpecification responseSpec() {

		return responseSpecification = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
	}
}
