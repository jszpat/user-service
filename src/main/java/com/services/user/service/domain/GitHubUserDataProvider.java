package com.services.user.service.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
class GitHubUserDataProvider
{
    static final String ACCEPT_HEADER_VALUE = "application/vnd.github.v3+json";

    private static final Logger LOG = LogManager.getLogger(GitHubUserDataProvider.class);

    private final GitHubApiProperties gitHubApiProperties;

    private final RestTemplate restTemplate;

    GitHubUserDataProvider(final GitHubApiProperties gitHubApiProperties, final RestTemplateBuilder restTemplateBuilder)
    {
        this.gitHubApiProperties = gitHubApiProperties;
        this.restTemplate = restTemplateBuilder.build();
    }

    Optional<GitHubUserDataDto> getUserDataByLogin(final String login)
    {
        final String url = gitHubApiProperties.getUserDataEndpointUrl() + login;
        final RequestEntity<Void> requestEntity = RequestEntity.get(url)
                                                               .header(HttpHeaders.ACCEPT, ACCEPT_HEADER_VALUE)
                                                               .build();

        try
        {
            final ResponseEntity<GitHubUserDataDto> responseEntity = restTemplate.exchange(requestEntity, GitHubUserDataDto.class);
            final GitHubUserDataDto gitHubUserDataDto = responseEntity.getBody();
            LOG.debug("GitHub API response for login {}: {}", login, gitHubUserDataDto);

            return Optional.ofNullable(gitHubUserDataDto);
        }
        catch (final HttpClientErrorException.NotFound e)
        {
            LOG.debug("GitHub user with login {} not found", login);
            return Optional.empty();
        }
    }
}
