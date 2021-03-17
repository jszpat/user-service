package com.services.user.service.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
class LoginRequestCounter
{
    private static final Logger LOG = LogManager.getLogger(LoginRequestCounter.class);

    private final LoginRequestCountRepository requestCountRepository;

    LoginRequestCounter(final LoginRequestCountRepository requestCountRepository)
    {
        this.requestCountRepository = requestCountRepository;
    }

    @EventListener
    public synchronized void onLoginRequest(final LoginRequestedEvent loginRequestedEvent)
    {
        final String login = loginRequestedEvent.getLogin();
        requestCountRepository.updateCountByLogin(login);
        LOG.debug("Request count successfully updated for login {}", login);
    }
}
