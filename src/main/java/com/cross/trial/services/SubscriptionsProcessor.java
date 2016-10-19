package com.cross.trial.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Processor implementation. Performs operations with subscriptions.
 *
 * @author mg
 */
@Service
public class SubscriptionsProcessor {

    /**
     * Subscriptions store.
     */
    @Autowired
    private SubscriptionsStore subscriptionsStore;

    /**
     * JPA data access layer.
     */
    @PersistenceContext
    private EntityManager dataStore;

}
