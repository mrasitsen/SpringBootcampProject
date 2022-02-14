package com.example.springbootcampproject.repository.user;

public interface UserDao {

    Long createUser(UserEntity userEntity);

    UserEntity retrieveUser(Long id);

    UserEntity updateUser(UserEntity userEntity);

    void deleteUser(Long id);

}
