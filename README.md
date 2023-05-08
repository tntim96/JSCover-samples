Samples for JSCover
===================

[![Build Status](https://github.com/tntim96/JSCover-samples/workflows/Java-CI/badge.svg)](https://github.com/tntim96/JSCover-samples/actions?query=workflow%3A%22Java-CI%22)

## What you need:
* Java (minimum 11)
* Maven 3
* [Chrome, FireFox (gecko) and MS Edge drivers for Selenium](https://www.seleniumhq.org/download/) for the WebDriver examples. These should be on the executable path.

To run all tests:
`mvn verify`

### Handling Drivers Not On Executable Path
#### Maven Plugins
The Maven plugins driver path can be with the `systemProperties` property in the `pom.xml` file.
For more details see the configuration details for the
[jscover-server maven plugin](https://github.com/tntim96/JSCover-maven-plugin/tree/master/plugin-parent/server)
and the [jscover-file-system maven plugin](https://github.com/tntim96/JSCover-maven-plugin/tree/master/plugin-parent/file-system).

#### Code Samples
The Java code samples driver path can be set with the system properties `webdriver.edge.driver` and `webdriver.chrome.driver`
set either on the command line or directly in the Java code.

## Java Code Samples
To run JUnit tests:
`mvn test`

### Server Examples In Multiple Browsers With Jasmine
The main class is
[WebDriverJasmineTestBase](https://github.com/tntim96/JSCover-samples/blob/master/src/main/java/jscover/webdriver/jasmine/WebDriverJasmineTestBase.java). Examples using FireFox, Chrome and MS Edge are [here](https://github.com/tntim96/JSCover-samples/blob/master/src/test/java/jscover/webdriver/jasmine/).

The examples use Jasmine and Jasmine's `HtmlReporter`.

### Proxy Examples
#### General
Run interactive sample from JSCover's web-site in FireFox: [WebDriverGeneralProxyTest](https://github.com/tntim96/JSCover-samples/blob/master/src/test/java/jscover/webdriver/proxy/WebDriverGeneralProxyTest.java)

#### With QUnit
Run Underscore's online QUnit tests in FireFox: [WebDriverUnderscoreProxyTest](https://github.com/tntim96/JSCover-samples/blob/master/src/test/java/jscover/webdriver/proxy/WebDriverUnderscoreProxyTest.java)



## JSCover Maven Plugin
To run the JSCover Server Maven Plugin:
`mvn jscover-server:jscover`

To run the JSCover File Maven Plugin:
`mvn jscover-file:jscover`

**Note** JSCover File Maven Plugin generates your coverage reports _without ever running a server_.

To merge the file and server reports above:
`mvn jscover-report:merge`

To generate cobertura XML and lcov data for the merged report above:
`mvn jscover-report:format`


See the configuration in the [pom.xml](https://github.com/tntim96/JSCover-samples/blob/master/pom.xml).

Also see the [JSCover-maven-plugin project](https://github.com/tntim96/JSCover-maven-plugin).