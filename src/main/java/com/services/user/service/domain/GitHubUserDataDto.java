package com.services.user.service.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

class GitHubUserDataDto
{
    private final long id;

    private final String login;

    private final String name;

    private final String type;

    private final String avatarUrl;

    private final String createdAt;

    private final long numberOfFollowers;

    private final long numberOfPublicRepositories;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    GitHubUserDataDto(@JsonProperty("id") final Long id,
                      @JsonProperty("login") final String login,
                      @JsonProperty("name") final String name,
                      @JsonProperty("type") final String type,
                      @JsonProperty("avatar_url") final String avatarUrl,
                      @JsonProperty("created_at") final String createdAt,
                      @JsonProperty("followers") final Long numberOfFollowers,
                      @JsonProperty("public_repos") final Long numberOfPublicRepositories)
    {
        this.id = id;
        this.login = login;
        this.name = name;
        this.type = type;
        this.avatarUrl = avatarUrl;
        this.createdAt = createdAt;
        this.numberOfFollowers = numberOfFollowers;
        this.numberOfPublicRepositories = numberOfPublicRepositories;
    }

    long getId()
    {
        return id;
    }

    String getLogin()
    {
        return login;
    }

    String getName()
    {
        return name;
    }

    String getType()
    {
        return type;
    }

    String getAvatarUrl()
    {
        return avatarUrl;
    }

    String getCreatedAt()
    {
        return createdAt;
    }

    long getNumberOfFollowers()
    {
        return numberOfFollowers;
    }

    long getNumberOfPublicRepositories()
    {
        return numberOfPublicRepositories;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof GitHubUserDataDto))
        {
            return false;
        }

        final GitHubUserDataDto that = (GitHubUserDataDto) o;
        return id == that.id && numberOfFollowers == that.numberOfFollowers && numberOfPublicRepositories == that.numberOfPublicRepositories && Objects.equals(login, that.login) && Objects.equals(name, that.name) && Objects.equals(type, that.type) && Objects.equals(avatarUrl, that.avatarUrl) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, login, name, type, avatarUrl, createdAt, numberOfFollowers, numberOfPublicRepositories);
    }

    @Override
    public String toString()
    {
        return "GitHubUserDataDto{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", numberOfFollowers=" + numberOfFollowers +
                ", numberOfPublicRepositories=" + numberOfPublicRepositories +
                '}';
    }
}
