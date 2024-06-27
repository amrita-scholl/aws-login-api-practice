# aws-login-api-practice
Lambda Login API and deploy on AWS

1. Create the Lambda Function
      	Update your Lambda function to handle login requests:
      	- refer LoginHandler class

2. Update Dependencies
	Ensure you have the Jackson library for JSON parsing in your pom.xml:

	<dependency>
	    <groupId>com.fasterxml.jackson.core</groupId>
	    <artifactId>jackson-databind</artifactId>
	    <version>2.12.3</version>
	</dependency>

3. Deploy the Lambda Function to AWS
	
	aws lambda update-function-code --function-name HelloLambda --zip-file fileb://target/LambdaAPIDemo-1.0-SNAPSHOT.jar
	
	or 
	
	aws lambda create-function --function-name LambdaLoginAPI --zip-file fileb://target/LambdaLoginAPI-1.0-SNAPSHOT.jar --handler com.example.LambdaHandler --runtime java21 --role 
	arn:aws:iam::ACCOUNT_ID:role/lambda-basic-execution


4. Update API Gateway


5.  Test with Postman

	- Open Postman and create a new POST request.
	- Set the request URL to your API Gateway endpoint.
	- Set the request body to raw JSON and provide the credentials:
		{
                   "username": "admin",
                   "password": "password123"
		}		
	- Send the request.

	You should receive a JSON response indicating whether the login was successful or if the credentials were invalid.
