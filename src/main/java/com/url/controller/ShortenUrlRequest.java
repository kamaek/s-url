package com.url.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ShortenUrlRequest(@JsonProperty String url) {
}
