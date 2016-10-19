package com.cross.trial.services;

import com.cross.trial.model.Subscription;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;

/**
 * Subscriptions database store bean.
 *
 * @author mg
 */
@Transactional
@Repository
public class SubscriptionsStore {

    /**
     * JPA data access layer.
     */
    @PersistenceContext
    private EntityManager dataStore;

    /**
     * {@inheritDoc}
     */
    public Subscription find(final Long aSubscriptionId) {
        TypedQuery<Subscription> fetcher = dataStore
                .createNamedQuery("subscription-by-id", Subscription.class);
        fetcher.setParameter("subscriptionId", aSubscriptionId);
        return fetcher.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    public Collection<Subscription> subscriptions() {
        TypedQuery<Subscription> fetcher = dataStore
                .createNamedQuery("subscriptions-all", Subscription.class);
        return fetcher.getResultList();
    }

}
