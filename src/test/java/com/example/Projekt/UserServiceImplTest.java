package com.example.Projekt;

import com.example.Projekt.simple.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


class UserServiceImplTest {
    User user;

    @BeforeEach
    void init(){
        user = new User(0L,"Jan", "Drewienkowski", "jdrew@gmail.com");
    }

    @Test
    void testSameId(){
        User user2 = new User();
        user2.setId(0L);
        assertEquals(user2.getId(), user.getId());
    }

    @RepeatedTest(3)
    void testNotSameName(){
        User user2 = new User(1L, "Ania", "Kowalczyk", "kowal@gmail.com");
        assertNotEquals(user.getName(), user2.getName());
    }

    @Test
    void testSamePerson(){
        User user2 = new User();
        user2.setId(0L);
        user2.setEmail("jdrew@gmail.com");
        user2.setName("Tomasz");
        user2.setSurname("Drewienkowski");
        assertAll("Are they the same?",
                ()-> assertEquals(user.getId(), user2.getId()),
        ()-> assertEquals(user.getEmail(), user2.getEmail()),
                ()-> assertEquals(user.getSurname(), user2.getSurname()),
                ()-> assertNotEquals(user.getName(), user2.getName())
        );
    }
}
