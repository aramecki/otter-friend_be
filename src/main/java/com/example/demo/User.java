package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname")
    @JsonProperty("firstName")
    private String firstName;

    @Column(name = "lastname")
    @JsonProperty("lastName")
    private String lastName;

    @Column(name = "birth")
    @JsonProperty("birth")
    private String birth;

    @Column(name = "cardtype")
    @JsonProperty("cardType")
    private String cardType;

    @Column(name = "email")
    @JsonProperty("email")
    private String email;

    @Column(name = "password")
    @JsonProperty("password")
    private String password;

    @Transient
    private String secPassword;

    public User() {

    }

    public User(String firstName, String lastName, String birth, String cardType, String email, String password) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.birth = birth;
        this.cardType = cardType;
        this.email = email;
        this.password = password;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirth() {
        return birth;
    }
    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getCardType() {
        return cardType;
    }
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecPassword() {
        return secPassword;
    }
    public void setSecPassword(String secPassword) {
        this.secPassword = secPassword;
    }
}
