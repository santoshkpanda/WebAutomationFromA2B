# Project Title

Test Automation Framework POC for From A To B Web Application

## Getting Started

This document will give you an idea about installation of necessary tools required to execute automation framework

### Prerequisites

JAVA Version 1.8.0_101
Maven


### Installing

After successfull installation, validate whether Java and maven are properly installed on your machine

Find the below steps

```
Open Terminal on MAC laptop and run below commands:

java -version (This should give you Java version )
mvn -version

## Installing Dependency For Automation Framework

Provide executable permission to *install.sh* and *run.sh*

```
chmod 777 install.sh
chmod 777 run.sh
```

After giving permission go to automation framework folder and run below command from the terminal

```
./install.sh
```


## Running the tests

To run the test run below command

```
./run.sh
```

For running the tests on parallel on same machine follow below steps:
* Open TestNG.xml file
* Change value *thread-count* parameter to which how many instances you want to run
* As per your *thread-count* create *<test>* tag in xml file like below

```
<test name="TestSuite1">
    <parameter name="browserName" value="CHROME" />
              <classes>
                  <class name="testCases.LoginTestCases"/>
                  <class name="testCases.TripsTesting"/>
              </classes>
</test>
<test name="TestSuite2">
    <parameter name="browserName" value="CHROME" />
              <classes>
                  <class name="testCases.LoginTestCases"/>
				  <class name="testCases.TripsTesting"/>
              </classes>
</test>

```


## Post Execution information

* If test case is failed Automation Framework captures screenshot and save it in *Screenshots* folder
* HTML report of execution is saved to *TestReport* folder with Date

```

## Framework (Have used hybrid framework using maven & testNg + Page object model)


Folder Structre & Defination:

```
Binary : COntains executable of browser
Config: Contains All configuration files related to the framework
Logs: Will generate all the runtime logs in this folder
TestReports: All emailable html report are stored here (I have used extent report plugin, its a third party library)
Screenshots* : incase of a failure all the screenshots will be save in this folder
Testdata: Contains test case document for the web application

SRC:main: packages:browser: all browser related initialization (for example: for chrome chrome.java for firefox firefox.java and for IE iBrowser.java)
SRC:main: packages:core:actions.java: This is the wrapper on top of selenium inbuild methods
SRC:main: packages:core: All constants are defined
SRC:main: packages:core:log: all loggers are implemented
SRC:main: packages:core:utilis: Required java methods/utilities are implemented
SRC:main: packages:Customlistner: this class is responsible for test execution
SRC:main: packages:Filehandler: Parser for excel reader and property reader
SRC:main: packages:managers:extentManager: Code for html report generation
SRC:main: packages:managers:WebDriverManager: handles the driver instance
SRC:main: packages:PageActions: Page object model is used :
Dashboard: All the web elements to the page are defined
DeutscheBahnPage: All the web elements to the page are defined
LoginPage: All the web elements to the page are defined
SRC:Test: Its the parent for all the test classes
SRC:Test: LoginTestcase: Test cases related to login are defined.
SRC:Test:TripsTesting: Test cases related to trips are defined.

```
