package com.example.springbootcampproject.controller.user;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateResponse {

    private Long id;

    public static UserCreateResponse createResponse(Long id){
        return UserCreateResponse.builder()
                .id(id)
                .build();
    }
}
