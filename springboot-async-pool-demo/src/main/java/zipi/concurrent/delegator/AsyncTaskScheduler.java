package zipi.concurrent.delegator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import zipi.concurrent.async.FooAsyncTask;

/**
 * Scheduler to kick start processing and continue submitting more and more tasks to fooPool.
 **
 */
@Component
public class AsyncTaskScheduler {

    @Autowired
    FooAsyncTask fooTask;

    @Scheduled(cron = "${scheduler.timeInterval}")
    void scheduleAsyncTask() {
        try {
            fooTask.executeAsyncTask();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
