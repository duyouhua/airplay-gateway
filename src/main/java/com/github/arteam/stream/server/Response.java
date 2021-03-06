package com.github.arteam.stream.server;

/**
 * Date: 5/12/13
 * Time: 12:50 AM
 *
 * @author Artem Prigoda
 */

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;

/**
 * HTTP response. Return one of these from serve().
 */
class Response {

    /**
     * Common mime types for dynamic content
     */
    public static final String MIME_PLAINTEXT = "text/plain";
    public static final String MIME_HTML = "text/html";

    /**
     * HTTP status code after processing, e.g. "200 OK", HTTP_OK
     */
    @NotNull
    public Status status;
    /**
     * MIME type of content, e.g. "text/html"
     */
    @NotNull
    public String mimeType;
    /**
     * Data of the response, may be null.
     */
    @NotNull
    public InputStream data;
    /**
     * Headers for the HTTP response. Use addHeader() to add lines.
     */
    @NotNull
    public Map<String, String> headers = new HashMap<String, String>();

    /**
     * Default constructor: response = HTTP_OK, mime = MIME_HTML and your supplied message
     */
    public Response(String msg) {
        this(Status.OK, MIME_HTML, msg);
    }

    public Response(Status status) {
        this(status, MIME_HTML, status.getDescription());
    }

    /**
     * Basic constructor.
     */
    public Response(@NotNull Status status, @NotNull String mimeType, @NotNull InputStream data) {
        this.status = status;
        this.mimeType = mimeType;
        this.data = data;
    }

    /**
     * Convenience method that makes an InputStream out of given text.
     */
    public Response(@NotNull Status status, @NotNull String mimeType, @NotNull String txt) {
        this.status = status;
        this.mimeType = mimeType;
        try {
            data = new ByteArrayInputStream(txt.getBytes("UTF-8"));
        } catch (java.io.UnsupportedEncodingException uee) {
        }
    }

    /**
     * Adds given line to the header.
     */
    public void addHeader(String name, String value) {
        headers.put(name, value);
    }

    @Override
    public String toString() {
        return "Response{" +
                "status=" + status +
                ", mimeType='" + mimeType + '\'' +
                ", header=" + headers +
                '}';
    }
}
