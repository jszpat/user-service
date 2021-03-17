package com.services.user.service.domain;

import java.util.Objects;

class LoginRequestedEvent
{
    private final String login;

    LoginRequestedEvent(final String login)
    {
        this.login = login;
    }

    String getLogin()
    {
        return login;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof LoginRequestedEvent))
        {
            return false;
        }

        final LoginRequestedEvent that = (LoginRequestedEvent) o;
        return Objects.equals(login, that.login);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(login);
    }

    @Override
    public String toString()
    {
        return "LoginRequestedEvent{" +
                "login='" + login + '\'' +
                '}';
    }
}
