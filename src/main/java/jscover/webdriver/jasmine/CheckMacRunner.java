package jscover.webdriver.jasmine;

import org.junit.Ignore;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

import java.lang.reflect.Method;


public class CheckMacRunner extends BlockJUnit4ClassRunner {

    public CheckMacRunner(Class<?> klass) throws InitializationError {
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
        return method.isAnnotationPresent(Ignore.class) || !isMac();
    }

    private boolean isMac() {
        return System.getProperty("os.name").toLowerCase().contains("mac");
    }
}
