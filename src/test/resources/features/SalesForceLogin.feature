Feature: checking login functionality
#String url=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "username");
Background:
Given the url 

Scenario: valid username and empty password
When user on "loginpage"
When enter username as "vidhya@tekarch.com"
And enter password as ""
And click login button
Then should see the error message

Scenario: valid username and valid password
When user on "LoginPage"
When enter username as "vidhya@tekarch.com"
And enter password as "Dayamayam@12"
And click login button
When user on "HomePage"
Then should see the home page

Scenario: remember me check
When user on "LoginPage"
When enter username as "vidhya@tekarch.com"
And enter password as "Dayamayam@12"
And click remember me checkbox
And click login button
When user on "homePage"
Then should see the home page
And click logout
When user on "LoginPage"
Then the username field should display the username "vidhya@tekarch.com"


Scenario: forgot password
When user on "loginpage"
When enter username as "vidhya@tekarch.com"
And click forgotpassword
When user on "forgotPasswordPage" 
And enter usernameField as "vidhya@tekarch.com"
And click continue button
When user on "checkyouremailpage"
Then should see the password reset message

Scenario: invalid username and invalid password
When user on "loginpage"
When enter username as "123"
And enter password as "22131"
And click login button
Then should see different error message
