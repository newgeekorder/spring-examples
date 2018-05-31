package zipi.concurrent.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import zipi.concurrent.exception.InvalidTokenSubmissionException;
import zipi.concurrent.helper.Token;


@Component
public class BarAsyncTask {

    @Async("BarPool")
    public void executeAsyncTask(Token token, int taskNumber, String fooTaskName)
            throws InterruptedException, InvalidTokenSubmissionException {
        System.out.println(fooTaskName + " " + Thread.currentThread().getName() + " Bar async task number " + taskNumber
                + " started");
        Thread.sleep(1000);
        System.out.println(fooTaskName + " " + Thread.currentThread().getName() + " Bar async task number " + taskNumber
                + " finished");
        token.releaseToken();
    }
}
