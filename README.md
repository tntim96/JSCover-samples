JSCover-samples - Samples for JSCover
================================

What you need:
* Java (minimum 1.5)
* Maven 3
* [InternetExplorer and Chrome drivers for Selenium](http://code.google.com/p/selenium/downloads/list) and [PhantomJS](http://phantomjs.org/) for the WebDriver examples

To run tests:
mvn test

To run jscover-server-maven-plugin:
* First install jscover-server-maven-plugin locally as there is currently no release or snap-shot
* Run 'mvn jscover:server'