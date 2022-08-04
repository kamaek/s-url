package com.url.service;

public interface UrlShortener {

    /**
     * Shortens the specified URL, e.g. you can supply
     * {@code http://www.xplace.com} and get back {@code http://xpl.ac/abcdef}.
     *
     * <p>The shortened URLs should {@linkplain #resolveShortened(String) resolve} to only one source URL.
     */
    String shorten(String url);

    /**
     * The backward operation for {@link #shorten(String)}
     */
    String resolveShortened(String shortenedUrl);
}
