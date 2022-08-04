package com.url.repository;

import com.url.model.ShortenedUrl;

import java.util.Optional;

public interface ShortenedUrlRepository {

    void save(ShortenedUrl url);

    Optional<ShortenedUrl> find(String shortenedUrlValue);
}
