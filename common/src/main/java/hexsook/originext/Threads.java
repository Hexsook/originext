package hexsook.originext;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * A utility class for executing {@link Runnable} tasks in different thread configurations.
 */
public class Threads {

    /**
     * Executes the given task immediately in a new thread.
     */
    public static void runInstant(Runnable runnable) {
        new Thread(runnable).start();
    }

    /**
     * Schedules the given task to execute in a new thread after the specified delay.
     */
    public static void runLater(Runnable runnable, long delay, TimeUnit unit) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.schedule(runnable, delay, unit);
    }

    /**
     * Schedules the given task to execute repeatedly in a new thread,
     * with the specified delay before the first execution and at fixed intervals thereafter.
     */
    public static void runTimer(Runnable runnable, long delay, long period, TimeUnit unit) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(runnable, delay, period, unit);
    }

    private Threads() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }
}
