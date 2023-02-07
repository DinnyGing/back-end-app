package com.my.backend.models;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.NonNull;

import java.util.Set;

@Entity
@Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class , property = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    @NonNull
    Long id;

    @NonNull
    @Column(name = "login_user")
    String login;

    @NonNull
    @Column(name = "password_user")
    String password;

    @NonNull
    @Column(name = "phone_number_user")
    String phone;

    @NonNull
    @Column(name = "email_user")
    String email;

    @OneToMany(mappedBy="user")
    private Set<Tag> tags;

    public  User(){}
    public User(String login, String password, String phone, String email) {
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public User(@NonNull String login, @NonNull String password) {
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
