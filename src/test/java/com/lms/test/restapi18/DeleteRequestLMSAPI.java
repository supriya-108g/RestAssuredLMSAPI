package com.lms.test.restapi18;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lms.test.utils18.ReadExcel_Supriya_AllDataTypes;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteRequestLMSAPI {
	
	@Test(priority=1)
	public void ValidateStatusCodeforSpecificProgramIDHardCoded()
	{   
		
		//RestAssured.baseURI = "http://lms-admin-rest-service.herokupp.com";
		RequestSpecification httpRequest = RestAssured.given().auth().basic("admin","password");
		Response response = httpRequest.request(Method.DELETE,"https://lms-program-rest-service.herokuapp.com/programs/"+3055);
       String responseBody = response.getBody().prettyPrint();
	System.out.println("Response Body is for hardcoded program id  =>  " + responseBody);
		Reporter.log("Response body for hardcoded program id  =>  "+responseBody);
		int statusCode=response.getStatusCode();
		Assert.assertEquals(statusCode,200,"Correct Status Code returned");
		
		
	}
	

	@Test(dataProvider="test1data",priority=2)
	public void DataDriven_Excel_testFindProgram(String programId)
	
	{ //String a=Integer.toString(programId);
		System.out.println(programId);
		Reporter.log(programId+"This is program ID");
		//RestAssured.baseURI = "http://lms-admin-rest-service.herokupp.com";
    	RequestSpecification httpRequest = RestAssured.given().auth().basic("admin","password");
		Response response = httpRequest.request(Method.DELETE,"https://lms-program-rest-service.herokuapp.com/programs/"+programId);
		String responseBody = response.getBody().prettyPrint();
	    Reporter.log("Get Response Body from Excel Sheet Data Driven =>  "+responseBody);
	    int statusCode=response.getStatusCode();
		Assert.assertEquals(statusCode,200,"Correct Status Code returned");
		 Reporter.log("The status code got is "+statusCode+"from prgram id from excel sheet");

	}
	
	@DataProvider(name = "test1data")

	public Object[][] getData() throws IOException
	{
	  String excelpath = "C:\\Users\\Supriya\\eclipse-workspace_202109NN\\18restassured\\src\\test\\resources\\TestData\\DeleteRequestLMSAPI.xlsx";
	  String sheetname="Sheet1";
	  Object data [][] = ReadExcel_Supriya_AllDataTypes.read(excelpath,sheetname);
	  return data;
	}
}


