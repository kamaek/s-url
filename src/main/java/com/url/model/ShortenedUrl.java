package com.url.model;

import org.springframework.data.annotation.Id;

public record ShortenedUrl(String original, @Id String shortened) {

}
