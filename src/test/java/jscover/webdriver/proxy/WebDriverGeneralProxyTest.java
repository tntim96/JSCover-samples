package jscover.webdriver.proxy;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;

import jscover.Main;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;


public class WebDriverGeneralProxyTest {
    private static Thread server;
    private static Main main = new Main();
    private static String reportDir = "target/reports/jscover-localstorage-general";
    private final static String[] args = new String[]{
            "-ws",
            "--port=3129",
            "--proxy",
            "--local-storage",
            "--no-instrument=test/vendor",
            "--report-dir=" + reportDir
    };

    private final WebDriver webClient = getWebClient();

    private WebDriver getWebClient() {
        Proxy proxy = new Proxy().setHttpProxy("localhost:3129");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setHeadless(true);
        firefoxOptions.setProxy(proxy);
        return new FirefoxDriver(firefoxOptions);
    }

    @BeforeClass
    public static void setUpOnce() {
        server = new Thread(() -> main.runMain(args));
        server.start();
    }

    @AfterClass
    public static void tearDownOnce() {
        main.stop();
    }

    @Before
    public void setUp() {
        File jsonFile = new File(reportDir+"/jscoverage.json");
        if (jsonFile.exists())
            jsonFile.delete();
    }

    @After
    public void tearDown() {
        try {
            webClient.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Test
    public void shouldRunExampleAndStoreResultProgrammatically() {
        deleteJSON("/no-frames");
        webClient.get("http://tntim96.github.io/JSCover/example/");
        new WebDriverWait(webClient, Duration.ofSeconds(2)).until(elementToBeClickable(By.id("radio2")));
        webClient.findElement(By.id("radio2")).click();
        webClient.findElement(By.id("radio4")).click();
        ((JavascriptExecutor) webClient).executeScript("window.jscoverFinished = false;");
        ((JavascriptExecutor) webClient).executeScript("jscoverage_report('no-frames', function(){window.jscoverFinished=true;});");
        (new WebDriverWait(webClient, Duration.ofSeconds(10)))
                .until((ExpectedCondition<Boolean>) d -> (Boolean)((JavascriptExecutor) webClient).executeScript("return window.jscoverFinished;"));
        verifyCoverage("/no-frames");
        verifyTotal(webClient, 89, 62, 100);
    }

    @Test
    public void shouldRunExample() {
        deleteJSON("");
        webClient.get("http://tntim96.github.io/JSCover/example/");
        new WebDriverWait(webClient, Duration.ofSeconds(2)).until(elementToBeClickable(By.id("radio2")));
        webClient.findElement(By.id("radio2")).click();
        webClient.findElement(By.id("radio4")).click();

        webClient.get("http://tntim96.github.io/jscoverage.html");

        new WebDriverWait(webClient, Duration.ofSeconds(1)).until(ExpectedConditions.elementToBeClickable(By.id("storeTab")));
        webClient.findElement(By.id("storeTab")).click();

        new WebDriverWait(webClient, Duration.ofSeconds(1)).until(ExpectedConditions.textToBePresentInElementLocated(By.id("progressLabel"),"Done"));
        new WebDriverWait(webClient, Duration.ofSeconds(1)).until(ExpectedConditions.elementToBeClickable(By.id("storeButton")));
        webClient.findElement(By.id("storeButton")).click();
        new WebDriverWait(webClient, Duration.ofSeconds(10)).until(textToBePresentInElementLocated(By.id("storeDiv"), "Coverage data stored at"));

        verifyCoverage("");
    }

    private void verifyCoverage(String reportSubDir) {
        webClient.get("file:///"+ new File(reportDir+reportSubDir+"/jscoverage.html").getAbsolutePath());
        verifyTotal(webClient, 89, 62, 100);
    }

    private void deleteJSON(String reportDir) {
        File jsonFile = new File(reportDir+reportDir+"/jscoverage.json");
        if (jsonFile.exists())
            jsonFile.delete();
    }

    void verifyTotal(WebDriver webClient, int percentage, int branchPercentage, int functionPercentage) {
        webClient.findElement(By.id("summaryTab")).click();

        verifyTotals(webClient, percentage, branchPercentage, functionPercentage);
    }

    void verifyTotals(WebDriver webClient, int percentage, int branchPercentage, int functionPercentage) {
        new WebDriverWait(webClient, Duration.ofSeconds(1)).until(textToBePresentInElementLocated(By.id("summaryTotal"), "%"));
        assertEquals(percentage + "%", webClient.findElement(By.id("summaryTotal")).getText());
        assertEquals(branchPercentage + "%", webClient.findElement(By.id("branchSummaryTotal")).getText());
        assertEquals(functionPercentage + "%", webClient.findElement(By.id("functionSummaryTotal")).getText());
    }
}
