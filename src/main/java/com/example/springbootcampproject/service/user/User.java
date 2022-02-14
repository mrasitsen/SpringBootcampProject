package com.example.springbootcampproject.service.user;

import com.example.springbootcampproject.repository.user.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class User {

    private Long id;

    private Long tcNo;

    private String name;

    private String lastName;

    private Integer income;

    private Long phone;

    public UserEntity convertToUserEntity(){
        return UserEntity.builder()
                .tcNo(tcNo)
                .name(name)
                .lastName(lastName)
                .income(income)
                .phone(phone)
                .build();
    }

    public static User ConvertFromUserEntity(UserEntity userEntity){
        return User.builder()
                .id(userEntity.getId())
                .tcNo(userEntity.getTcNo())
                .name(userEntity.getName())
                .lastName(userEntity.getLastName())
                .income(userEntity.getIncome())
                .phone(userEntity.getPhone())
                .build();
    }

}
