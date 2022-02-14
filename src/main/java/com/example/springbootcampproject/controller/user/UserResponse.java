package com.example.springbootcampproject.controller.user;

import com.example.springbootcampproject.service.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse {

    private Long id;
    private Long tcNo;
    private String name;
    private String lastName;
    private Integer income;
    private Long phone;

    public static UserResponse createResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .tcNo(user.getTcNo())
                .name(user.getName())
                .lastName(user.getLastName())
                .income(user.getIncome())
                .phone(user.getPhone())
                .build();
    }
}
