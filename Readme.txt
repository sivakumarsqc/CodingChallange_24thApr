Read-me File for Code Challenge 24th Apr 2019
---------------------------------------------
Cases to be Automated
---------------------
1. Navigate to "Makemytrip.com"
2  Select Flight Icon
3. Select Round Trip Radio button
4. Select Delhi as Source station and Bangalore as Destination Station
5. Select Today date as Departure date and after 7 days as return date. 
6. Click on Search button.
7. Print Total number of Departure Flight in Console.
8. Print Total number of Return Flight in Console
9. Select 'Non Stop' in Filter section.
10. Print Total number of Departure Flight in Console.
11. Print Total number of Return Flight in Console.
12. Unselect the 'Non Stop', then select 'One stop'.
13. Print Total number of Departure Flight in Console.
14. Print Total number of Return Flight in Console.
15. Unselect the One stop filter.
16. Select 'Random' (with in 10) flights in both Departure and Return section.
17  Verify the Flight price is displaying in Below section or not.
18. Get total of both the Flights and verify the Total in Below section
19. Verify the test case for 5 random numbers.

***************************************************************************************
Framework
---------
I am used TestNG frame work to create Test Cases and Page Object Model Design Pattern to get Elements and Actions on the Objects.

Created 2 Folder structure,
		1. First folder Contains Base Package, Pages Package and Utility.
		2. Second Folder contains Test cases using TestNG.
		
1. In Base Package, created a class to Initialize Driver and Property file, so that we can use that driver and property file any where if extend this class.
2. In Page Package, create separate classes for each page. So that we can define and create identifier and methods related to that corresponding page.
3. In Utility Package, created multiple classes like,
		Utility class - Java related reusable methods.
		ApplicationUtill - Application Related reusable methods like click button, select radio button, etc
		Extend Report - To get Detailed Report
		Log - To get Log file using Log4j
		RetryAnalyzer - To retry incase of Failure
		TestListener - To get Screen shot if Test Case getting Failed.
4. Logs will be written in Log Folder which is available in Root folder.
5. Screen Shots will be saved in Screen shot Folder which is available in Root folder.
6. Config file, Extend Report and TestNg.xml will be available in Project Root Folder.
***************************************************************************************
Required Libraries
------------------
Below are the Libraries used in this Framework to imporve our Reports and Logging,

	1. Selenium Client
	2. TestNG 
	3. Extend Report
	4. Log4j
	5. Retry Logic


