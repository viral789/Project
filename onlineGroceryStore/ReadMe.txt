Run Application in eclipse:
	1. Import the application in eclipse as existing maven project as the project is MAVEN project
	2. Use Tomcat 7.0.78 as server to run the application at port 8080
	3. Right click on project folder -> Go to Property -> click on Deployment assembly
	4. Click on Add -> Java Built Path Entries -> Select MAVEN dependencies
	5. Before Running the application set MAVEN path in Environment variables
		Steps to set MAVEN path in Environment variables
			a. Go to Control Panel -> All Control Panel Items -> System
			b. Click on Advanced system settings 
			c. click on Environment variables
			d. Under System variables click on new 
			e. Set Variable name as MAVEN_HOME and variable path as path of the maven folder on your machine
			f. Now edit Path variable under system variables
			e. Click on new as set the path as %MAVEN_HOME%\bin
	6. Once the MAVEN is set in Environment variable, Right click on folder of the application and select run as MAVEN CLEAN and then MAVEN INSTALL
	7. Lastly set the application in server and start the server
	8. Open browser and type the following url: localhost:8080/onlineGroceryStore

	
Run application directly on tomcat server
	1. Extract the application in file explorer
	2. Go to onlineGroceryStore/target folder
	3. There will be a war file named onlineGroceryStore.war, copy the war file and paste it under tomcat folder at webapp directory.
	4. Go to bin directory and start tomcat server by running startup.bat 
	5. Before starting server follow the 5th step as mention above in Run Application in eclipse
	6. Open browser and type the following url: localhost:8080/onlineGroceryStore
	
	
General information about application:
	1. We are uploading the application with minimum database.
	2. The username and password for staff is vbhojani and password is 12345
	3. The username and password for customer is pdusane and password is 12345
	4. Staff as access to add product and they can also order for the product
	5. Customer can only order the product
	6. Customer can pay whatever amount he/she wants to pay and the remaining balance can be added in the next transaction they will make.
	7. Like if the total amount to be paid is 50$ and he/she choose to pay only 40$ then the remaining balance of 10$ will be added in the next transaction.	