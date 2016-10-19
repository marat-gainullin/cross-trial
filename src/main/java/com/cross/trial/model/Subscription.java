package com.cross.trial.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Subscription persistent POJO.
 * This is persistent entity.
 *
 * @author mg
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "subscription-by-id", query = ""
                + " from Subscription s"
                + " where s.id = :subscriptionId"),
        @NamedQuery(name = "subscriptions-all", query = ""
                + " from Subscription s order by s.id")})
public class Subscription implements Serializable {

    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = 3471069051927286254L;

    /**
     * Subscription id. It is not final because of ORM.
     */
    @Id
    @Column
    private Long id;

    /**
     * The subscription's amount. It is not final
     * because of ORM.
     */
    @Column
    private Long amount;

    /**
     * Subscription's description. It is not final because of ORM.
     */
    @Column
    private String description;

    /**
     * Subscription's no args constructor. Used by ORM.
     */
    public Subscription() {
        super();
    }

    /**
     * Subscription's amount getter.
     *
     * @return subscription amount.
     */
    public Long getAmount() {
        return amount;
    }

    /**
     * Subscription number getter.
     *
     * @return this subscription id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Subscription's description getter.
     *
     * @return account's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Add an amount to this subscription.
     *
     * @param aAmount Amount to add to this subscription.
     */
    public final void add(final long aAmount) {
        amount += aAmount;
    }

}
