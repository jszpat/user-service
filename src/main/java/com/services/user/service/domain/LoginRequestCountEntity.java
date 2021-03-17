package com.services.user.service.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "LOGIN_REQUEST_COUNT")
class LoginRequestCountEntity
{
    @Id
    @Column(name = "LOGIN")
    private String login;

    @Column(name = "REQUEST_COUNT")
    private long requestCount;

    protected LoginRequestCountEntity()
    {
    }

    LoginRequestCountEntity(final String login, final long requestCount)
    {
        this.login = login;
        this.requestCount = requestCount;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(final String login)
    {
        this.login = login;
    }

    public long getRequestCount()
    {
        return requestCount;
    }

    public void setRequestCount(final long requestCount)
    {
        this.requestCount = requestCount;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof LoginRequestCountEntity))
        {
            return false;
        }

        final LoginRequestCountEntity that = (LoginRequestCountEntity) o;
        return requestCount == that.requestCount && Objects.equals(login, that.login);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(login, requestCount);
    }

    @Override
    public String toString()
    {
        return "LoginRequestCount{" +
                "login='" + login + '\'' +
                ", requestCount=" + requestCount +
                '}';
    }
}
