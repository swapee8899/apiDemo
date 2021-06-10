package stepdefinations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.ResourcesPath;
import resources.TestDataBuild;
import resources.Utils;
import static org.junit.Assert.*;
import static io.restassured.RestAssured.*;
import java.io.IOException;
public class Step extends Utils {
	
	TestDataBuild testData = new TestDataBuild();
	ResponseSpecification resspec;
	RequestSpecification res;
	Response response;
	JsonPath js;
	ResourcesPath path;
	static String SessionidExtracted;	
	//SessionFilter session = new SessionFilter();
	
//*******************   Login scenario 	***********************
	@Given("Login Payload with {string} and {string}")
	public void login_payload(String email, String password) throws IOException {
		
		res =given().spec(requestSpecification()).header("clientip","::ffff:10.251.50.80")
		.body(testData.loginPayload(email,password));
		
	}


	@And("Store sessionID")
	public void store_sessionid() {
		SessionidExtracted = getJsonPath(response,"sessionId");
	}
 
//*******************Common scenario which is having only sessionId in body***********************

	@Given("User is logged in")
	public void user_is_logged_in() throws IOException {
		res =given().spec(requestSpecification()).header("clientip","::ffff:10.251.50.80")
				.body(testData.sessionPayload(SessionidExtracted));
	}

	@When("User calls {string} post request")
	public void user_calls_post_request(String apiPath) {
//System.out.println("logout when");		
		path = ResourcesPath.valueOf(apiPath);
		resspec= new ResponseSpecBuilder().expectStatusCode(200).build();
		response =res.when().post(path.getResource()).
		then().log().all().spec(resspec).extract().response();
	}
	@Then("API is success with {int} status code")
	public void api_is_success_with_status_code(int statusCodeExpected) {
System.out.println("Status code ="+statusCodeExpected);
		assertEquals(response.getStatusCode(),statusCodeExpected);
	}
	@And("{string} is {string} in response body")
	public void is_in_response_body(String ResponseKey, String ResponseValue) {
System.out.println(ResponseKey+" = "+ResponseValue);
		assertEquals(getJsonPath(response, ResponseKey),ResponseValue);
	}

}
