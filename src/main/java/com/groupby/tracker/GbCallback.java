package com.groupby.tracker;

import java.util.List;
import java.util.Map;

/**
 * Callback for asynchronous API call.
 *
 */
public interface GbCallback {
    /**
     * This is called when the API call fails.
     *
     * @param e The exception causing the failure
     * @param statusCode Status code of the response if available, otherwise it would be 0
     */
    void onFailure(GbException e, int statusCode);

    /**
     * This is called when the API call succeeded.
     *
     */
    void onSuccess();
}
