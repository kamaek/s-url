package com.url.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ResolveUrlRequest(@JsonProperty String shortened) {
}
