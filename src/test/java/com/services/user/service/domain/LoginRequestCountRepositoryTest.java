package com.services.user.service.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LoginRequestCountRepositoryTest
{
    private static final String LOGIN = "user-123";

    @Autowired
    private LoginRequestCountRepository loginRequestCountRepository;

    @BeforeEach
    void beforeEach()
    {
        loginRequestCountRepository.deleteAll();
    }

    @Test
    void updateCountByLogin_loginNotPresentInRepository_loginInsertedWithRequestCountSetToOne()
    {
        // given
        final long expectedCount = 1;

        // when
        loginRequestCountRepository.updateCountByLogin(LOGIN);

        // then
        assertLoginRequestCountEqualTo(LOGIN, expectedCount);
    }

    @Test
    void updateCountByLogin_loginPresentInRepository_countIncremented()
    {
        // given
        final LoginRequestCountEntity currentEntity = new LoginRequestCountEntity(LOGIN, 10);
        loginRequestCountRepository.save(currentEntity);
        final long expectedCount = currentEntity.getRequestCount() + 1;

        // when
        loginRequestCountRepository.updateCountByLogin(LOGIN);

        // then
        assertLoginRequestCountEqualTo(currentEntity.getLogin(), expectedCount);
    }

    private void assertLoginRequestCountEqualTo(final String login, final long expectedCount)
    {
        final LoginRequestCountEntity expectedEntity = new LoginRequestCountEntity(login, expectedCount);
        final Optional<LoginRequestCountEntity> loginRequestCountEntity = loginRequestCountRepository.findById(LOGIN);
        assertThat(loginRequestCountEntity).hasValue(expectedEntity);
    }
}
