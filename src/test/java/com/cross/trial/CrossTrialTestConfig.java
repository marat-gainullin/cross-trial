package com.cross.trial;

import com.cross.trial.services.SubscriptionsStore;
import com.cross.trial.web.SubscriptionsController;
import org.springframework.context.annotation.Bean;

/**
 * @author mg
 */
public class CrossTrialTestConfig extends CrossTrialConfig {
    @Bean
    public SubscriptionsController crossesController() {
        return new SubscriptionsController();
    }

    @Bean
    public SubscriptionsStore crossesStore() {
        return new SubscriptionsStore();
    }
}
