package com.services.user.service.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GitHubApiPropertiesTest
{
    private final GitHubApiProperties gitHubApiProperties = new GitHubApiProperties();

    @Test
    void getUserDataEndpointUrl_concatenationOfGitHubUrlAndUserDataEndpointPathReturned()
    {
        // given
        final String gitHubUrl = "https://api.github.com";
        final String userDataEndpointPath = "/users/";
        gitHubApiProperties.setUrl(gitHubUrl);
        gitHubApiProperties.setUserDataEndpointPath(userDataEndpointPath);

        // when
        final String userDataEndpointUrl = gitHubApiProperties.getUserDataEndpointUrl();

        // then
        assertThat(userDataEndpointUrl).isEqualTo(gitHubUrl + userDataEndpointPath);
    }
}
