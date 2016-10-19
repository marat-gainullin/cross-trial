package com.cross.trial.exceptions;

/**
 * Exception thrown when no subscription found, an operation has bad data.
 *
 * @author mg
 */
public class BadOperationException extends Exception {

    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = 5561114535729550804L;

    /**
     * The exception constructor.
     *
     * @param aDetails A details message about what is wrong.
     */
    public BadOperationException(final String aDetails) {
        super(String.format("Bad transfer. %s", aDetails));
    }

}
