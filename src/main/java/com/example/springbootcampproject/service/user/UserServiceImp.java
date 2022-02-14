package com.example.springbootcampproject.service.user;

import com.example.springbootcampproject.repository.user.UserDao;
import com.example.springbootcampproject.repository.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService{

    private final UserDao userDao;

    @Override
    public Long createUser(User user) {
        UserEntity userEntity = user.convertToUserEntity();
        Long id = userDao.createUser(userEntity);
        return id;
    }

    @Override
    public User updateUser(User user, Long id) {
        UserEntity currentUser = userDao.retrieveUser(id);

        UserEntity updatedUserEntity = updateEntity(currentUser, user);

        UserEntity userEntity = userDao.updateUser(updatedUserEntity);
        User updatedUser = User.ConvertFromUserEntity(userEntity);
        return updatedUser;
    }

    private UserEntity updateEntity(UserEntity currentEntity, User updatedUser){

        /*
        This function makes the validation for the updated user data
        It updates the data given, does not change the not given.
         */

        if(updatedUser.getTcNo() != null){
            currentEntity.setTcNo(updatedUser.getTcNo());
        }

        if(!Objects.equals(updatedUser.getName(), "") && updatedUser.getName() != null){
            currentEntity.setName(updatedUser.getName());
        }

        if(!Objects.equals(updatedUser.getLastName(), "") && updatedUser.getLastName() != null){
            currentEntity.setLastName(updatedUser.getLastName());
        }

        if(updatedUser.getIncome() != null){
            currentEntity.setIncome(updatedUser.getIncome());
        }

        if(updatedUser.getPhone() != null){
            currentEntity.setPhone(updatedUser.getPhone());
        }

        return currentEntity;
    }

    public void deleteUser(Long id){
        userDao.deleteUser(id);
    }

    @Override
    public User retrieveUser(Long id) {
        UserEntity userEntity = userDao.retrieveUser(id);
        User user = User.ConvertFromUserEntity(userEntity);
        return user;
    }
}
