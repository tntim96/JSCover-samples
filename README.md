Samples for JSCover
===================

## What you need:
* Java (minimum 1.5)
* Maven 3
* [InternetExplorer and Chrome drivers for Selenium](http://code.google.com/p/selenium/downloads/list) and [PhantomJS](http://phantomjs.org/) for the WebDriver examples

## Java Code Samples
To run JUnit tests:
`mvn test`

### Server Examples In Multiple Browsers With Jasmine
The main class is
[WebDriverJasmineTestBase](https://github.com/tntim96/JSCover-samples/blob/master/src/main/java/jscover/webdriver/jasmine/WebDriverJasmineTestBase.java). Examples using PhantomJS, FireFox, Chrome and IE are [here](https://github.com/tntim96/JSCover-samples/blob/master/src/test/java/jscover/webdriver/jasmine/).

The examples use Jasmine and Jasmine's `HtmlReporter`.

### Proxy Example With QUnit
Run Underscore's online QUnit tests in FireFox: [WebDriverUnderscoreProxyTest](https://github.com/tntim96/JSCover-samples/blob/master/src/test/java/jscover/webdriver/jasmine/WebDriverUnderscoreProxyTest.java)



## JSCover Maven Plugin
To run the JSCover Server Maven Plugin:
`mvn jscover-server:server`

To run the JSCover File Maven Plugin:
`mvn jscover-file:file`

See the configuration in the [pom.xml](https://github.com/tntim96/JSCover-samples/blob/master/pom.xml).