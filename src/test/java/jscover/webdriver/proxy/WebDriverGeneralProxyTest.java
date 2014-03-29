package jscover.webdriver.proxy;

import jscover.Main;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;

public class WebDriverGeneralProxyTest {
    private static Thread server;

    private final WebDriver webClient = getWebClient();
    private final String[] args = new String[]{
            "-ws",
            "--port=3129",
            "--proxy",
            "--local-storage",
            "--no-instrument=test/vendor",
            "--report-dir=" + getReportDir()
    };

    WebDriver getWebClient() {
        Proxy proxy = new Proxy().setHttpProxy("localhost:3129");
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.PROXY, proxy);
        return new FirefoxDriver(cap);
    }

    String getReportDir() {
        return "target/reports/jscover-localstorage-general";
    }

    @Before
    public void setUp() throws IOException {
        File jsonFile = new File(getReportDir()+"/jscoverage.json");
        if (jsonFile.exists())
            jsonFile.delete();
        if (server == null) {
            server = new Thread(new Runnable() {
                public void run() {
                    try {
                        Main.main(getArgs());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            server.start();
        }
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

    String[] getArgs() {
        return args;
    }

    @Test
    public void shouldRunExampleAndStoreResultProgrammatically() throws IOException {
        deleteJSON("/no-frames");
        webClient.get("http://tntim96.github.io/JSCover/example/");
        new WebDriverWait(webClient, 2).until(elementToBeClickable(By.id("radio2")));
        webClient.findElement(By.id("radio2")).click();
        webClient.findElement(By.id("radio4")).click();
        ((JavascriptExecutor) webClient).executeScript("jscoverage_report('no-frames');");
        verifyCoverage("/no-frames");
        verifyTotal(webClient, 89, 62, 100);
    }

    @Test
    public void shouldRunExample() throws IOException {
        deleteJSON("");
        webClient.get("http://tntim96.github.io/JSCover/example/");
        new WebDriverWait(webClient, 2).until(elementToBeClickable(By.id("radio2")));
        webClient.findElement(By.id("radio2")).click();
        webClient.findElement(By.id("radio4")).click();

        webClient.get("http://tntim96.github.io/jscoverage.html");

        new WebDriverWait(webClient, 1).until(ExpectedConditions.elementToBeClickable(By.id("storeTab")));
        webClient.findElement(By.id("storeTab")).click();

        new WebDriverWait(webClient, 1).until(ExpectedConditions.elementToBeClickable(By.id("storeButton")));
        webClient.findElement(By.id("storeButton")).click();
        new WebDriverWait(webClient, 10).until(textToBePresentInElementLocated(By.id("storeDiv"), "Coverage data stored at"));

        verifyCoverage("");
    }

    private void verifyCoverage(String reportDir) {
        webClient.get("file:///"+ new File(getReportDir()+reportDir+"/jscoverage.html").getAbsolutePath());
        verifyTotal(webClient, 89, 62, 100);
    }

    private void deleteJSON(String reportDir) {
        File jsonFile = new File(getReportDir()+reportDir+"/jscoverage.json");
        if (jsonFile.exists())
            jsonFile.delete();
    }

    void verifyTotal(WebDriver webClient, int percentage, int branchPercentage, int functionPercentage) {
        webClient.findElement(By.id("summaryTab")).click();

        verifyTotals(webClient, percentage, branchPercentage, functionPercentage);
    }

    void verifyTotals(WebDriver webClient, int percentage, int branchPercentage, int functionPercentage) {
        new WebDriverWait(webClient, 1).until(textToBePresentInElementLocated(By.id("summaryTotal"), "%"));
        assertEquals(percentage + "%", webClient.findElement(By.id("summaryTotal")).getText());
        assertEquals(branchPercentage + "%", webClient.findElement(By.id("branchSummaryTotal")).getText());
        assertEquals(functionPercentage + "%", webClient.findElement(By.id("functionSummaryTotal")).getText());
    }
}
