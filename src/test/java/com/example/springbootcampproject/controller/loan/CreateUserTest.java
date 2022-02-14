package com.example.springbootcampproject.controller.loan;


import com.example.springbootcampproject.SpringBootcampProjectApplicationTests;
import com.example.springbootcampproject.controller.user.UserCreateRequest;
import com.example.springbootcampproject.controller.user.UserCreateResponse;
import com.example.springbootcampproject.repository.user.UserEntity;
import com.example.springbootcampproject.repository.user.UserJpaRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateUserTest extends SpringBootcampProjectApplicationTests {

    @Autowired
    UserJpaRepository userJpaRepository;

    @AfterAll
    @Sql(scripts = {"/cleanup.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    static void afterAll() {

    }

    @Test
    void should_create_user() {

        //given
        UserCreateRequest userCreateRequest = new UserCreateRequest();
        userCreateRequest.setTcNo(8374957L);
        userCreateRequest.setName("Serkan");
        userCreateRequest.setLastName("Ates");
        userCreateRequest.setIncome(3950);
        userCreateRequest.setPhone(5559009090L);

        //when
        ResponseEntity<UserCreateResponse> response = testRestTemplate.postForEntity("/user", userCreateRequest, UserCreateResponse.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull();

        Optional<UserEntity> userEntity = userJpaRepository.findById(response.getBody().getId());
        assertThat(userEntity.isPresent()).isTrue();
        assertThat(userEntity.get()).extracting("tcNo", "name", "lastName", "income", "phone")
                .containsExactly(8374957L, "Serkan", "Ates", 3950, 5559009090L);


    }
}
