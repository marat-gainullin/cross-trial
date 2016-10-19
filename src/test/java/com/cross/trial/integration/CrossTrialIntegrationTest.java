package com.cross.trial.integration;

import com.cross.trial.CrossTrialTestConfig;
import com.cross.trial.web.SubscriptionsController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author mg
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CrossTrialIntegrationTest.Config.class},
        loader = AnnotationConfigContextLoader.class)
public class CrossTrialIntegrationTest extends AbstractJUnit4SpringContextTests {

    /**
     * Configuration stub for spring <code>SpringJUnit4ClassRunner</code>
     *
     * @see SpringJUnit4ClassRunner
     */
    @Configuration
    public static class Config extends CrossTrialTestConfig {
    }

    /**
     * An <code>SubscriptionsController</code> autowired instance.
     *
     * @see SubscriptionsController
     */
    @Autowired
    private SubscriptionsController subscriptionsController;

    /**
     * Spring's test mockery.
     *
     * @see MockMvc
     */
    private MockMvc restMock;

    /**
     * Setup of Spring's mockery.
     */
    @Before
    public void setup() {
        restMock = MockMvcBuilders.standaloneSetup(subscriptionsController).build();
    }

    @Test
    public void crossesTest() throws Exception {
        restMock.perform(get("/subscriptions"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(""
                        + "["
                        + "{\"id\":15800002589632588,\"amount\":5000000,\"description\":\"Subscription of Pizza Hutt\"},"
                        + "{\"id\":25800002589632588,\"amount\":3000000,\"description\":\"Subscription of CIA\"},"
                        + "{\"id\":35800002589632588,\"amount\":2000000,\"description\":\"Jurassic park subscription\"},"
                        + "{\"id\":45800002589632588,\"amount\":1000000,\"description\":\"University subscription\"},"
                        + "{\"id\":55800002589632588,\"amount\":900000,\"description\":\"Inter subscription\"},"
                        + "{\"id\":65800002589632588,\"amount\":800000,\"description\":\"FinTech subscription\"}]"));

    }

    @Test
    public void crossByIdTest() throws Exception {
        restMock.perform(get("/subscriptions/{subscription-id}", 25800002589632588L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("{\"id\":25800002589632588,\"amount\":3000000,\"description\":\"Subscription of CIA\"}"));
    }

    @Test
    public void absentCrossTest() throws Exception {
        restMock.perform(get("/subscriptions/{subscription-id}", 101010101010L))
                .andExpect(status().isNotFound());
    }    
}
