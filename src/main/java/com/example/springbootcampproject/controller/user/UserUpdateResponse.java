package com.example.springbootcampproject.controller.user;

import com.example.springbootcampproject.service.user.User;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateResponse {

    private Long id;
    private Long tcNo;
    private String name;
    private String lastName;
    private Integer income;
    private Long phone;

    public static UserUpdateResponse createResponse(User user){
        return UserUpdateResponse.builder()
                .id(user.getId())
                .tcNo(user.getTcNo())
                .name(user.getName())
                .lastName(user.getLastName())
                .income(user.getIncome())
                .phone(user.getPhone())
                .build();
    }

}
