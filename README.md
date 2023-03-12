[![License: Apache License 2.0](https://img.shields.io/badge/license-Apache%202-blue)](https://opensource.org/licenses/Apache)
[![badge-jdk](https://img.shields.io/badge/jdk-8-blue.svg)](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
[![Build Status](https://travis-ci.org/ngoanh2n/selenide-junit5-allure-example.svg?branch=master)](https://travis-ci.org/ngoanh2n/selenide-junit5-allure-example)

# Selenide, JUnit5, Allure Example


- [**Libraries & Tools**](#libraries--tools)
- [**Demo**](#demo)
- [**Project Structure**](#project-structure)
- [**How To Use**](#how-to-use)
  - [**Clone Repository**](#clone-repository)
  - [**Building**](#building)
  - [**Generate Allure report**](#Generate-Allure-Report)
  - [**Perform On Browsers**](#perform-on-browsers)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## **Libraries & Tools**
> **Maven**: <em>11 - Build Tool</em><br/>
> **Selenide**: <em>6.12.0 - WebDriver</em><br/>
> **Allure**: <em>2.21.0 - Test Report</em><br/>
> **JUnit5 - Platform**: <em>1.9.2 - Testing Launcher</em><br/>
> **JUnit5 - Jupiter**: <em>5.9.2 - Testing Framework</em><br/>
> **JUnit5 - Vintage**: <em>5.9.2 - Test Engine</em><br/>

## **Demo**
[<img src="https://img.youtube.com/vi/g-_bCtjSOrY/maxresdefault.jpg" width="50%">](https://youtu.be/g-_bCtjSOrY)

## **Project Structure**
```
├── LICENSE
├── README.md
├── UI_API_Tests.iml
├── pom.xml
├── src
│├── main
││└── java
││    ├── api
││    │├── reqres_in
││    ││├── CreateUserRequest.java
││    ││├── CreatedUserResponse.java
││    ││├── ListResourceResponse.java
││    ││├── SuccessfulLoginRequest.java
││    ││├── SuccessfulLoginResponse.java
││    ││├── SuccessfulRegisterRequest.java
││    ││├── SuccessfulRegisterResponse.java
││    ││├── UnSuccessfulLoginRequest.java
││    ││├── UnSuccessfulLoginResponse.java
││    ││├── UnSuccessfulRegisterRequest.java
││    ││└── UserdataResponse.java
││    │├── setup
││    ││├── RequestApiService.java
││    ││└── RequestSpecs.java
││    │└── util
││    │    ├── JsonToStringConverter.java
││    │    └── ReadProperties.java
││    ├── ui
││    │└── pageObject
││    │    ├── CartPage.java
││    │    ├── CheckoutCompletePage.java
││    │    ├── CheckoutInformationPage.java
││    │    ├── CheckoutOverviewPage.java
││    │    ├── LoginPage.java
││    │    └── ProductsPage.java
││    └── utils
││        ├── PropertyReader.java
││        ├── UIBaseTest.java
││        └── WebEventListener.java
│└── test
│    ├── java
│    │└── tests
│    │    ├── api
│    │    │├── LoginTests.java
│    │    │├── RegisterTests.java
│    │    │├── ResourceTests.java
│    │    │├── UserTests.java
│    │    │└── UserdataTests.java
│    │    └── ui
│    │        └── Tests.java
│    └── resources
│        ├── allure.properties
│        ├── log4j.properties
│        ├── reqres
│        │├── json_scheme
│        ││├── list_resource.json
│        ││├── single_user.json
│        ││└── userdata_list.json
│        │└── test_data
│        │    ├── td1.json
│        │    └── td2.json
│        └── test.properties
└── testCases.txt
```

## **How To Use**
### **Clone Repository**
> `$ git clone https://github.com/valpogor/UI_API_Selenide_Junit_Tests.git`
>
### **Building**

> `mvn clean verify`

### **Generate Allure report**

> `allure serve target/allure-results`


### **Perform On Browsers**
- chrome
- edge
- firefox
- ie
- safari

> If run safari, you must enable the 'Allow Remote Automation' option in Safari's Develop menu to control Safari via WebDriver.
