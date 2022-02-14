package com.example.springbootcampproject.service.user;

public interface UserService {

    Long createUser(User user);

    User updateUser(User user, Long id);

    void deleteUser(Long id);

    User retrieveUser(Long id);
}
