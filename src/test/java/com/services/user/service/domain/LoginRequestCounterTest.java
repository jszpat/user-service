package com.services.user.service.domain;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class LoginRequestCounterTest
{
    private static final String LOGIN = "user-123";

    private final LoginRequestCountRepository mockRepository = mock(LoginRequestCountRepository.class);

    private final LoginRequestCounter loginRequestCounter = new LoginRequestCounter(mockRepository);

    @Test
    void onLoginRequest_countUpdatedInRepository()
    {
        // given
        final LoginRequestCountEntity entity = new LoginRequestCountEntity(LOGIN, 5);
        doReturn(Optional.of(entity)).when(mockRepository).findById(LOGIN);
        final LoginRequestedEvent loginRequestedEvent = new LoginRequestedEvent(LOGIN);

        // when
        loginRequestCounter.onLoginRequest(loginRequestedEvent);

        // then
        verify(mockRepository).updateCountByLogin(LOGIN);
    }
}
