NDTV selenium-automation
===================

#### Summary:

This is NDTV automation testing project. This suite of automated tests covers API and UI tests.

Repository URL: [https://github.com/SwarnimaGupta15/NDTV.git]

#### System Requirements:

* JDK 1.8 or above
* Maven 3.1
* Eclipse or IDE of choice in case there is need to update the script. (optional)
* TestNG
* Git Bash

#### Automation Framework Package Structure:
  
- automation.base : Contains classes to initialize a test session. Also, contains BaseUi, GetPage Classes which comprises of commonly used methods.
- automation.keywords : Contains Action classes corresponding to each web page of application , the Action methods use elements declared in the spec file.
- automation.utils_ : Contains various utility classes like selenium waits, reading from yaml file
- automation.tests_ : This is the final layer, which contains the tests.
- src/test/resources/PageObjectRepository: Spec files corresponding to each web page of application is maintained
- src/test/resources/TestData: test data files are maintained for storing application url etc

#### Test Execution:

All the test suites can be configured and kept in _./src/test/resources/testxml folder_

##### Executing the default tests as mentioned in the TestNG.xml

    mvn clean integration-test

##### Executing custom suite of tests (e.g. as defined in WeatherTest.xml)

    mvn clean test -Dtestxml=WeatherTest.xml

##### Executing a single test (e.g. Topic_Assessment_Test)

    mvn clean test -Dtest=Weather_Verification_Test


#### Result Files:	
The Test Execution Results will be stored in the following directory once the test has completed

##### TestNG Surefire reports
    ./target/surefire-reports/emailable-report.html (for single test suite)
	