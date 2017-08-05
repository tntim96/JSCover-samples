Samples for JSCover
===================

[![Build Status](https://travis-ci.org/tntim96/JSCover-samples.svg?branch=master)](https://travis-ci.org/tntim96/JSCover-samples)

## What you need:
* Java (minimum 1.8)
* Maven 3
* [InternetExplorer and Chrome drivers for Selenium](http://code.google.com/p/selenium/downloads/list) and [PhantomJS](http://phantomjs.org/) for the WebDriver examples. These should be on the executable path.

To run all tests:
`mvn verify`

### Handling Drivers Not On Executable Path
#### Maven Plugins
The Maven plugins driver path can be with the `systemProperties` property in the `pom.xml` file.
For more details see the configuration details for the
[jscover-server maven plugin](https://github.com/tntim96/JSCover-maven-plugin/tree/master/plugin-parent/server)
and the [jscover-file-system maven plugin](https://github.com/tntim96/JSCover-maven-plugin/tree/master/plugin-parent/file-system).

#### Code Samples
The Java code samples driver path can be set with the system properties `webdriver.ie.driver`, `webdriver.chrome.driver`
and `phantomjs.binary.path` set either on the command line or directly in the Java code.

## Java Code Samples
To run JUnit tests:
`mvn test`

### Server Examples In Multiple Browsers With Jasmine
The main class is
[WebDriverJasmineTestBase](https://github.com/tntim96/JSCover-samples/blob/master/src/main/java/jscover/webdriver/jasmine/WebDriverJasmineTestBase.java). Examples using PhantomJS, FireFox, Chrome and IE are [here](https://github.com/tntim96/JSCover-samples/blob/master/src/test/java/jscover/webdriver/jasmine/).

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