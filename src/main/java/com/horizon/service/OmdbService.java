package com.horizon.service;

import com.horizon.entity.OmdbResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class OmdbService {

    @Value("${omdb.api.url}")
    private String apiUrl;

    @Value("${omdb.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public OmdbResponse searchMovieByTitle(String title) {
        String uri = UriComponentsBuilder
                .fromHttpUrl(apiUrl)
                .queryParam("apikey", apiKey)
                .queryParam("t", title)
                .toUriString();

        return restTemplate.getForObject(uri, OmdbResponse.class);
    }
    public OmdbResponse searchMovieById(String omdbId) {
        String uri = UriComponentsBuilder
                .fromHttpUrl(apiUrl)
                .queryParam("apikey", apiKey)
                .queryParam("i", omdbId) // "i" parametresi ID ile arama yapar
                .toUriString();

        return restTemplate.getForObject(uri, OmdbResponse.class);
    }
}
