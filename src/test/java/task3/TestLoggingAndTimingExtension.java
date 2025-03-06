package task3;

import org.junit.jupiter.api.extension.*;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;

public class TestLoggingAndTimingExtension implements
        BeforeEachCallback, AfterEachCallback,
        BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private static final String START_TIME_KEY = "startTime";

    @Override
    public void beforeEach(ExtensionContext context) {
        System.out.println("Start test: " + context.getDisplayName());
    }

    @Override
    public void afterEach(ExtensionContext context) {
        System.out.println("Finish test: " + context.getDisplayName());
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        context.getStore(ExtensionContext.Namespace.create(context.getRequiredTestMethod()))
                .put(START_TIME_KEY, Instant.now());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        Instant startTime = context.getStore(ExtensionContext.Namespace.create(context.getRequiredTestMethod()))
                .get(START_TIME_KEY, Instant.class);

        Duration duration = Duration.between(startTime, Instant.now());

        System.out.println("Test " + context.getDisplayName() + " completed in " + duration.toMillis() + " ms");
    }
}