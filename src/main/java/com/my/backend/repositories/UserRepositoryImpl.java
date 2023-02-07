package com.my.backend.repositories;

import com.my.backend.models.Tag;
import com.my.backend.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class UserRepositoryImpl implements UserRepositoryCustom{
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<User> checkLogin(String login) {
        Query query = manager.createNativeQuery("SELECT u.* FROM users u " +
                "WHERE u.login_user = ?", User.class);
        query.setParameter(1, login);
        return query.getResultList();
    }

}
