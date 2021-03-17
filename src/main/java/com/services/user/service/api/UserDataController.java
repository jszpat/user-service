package com.services.user.service.api;

import com.services.user.service.api.dto.UserDto;
import com.services.user.service.domain.UserFacade;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserDataController
{
    private final UserFacade userFacade;

    public UserDataController(final UserFacade userFacade)
    {
        this.userFacade = userFacade;
    }

    /**
     * Endpoint that returns user data for a given login
     *
     * @param login user login
     *
     * @return user data for a given login, 404 if there is no user with a given login
     */
    @GetMapping(value = "/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUserDataByLogin(@PathVariable final String login)
    {
        return ResponseEntity.of(userFacade.getUserByLogin(login));
    }
}
