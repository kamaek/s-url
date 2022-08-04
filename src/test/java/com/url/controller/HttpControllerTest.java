package com.url.controller;

import com.url.service.UrlShortener;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HttpController.class)
class HttpControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UrlShortener shortener;

    @Test
    void handleShorteningRequestSuccessfully() throws Exception {
        String originalUrl = "google.com/maps";
        String shortenedUrlMock = "g.com/m";
        when(shortener.shorten(originalUrl)).thenReturn(shortenedUrlMock);

        MockHttpServletRequestBuilder request = post("/shorten")
                .content("{\"url\": \"google.com/maps\"}")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(shortenedUrlMock));
    }

    @Test
    void handleResolveRequestSuccessfully() throws Exception {
        String originalUrl = "google.com/maps";
        String shortenedUrl = "g.com/m";
        when(shortener.resolveShortened(shortenedUrl)).thenReturn(originalUrl);

        MockHttpServletRequestBuilder request = post("/resolve")
                .content("{\"shortened\": \"g.com/m\"}")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(originalUrl));
    }
}