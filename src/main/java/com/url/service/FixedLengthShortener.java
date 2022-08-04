package com.url.service;

import com.url.model.ShortenedUrl;
import com.url.repository.ShortenedUrlRepository;
import com.url.service.counter.UniqueValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collector;

@Service
public final class FixedLengthShortener implements UrlShortener {

    private final UniqueValue uniqueValue;
    private final ShortenedUrlRepository repository;
    private final String alphabet;
    private final BigInteger alphabetLength;
    private final String shortenedPrefix;
    private final int shortenedSize;

    @Autowired
    public FixedLengthShortener(UniqueValue uniqueValue, ShortenedUrlRepository repository) {
        this.uniqueValue = Objects.requireNonNull(uniqueValue);
        this.repository = Objects.requireNonNull(repository);
        this.alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        this.alphabetLength = BigInteger.valueOf(this.alphabet.length());
        this.shortenedPrefix = "https://s-url/";
        this.shortenedSize = 7;
    }

    @Override
    public String shorten(String url) {
        BigInteger value = uniqueValue.next();
        if (alphabetLength.pow(shortenedSize).compareTo(value) < 0) {
            throw new IllegalArgumentException(String.format("Cannot encode %s using %s symbols", value, shortenedSize));
        }

        int[] alphabetIndexes = new int[shortenedSize];
        for (int position = 0; value.signum() > 0; position++) {
            int remainder = value.mod(alphabetLength).intValueExact();
            alphabetIndexes[position] = remainder;
            value = value.divide(alphabetLength);
        }
        String shortened = Arrays.stream(alphabetIndexes)
                .mapToObj(alphabet::charAt)
                .collect(Collector.of(
                        () -> new StringBuilder(shortenedPrefix),
                        StringBuilder::append,
                        StringBuilder::append,
                        StringBuilder::toString));
        repository.save(new ShortenedUrl(url, shortened));
        return shortened;
    }

    @Override
    public String resolveShortened(String shortenedUrl) {
        return repository.findById(shortenedUrl)
                .map(ShortenedUrl::original)
                .orElseThrow(() -> new IllegalArgumentException("Couldn't find the original URL for " + shortenedUrl));

    }
}
