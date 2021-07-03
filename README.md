NDTV selenium-automation
===================

#### Summary:

This is automation testing project for NDTV Weather feature. This suite of automated tests covers API and UI tests and a data comparison test between UI data and API response.

Repository URL: [https://github.com/SwarnimaGupta15/NDTV.git]

#### System Requirements:

* JDK 1.8 or above
* Maven 3.1
* Eclipse or IDE of choice in case there is need to update the script. (optional)
* Git Bash

#### Automation Framework Package Structure:
  
- **automation.base :** Contains classes to initialize a test session. Also, contains BaseUi, GetPage Classes which comprises of commonly used methods.
- **automation.APIbase :** Contains classes to initialize a service objects. Also, contains BaseAPI Class which comprises of commonly used methods.
- **automation.keywords :** Contains Action classes corresponding to each web page of application , the Action methods use elements declared in the spec file.
- **automation.APIkeywords :** Contains service classes corresponding to each API of application , the service methods reads end points and key values from yaml file.
- **automation.utils :** Contains various utility classes like selenium waits, reading from yaml file, customized user defined exception
- **automation.tests :** This is the final layer, which contains the tests: UI tests, API tests, Data Comparison test.
- **src/test/resources/PageObjectRepository :** Spec files corresponding to each web page of application is maintained
- **src/test/resources/testData :** test data files are maintained for storing application url etc, json files for storing API response

#### Test Execution:

All the test suites can be configured and kept in _./src/test/resources/testXML folder

##### Executing the default tests as mentioned in the TestNG.xml

    mvn clean test

##### Executing NDTV weather suite of tests (e.g. as defined in WeatherTest.xml)

    mvn clean test -Dtestxml=WeatherTest.xml

##### Executing a single test (e.g. Weather_Verification_API_Test)

    mvn clean test -Dtest=Weather_Verification_API_Test


#### Result Files:	
The Test Execution Results will be stored in the following directory once the test has completed

##### TestNG Surefire reports
    ./target/surefire-reports/emailable-report.html (for single test suite)
	