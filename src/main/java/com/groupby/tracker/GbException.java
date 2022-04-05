package com.groupby.tracker;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import okhttp3.Response;

public class GbException extends Exception {
    private int code = 0;
    private Map<String, List<String>> responseHeaders = null;
    private String responseBody = null;
    private GbError error = null;
    private JSON json;

    public GbException() {}

    public GbException(Throwable throwable) {
        super(throwable);
    }

    public GbException(String message) {
        super(message);
    }

    public GbException(String message, Throwable throwable, int code, Map<String, List<String>> responseHeaders, String responseBody) {
        super(message, throwable);
        this.code = code;
        this.responseHeaders = responseHeaders;
        this.responseBody = responseBody;

        if (this.code == 400)
        {
            json = new JSON();
            Type gbErrorReturnType = new TypeToken<GbError>(){}.getType();
            this.error = this.deserialize(responseBody, gbErrorReturnType);
        }
    }

    public GbException(String message, int code, Map<String, List<String>> responseHeaders, String responseBody) {
        this(message, (Throwable) null, code, responseHeaders, responseBody);
    }

    public GbException(String message, Throwable throwable, int code, Map<String, List<String>> responseHeaders) {
        this(message, throwable, code, responseHeaders, null);
    }

    public GbException(int code, Map<String, List<String>> responseHeaders, String responseBody) {
        this((String) null, (Throwable) null, code, responseHeaders, responseBody);
    }

    public GbException(int code, String message) {
        super(message);
        this.code = code;
    }

    public GbException(int code, String message, Map<String, List<String>> responseHeaders, String responseBody) {
        this(code, message);
        this.responseHeaders = responseHeaders;
        this.responseBody = responseBody;

        if (this.code == 400)
        {
            json = new JSON();
            Type gbErrorReturnType = new TypeToken<GbError>(){}.getType();
            this.error = this.deserialize(responseBody, gbErrorReturnType);
        }
    }

    /**
     * Get the HTTP status code.
     *
     * @return HTTP status code
     */
    public int getCode() {
        return code;
    }

    /**
     * Get the HTTP response headers.
     *
     * @return A map of list of string
     */
    public Map<String, List<String>> getResponseHeaders() {
        return responseHeaders;
    }

    /**
     * Get the HTTP response body.
     *
     * @return Response body in the form of string
     */
    public String getResponseBody() {
        return responseBody;
    }

    /**
     * Get the error.
     *
     * @return Error in the form of GbError
     */
    public GbError getError() {
        return error;
    }

    /**
     * Deserialize response body to Java object, according to the return type and
     * the Content-Type response header.
     *
     * @param <T> Type
     * @param respBody HTTP response body
     * @param returnType The type of the Java object
     * @return The deserialized Java object
     * @throws GbException If fail to deserialize response body, i.e. cannot read response body
     *   or the Content-Type of the response is not supported.
     */
    @SuppressWarnings("unchecked")
    public <T> T deserialize(String respBody, Type returnType) {
        if (respBody == null || "".equals(respBody)) {
            return null;
        }

        return json.deserialize(respBody, returnType);
    }
}
