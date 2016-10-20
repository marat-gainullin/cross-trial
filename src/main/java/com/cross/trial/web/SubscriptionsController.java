package com.cross.trial.web;

import com.cross.trial.model.Subscription;
import com.cross.trial.services.SubscriptionsStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.util.Collection;

/**
 * JSON REST end point of subscriptions API.
 *
 * @author mg
 */
@RestController
public class SubscriptionsController {

    /**
     * Subscriptions store.
     */
    @Autowired
    private SubscriptionsStore subscriptionsStore;

    /**
     * Retrieves a subscriptions list.
     *
     * @return JSON binded collection of subscriptions.
     * @throws IOException if some error while communications occur.
     */
    @RequestMapping(path = "/subscriptions",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public final Collection<Subscription> subscriptions() throws IOException {
        return subscriptionsStore.subscriptions();
    }

    /**
     * Looks up a subscription by its id.
     *
     * @param aId Id of a subscription to lookup.
     * @return An {@code Subscription} instance got from repository.
     * @throws IOException if some error occurs while communication.
     */
    @RequestMapping(path = "/subscriptions/{subscription-id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public final Subscription subscriptionsById(
            @PathVariable("subscription-id") final Long aId)
            throws IOException {
        return subscriptionsStore.find(aId);
    }

    /**
     * Handles {@code NoResultException} exceptions.
     *
     * @param ex A {@code NoResultException} instance.
     * @return A {@code String} to be binded as graceful http response.
     * @see NoResultException
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoResultException.class)
    public final String handleNoResult(final NoResultException ex) {
        return ex.getMessage();
    }
}
