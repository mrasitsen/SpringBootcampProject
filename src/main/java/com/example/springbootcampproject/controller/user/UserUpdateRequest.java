package com.example.springbootcampproject.controller.user;

import com.example.springbootcampproject.service.user.User;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {

    private Long tcNo;
    private String name;
    private String lastName;
    private Integer income;
    private Long phone;

    public User convertToUser(){
        return User.builder()
                .tcNo(tcNo)
                .name(name)
                .lastName(lastName)
                .income(income)
                .phone(phone)
                .build();
    }
}
