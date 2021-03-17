package com.services.user.service.api.dto;

import java.util.Objects;

public class UserDto
{
    private final long id;

    private final String login;

    private final String name;

    private final String type;

    private final String avatarUrl;

    private final String createdAt;

    private final double calculations;

    private UserDto(final Builder builder)
    {
        this.id = builder.id;
        this.login = builder.login;
        this.name = builder.name;
        this.type = builder.type;
        this.avatarUrl = builder.avatarUrl;
        this.createdAt = builder.createdAt;
        this.calculations = builder.calculations;
    }

    public long getId()
    {
        return id;
    }

    public String getLogin()
    {
        return login;
    }

    public String getName()
    {
        return name;
    }

    public String getType()
    {
        return type;
    }

    public String getAvatarUrl()
    {
        return avatarUrl;
    }

    public String getCreatedAt()
    {
        return createdAt;
    }

    public double getCalculations()
    {
        return calculations;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof UserDto))
        {
            return false;
        }

        final UserDto userDto = (UserDto) o;
        return id == userDto.id && Double.compare(userDto.calculations, calculations) == 0 && Objects.equals(login, userDto.login) && Objects.equals(name, userDto.name) && Objects.equals(type, userDto.type) && Objects.equals(avatarUrl, userDto.avatarUrl) && Objects.equals(createdAt, userDto.createdAt);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, login, name, type, avatarUrl, createdAt, calculations);
    }

    @Override
    public String toString()
    {
        return "UserDto{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", calculations=" + calculations +
                '}';
    }

    public static class Builder
    {
        private Long id;

        private String login;

        private String name;

        private String type;

        private String avatarUrl;

        private String createdAt;

        private Double calculations;

        public Builder withId(final long id)
        {
            this.id = id;
            return this;
        }

        public Builder withLogin(final String login)
        {
            this.login = login;
            return this;
        }

        public Builder withName(final String name)
        {
            this.name = name;
            return this;
        }

        public Builder withType(final String type)
        {
            this.type = type;
            return this;
        }

        public Builder withAvatarUrl(final String avatarUrl)
        {
            this.avatarUrl = avatarUrl;
            return this;
        }

        public Builder withCreatedAt(final String createdAt)
        {
            this.createdAt = createdAt;
            return this;
        }

        public Builder withCalculations(final double calculations)
        {
            this.calculations = calculations;
            return this;
        }

        public UserDto build()
        {
            if (id == null || login == null || name == null || type == null || avatarUrl == null || createdAt == null || calculations == null)
            {
                throw new IllegalArgumentException("Cannot build, not all required parameters set: " + this);
            }

            return new UserDto(this);
        }

        @Override
        public String toString()
        {
            return "Builder{" +
                    "id=" + id +
                    ", login='" + login + '\'' +
                    ", name='" + name + '\'' +
                    ", type='" + type + '\'' +
                    ", avatarUrl='" + avatarUrl + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", calculations=" + calculations +
                    '}';
        }
    }
}
