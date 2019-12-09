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

