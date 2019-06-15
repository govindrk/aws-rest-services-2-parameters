package com.amazonaws.lambda.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class PersonLambda implements RequestHandler<PersonInput, PersonOutput> {

    @Override
    public PersonOutput handleRequest(PersonInput input, Context context) {
    	PersonOutput output = new PersonOutput();
    	output.setMessage("Hello " + input.firstname + " " + input.lastname);
    	output.setFunctionName(context.getFunctionName());
    	output.setRequestId(context.getAwsRequestId());
    	output.setMemory(context.getMemoryLimitInMB());
        context.getLogger().log("Input: " + input.getFirstname() + " " + input.getLastname());
        return output;
    }

}
