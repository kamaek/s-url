package com.url;

import com.url.service.FixedLengthShortener;
import com.url.service.counter.IncrementingInMemoryValue;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FixedLengthShortenerTest {

    @ParameterizedTest
    @MethodSource("successValues")
    void shortenUrlSuccessfully(BigInteger initialValue, String expectedShortenedSuffix) {
        FixedLengthShortener shortener = new FixedLengthShortener(
                new IncrementingInMemoryValue(initialValue)
        );
        String original = "http://www.xplace.com";
        String shortened = shortener.shorten(original);
        assertEquals("https://s-url/" + expectedShortenedSuffix, shortened);
        assertEquals(original, shortener.resolveShortened(shortened));
    }

    private static Stream<Arguments> successValues() {
        return Stream.of(
                Arguments.of(BigInteger.valueOf(-1), "aaaaaaa"),
                Arguments.of(BigInteger.valueOf(Integer.MAX_VALUE), "a"),
                Arguments.of(BigInteger.valueOf(Long.MAX_VALUE), "a")
        );
    }
}