package com.services.user.service.domain;

import com.services.user.service.api.dto.UserDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class UserDtoCreatorTest
{
    private final UserDtoCreator userDtoCreator = new UserDtoCreator();

    @Test
    void fromGitHubResponse_validUserDtoReturned()
    {
        // given
        final long id = 12345;
        final String login = "user-123";
        final String name = "Jakub";
        final String type = "User";
        final String avatarUrl = "https://avatars.githubusercontent.com/u/59649467?v=4";
        final String createdAt = "2020-01-08T11:41:34Z";
        final long numberOfFollowers = 4;
        final long numberOfPublicRepos = 5;

        final GitHubUserDataDto gitHubUserDataDto = new GitHubUserDataDto(id, login, name, type, avatarUrl, createdAt, numberOfFollowers, numberOfPublicRepos);
        final double expectedCalculations = 10.5; // (6/4 * (2 + 5)) = 10.5

        // when
        final UserDto userDto = userDtoCreator.fromGitHubResponse(gitHubUserDataDto);

        // then
        assertThat(userDto.getId()).isEqualTo(id);
        assertThat(userDto.getLogin()).isEqualTo(login);
        assertThat(userDto.getName()).isEqualTo(name);
        assertThat(userDto.getType()).isEqualTo(type);
        assertThat(userDto.getAvatarUrl()).isEqualTo(avatarUrl);
        assertThat(userDto.getCreatedAt()).isEqualTo(createdAt);
        assertThat(userDto.getCalculations()).isEqualTo(expectedCalculations);
    }

    @Test
    void getCalculationsForGitHubUser_userHasNoFollowers_zeroReturned()
    {
        // given
        final GitHubUserDataDto mockGitHubUserDataDto = mock(GitHubUserDataDto.class);
        doReturn(0L).when(mockGitHubUserDataDto).getNumberOfFollowers();

        // when
        final double calculations = UserDtoCreator.Calculator.getCalculationsForGitHubUser(mockGitHubUserDataDto);

        // then
        assertThat(calculations).isZero();
    }
}
