package com.services.user.service.domain;

import com.services.user.service.api.dto.UserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserFacade
{
    private static final Logger LOG = LogManager.getLogger(UserFacade.class);

    private final GitHubUserDataProvider gitHubUserDataProvider;

    private final UserDtoCreator userDtoCreator;

    private final ApplicationEventPublisher eventPublisher;

    public UserFacade(final GitHubUserDataProvider gitHubUserDataProvider,
                      final UserDtoCreator userDtoCreator,
                      final ApplicationEventPublisher eventPublisher)
    {
        this.gitHubUserDataProvider = gitHubUserDataProvider;
        this.userDtoCreator = userDtoCreator;
        this.eventPublisher = eventPublisher;
    }

    public Optional<UserDto> getUserByLogin(final String login)
    {
        LOG.debug("About to request user data for login: {}", login);
        final Optional<GitHubUserDataDto> userData = gitHubUserDataProvider.getUserDataByLogin(login);
        userData.ifPresent(user -> eventPublisher.publishEvent(new LoginRequestedEvent(login)));

        return userData.map(userDtoCreator::fromGitHubResponse);
    }
}
