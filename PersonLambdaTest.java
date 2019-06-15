package com.amazonaws.lambda.demo;

import java.nio.charset.StandardCharsets;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambdaAsyncClient;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.google.gson.Gson;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class PersonLambdaTest {

	public static void main(String[] args) {
		String firstname = "Yamini";
		String lastname = "Govind";
		String region = "us-east-1";
		
		if (args.length > 0) {
			firstname = args[0];
		}
		if (args.length > 1) {
			lastname = args[1];
		}
		if (args.length > 2) {
			region = args[2];
		}
		
		Gson gson = new Gson();
		@SuppressWarnings("deprecation")
		AWSLambdaAsyncClient client = new AWSLambdaAsyncClient(new ProfileCredentialsProvider("lambdaUser")).withRegion(Regions.fromName(region));
		
		PersonInput input = new PersonInput();
		input.setFirstname(firstname);
		input.setLastname(lastname);
		
		InvokeRequest request = new InvokeRequest().withFunctionName("personLambda").withPayload(gson.toJson(input));
		InvokeResult result = client.invoke(request);
		String set = StandardCharsets.UTF_8.decode(result.getPayload()).toString();
		
		PersonOutput output = gson.fromJson(set, PersonOutput.class);
		System.out.println("Message : " + output.getMessage());
		System.out.println("RequestId : " + output.getRequestId());
		System.out.println("Function : " + output.getFunctionName());
		System.out.println("Memory : " + output.getMemory());
	}
}
