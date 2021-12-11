package com.lms.test.restapi18;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lms.test.utils18.ReadExcel_Supriya_AllDataTypes;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PutRequestLMSAPI {
	
@Test
	
	public void HardCoded_TestPutspecificPrograms()
	{
		RestAssured.baseURI = "http://lms-admin-rest-service.herokupp.com";
		RequestSpecification httpRequest = RestAssured.given().auth().basic("admin","password");
		Response response_get = httpRequest.request(Method.GET, "https://lms-admin-rest-service.herokuapp.com/programs/"+2759);
        String responseBody = response_get.getBody().prettyPrint();
	   // System.out.println("Response Body is for hardcoded program id  =>  " + responseBody);
		Reporter.log("Response body for hardcoded program id  =>  "+responseBody);
		
		int statusCode=response_get.getStatusCode();
		Assert.assertEquals(statusCode,200,"Correct Status Code returned");
		
	    RestAssured.baseURI ="http://lms-program-rest-service.herokuapp.com";
		RequestSpecification httprequest = RestAssured.given().auth().basic("admin","password");
		HashMap data=new HashMap();
	    data.put("online",true);
	    data.put("programDescription","S Test");
		data.put("programName","Supriya2 Testing");
		data.put("programId","2759");
		
		httprequest.header("Content-Type","application/json");
		httprequest.body(data);
		Response response_1=httprequest.request(Method.PUT,"http://lms-program-rest-service.herokuapp.com/programs/"+2759);
		
		int statusCode_1 = response_1.getStatusCode();
		System.out.println("The status code recieved: " + statusCode_1);
		Reporter.log("The status code received for hard coded post"+statusCode_1);
	  
	    String responseBody_1 = response_1.getBody().asString();
		System.out.println("Hard Coded Put response Body using hash map" + responseBody_1);
		Reporter.log("Hard Coded Put response Body using hash map" + responseBody_1);
     
	} 


   @DataProvider(name = "testputdata")
   public Object[][] getData1() throws IOException
	{
		
		String excelpath = "C:\\Users\\Supriya\\eclipse-workspace_202109NN\\18restassured\\src\\test\\resources\\TestData\\PutRequestLMSAPI.xlsx";
		String sheetname="Sheet1";
		Object data [][] =ReadExcel_Supriya_AllDataTypes.read(excelpath,sheetname);
		
		return data; 
		
	}
   

	@Test(dataProvider ="testputdata")
	
	public void HashMap_Data_Driven_Excel_testPutspecificPrograms(String programId,String programName,String programDescription,Boolean online )
	{
	
		System.out.println("The program id from excel sheet"+programId);
		Reporter.log(programId+"This is program ID from excel sheet");
		
    	RequestSpecification httpRequest = RestAssured.given().auth().basic("admin","password");
		Response response = httpRequest.request(Method.GET,"https://lms-program-rest-service.herokuapp.com/programs/"+programId);
    	String responseBody = response.getBody().prettyPrint();
	    Reporter.log("Get Response Body from Excel Sheet Data Driven =>  "+responseBody);
	    int statusCode=response.getStatusCode();
		Assert.assertEquals(statusCode,200,"Correct Status Code returned before update");
		 Reporter.log("The status code got is before update"+statusCode+"from program id from excel sheet");
		

		
	    RestAssured.baseURI ="http://lms-program-rest-service.herokuapp.com";
		RequestSpecification httprequest = RestAssured.given().auth().basic("admin","password");
		HashMap data=new HashMap();
	    data.put("online",online);
	    data.put("programDescription",programDescription);
		data.put("programName",programName);
		data.put("programId",programId);
		
		httprequest.header("Content-Type","application/json");
		httprequest.body(data);
		Response response_1=httprequest.request(Method.PUT,"http://lms-program-rest-service.herokuapp.com/programs/"+programId);
		
		int statusCode_1 = response_1.getStatusCode();
		System.out.println("The status code recieved: after the update" + statusCode_1);
		Reporter.log("The status code received for hard coded post"+statusCode_1);
	  
	    String responseBody_1 = response_1.getBody().asString();
		System.out.println("Response Body is received after update(data driven) from " + responseBody_1);
		Reporter.log("Response Body is received after update(data driven) from " + responseBody_1);
      
	} 

}

