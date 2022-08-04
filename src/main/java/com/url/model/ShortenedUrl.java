package com.url.model;

import java.math.BigDecimal;

// TODO: 04.08.2022 add expiration date
public record ShortenedUrl(BigDecimal shortId, String original, String shortened) {

}
