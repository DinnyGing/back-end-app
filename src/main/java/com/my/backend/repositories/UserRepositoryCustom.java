package com.my.backend.repositories;

import com.my.backend.models.Tag;
import com.my.backend.models.User;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> checkLogin(String login);
}
