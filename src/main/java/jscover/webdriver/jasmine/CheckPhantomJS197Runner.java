package jscover.webdriver.jasmine;

import org.junit.Ignore;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.lang.reflect.Method;
import java.util.Map;

public class CheckPhantomJS197Runner extends BlockJUnit4ClassRunner {

    public CheckPhantomJS197Runner(Class<?> klass) throws InitializationError {
        super(klass);
    }
    @Override
    protected void runChild(FrameworkMethod frameworkMethod, RunNotifier notifier) {
        if (isTestMethodIgnored(frameworkMethod)) {
            Description description = getDescription();
            notifier.fireTestIgnored(description);
            return;
        }
        super.runChild(frameworkMethod, notifier);
    }

    protected boolean isTestMethodIgnored(FrameworkMethod frameworkMethod) {
        Method method = frameworkMethod.getMethod();
        return method.isAnnotationPresent(Ignore.class) || !isPhantomJS197OrBetter();
    }

    private boolean isPhantomJS197OrBetter() {
        try {
            Map<String, Long> version = (Map<String, Long>) new PhantomJSDriver(new DesiredCapabilities()).executePhantomJS("return phantom.version;");
            //{minor=9, major=1, patch=7}
            return version.get("major") >= 1 && version.get("minor") >= 9 && version.get("patch") >= 7;
        } catch(WebDriverException e) {
            e.printStackTrace();
            return false;
        }
    }
}
