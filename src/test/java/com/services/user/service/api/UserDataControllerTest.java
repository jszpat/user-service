package com.services.user.service.api;

import com.services.user.service.api.dto.UserDto;
import com.services.user.service.domain.UserFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserDataController.class)
class UserDataControllerTest
{
    private static final String LOGIN = "user-123";

    private static final String USER_ENDPOINT_URL_TEMPLATE = "/users/{login}";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserFacade mockUserFacade;

    @Test
    void getUserDataByLogin_userFound_userReturned() throws Exception
    {
        // given
        final UserDto userDto = new UserDto.Builder().withId(12345)
                                                     .withLogin(LOGIN)
                                                     .withName("Jakub")
                                                     .withType("User")
                                                     .withAvatarUrl("https://avatars.githubusercontent.com/u/59649467?v=4")
                                                     .withCreatedAt("2020-01-08T11:41:34Z")
                                                     .withCalculations(25.0)
                                                     .build();

        doReturn(Optional.of(userDto)).when(mockUserFacade).getUserByLogin(LOGIN);

        // when
        final ResultActions resultActions = mockMvc.perform(get(USER_ENDPOINT_URL_TEMPLATE, LOGIN));

        // then
        try(InputStream in = UserDataControllerTest.class.getResourceAsStream("/json/expected-user-data.json"))
        {
            final String expectedJson = new String(in.readAllBytes(), StandardCharsets.UTF_8);
            resultActions.andExpect(status().isOk()).andExpect(status().isOk()).andExpect(content().json(expectedJson));
        }
    }

    @Test
    void getUserDataByLogin_userNotFound_NotFound404CodeReturned() throws Exception
    {
        // given
        doReturn(Optional.empty()).when(mockUserFacade).getUserByLogin(LOGIN);

        // when
        final ResultActions resultActions = mockMvc.perform(get(USER_ENDPOINT_URL_TEMPLATE, LOGIN));

        // then
        resultActions.andExpect(status().isNotFound());
    }
}
