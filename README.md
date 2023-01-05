# restAssured API Automation using Cucumber 
#### This project is RestAssured based Cucumber framework to perform API testing. This project is useful as an example of implementing API Testing concepts with RestAssured and Java.

#### Implementations
1. Smart step definition file with supported code
2. Utils File to define reusable response and request spec builders
3. pojo classes for serialization and deserialization json payload
4. Implemented logging request and response details to external text file
5. Defining global properties and deriving global vars from prop file
6. Defining Enum classes to centralize all resource details
7. Executing tests with multiple sets of test data using scenario outline
8. Pre and post conditions for tests using Hooks
9. Executing tests using tags
10. Execute complete framework using Maven commands 
11. Generating test results in Html files. 


#### Dependencies

<dependencies>
		<!-- https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api -->
		<dependency>
			<groupId>org.junit.jupiter</groupId>
			<artifactId>junit-jupiter-api</artifactId>
			<version>5.9.1</version>
			<scope>test</scope>
		</dependency>

		<!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-java -->
		<dependency>
			<groupId>io.cucumber</groupId>
			<artifactId>cucumber-java</artifactId>
			<version>7.8.0</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-junit -->
		<dependency>
			<groupId>io.cucumber</groupId>
			<artifactId>cucumber-junit</artifactId>
			<version>7.8.0</version>
			<scope>test</scope>
		</dependency>
		<!-- https://mvnrepository.com/artifact/io.rest-assured/rest-assured -->
		<dependency>
			<groupId>io.rest-assured</groupId>
			<artifactId>rest-assured</artifactId>
			<version>5.2.0</version>
			<scope>test</scope>
		</dependency>
		<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
		<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-databind</artifactId>
			<version>2.14.1</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.datatype/jackson-datatype-jdk8 -->
		<dependency>
			<groupId>com.fasterxml.jackson.datatype</groupId>
			<artifactId>jackson-datatype-jdk8</artifactId>
			<version>2.14.1</version>
		</dependency>
	</dependencies>
  
#### html reporting 
https://github.com/damianszczepanik/maven-cucumber-reporting 
sample-report.html
![image](https://user-images.githubusercontent.com/20392181/210774031-47bb9aaf-ea00-4c9f-ad24-cd065360a81f.png)


#### TestRunner class
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features", glue = { "stepDefinitions" }, plugin="json:target/jsonReports/cucumber-reports.json")
public class TestRunner {
	
}

#### feature file
![image](https://user-images.githubusercontent.com/20392181/210774523-c7671262-bf49-4819-b823-23a936873ebd.png)


### Steps to clone execute the tests

git clone https://github.com/manok584/restAssuredCucumber.git

cd restAssuredCucumber

mvn clean test -- to run all tests

mvn test verify -- to run all tests and generate html report in target/cucumber-html-reports

mvn test -Dcucumber.options="--tags @AddPlace" verify --to run only AddPlace API tests

