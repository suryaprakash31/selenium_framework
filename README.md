# OrangeHRM Selenium Automation Framework

A hybrid test automation framework built while learning Selenium, testing the
[OrangeHRM open source demo](https://opensource-demo.orangehrmlive.com).

## Tech stack
- Java, Maven
- Selenium WebDriver
- TestNG (with data-driven tests from Excel using Apache POI)
- Cucumber BDD (feature files, step definitions, hooks, tags)
- Page Object Model
- Log4j2 logging, ExtentReports and Cucumber reports
- Parallel and cross-browser execution (Chrome, Edge, Firefox)

## Project layout
```
orangehrm-framework/
  src/main/java/com/qa/pages      page classes (POM)
  src/main/java/com/qa/utils      driver factory, config reader, Excel, screenshots
  src/test/java/com/qa/tests      TestNG tests
  src/test/java/com/qa/stepdefs   Cucumber step definitions
  src/test/resources              config, feature files, test data
  testng.xml                      full suite
```

## How to run
Run all commands from the `orangehrm-framework` folder.

| What | Command |
|------|---------|
| Full suite | `mvn test` |
| Quick smoke suite | `mvn test "-DsuiteFile=testng-smoke.xml"` |
| Another browser | `mvn test "-Dbrowser=edge"` |
| Cucumber by tag | `mvn test "-Dtest=TestRunner" "-Dcucumber.filter.tags=@smoke"` |

## Reports
- TestNG tests: `target/extent-report/report.html`
- Cucumber scenarios: `target/cucumber-reports/report.html`
- Logs: `target/logs/automation.log`
- Failure screenshots: `target/screenshots`

## Local settings
Machine-specific settings (for example a driver path) go in
`src/test/resources/config.local.properties`. That file is not committed.