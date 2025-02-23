package net.theevilreaper.vulpes.backend.exception;

import io.micronaut.http.HttpMethod;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.exceptions.HttpStatusException;

/**
 * Thrown if a resource was not found in the database.
 * @author theEvilReaper
 * @since 1.0.0
 * @version 1.0.0
 */
public class ResourceNotFoundException extends HttpStatusException {

    private final HttpMethod httpMethod;

    /**
     * @param httpMethod The HTTP method which was used to access the resource
     * @param resourceId The id of the resource which was not found
     */
    public ResourceNotFoundException(HttpMethod httpMethod, String resourceId) {
        super(HttpStatus.NOT_FOUND, resourceId);
        this.httpMethod = httpMethod;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }
}