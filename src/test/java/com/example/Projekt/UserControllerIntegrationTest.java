package com.example.Projekt;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.Projekt.model.User;
import com.example.Projekt.model.UserDto;
import com.example.Projekt.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.health.HealthEndpointAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request
        .MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request
        .MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result
        .MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result
        .MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result
        .MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ProjektApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude= {SecurityAutoConfiguration.class, HealthEndpointAutoConfiguration.class, WebEndpointAutoConfiguration.class})
@AutoConfigureTestDatabase
class UserControllerIntegrationTest {
    @Autowired
    MockMvc mvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private String toJson(Object obj) throws IOException{
        return objectMapper.writeValueAsString(obj);


    @Test
    public void whenValidInput_thenCreateUser() throws Exception{
        UserDto user = new UserDto(null, "Jan", "Drewienkowski", "jdrew3081@gmail.com");
        mvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(user)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName", is("Jan")))
                .andExpect(jsonPath("$.id", greaterThanOrEqualTo(1)));
    }
}
