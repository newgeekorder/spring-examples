package zipi.concurrent;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;


@Configuration
@EnableScheduling
public class SchedulerConfiguration implements SchedulingConfigurer {

    @Value("${scheduler.poolSize}")
    int poolSize;

    /*
     * (non-Javadoc)
     * @see org.springframework.scheduling.annotation.SchedulingConfigurer#
     * configureTasks(org.springframework.scheduling.config. ScheduledTaskRegistrar)
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setTaskScheduler(taskScheduler());
    }

    /**
     * Bean for configuring a custom task scheduler.
     * 
     * @return Task scheduler.
     */
    @Bean
    TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(poolSize);
        return taskScheduler;
    }

}
