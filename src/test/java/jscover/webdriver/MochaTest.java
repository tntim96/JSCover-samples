package jscover.webdriver;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;

import jscover.Main;
import jscover.maven.MochaWebDriverRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;


public class MochaTest {

  private static Thread server;

  protected WebDriver webClient;
  private String[] args = new String[]{
      "-ws",
      "--port=8081",
      "--no-instrument=src/main/webapp/js/vendor",
      "--no-instrument=src/test/javascript/lib",
      "--no-instrument=src/test/javascript/spec",
      "--no-instrument=target",
      "--report-dir=" + getReportDir()
  };

  private MochaWebDriverRunner runner = new MochaWebDriverRunner();


  public WebDriver getWebClient() {
    return new ChromeDriver(new DesiredCapabilities());
  }


  protected String getReportPartialSubDirectory() {
    return "mocha";
  }


  protected String getReportDir() {
    return "target/reports/jscover-" + getReportPartialSubDirectory();
  }


  @Before
  public void setUp() throws Exception {
    runner.setTimeOutSeconds(10);
    //boolean justStarted = false;
    if (server == null) {
      //justStarted = true;
      server = new Thread(new Runnable() {
        public void run() {
          new Main().runMain(args);
        }
      });
      server.start();
    }
    webClient = getWebClient();
  }


  @After
  public void tearDown() {
    try {
      webClient.close();
    } catch (Throwable t) {
      t.printStackTrace();
    }
    try {
      webClient.quit();
    } catch (Throwable t) {
      t.printStackTrace();
    }
  }


  protected String getTestUrl() {
    return "src/test/javascript/mocha-code-pass.html";
  }


  @Test
  public void shouldRunMochaTestAndStoreResultViaJavaScriptCall() throws Exception {
    File jsonFile = new File(getReportDir() + "/" + getReportPartialSubDirectory() + "/jscoverage.json");
    if (jsonFile.exists())
      jsonFile.delete();

    webClient.get("http://localhost:8081/" + getTestUrl());

    runner.waitForTestsToComplete(webClient);
    runner.verifyTestsPassed(webClient);

    ((JavascriptExecutor) webClient).executeScript("window.jscoverFinished = false;");
    ((JavascriptExecutor) webClient).executeScript("jscoverage_report('" + getReportPartialSubDirectory() + "', function(){window.jscoverFinished=true;});");
    (new WebDriverWait(webClient, 10))
            .until((ExpectedCondition<Boolean>) d -> (Boolean)((JavascriptExecutor) webClient).executeScript("return window.jscoverFinished;"));

    webClient.get(format("http://localhost:8081/%s/%s/jscoverage.html", getReportDir(), getReportPartialSubDirectory()));
    verifyTotal(webClient, 100, 100, 100);
  }


  protected void verifyTotal(WebDriver webClient, int percentage, int branchPercentage, int functionPercentage) {
    webClient.findElement(By.id("summaryTab")).click();
    verifyTotals(webClient, percentage, branchPercentage, functionPercentage);
  }


  protected void verifyTotals(WebDriver webClient, int percentage, int branchPercentage, int functionPercentage) {
    new WebDriverWait(webClient, 1).until(textToBePresentInElementLocated(By.id("summaryTotal"), "%"));
    assertEquals(percentage + "%", webClient.findElement(By.id("summaryTotal")).getText());
    assertEquals(branchPercentage + "%", webClient.findElement(By.id("branchSummaryTotal")).getText());
    assertEquals(functionPercentage + "%", webClient.findElement(By.id("functionSummaryTotal")).getText());
  }
}
