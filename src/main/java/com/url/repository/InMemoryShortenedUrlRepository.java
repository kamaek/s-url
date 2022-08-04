package com.url.repository;

import com.url.model.ShortenedUrl;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

@Profile("dev")
@Repository
public class InMemoryShortenedUrlRepository implements ShortenedUrlRepository {

    private final ConcurrentMap<String, ShortenedUrl> urls = new ConcurrentHashMap<>();

    @Override
    public ShortenedUrl save(ShortenedUrl url) {
        urls.put(url.shortened(), url);
        return url;
    }

    @Override
    public Optional<ShortenedUrl> findById(String shortenedUrlValue) {
        return Optional.ofNullable(urls.get(shortenedUrlValue));
    }

    // TODO: 04.08.2022 all operations below are basically no-op.
    //  Repository creation started working only when I implemented MongoRepository interface.
    // Need to double-check if it can be avoided.
    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public <S extends ShortenedUrl> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public List<ShortenedUrl> findAll() {
        return null;
    }

    @Override
    public Iterable<ShortenedUrl> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(ShortenedUrl entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends ShortenedUrl> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<ShortenedUrl> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<ShortenedUrl> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends ShortenedUrl> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends ShortenedUrl> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends ShortenedUrl> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends ShortenedUrl> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends ShortenedUrl> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends ShortenedUrl> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends ShortenedUrl> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends ShortenedUrl> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends ShortenedUrl, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
