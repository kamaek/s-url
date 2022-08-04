package com.url.repository;

import com.url.model.ShortenedUrl;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public final class InMemoryShortenedUrlRepository implements ShortenedUrlRepository {

    private final ConcurrentMap<String, ShortenedUrl> urls = new ConcurrentHashMap<>();

    @Override
    public void save(ShortenedUrl url) {
        urls.put(url.shortened(), url);
    }

    @Override
    public Optional<ShortenedUrl> find(String shortenedUrlValue) {
        return Optional.ofNullable(urls.get(shortenedUrlValue));
    }
}
