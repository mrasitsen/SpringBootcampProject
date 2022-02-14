package com.example.springbootcampproject.controller.loan;

import com.example.springbootcampproject.SpringBootcampProjectApplicationTests;
import com.example.springbootcampproject.controller.user.UserCreateRequest;
import com.example.springbootcampproject.controller.user.UserCreateResponse;
import com.example.springbootcampproject.controller.user.UserUpdateRequest;
import com.example.springbootcampproject.controller.user.UserUpdateResponse;
import com.example.springbootcampproject.repository.user.UserEntity;
import com.example.springbootcampproject.repository.user.UserJpaRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class UpdateUserTest extends SpringBootcampProjectApplicationTests {

    @Autowired
    UserJpaRepository userJpaRepository;

    @AfterAll
    @Sql(scripts = {"/cleanup.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    static void afterAll() {

    }

    @Test
    void should_update_user(){

        //given
        UserCreateRequest userCreateRequest = new UserCreateRequest();
        userCreateRequest.setTcNo(8374957L);
        userCreateRequest.setName("Serkan");
        userCreateRequest.setLastName("Ates");
        userCreateRequest.setIncome(3950);
        userCreateRequest.setPhone(5559009090L);

        ResponseEntity<UserCreateResponse> createResponse = testRestTemplate.postForEntity("/user", userCreateRequest, UserCreateResponse.class);

        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(createResponse.getBody()).isNotNull();
        assertThat(createResponse.getBody().getId()).isNotNull();

        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
        userUpdateRequest.setName("Kemal");
        userUpdateRequest.setLastName("");
        userUpdateRequest.setPhone(5509009090L);

        HttpEntity<UserUpdateRequest> requestEntity = new HttpEntity<>(userUpdateRequest);

        //when
        String url = "/user/"+createResponse.getBody().getId();

        ResponseEntity<UserUpdateResponse> updateResponse = testRestTemplate
                .exchange(url, HttpMethod.PUT, requestEntity, UserUpdateResponse.class);


        //then
        assertThat(updateResponse.getBody()).isNotNull();

        assertThat(updateResponse.getBody().getId()).isEqualTo(createResponse.getBody().getId());
        assertThat(updateResponse.getBody().getTcNo()).isEqualTo(8374957L);
        assertThat(updateResponse.getBody().getName()).isEqualTo("Kemal");
        assertThat(updateResponse.getBody().getLastName()).isEqualTo("Ates");
        assertThat(updateResponse.getBody().getIncome()).isEqualTo(3950);
        assertThat(updateResponse.getBody().getPhone()).isEqualTo(5509009090L);
//

        Optional<UserEntity> userEntity = userJpaRepository.findById(updateResponse.getBody().getId());

        assertThat(userEntity.get()).extracting("id", "tcNo", "name", "lastName", "income", "phone")
                .containsExactly(createResponse.getBody().getId(), 8374957L, "Kemal", "Ates", 3950, 5509009090L);
        
    }
}
