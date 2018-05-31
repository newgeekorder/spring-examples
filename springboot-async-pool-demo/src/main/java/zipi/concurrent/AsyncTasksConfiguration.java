package zipi.concurrent;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


@Configuration
@EnableAsync
public class AsyncTasksConfiguration {

    /**
     * Bean for configuring a custom thread pool
     * 
     * @return custom thread pool
     */
    @Bean
    @Qualifier("FooPool")
    public Executor getFooPoolAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("FooPool-");
        executor.initialize();
        return executor;
    }

    /**
     * Bean for configuring a custom thread pool
     * 
     * @return custom thread pool
     */
    @Bean
    @Qualifier("BarPool")
    public Executor getBarPoolAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("BarPool-");
        executor.initialize();
        return executor;
    }

}
