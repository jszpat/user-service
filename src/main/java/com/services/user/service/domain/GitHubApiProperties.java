package com.services.user.service.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Validated
@Configuration
@ConfigurationProperties("github.api")
class GitHubApiProperties
{
    @NotBlank
    private String url;

    @NotBlank
    private String userDataEndpointPath;

    void setUrl(final String url)
    {
        this.url = url;
    }

    void setUserDataEndpointPath(final String userDataEndpointPath)
    {
        this.userDataEndpointPath = userDataEndpointPath;
    }

    String getUserDataEndpointUrl()
    {
        return url + userDataEndpointPath;
    }
}
