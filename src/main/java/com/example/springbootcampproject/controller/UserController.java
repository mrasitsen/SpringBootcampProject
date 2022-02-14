package com.example.springbootcampproject.controller;

import com.example.springbootcampproject.controller.user.*;
import com.example.springbootcampproject.service.user.User;
import com.example.springbootcampproject.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/user", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserCreateResponse createUser(@RequestBody @Valid UserCreateRequest userCreateRequest) {
        User user = userCreateRequest.convertToUser();
        Long id = userService.createUser(user);
        return UserCreateResponse.createResponse(id);
    }

    @PatchMapping(value = "/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserUpdateResponse updateUser(@RequestBody UserUpdateRequest userUpdateRequest, @PathVariable Long userId){
        User user = userUpdateRequest.convertToUser();
        User updatedUser = userService.updateUser(user, userId);
        return UserUpdateResponse.createResponse(updatedUser);
    }

    @DeleteMapping(value = "/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }

    @GetMapping(value = "/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse retrieveUser(@PathVariable Long userId){
        User user = userService.retrieveUser(userId);
        return UserResponse.createResponse(user);
    }

}


