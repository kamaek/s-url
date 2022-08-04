package com.url.controller;

import com.url.service.UrlShortener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HttpController {

    private final UrlShortener shortener;

    @Autowired
    public HttpController(UrlShortener shortener) {
        this.shortener = shortener;
    }

    @PostMapping(path = "/shorten")
    public ResponseEntity<String> shorten(@RequestBody ShortenUrlRequest request) {
        String shortened = shortener.shorten(request.url());
        return ResponseEntity.ok(shortened);
    }

    @PostMapping("/resolve")
    public ResponseEntity<String> resolve(@RequestBody ResolveUrlRequest request) {
        String originalUrl = shortener.resolveShortened(request.shortened());
        return ResponseEntity.ok(originalUrl);
    }
}
