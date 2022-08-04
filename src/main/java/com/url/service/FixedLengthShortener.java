package com.url.service;

import com.url.service.counter.UniqueValue;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;

public final class FixedLengthShortener implements UrlShortener {

    private final UniqueValue uniqueValue;
    private final String alphabet;
    private final BigInteger alphabetLength;
    private final String shortenedPrefix;
    private final int shortenedSize;

    public FixedLengthShortener(UniqueValue uniqueValue) {
        this.uniqueValue = Objects.requireNonNull(uniqueValue);
        this.alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        this.alphabetLength = BigInteger.valueOf(this.alphabet.length());
        this.shortenedPrefix = "https://s-url/";
        this.shortenedSize = 7;
    }

    @Override
    public String shorten(String url) {
        BigInteger value = uniqueValue.next();
        List<Integer> alphabetIndexes = new LinkedList<>();
        while (value.signum() > 0) {
            Integer remainder = value.mod(alphabetLength).intValueExact();
            alphabetIndexes.add(remainder);
            value = value.divide(alphabetLength);
        }
        while (alphabetIndexes.size() < shortenedSize) {
            alphabetIndexes.add(0);
        }
        return alphabetIndexes.stream()
                .map(alphabet::charAt)
                .collect(Collector.of(
                        () -> new StringBuilder(shortenedPrefix),
                        StringBuilder::append,
                        StringBuilder::append,
                        StringBuilder::toString));
    }

    @Override
    public String resolveShortened(String shortenedUrl) {
        throw new UnsupportedOperationException();
    }
}
