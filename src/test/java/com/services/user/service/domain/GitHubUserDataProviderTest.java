package com.services.user.service.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

class GitHubUserDataProviderTest
{
    private static final String USER_DATA_ENDPOINT_URL = "http://api.github.com/users/";

    private static final String LOGIN = "user-123";

    private final GitHubApiProperties mockGitHubApiProperties = mock(GitHubApiProperties.class);

    private final RestTemplateBuilder mockRestTemplateBuilder = mock(RestTemplateBuilder.class);

    private final RestTemplate mockRestTemplate = mock(RestTemplate.class);

    private GitHubUserDataProvider gitHubUserDataProvider;

    @BeforeEach
    void setUp()
    {
        doReturn(mockRestTemplate).when(mockRestTemplateBuilder).build();
        doReturn(USER_DATA_ENDPOINT_URL).when(mockGitHubApiProperties).getUserDataEndpointUrl();
        gitHubUserDataProvider = new GitHubUserDataProvider(mockGitHubApiProperties, mockRestTemplateBuilder);
    }

    @Test
    void getUserDataByLogin_successfulExternalApiCall_userDataReturned()
    {
        // given
        final GitHubUserDataDto mockGitHubUserDataDto = mock(GitHubUserDataDto.class);
        doReturn(ResponseEntity.ok(mockGitHubUserDataDto)).when(mockRestTemplate).exchange(any(), eq(GitHubUserDataDto.class));

        // when
        final Optional<GitHubUserDataDto> userData = gitHubUserDataProvider.getUserDataByLogin(LOGIN);

        // then
        assertThat(userData).hasValue(mockGitHubUserDataDto);
    }

    @Test
    void getUserDataByLogin_404NotFound_noUserDataReturned()
    {
        // given
        doThrow(HttpClientErrorException.NotFound.class).when(mockRestTemplate).exchange(any(), eq(GitHubUserDataDto.class));

        // when
        final Optional<GitHubUserDataDto> userData = gitHubUserDataProvider.getUserDataByLogin(LOGIN);

        // then
        assertThat(userData).isEmpty();
    }
}
