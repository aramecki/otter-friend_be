package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


@JsonTest
public class UserControllerTest {

    @Autowired
    private JacksonTester<User> json;

    @Test
    void createUserTest() throws IOException {
        User user = new User("Nome", "Cognome", "1111-11-11", "red", "nome@email.com", "ciao");
        assertThat(json.write(user)).isStrictlyEqualToJson("expected.json");
        assertThat(json.write(user)).hasJsonPathStringValue("@.firstName");
        assertThat(json.write(user)).extractingJsonPathStringValue("@.firstName")
                .isEqualTo("Nome");
        assertThat(json.write(user)).hasJsonPathStringValue("@.lastName");
        assertThat(json.write(user)).extractingJsonPathStringValue("@.lastName")
                .isEqualTo("Cognome");
        assertThat(json.write(user)).hasJsonPathStringValue("@.birth");
        assertThat(json.write(user)).extractingJsonPathStringValue("@.birth")
                .isEqualTo("1111-11-11");
        assertThat(json.write(user)).hasJsonPathStringValue("@.cardType");
        assertThat(json.write(user)).extractingJsonPathStringValue("@.cardType")
                .isEqualTo("red");
        assertThat(json.write(user)).hasJsonPathStringValue("@.email");
        assertThat(json.write(user)).extractingJsonPathStringValue("@.email")
                .isEqualTo("nome@email.com");
        assertThat(json.write(user)).hasJsonPathStringValue("@.password");
        assertThat(json.write(user)).extractingJsonPathStringValue("@.password")
                .isEqualTo("ciao");
    }

}
