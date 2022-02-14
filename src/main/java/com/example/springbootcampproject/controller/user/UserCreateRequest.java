package com.example.springbootcampproject.controller.user;

import com.example.springbootcampproject.service.user.User;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {

    @NotNull
    private Long tcNo;

    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    @NotNull
    private Integer income;

    @NotNull
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
