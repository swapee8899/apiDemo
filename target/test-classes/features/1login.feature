Feature: Login and User profile info testing of Compucom connect API's
@smoke
Scenario Outline: Verify if user is able to login with loginAPI
	Given Login Payload with "<email>" and "<password>"
	When User calls "<API_Name>" post request 
	Then API is success with 200 status code
	And Store sessionID
	And "<Response_body_Key>" is "<Response_body_value>" in response body
	And "<Response_body_Key2>" is "<Response_body_value2>" in response body 	
Examples:
|email                |password   |API_Name |Response_body_Key|Response_body_value|Response_body_Key2|Response_body_value2|
|Testuser2@fgatest.com|Compucom@12|LoginAPI |result           |success            |reason            |loginsuccess        |



@regression
Scenario Outline: Verify if user is able to get GetProfile with GetProfileAPI
	Given User is logged in
	When User calls "<API_Name>" post request
	Then API is success with 200 status code
	And "<Response_body_Key>" is "<Response_body_value>" in response body
	And "<Response_body_Key2>" is "<Response_body_value2>" in response body	
Examples:
|API_Name          |Response_body_Key|Response_body_value|Response_body_Key2|Response_body_value2|
|GetProfileAPI     |result           |success            |reason            |attributefound         |
	