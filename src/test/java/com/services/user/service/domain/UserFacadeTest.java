package com.services.user.service.domain;

import com.services.user.service.api.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class UserFacadeTest
{
    private static final String LOGIN = "user-123";

    private final GitHubUserDataProvider mockGitHubUserDataProvider = mock(GitHubUserDataProvider.class);

    private final UserDtoCreator mockUserDtoCreator = mock(UserDtoCreator.class);

    private final ApplicationEventPublisher mockEventPublisher = mock(ApplicationEventPublisher.class);

    private final UserFacade userFacade = new UserFacade(mockGitHubUserDataProvider,
                                                         mockUserDtoCreator,
                                                         mockEventPublisher);

    @Test
    void getUserByLogin_userFound_userReturned()
    {
        // given
        final GitHubUserDataDto mockGitHubUserDataDto = mock(GitHubUserDataDto.class);
        doReturn(Optional.of(mockGitHubUserDataDto)).when(mockGitHubUserDataProvider).getUserDataByLogin(LOGIN);

        final UserDto mockUserDto = mock(UserDto.class);
        doReturn(mockUserDto).when(mockUserDtoCreator).fromGitHubResponse(mockGitHubUserDataDto);

        // when
        final Optional<UserDto> user = userFacade.getUserByLogin(LOGIN);

        // then
        assertThat(user).hasValue(mockUserDto);
    }

    @Test
    void getUserByLogin_userFound_loginRequestedEventPublished()
    {
        // given
        final GitHubUserDataDto mockGitHubUserDataDto = mock(GitHubUserDataDto.class);
        doReturn(LOGIN).when(mockGitHubUserDataDto).getLogin();
        doReturn(Optional.of(mockGitHubUserDataDto)).when(mockGitHubUserDataProvider).getUserDataByLogin(LOGIN);

        final UserDto mockUserDto = mock(UserDto.class);
        doReturn(mockUserDto).when(mockUserDtoCreator).fromGitHubResponse(mockGitHubUserDataDto);

        // when
        userFacade.getUserByLogin(LOGIN);

        // then
        verify(mockEventPublisher).publishEvent(new LoginRequestedEvent(LOGIN));
    }

    @Test
    void getUserByLogin_userNotFound_noUserReturned()
    {
        // given
        doReturn(Optional.empty()).when(mockGitHubUserDataProvider).getUserDataByLogin(LOGIN);

        // when
        final Optional<UserDto> user = userFacade.getUserByLogin(LOGIN);

        // then
        assertThat(user).isEmpty();
    }

    @Test
    void getUserByLogin_userNotFound_loginRequestedEventNotPublished()
    {
        // given
        doReturn(Optional.empty()).when(mockGitHubUserDataProvider).getUserDataByLogin(LOGIN);

        // when
        userFacade.getUserByLogin(LOGIN);

        // then
        verify(mockEventPublisher, never()).publishEvent(any(LoginRequestedEvent.class));
    }
}
