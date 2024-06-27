package com.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "password123";

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        ObjectMapper objectMapper = new ObjectMapper();
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        try {
            JsonNode body = objectMapper.readTree(input.getBody());
            String username = body.get("username").asText();
            String password = body.get("password").asText();

            if (USERNAME.equals(username) && PASSWORD.equals(password)) {
                response.setStatusCode(200);
                response.setBody("{\"message\": \"Login successful\"}");
            } else {
                response.setStatusCode(401);
                response.setBody("{\"message\": \"Invalid credentials\"}");
            }
        } catch (IOException e) {
            response.setStatusCode(500);
            response.setBody("{\"message\": \"Internal server error\"}");
        }

        response.setHeaders(headers);
        return response;
    }
}
