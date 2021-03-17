package com.services.user.service.domain;

import com.services.user.service.api.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
class UserDtoCreator
{
    UserDto fromGitHubResponse(final GitHubUserDataDto gitHubUserData)
    {
        final double calculations = Calculator.getCalculationsForGitHubUser(gitHubUserData);

        return new UserDto.Builder().withId(gitHubUserData.getId())
                                    .withName(gitHubUserData.getName())
                                    .withLogin(gitHubUserData.getLogin())
                                    .withType(gitHubUserData.getType())
                                    .withAvatarUrl(gitHubUserData.getAvatarUrl())
                                    .withCreatedAt(gitHubUserData.getCreatedAt())
                                    .withCalculations(calculations)
                                    .build();
    }

    static class Calculator
    {
        static double getCalculationsForGitHubUser(final GitHubUserDataDto gitHubUserData)
        {
            final long numberOfFollowers = gitHubUserData.getNumberOfFollowers();
            if (numberOfFollowers == 0)
            {
                return 0;
            }

            return 6.0 / numberOfFollowers * (2 + gitHubUserData.getNumberOfPublicRepositories());
        }
    }
}
