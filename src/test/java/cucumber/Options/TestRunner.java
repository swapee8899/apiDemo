package cucumber.Options;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import resources.SendMailSSLWithAttachment;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features",
//plugin = "json:target/jsonReports/Report.json",
plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","json:target/jsonReports/Report.json"},
glue= {"stepdefinations"},
dryRun=false,
tags = {"@smoke,~@regression"})

public class TestRunner {
	@AfterClass
	public static void tearDown() {
		/*SendMailSSLWithAttachment mail = new SendMailSSLWithAttachment();
		try {
			mail.email();
		} catch (IOException e) {
			System.out.println("error while sending email");
			e.printStackTrace();
		}*/
	}
	}
	
