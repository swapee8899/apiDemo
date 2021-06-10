Feature: Logout testing of Compucom connect API's
@smoke
Scenario Outline: Verify if user is able to logout with logoutAPI
	Given User is logged in
	When User calls "<API_Name>" post request
	Then API is success with 200 status code
	And "<Response_body_Key>" is "<Response_body_value>" in response body
	And "<Response_body_Key2>" is "<Response_body_value2>" in response body
	
Examples:
|API_Name          |Response_body_Key|Response_body_value|Response_body_Key2|Response_body_value2|
|LogoutAPI         |result           |success            |reason            |logout              |
