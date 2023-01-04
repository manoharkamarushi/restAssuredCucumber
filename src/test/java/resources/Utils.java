package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {

	public static RequestSpecification requestSpecification;
	protected ResponseSpecification responseSpecification;

	public RequestSpecification requestSpec() throws FileNotFoundException {

		if (requestSpecification == null) {
			PrintStream printToFile = new PrintStream(new FileOutputStream("log.txt"));

			return requestSpecification = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
					.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON)
					.addFilter(RequestLoggingFilter.logRequestTo(printToFile))
					.addFilter(ResponseLoggingFilter.logResponseTo(printToFile)).build();
		}
		return requestSpecification;
	}

	public ResponseSpecification responseSpec() {

		return responseSpecification = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
	}

	public String getGlobalValue(String key) throws IOException {

		Properties prop = new Properties();
		FileInputStream fileip = new FileInputStream(new File("./src/test/java/resources/global.properties"));
		prop.load(fileip);

		return prop.getProperty(key);
	}

	public String getJsonPath(Response response,String keyValue) {
		
		String resp = response.asString();
		JsonPath json = new JsonPath(resp);
		
		return json.get(keyValue).toString();
		
	}
}
