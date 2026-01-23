# Sauce demo UI automation
# SwagLabs Selenium Automation Tests

## Overview
This project contains automated UI tests for the [SwagLabs](https://www.saucedemo.com/) web application using **Java**, **Selenium WebDriver**, **JUnit**, and **Allure** for reporting.  
The tests cover functionalities such as login, logout, cart operations, checkout flow, product sorting, and app reset state.

## Audience
This README is intended for:
- QA engineers who want to run or extend automated tests
- Developers who need to verify application flows via automated UI tests
- Anyone reviewing the test coverage of SwagLabs

## Project Structure
- `src/main/java` — page objects and utility classes
- `src/test/java` — automated test classes
- `drivers/` — place browser drivers here (`chromedriver`, `geckodriver`, `msedgedriver`)
- `target/` — test results and Allure reports after running tests

**Important:**  
For Chrome, place the driver in `drivers/chromedriver`.  
For Firefox, place the driver in `drivers/geckodriver`.  
For Yandex, place the driver in `drivers/yandexdriver`.

## Prerequisites
- Java 21+
- Maven 3+
- Browser(s) installed (Chrome, Firefox, Yandex)
- Drivers placed in the `drivers` folder

## How to Run Tests
1. Open terminal or IDE.
2. Navigate to the project root.
3. Run all tests using Maven:

## How to Run Tests

#### Run all tests
From terminal (bash) or IDE terminal:
```bash
mvn clean test
```
#### Generate and view Allure report
From terminal (bash) or IDE terminal:
```bash
mvn allure:serve
```
Then open:
target/site/allure-maven-plugin/index.html