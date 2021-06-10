package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification req;
	
	public RequestSpecification requestSpecification() throws IOException {
		
		if(req==null) {
		PrintStream log =new PrintStream(new FileOutputStream("Logging.txt")); // Writing logs to text file
		
		req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")) // Building a generic request body
				.setContentType(ContentType.JSON)
				.addFilter(RequestLoggingFilter.logRequestTo(log))   // Writing request logs to text file
				.addFilter(ResponseLoggingFilter.logResponseTo(log))  // Writing response logs to text file
				.build();
		return req;
		}
		return req;
	}
	
	public static String getGlobalValue(String key) throws IOException  {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("src/test/java/resources/global.properties");
		prop.load(fis); 
		return prop.getProperty(key);
		 
	}
	
	public String getJsonPath(Response response, String key) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		//System.out.println(js.get(key)+"inside json path");
		return js.get(key).toString();
	}
}
