package com.url.repository;

import com.url.model.ShortenedUrl;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@Profile("prod")
public interface ShortenedUrlRepository extends MongoRepository<ShortenedUrl, String> {

    @Override
    Optional<ShortenedUrl> findById(String id);

    @Override
    ShortenedUrl save(ShortenedUrl entity);
}
