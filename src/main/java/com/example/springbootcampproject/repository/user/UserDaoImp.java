package com.example.springbootcampproject.repository.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDaoImp implements UserDao{

    private final UserJpaRepository userJpaRepository;

    @Override
    public Long createUser(UserEntity userEntity) {
        UserEntity createdUserEntity = userJpaRepository.save(userEntity);
        return createdUserEntity.getId();
    }

    @Override
    public UserEntity retrieveUser(Long id) {
        Optional<UserEntity> userEntity = userJpaRepository.findById(id);
        if(userEntity.isPresent()){
            return userEntity.get();
        }else {
            throw new RuntimeException("No such user with that id " + id);
        }
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity) {
        UserEntity updatedUserEntity = userJpaRepository.save(userEntity);
        return updatedUserEntity;
    }

    @Override
    public void deleteUser(Long id) {
        userJpaRepository.deleteById(id);
    }
}
