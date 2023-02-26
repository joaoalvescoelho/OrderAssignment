package com.example.orderassignment.user;

import com.example.orderassignment.endpoints.UsersEndpoint;
import com.example.orderassignment.entity.Users;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDataApi {

    private final WebClient client = WebClient.create();

    public List<Users> getUserDataApi() throws JsonProcessingException {

        List<Users> users = new ArrayList<>();

        // page 1
        WebClient.ResponseSpec responseSpec = client.get()
                .uri(UsersEndpoint.USERS_LIST_PER_PAGE + 1)
                .retrieve()
                ;
        String responseBody = responseSpec.bodyToMono(String.class).block();
        ObjectMapper mapper = new ObjectMapper();
        Users usersPage = mapper.readValue(responseBody, Users.class);
        users.add(usersPage);

        //page 2
        responseSpec = client.get()
                .uri(UsersEndpoint.USERS_LIST_PER_PAGE + 2)
                .retrieve();
        responseBody = responseSpec.bodyToMono(String.class).block();
        usersPage = mapper.readValue(responseBody, Users.class);
        users.add(usersPage);

        return users;
    }
}
