/*This code is to test data driven testing for registration page only
No object repository was used for this page*/

package com.lms.test.restapi18;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.lms.test.utils18.ReadExcel_Supriya_AllDataTypes;


import java.io.IOException;
import java.util.HashMap;

import org.testng.Reporter;
public class PostRequestLMSAPI

{
	@Test
	
	public void HardCoded_TestPostspecificPrograms()
	{
	
		RestAssured.baseURI ="http://lms-program-rest-service.herokuapp.com";
		RequestSpecification httprequest = RestAssured.given().auth().basic("admin","password");
		HashMap data=new HashMap();
	    data.put("online",false);
	    data.put("programDescription","RestAssuredSDET18");
		data.put("programName","NumpyNinja");
		
		httprequest.header("Content-Type","application/json");
		httprequest.body(data);
		Response response=httprequest.request(Method.POST,"/programs");
		
		int statusCode = response.getStatusCode();
		System.out.println("The status code recieved: " + statusCode);
		Reporter.log("The status code received for hard coded post"+statusCode);
	  
	    String responseBody = response.getBody().asString();
		System.out.println("Response Body is Sup Code HAshmap:" + responseBody);
		Reporter.log("Hard Coded Post response Body using hash map" + responseBody);
     
	} 
   @DataProvider(name = "testpostdata")
   public Object[][] getData1() throws IOException
	{
		
		String excelpath = "C:\\Users\\Supriya\\eclipse-workspace_202109NN\\18restassured\\src\\test\\resources\\TestData\\PostRequestLMSAPI.xlsx";
		String sheetname="Sheet1";
		Object data [][] =ReadExcel_Supriya_AllDataTypes.read(excelpath,sheetname);
		
		return data; 
		
	}
   

	@Test(dataProvider ="testpostdata")
	
	public void HashMap_Data_Driven_Excel_testPostspecificPrograms(String programName,String programDescription,Boolean online )
	{
	
		RestAssured.baseURI ="http://lms-program-rest-service.herokuapp.com";
		RequestSpecification httprequest = RestAssured.given().auth().basic("admin","password");
  
			
		HashMap data=new HashMap();
	    data.put("online",online);
	    data.put("programDescription",programDescription);
		//data.put("programId",5890);
		data.put("programName",programName);
		
		
		httprequest.header("Content-Type","application/json");
		httprequest.body(data);
		Response response=httprequest.request(Method.POST,"/programs");
		int statusCode = response.getStatusCode();
		System.out.println("The status code recieved: " + statusCode);
		Reporter.log("The status code received for post"+statusCode);
		
	    System.out.println("Response body: " + response.body().asString());
	    String responseBody = response.getBody().asString();
		System.out.println("Response Body is Sup Code HAshmap:" + responseBody);
		Reporter.log("Post response Body using hash map" + responseBody);
      
	} 
}
	
	

	