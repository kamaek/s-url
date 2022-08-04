package com.url;

import com.url.repository.InMemoryShortenedUrlRepository;
import com.url.service.FixedLengthShortener;
import com.url.service.counter.IncrementingInMemoryValue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FixedLengthShortenerTest {

    @Test
    void shortenSameUrlToDifferentValues() {
        FixedLengthShortener shortener = new FixedLengthShortener(
                new IncrementingInMemoryValue(),
                new InMemoryShortenedUrlRepository()
        );
        String original = "http://www.xplace.com";
        String shortenedFirstTime = shortener.shorten(original);
        String shortenedSecondTime = shortener.shorten(original);
        assertNotEquals(shortenedFirstTime, shortenedSecondTime);
    }

    @ParameterizedTest
    @MethodSource("successValues")
    void shortenUrlSuccessfully(BigInteger initialValue, String expectedShortenedSuffix) {
        FixedLengthShortener shortener = new FixedLengthShortener(
                new IncrementingInMemoryValue(initialValue),
                new InMemoryShortenedUrlRepository()
        );
        String original = "http://www.xplace.com";
        String shortened = shortener.shorten(original);
        assertEquals("https://s-url/" + expectedShortenedSuffix, shortened);
        assertEquals(original, shortener.resolveShortened(shortened));
    }

    @Test
    void failIfRanOutOfShortenedValues() {
        FixedLengthShortener shortener = new FixedLengthShortener(
                new IncrementingInMemoryValue(BigInteger.valueOf(Long.MAX_VALUE)),
                new InMemoryShortenedUrlRepository()
        );
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> shortener.shorten("x")
        );
        assertEquals("Cannot encode 9223372036854775808 using 7 symbols", exception.getMessage());
    }

    private static Stream<Arguments> successValues() {
        return Stream.of(
                Arguments.of(BigInteger.valueOf(-1), "aaaaaaa"),
                Arguments.of(BigInteger.valueOf(Integer.MAX_VALUE), "cLMuvca")
        );
    }
}